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
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public MidTermOceanEntity saveMidTermOcean (
            @RequestBody String data
    ) throws JsonProcessingException {

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


    // MidTermOceanEntity 의 list 를  return
    // location 이 파라미터로 전달될경우 location 별 데이터 return
    @GetMapping("/mid-term/ocean/list")
    public String midTermOceanList (
            final Pageable pageable,
            @RequestParam(name = "location", required = false) String location
    ) throws JsonProcessingException {

        if (location == null || location.equals("")) {
            return objectMapper.writeValueAsString(midTermOceanRepository.selectList(pageable));
        } else {
            return objectMapper.writeValueAsString(midTermOceanRepository.selectListByLocation(pageable, location));
        }
    }


    // MidTermOcean 의 총 갯수를 return
    @GetMapping("/mid-term/ocean/count")
    public String midTermOceanCount (
            @RequestParam(name = "location", required = false) String location
    ) {

        if (location == null || location.equals("")) {
            return "{\"count\": \"" + midTermOceanRepository.count()+"\"}";
        } else {
            return "{\"count\": \"" + midTermOceanRepository.countByLocation(location)+"\"}";
        }
    }


    // 아이디로 데이터 조회
    @GetMapping("/mid-term/ocean/{id}")
    public String midTermOceanAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        return objectMapper.writeValueAsString(midTermOceanRepository.selectById(id));
    }


    @DeleteMapping("/mid-term/ocean/{id}")
    public ResponseEntity<String> midTermOceanDelete (
            @PathVariable Long id
    ) {

        // ID로 데이터 조회 안될 시 Not Found return
        if (!midTermOceanRepository.existsById(id)) {
            log.error("[midTermOceanDelete] Delete failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\": \"Data not found.\"}");
        }

        try {
            // 삭제 성공시 삭제된 데이터의 id return
            midTermOceanRepository.deleteById(id);
            return ResponseEntity.ok("{\"result\": \"" + id + "\"}");
        } catch (Exception e) {
            log.error("[midTermOceanDelete] Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"result\": \"Exception occurred.\"}");
        }
    }
    
}
