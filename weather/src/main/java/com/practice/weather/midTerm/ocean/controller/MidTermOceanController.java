package com.practice.weather.midTerm.ocean.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.repository.MidTermOceanRepository;
import com.practice.weather.midTerm.ocean.service.MidTermOceanService;
import com.practice.weather.utility.Utility;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;
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
public class MidTermOceanController {

    @Autowired
    MidTermOceanService midTermOceanService;

    @Autowired
    MidTermOceanRepository midTermOceanRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/mid-term/ocean/location")
    public String midTermOceanLocationController () {
        return "/midTerm/ocean/midTermOceanLocation";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/ocean/data")
    public String midTermOceanController(
            @RequestParam(value = "location", required = false) String location,
            Model model
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate와 baseTime값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidSeaFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "12A20000") + "&tmFc=" + baseDateTime;

        HashMap<String, String> map = utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr));

        if (!map.isEmpty()) {
            MidTermOceanDto midTermOceanDto = midTermOceanService.parseMapToMidTermOceanDto(map);
            model.addAttribute("midTermOceanDto", midTermOceanDto);
            model.addAttribute("midTermOceanDtoJson", objectMapper.writeValueAsString(midTermOceanDto));
        } else {
            model.addAttribute("midTermOceanDto", null);
            model.addAttribute("midTermOceanDtoJson", null);
        }

        return "/midTerm/ocean/midTermOceanData";
    }

    @ResponseBody
    @PostMapping("/mid-term/ocean/data")
    public MidTermOceanEntity saveMidTermOcean (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermOceanDto midTermOceanDto = objectMapper.readValue(jObject.get("data").toString(), MidTermOceanDto.class);

        if (!midTermOceanRepository.isExist(midTermOceanDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermOceanRepository.save(midTermOceanDto.toEntity());
        }

        return MidTermOceanEntity.builder().regId("").build();
    }
}
