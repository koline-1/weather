package com.practice.weather.midTerm.temperature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.repository.MidTermTemperatureRepository;
import com.practice.weather.midTerm.temperature.service.MidTermTemperatureService;
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


    @GetMapping("/mid-term/temperature/location")
    public String midTermTemperatureLocationController () {
        return "/midTerm/temperature/midTermTemperatureLocation";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/temperature/data")
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

        if (!map.isEmpty()) {
            MidTermTemperatureDto midTermTemperatureDto = midTermTemperatureService.parseMapToMidTermTemperatureDto(map);
            model.addAttribute("midTermTemperatureDto", midTermTemperatureDto);
            model.addAttribute("midTermTemperatureDtoJson", objectMapper.writeValueAsString(midTermTemperatureDto));
        } else {
            model.addAttribute("midTermTemperatureDto", null);
            model.addAttribute("midTermTemperatureDtoJson", null);
        }

        return "/midTerm/temperature/midTermTemperatureData";
    }

    @ResponseBody
    @PostMapping("/mid-term/temperature/data")
    public String saveMidTermTemperature (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermTemperatureDto midTermTemperatureDto = objectMapper.readValue(jObject.get("data").toString(), MidTermTemperatureDto.class);

        if (!midTermTemperatureRepository.isExist(midTermTemperatureDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            midTermTemperatureRepository.save(midTermTemperatureDto.toEntity());
            return "saved";
        }

        return "exists";
    }
}
