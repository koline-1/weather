package com.practice.weather.midTerm.land.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
import com.practice.weather.midTerm.land.service.MidTermLandService;
import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
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
    private ResponseEntity<MidTermLandDto> midTermLandController(
            @PathVariable String location
    ) {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "11B10101") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return ResponseEntity.ok(midTermLandService.parseMapToMidTermLandDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr))));
    }

    // 중기 육상 예보 조회 데이터 DB 저장
    @PostMapping("/mid-term/land/current")
    public ResponseEntity<MidTermLandEntity> saveMidTermLand (
            @RequestBody String data
    ) {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        try {
            // 필요한 data 부분만 추출하여 DTO 로 파싱
            MidTermLandDto midTermLandDto = objectMapper.readValue(jObject.get("data").toString(), MidTermLandDto.class);

            // 중복 확인 후 저장 & return
            if (!midTermLandRepository.isExist(midTermLandDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
                return ResponseEntity.ok(midTermLandRepository.save(midTermLandDto.toEntity()));
            }

            // 중복된 데이터일 경우 빈 Entity return
            return ResponseEntity.ok(MidTermLandEntity.builder().regId("0").build());

        } catch (JsonProcessingException e) {
            log.error("[saveMidTermLand]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermLandEntity());
        }
    }

    
    // MidTermLandEntity 의 list 를  return
    @GetMapping("/mid-term/land/list")
    public ResponseEntity<List<MidTermLandEntity>> midTermLandList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) {

        // location 이 파라미터로 전달될경우 location 별 데이터 return
        if (location == null || location.equals("")) {
            return ResponseEntity.ok(midTermLandRepository.selectList(pageable));
        } else {
            return ResponseEntity.ok(midTermLandRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermLand 의 총 갯수를 return
    @GetMapping("/mid-term/land/count")
    public ResponseEntity<String> midTermLandCount (
            @RequestParam(name = "location", required = false) String location
    ) {
        long count;

        if (location == null || location.equals("")) {
            count = midTermLandRepository.count();
        } else {
            count = midTermLandRepository.countByLocation(location);
        }

        return ResponseEntity.ok("{\"count\": \"" + count + "\"}");
    }


    @GetMapping("/mid-term/land/{id}")
    public ResponseEntity<MidTermLandEntity> midTermLandAllData (
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(midTermLandRepository.selectById(id));
    }

    
    // MidTermLand 데이터 수정
    @PatchMapping("/mid-term/land/{id}")
    public ResponseEntity<MidTermLandEntity> midTermLandPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) {

        try {

            //JSONObject 로 받아서 dto 객체로 변환
            JSONObject jObject = new JSONObject(data);

            MidTermLandDto dto = objectMapper.readValue(jObject.get("data").toString(), MidTermLandDto.class);

            // 수정 대상에 변경사항 적용
            MidTermLandEntity entityToUpdate = midTermLandRepository.selectById(id);

            entityToUpdate.updateFromDto(dto);

            midTermLandRepository.save(entityToUpdate);

            return ResponseEntity.ok(entityToUpdate);

        } catch (JsonProcessingException e) {
            log.error("[midTermLandPatch]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermLandEntity());
        } catch (Exception e) {
            log.error("[midTermLandPatch]Exception Occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermLandEntity());
        }
    }


    @DeleteMapping("/mid-term/land/{id}")
    public ResponseEntity<String> midTermLandDelete (
            @PathVariable Long id
    ) {

        // ID로 데이터 조회 안될 시 Not Found return
        if (!midTermLandRepository.existsById(id)) {
            log.error("[midTermLandDelete] Delete failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\": \"Data not found.\"}");
        }

        try {
            // 삭제 성공시 삭제된 데이터의 id return
            midTermLandRepository.deleteById(id);
            return ResponseEntity.ok("{\"result\": \"" + id + "\"}");
        } catch (Exception e) {
            log.error("[midTermLandDelete] Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"result\": \"Exception occurred.\"}");
        }
    }
    
}
