package com.practice.weather.shortTerm.status.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import com.practice.weather.shortTerm.status.repository.ShortTermStatusRepository;
import com.practice.weather.shortTerm.status.service.ShortTermStatusService;
import com.practice.weather.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShortTermStatusController {

    @Autowired
    ShortTermStatusService shortTermStatusService;

    @Autowired
    ShortTermStatusRepository shortTermStatusRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 초단기실황조회
    @GetMapping("/short-term/status/data")
    public String shortTermStatusController(
            @RequestParam(value = "nxValue", required = false) String nxValue,
            @RequestParam(value = "nyValue", required = false) String nyValue,
            Model model
    ) throws JsonProcessingException {

        String[] dateTime = utility.getShortTermBaseDateTime("status");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        JSONArray jArray = utility.getDataAsJsonArray(urlStr);

        if (!jArray.isEmpty()) {
            ShortTermStatusDto shortTermStatusDto =
                    shortTermStatusService.parseJsonArrayToShortTermStatusDto(jArray, utility.getShortTermVersion("ODAM", dateTime[0]+dateTime[1]));
            model.addAttribute("shortTermStatusDto", shortTermStatusDto);
            model.addAttribute("shortTermStatusDtoJson", objectMapper.writeValueAsString(shortTermStatusDto));
        }else {
            model.addAttribute("shortTermStatusDtoList", null);
            model.addAttribute("shortTermStatusDtoListJson", null);
        }

        return "/shortTerm/status/shortTermStatusData";
    }

    @ResponseBody
    @PostMapping("/short-term/status/data")
    public String saveShortTermStatus (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        ShortTermStatusDto shortTermStatusDto = objectMapper.readValue(jObject.get("data").toString(), ShortTermStatusDto.class);

        if (!shortTermStatusRepository.isExist(shortTermStatusDto)) {
            shortTermStatusRepository.save(shortTermStatusDto.toEntity());
            return "saved";
        }

        return "exists";

    }
    
}
