package com.practice.weather.shortTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.repository.ShortTermExpectationRepository;
import com.practice.weather.shortTerm.expectation.service.ShortTermExpectationService;
import com.practice.weather.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShortTermExpectationController {

    @Autowired
    ShortTermExpectationService shortTermExpectationService;

    @Autowired
    ShortTermExpectationRepository shortTermExpectationRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 중기해상예보조회
    @GetMapping("/short-term/expectation/current")
    public String shortTermExpectationController(
            @RequestParam(value = "nxValue", required = false) String nxValue,
            @RequestParam(value = "nyValue", required = false) String nyValue,
            Model model
    ) throws JsonProcessingException {

        String[] dateTime = utility.getShortTermBaseDateTime("expectation");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        return objectMapper.writeValueAsString(shortTermExpectationService.parseJsonArrayToShortTermExpectationDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("SHRT", dateTime[0]+dateTime[1])));
    }


    @PostMapping("/short-term/expectation/current")
    public String saveShortTermExpectation (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        List<ShortTermExpectationDto> shortTermExpectationDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExpectationDto>>() {});

        int saveCount = 0;

        for (ShortTermExpectationDto dto : shortTermExpectationDtoList) {
            if (!shortTermExpectationRepository.isExist(dto)) {
                shortTermExpectationRepository.save(dto.toEntity());
                saveCount++;
            }
        }

        return "{ \"count\": \"" + String.valueOf(saveCount) + "\"}";

    }

}
