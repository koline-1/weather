package com.practice.weather.midTerm.land.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
import com.practice.weather.midTerm.land.service.MidTermLandService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MidTermLandController {

    @Autowired
    MidTermLandService midTermLandService;

    @Autowired
    MidTermLandRepository midTermLandRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 중기해상예보조회
    @GetMapping("/mid-term/land/current")
    public String midTermLandController(
            @RequestParam(value = "location", required = false) String location,
            Model model
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate와 baseTime값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "11B10101") + "&tmFc=" + baseDateTime;

        HashMap<String, String> map = utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr));


        return objectMapper.writeValueAsString(midTermLandService.parseMapToMidTermLandDto(map));
    }


    @PostMapping("/mid-term/land/current")
    public MidTermLandEntity saveMidTermLand (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermLandDto midTermLandDto = objectMapper.readValue(jObject.get("data").toString(), MidTermLandDto.class);

        if (!midTermLandRepository.isExist(midTermLandDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermLandRepository.save(midTermLandDto.toEntity());
        }

        return MidTermLandEntity.builder().regId("").build();
    }
    
}
