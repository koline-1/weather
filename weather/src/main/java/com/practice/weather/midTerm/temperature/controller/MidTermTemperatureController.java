package com.practice.weather.midTerm.temperature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import com.practice.weather.midTerm.temperature.repository.MidTermTemperatureRepository;
import com.practice.weather.midTerm.temperature.service.MidTermTemperatureService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MidTermTemperatureController {

    @Autowired
    MidTermTemperatureService midTermTemperatureService;

    @Autowired
    MidTermTemperatureRepository midTermTemperatureRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 중기해상예보조회
    @GetMapping("/mid-term/temperature/current")
    public String midTermTemperatureController(
            @RequestParam(value = "location", required = false) String location,
            Model model
        ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate와 baseTime값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "11B10101") + "&tmFc=" + baseDateTime;

        HashMap<String, String> map = utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr));


        return objectMapper.writeValueAsString(midTermTemperatureService.parseMapToMidTermTemperatureDto(map));
    }


    @PostMapping("/mid-term/temperature/current")
    public MidTermTemperatureEntity saveMidTermTemperature (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermTemperatureDto midTermTemperatureDto = objectMapper.readValue(jObject.get("data").toString(), MidTermTemperatureDto.class);

        if (!midTermTemperatureRepository.isExist(midTermTemperatureDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermTemperatureRepository.save(midTermTemperatureDto.toEntity());
        }

        return MidTermTemperatureEntity.builder().regId("").build();
    }
}
