package com.practice.weather.midTerm.land.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
import com.practice.weather.midTerm.land.service.MidTermLandService;
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


    @GetMapping("/mid-term/land/location")
    public String midTermLandLocationController () {
        return "/midTerm/land/midTermLandLocation";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/land/data")
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

        if (!map.isEmpty()) {
            MidTermLandDto midTermLandDto = midTermLandService.parseMapToMidTermLandDto(map);
            model.addAttribute("midTermLandDto", midTermLandDto);
            model.addAttribute("midTermLandDtoJson", objectMapper.writeValueAsString(midTermLandDto));
        } else {
            model.addAttribute("midTermLandDto", null);
            model.addAttribute("midTermLandDtoJson", null);
        }

        return "/midTerm/land/midTermLandData";
    }

    @ResponseBody
    @PostMapping("/mid-term/land/data")
    public String saveMidTermLand (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermLandDto midTermLandDto = objectMapper.readValue(jObject.get("data").toString(), MidTermLandDto.class);

        if (!midTermLandRepository.isExist(midTermLandDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            midTermLandRepository.save(midTermLandDto.toEntity());
            return "saved";
        }

        return "exists";
    }
    
}
