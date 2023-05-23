package com.practice.weather.midTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import com.practice.weather.midTerm.expectation.repository.MidTermExpectationRepository;
import com.practice.weather.midTerm.expectation.service.MidTermExpectationService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
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

    
    // 중기 전망 조회 데이터 조회
    @GetMapping("/mid-term/expectation/current")
    public String midTermExpectationCurrent (
            @RequestParam(value = "location", required = false) String location,
            Model model
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate와 baseTime값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&stnId=" +
                (location != null && !location.equals("") ? location : "108") + "&tmFc=" + baseDateTime;

        // 데이터 받아와서 HashMap 형태로 저장
        HashMap<String, String> map = utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr));


        return objectMapper.writeValueAsString(midTermExpectationService.parseMapToMidTermExpectationDto(map, location));
    }

    
    // 중기 전망 조회 데이터 DB 저장
    @PostMapping("/mid-term/expectation/current")
    public MidTermExpectationEntity saveMidTermExpectation (@RequestBody String data) throws JsonProcessingException {

        // 받아온 data JSONObject로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data부분만 추출하여 DTO로 파싱
        MidTermExpectationDto midTermExpectationDto = objectMapper.readValue(jObject.get("data").toString(), MidTermExpectationDto.class);

        // 중복 확인 후 저장 & return
        if (!midTermExpectationRepository.isExist(midTermExpectationDto.getStnId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermExpectationRepository.save(midTermExpectationDto.toEntity());
        }

        // 중복된 데이터일 경우 빈 Entity return
        return MidTermExpectationEntity.builder().stnId("0").build();
    }

    @GetMapping("/mid-term/expectation/data")
    public String midTermExpectationAllData () {
        return null;
    }

}
