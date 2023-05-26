package com.practice.weather.midTerm.ocean.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.midTerm.ocean.repository.MidTermOceanRepository;
import com.practice.weather.midTerm.ocean.service.MidTermOceanService;
import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.utility.Utility;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class MidTermOceanController {

    private final MidTermOceanService midTermOceanService;

    private final MidTermOceanRepository midTermOceanRepository;

    private final Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Dependency Injection - 생성자 주입
    @Autowired
    public MidTermOceanController(
            MidTermOceanService midTermOceanService,
            MidTermOceanRepository midTermOceanRepository,
            Utility utility
        ) {
        this.midTermOceanService = midTermOceanService;
        this.midTermOceanRepository = midTermOceanRepository;
        this.utility = utility;
    }


    // 중기 해상 예보 조회 실시간
    @GetMapping("/mid-term/ocean/current/{location}")
    public String midTermOceanController(
            @PathVariable String location
        ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidSeaFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "12A20000") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(midTermOceanService.parseMapToMidTermOceanDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr))));
    }


    // 중기 해상 예보 조회 데이터 DB 저장
    @PostMapping("/mid-term/ocean/current")
    public MidTermOceanEntity saveMidTermOcean (@RequestBody String data) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 DTO 로 파싱
        MidTermOceanDto midTermOceanDto = objectMapper.readValue(jObject.get("data").toString(), MidTermOceanDto.class);

        // 중복 확인 후 저장 & return
        if (!midTermOceanRepository.isExist(midTermOceanDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermOceanRepository.save(midTermOceanDto.toEntity());
        }

        // 중복된 데이터일 경우 빈 Entity return
        return MidTermOceanEntity.builder().regId("").build();
    }

    // 아이디로 데이터 조회
    @GetMapping("/mid-term/ocean/{id}")
    public String midTermOceanAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(midTermOceanRepository.selectById(id));
    }
}
