package com.practice.weather.midTerm.ocean.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;
import com.practice.weather.midTerm.ocean.repository.MidTermOceanRepository;
import com.practice.weather.midTerm.ocean.service.MidTermOceanService;
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
import java.util.Optional;

@Slf4j
@RestController
public class MidTermOceanController {

    private final MidTermOceanService midTermOceanService;

    private final MidTermOceanRepository midTermOceanRepository;

    private final Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public MidTermOceanController(
            MidTermOceanService midTermOceanService,
            MidTermOceanRepository midTermOceanRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.midTermOceanService = midTermOceanService;
        this.midTermOceanRepository = midTermOceanRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }


    // 중기 해상 예보 조회 실시간
    @Deprecated
    @GetMapping("/mid-term/ocean/current/{location}")
    private ResponseEntity<MidTermOceanDto> midTermOceanController(
            @PathVariable String location
    ) {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String baseDateTime = utility.getMidTermBaseDateTimeAsString();

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidSeaFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "12A20000") + "&tmFc=" + baseDateTime;

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return ResponseEntity.ok(midTermOceanService.parseMapToMidTermOceanDto(
                utility.parseJsonArrayToMap(utility.getDataAsJsonArray(urlStr))));
    }


    // 중기 해상 예보 조회 데이터 DB 저장
    @PostMapping("/mid-term/ocean/current")
    public ResponseEntity<MidTermOceanEntity> saveMidTermOcean (
            @RequestBody String data
    ) {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        try {
            // 필요한 data 부분만 추출하여 DTO 로 파싱
            MidTermOceanDto midTermOceanDto = objectMapper.readValue(jObject.get("data").toString(), MidTermOceanDto.class);

            // 중복 확인 후 저장 & return
            if (!midTermOceanRepository.isExist(midTermOceanDto.getRegId(), utility.getMidTermBaseDateTimeAsLocalDateTime())) {
                return ResponseEntity.ok(midTermOceanRepository.save(midTermOceanDto.toEntity()));
            }

            // 중복된 데이터일 경우 빈 Entity return
            return ResponseEntity.ok(MidTermOceanEntity.builder().regId("").build());
        } catch (JsonProcessingException e) {
            log.error("[saveMidTermOcean]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermOceanEntity());
        }
    }


    // MidTermOceanEntity 의 list 를  return
    @GetMapping("/mid-term/ocean/list")
    public ResponseEntity<List<MidTermOceanEntity>> midTermOceanList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) {

        // location 이 파라미터로 전달될경우 location 별 데이터 return
        if (location == null || location.equals("")) {
            return ResponseEntity.ok(midTermOceanRepository.selectList(pageable));
        } else {
            return ResponseEntity.ok(midTermOceanRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermOcean 의 총 갯수를 return
    @GetMapping("/mid-term/ocean/count")
    public ResponseEntity<String> midTermOceanCount (
            @RequestParam(name = "location", required = false) String location
    ) {
        long count;

        if (location == null || location.equals("")) {
            count = midTermOceanRepository.count();
        } else {
            count = midTermOceanRepository.countByLocation(location);
        }

        return ResponseEntity.ok("{\"count\": \"" + count + "\"}");
    }


    // 아이디로 데이터 조회
    @GetMapping("/mid-term/ocean/{id}")
    public ResponseEntity<Optional<MidTermOceanEntity>> midTermOceanData (
            @PathVariable Long id
    ) {

        if (!midTermOceanRepository.existsById(id)) {
            log.error("[midTermOceanData] Get failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.of(new MidTermOceanEntity()));
        }

        return ResponseEntity.ok(midTermOceanRepository.findById(id));
    }


    // MidTermOcean 데이터 수정
    @PatchMapping("/mid-term/ocean/{id}")
    public ResponseEntity<MidTermOceanEntity> midTermOceanPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) {
        try {

            //JSONObject 로 받아서 dto 객체로 변환
            JSONObject jObject = new JSONObject(data);

            MidTermOceanDto dto = objectMapper.readValue(jObject.get("data").toString(), MidTermOceanDto.class);

            // 수정 대상 찾기
            Optional<MidTermOceanEntity> optionalEntity = midTermOceanRepository.findById(id);

            // 수정 대상이 없을 시 NOT_FOUND return
            if (optionalEntity.isPresent()) {

                MidTermOceanEntity entityToUpdate = optionalEntity.get();

                entityToUpdate.updateFromDto(dto);

                midTermOceanRepository.save(entityToUpdate);

                return ResponseEntity.ok(entityToUpdate);

            } else {
                log.error("[midTermOceanPatch] Data not found: midTermOceanRepository.findById({})", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MidTermOceanEntity());
            }
        } catch (JsonProcessingException e) {
            log.error("[midTermOceanPatch]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermOceanEntity());
        } catch (Exception e) {
            log.error("[midTermOceanPatch]Exception Occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MidTermOceanEntity());
        }
    }


    // MidTermOcean 데이터 삭제
    @DeleteMapping("/mid-term/ocean/{id}")
    public ResponseEntity<String> midTermOceanDelete (
            @PathVariable Long id
    ) {

        Optional<MidTermOceanEntity> optionalEntity = midTermOceanRepository.findById(id);

        // 삭제 대상이 없을 시 NOT_FOUND return
        if (optionalEntity.isPresent()) {
            // 삭제 성공시 삭제된 데이터의 id return
            midTermOceanRepository.deleteById(id);
            return ResponseEntity.ok("{\"result\": \"" + id + "\"}");
        } else {
            log.error("[midTermOceanDelete] Delete failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\": \"Data not found.\"}");
        }
    }
    
}
