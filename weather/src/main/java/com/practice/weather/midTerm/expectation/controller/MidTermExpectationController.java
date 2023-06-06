package com.practice.weather.midTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import com.practice.weather.midTerm.expectation.repository.MidTermExpectationRepository;
import com.practice.weather.midTerm.expectation.service.MidTermExpectationService;
import com.practice.weather.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MidTermExpectationController {

    private final MidTermExpectationService midTermExpectationService;

    private final MidTermExpectationRepository midTermExpectationRepository;

    private final Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public MidTermExpectationController(
            MidTermExpectationService midTermExpectationService,
            MidTermExpectationRepository midTermExpectationRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.midTermExpectationService = midTermExpectationService;
        this.midTermExpectationRepository = midTermExpectationRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }

    
    // 중기 전망 예보 조회 실시간
    @Deprecated
    @GetMapping("/mid-term/expectation/current/{location}")
    private String midTermExpectationCurrent (
            @PathVariable String location
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&stnId=" +
                (location != null && !location.equals("") ? location : "108") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(midTermExpectationService.parseMapToMidTermExpectationDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr)), location));
    }

    
    // 중기 전망 조회 데이터 DB 저장
    @PostMapping("/mid-term/expectation/current")
    public MidTermExpectationEntity saveMidTermExpectation (
            @RequestBody String data
    ) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 DTO 로 파싱
        MidTermExpectationDto midTermExpectationDto = objectMapper.readValue(jObject.get("data").toString(), MidTermExpectationDto.class);

        // 중복 확인 후 저장 & return
        if (!midTermExpectationRepository.isExist(midTermExpectationDto.getStnId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
            return midTermExpectationRepository.save(midTermExpectationDto.toEntity());
        }

        // 중복된 데이터일 경우 빈 Entity return
        return MidTermExpectationEntity.builder().stnId("0").build();
    }


    // MidTermExpectationEntity 의 list 를  return
    // location 이 파라미터로 전달될경우 location 별 데이터 return
    @GetMapping("/mid-term/expectation/list")
    public String midTermExpectationList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) throws JsonProcessingException {

        if (location == null || location.equals("")) {
            return objectMapper.writeValueAsString(midTermExpectationRepository.selectList(pageable));
        } else {
            return objectMapper.writeValueAsString(midTermExpectationRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermExpectation 의 총 갯수를 return
    @GetMapping("/mid-term/expectation/count")
    public String midTermExpectationCount (
            @RequestParam(name = "location", required = false) String location
    ) {

        if (location == null || location.equals("")) {
            return "{\"count\": \"" + midTermExpectationRepository.count()+"\"}";
        } else {
            return "{\"count\": \"" + midTermExpectationRepository.countByLocation(location)+"\"}";
        }
    }


    // 아이디로 데이터 조회
    @GetMapping("/mid-term/expectation/{id}")
    public String midTermExpectationData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        return objectMapper.writeValueAsString(midTermExpectationRepository.selectById(id));
    }


    // 데이터 수정
    @PatchMapping("/mid-term/expectation/{id}")
    public String midTermExpectationPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermExpectationDto midTermExpectationDto = objectMapper.readValue(jObject.get("data").toString(), MidTermExpectationDto.class);
        log.info("dto = {}", midTermExpectationDto);
        log.info("id = {}, data = {}", id, data);

        midTermExpectationRepository.save(midTermExpectationDto.toEntity());

        return "{\"result\": \"success\"}";
    }

}
