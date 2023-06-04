package com.practice.weather.midTerm.land.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
import com.practice.weather.midTerm.land.service.MidTermLandService;
import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class MidTermLandController {

    private final MidTermLandService midTermLandService;

    private final MidTermLandRepository midTermLandRepository;

    private final Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public MidTermLandController(
            MidTermLandService midTermLandService,
            MidTermLandRepository midTermLandRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.midTermLandService = midTermLandService;
        this.midTermLandRepository = midTermLandRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }


    // 중기 육상 예보 조회 실시간
    @Deprecated
    @GetMapping("/mid-term/land/current/{location}")
    private String midTermLandController(
            @PathVariable String location
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "11B10101") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(midTermLandService.parseMapToMidTermLandDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr))));
    }

    // 중기 육상 예보 조회 데이터 DB 저장
    @PostMapping("/mid-term/land/current")
    public MidTermLandEntity saveMidTermLand (
            @RequestBody String data
    ) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 DTO 로 파싱
        MidTermLandDto midTermLandDto = objectMapper.readValue(jObject.get("data").toString(), MidTermLandDto.class);

        // 중복 확인 후 저장 & return
        if (!midTermLandRepository.isExist(midTermLandDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermLandRepository.save(midTermLandDto.toEntity());
        }

        // 중복된 데이터일 경우 빈 Entity return
        return MidTermLandEntity.builder().regId("").build();
    }

    
    // MidTermLandEntity 의 list 를  return
    // location 이 파라미터로 전달될경우 location 별 데이터 return
    @GetMapping("/mid-term/land/list")
    public String midTermLandList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) throws JsonProcessingException {

        if (location == null || location.equals("")) {
            return objectMapper.writeValueAsString(midTermLandRepository.selectList(pageable));
        } else {
            return objectMapper.writeValueAsString(midTermLandRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermLand 의 총 갯수를 return
    @GetMapping("/mid-term/land/count")
    public String midTermLandCount (
            @RequestParam(name = "location", required = false) String location
    ) {

        if (location == null || location.equals("")) {
            return "{\"count\": \"" + midTermLandRepository.count()+"\"}";
        } else {
            return "{\"count\": \"" + midTermLandRepository.countByLocation(location)+"\"}";
        }
    }


    @GetMapping("/mid-term/land/{id}")
    public String midTermLandAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        return objectMapper.writeValueAsString(midTermLandRepository.selectById(id));
    }
    
}
