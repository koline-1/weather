package com.practice.weather.midTerm.temperature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.midTerm.temperature.repository.MidTermTemperatureRepository;
import com.practice.weather.midTerm.temperature.service.MidTermTemperatureService;
import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class MidTermTemperatureController {

    private final MidTermTemperatureService midTermTemperatureService;

    private final MidTermTemperatureRepository midTermTemperatureRepository;

    private final Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public MidTermTemperatureController(
            MidTermTemperatureService midTermTemperatureService,
            MidTermTemperatureRepository midTermTemperatureRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.midTermTemperatureService = midTermTemperatureService;
        this.midTermTemperatureRepository = midTermTemperatureRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }


    // 중기 기온 조회 실시간
    @Deprecated
    @GetMapping("/mid-term/temperature/current/{location}")
    private String midTermTemperatureController(
            @PathVariable String location
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "11B10101") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(midTermTemperatureService.parseMapToMidTermTemperatureDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr))));
    }


    // 중기 기온 조회 데이터 DB 저장
    @PostMapping("/mid-term/temperature/current")
    public MidTermTemperatureEntity saveMidTermTemperature (
            @RequestBody String data
    ) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 DTO 로 파싱
        MidTermTemperatureDto midTermTemperatureDto = objectMapper.readValue(jObject.get("data").toString(), MidTermTemperatureDto.class);

        // 중복 확인 후 저장 & return
        if (!midTermTemperatureRepository.isExist(midTermTemperatureDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermTemperatureRepository.save(midTermTemperatureDto.toEntity());
        }

        // 중복된 데이터일 경우 빈 Entity return
        return MidTermTemperatureEntity.builder().regId("").build();
    }


    // MidTermTemperatureEntity 의 list 를  return
    // location 이 파라미터로 전달될경우 location 별 데이터 return
    @GetMapping("/mid-term/temperature/list")
    public String midTermTemperatureList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) throws JsonProcessingException {

        if (location == null || location.equals("")) {
            return objectMapper.writeValueAsString(midTermTemperatureRepository.selectList(pageable));
        } else {
            return objectMapper.writeValueAsString(midTermTemperatureRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermTemperature 의 총 갯수를 return
    @GetMapping("/mid-term/temperature/count")
    public String midTermTemperatureCount (
            @RequestParam(name = "location", required = false) String location
    ) {

        if (location == null || location.equals("")) {
            return "{\"count\": \"" + midTermTemperatureRepository.count()+"\"}";
        } else {
            return "{\"count\": \"" + midTermTemperatureRepository.countByLocation(location)+"\"}";
        }
    }
    

    // 아이디로 데이터 조회
    @GetMapping("/mid-term/temperature/{id}")
    public String midTermTemperatureAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        return objectMapper.writeValueAsString(midTermTemperatureRepository.selectById(id));
    }
}
