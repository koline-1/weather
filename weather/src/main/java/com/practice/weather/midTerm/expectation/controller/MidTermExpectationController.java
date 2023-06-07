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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private ResponseEntity<MidTermExpectationDto> midTermExpectationCurrent (
            @PathVariable String location
    ) {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&stnId=" +
                (location != null && !location.equals("") ? location : "108") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return ResponseEntity.ok(midTermExpectationService.parseMapToMidTermExpectationDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr)), location));
    }

    
    // 중기 전망 조회 데이터 DB 저장
    @PostMapping("/mid-term/expectation/current")
    public ResponseEntity<MidTermExpectationEntity> saveMidTermExpectation (
            @RequestBody String data
    ) {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        try {
            // 필요한 data 부분만 추출하여 DTO 로 파싱
            MidTermExpectationDto midTermExpectationDto = objectMapper.readValue(jObject.get("data").toString(), MidTermExpectationDto.class);

            // 중복 확인 후 저장 & return
            if (!midTermExpectationRepository.isExist(midTermExpectationDto.getStnId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
                return ResponseEntity.ok(midTermExpectationRepository.save(midTermExpectationDto.toEntity()));
            }

            // 중복된 데이터일 경우 빈 Entity return
            return ResponseEntity.ok(MidTermExpectationEntity.builder().stnId("0").build());

        } catch (JsonProcessingException e) {
            log.error("[saveMidTermExpectation]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermExpectationEntity());
        }

    }


    // MidTermExpectationEntity 의 list 를  return
    @GetMapping("/mid-term/expectation/list")
    public ResponseEntity<List<MidTermExpectationEntity>> midTermExpectationList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) {
        // location 이 파라미터로 전달될경우 location 별 데이터 return
        if (location == null || location.equals("")) {
            return ResponseEntity.ok(midTermExpectationRepository.selectList(pageable));
        } else {
            return ResponseEntity.ok(midTermExpectationRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermExpectation 의 총 갯수를 return
    @GetMapping("/mid-term/expectation/count")
    public ResponseEntity<String> midTermExpectationCount (
            @RequestParam(name = "location", required = false) String location
    ) {
        long count;

        if (location == null || location.equals("")) {
            count = midTermExpectationRepository.count();
        } else {
            count = midTermExpectationRepository.countByLocation(location);
        }

        return ResponseEntity.ok("{\"count\": \"" + count + "\"}");
    }


    // 아이디로 데이터 조회
    @GetMapping("/mid-term/expectation/{id}")
    public ResponseEntity<MidTermExpectationEntity> midTermExpectationData (
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(midTermExpectationRepository.selectById(id));
    }


    // MidTermExpectation 데이터 수정
    @PatchMapping("/mid-term/expectation/{id}")
    public ResponseEntity<MidTermExpectationEntity> midTermExpectationPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) {
        try {

            //JSONObject 로 받아서 dto 객체로 변환
            JSONObject jObject = new JSONObject(data);

            MidTermExpectationDto dto = objectMapper.readValue(jObject.get("data").toString(), MidTermExpectationDto.class);

            // 수정 대상에 변경사항 적용
            MidTermExpectationEntity entityToUpdate = midTermExpectationRepository.selectById(id);

            entityToUpdate.updateFromDto(dto);

            midTermExpectationRepository.save(entityToUpdate);

            return ResponseEntity.ok(entityToUpdate);

        } catch (JsonProcessingException e) {
            log.error("[midTermExpectationPatch]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermExpectationEntity());
        } catch (Exception e) {
            log.error("[midTermExpectationPatch]Exception Occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermExpectationEntity());
        }
    }

}
