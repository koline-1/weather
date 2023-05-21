package com.practice.weather.shortTerm.extraExpectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.extraExpectation.dto.ShortTermExtraExpectationDto;
import com.practice.weather.shortTerm.extraExpectation.repository.ShortTermExtraExpectationRepository;
import com.practice.weather.shortTerm.extraExpectation.service.ShortTermExtraExpectationService;
import com.practice.weather.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShortTermExtraExpectationController {

    @Autowired
    ShortTermExtraExpectationService shortTermExtraExpectationService;

    @Autowired
    ShortTermExtraExpectationRepository shortTermExtraExpectationRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 중기해상예보조회
    @GetMapping("/short-term/extraExpectation/data")
    public String shortTermExtraExpectationController(
            @RequestParam(value = "nxValue", required = false) String nxValue,
            @RequestParam(value = "nyValue", required = false) String nyValue,
            Model model
    ) throws JsonProcessingException {

        String[] dateTime = utility.getShortTermBaseDateTime("extraExpectation");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        JSONArray jArray = utility.getDataAsJsonArray(urlStr);

        String version = utility.getShortTermVersion("VSRT", dateTime[0]+dateTime[1]);

        if (!jArray.isEmpty()) {
            List<ShortTermExtraExpectationDto> shortTermExtraExpectationDtoList =
                    shortTermExtraExpectationService.parseJsonArrayToShortTermExtraExpectationDto(jArray, utility.getShortTermVersion("VSRT", dateTime[0]+dateTime[1]));
            model.addAttribute("shortTermExtraExpectationDtoList", shortTermExtraExpectationDtoList);
            model.addAttribute("shortTermExtraExpectationDtoListJson", objectMapper.writeValueAsString(shortTermExtraExpectationDtoList));
        }else {
            model.addAttribute("shortTermExtraExpectationDtoList", null);
            model.addAttribute("shortTermExtraExpectationDtoListJson", null);
        }

        return "/shortTerm/extraExpectation/shortTermExtraExpectationData";
    }

    @ResponseBody
    @PostMapping("/short-term/extraExpectation/data")
    public String saveShortTermExtraExpectation (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        List<ShortTermExtraExpectationDto> shortTermExtraExpectationDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExtraExpectationDto>>() {});

        int saveCount = 0;

        for (ShortTermExtraExpectationDto dto : shortTermExtraExpectationDtoList) {
            if (!shortTermExtraExpectationRepository.isExist(dto)) {
                shortTermExtraExpectationRepository.save(dto.toEntity());
                saveCount++;
            }
        }

        return  "{ \"count\": \"" + String.valueOf(saveCount) + "\"}";

    }

}
