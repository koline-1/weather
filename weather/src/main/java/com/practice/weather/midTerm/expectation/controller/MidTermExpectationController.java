package com.practice.weather.midTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.repository.MidTermExpectationRepository;
import com.practice.weather.midTerm.expectation.service.MidTermExpectationService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;

@Controller
public class MidTermExpectationController {

    @Autowired
    private MidTermExpectationService midTermExpectationService;

    @Autowired
    private MidTermExpectationRepository midTermExpectationRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/mid-term/expectation/location")
    public String midTermExpectationLocationController () {
        return "/midTerm/expectation/midTermExpectationLocation";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/expectation/data")
    public String midTermExpectationController(
            @RequestParam(value = "location", required = false) String location,
            Model model
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate와 baseTime값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&stnId=" +
                (location != null && !location.equals("") ? location : "108") + "&tmFc=" + baseDateTime;

        HashMap<String, String> map = utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr));

        if (!map.isEmpty()) {
            MidTermExpectationDto midTermExpectationDto = midTermExpectationService.parseMapToMidTermExpectationDto(map, location);
            model.addAttribute("midTermExpectationDto", midTermExpectationDto);
            model.addAttribute("midTermExpectationDtoJson", objectMapper.writeValueAsString(midTermExpectationDto));
        } else {
            model.addAttribute("midTermExpectationDto", null);
            model.addAttribute("midTermExpectationDtoJson", null);
        }

        return "/midTerm/expectation/midTermExpectationData";
    }

    @ResponseBody
    @PostMapping("/mid-term/expectation/data")
    public String saveMidTermExpectation (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermExpectationDto midTermExpectationDto = objectMapper.readValue(jObject.get("data").toString(), MidTermExpectationDto.class);

        if (!midTermExpectationRepository.isExist(midTermExpectationDto.getStnId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            midTermExpectationRepository.save(midTermExpectationDto.toEntity());
            return "saved";
        }

        return "exists";
    }

}
