package com.practice.weather.shortTerm.status.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import com.practice.weather.shortTerm.status.repository.ShortTermStatusRepository;
import com.practice.weather.shortTerm.status.service.ShortTermStatusService;
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
public class ShortTermStatusController {
    
    private final ShortTermStatusService shortTermStatusService;

    private final ShortTermStatusRepository shortTermStatusRepository;

    private final Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public ShortTermStatusController(
            ShortTermStatusService shortTermStatusService,
            ShortTermStatusRepository shortTermStatusRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.shortTermStatusService = shortTermStatusService;
        this.shortTermStatusRepository = shortTermStatusRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }


    // 초단기 실황 조회 실시간
    @Deprecated
    @GetMapping("/short-term/status/current/{nxValue}/{nyValue}")
    private ResponseEntity<ShortTermStatusDto> shortTermStatusController(
            @PathVariable String nxValue,
            @PathVariable String nyValue
    ) {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String[] dateTime = utility.getShortTermBaseDateTime("status");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return ResponseEntity.ok(shortTermStatusService.parseJsonArrayToShortTermStatusDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("ODAM", dateTime[0]+dateTime[1])));
    }

    // 초단기 실황 조회 데이터 DB 저장
    @ResponseBody
    @PostMapping("/short-term/status/current")
    public ResponseEntity<ShortTermStatusEntity> saveShortTermStatus (
            @RequestBody String data
    ) {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        try {
            // 필요한 data 부분만 추출하여 DTO 로 파싱
            ShortTermStatusDto shortTermStatusDto = objectMapper.readValue(jObject.get("data").toString(), ShortTermStatusDto.class);

            // 중복 확인후 저장 & return
            if (!shortTermStatusRepository.isExist(shortTermStatusDto)) {
                return ResponseEntity.ok(shortTermStatusRepository.save(shortTermStatusDto.toEntity()));
            }

            // 중복된 데이터일 경우 빈 Entity return
            return ResponseEntity.ok(ShortTermStatusEntity.builder().baseDate("").build());

        } catch (JsonProcessingException e) {
            log.error("[saveShortTermStatus]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermStatusEntity());
        }

    }


    // ShortTermStatusEntity 의 list 를  return
    @GetMapping("/short-term/status/list")
    public ResponseEntity<List<ShortTermStatusEntity>> shortTermStatusList (
            final Pageable pageable,
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {

        // location 이 파라미터로 전달될경우 location 별 데이터 return
        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            return ResponseEntity.ok(shortTermStatusRepository.selectList(pageable));
        } else {
            return ResponseEntity.ok(shortTermStatusRepository.selectListByLocation(pageable, nxValue, nyValue));
        }
    }


    // ShortTermStatus 의 총 갯수를 return
    @GetMapping("/short-term/status/count")
    public ResponseEntity<String> shortTermStatusCount (
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {
        long count;

        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            count = shortTermStatusRepository.count();
        } else {
            count = shortTermStatusRepository.countByLocation(nxValue, nyValue);
        }

        return ResponseEntity.ok("{\"count\": \"" + count + "\"}");
    }


    // 아이디로 데이터 조회
    @GetMapping("/short-term/status/{id}")
    public ResponseEntity<ShortTermStatusEntity> shortTermStatusAllData (
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(shortTermStatusRepository.selectById(id));
    }


    // ShortTermStatus 데이터 수정
    @PatchMapping("/short-term/status/{id}")
    public ResponseEntity<ShortTermStatusEntity> shortTermStatusPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) {
        try {

            //JSONObject 로 받아서 dto 객체로 변환
            JSONObject jObject = new JSONObject(data);

            ShortTermStatusDto dto = objectMapper.readValue(jObject.get("data").toString(), ShortTermStatusDto.class);

            // 수정 대상에 변경사항 적용
            ShortTermStatusEntity entityToUpdate = shortTermStatusRepository.selectById(id);

            entityToUpdate.updateFromDto(dto);

            shortTermStatusRepository.save(entityToUpdate);

            return ResponseEntity.ok(entityToUpdate);

        } catch (JsonProcessingException e) {
            log.error("[shortTermStatusPatch]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermStatusEntity());
        } catch (Exception e) {
            log.error("[shortTermStatusPatch]Exception Occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermStatusEntity());
        }
    }


    @DeleteMapping("/short-term/status/{id}")
    public ResponseEntity<String> shortTermStatusDelete (
            @PathVariable Long id
    ) {

        // ID로 데이터 조회 안될 시 Not Found return
        if (!shortTermStatusRepository.existsById(id)) {
            log.error("[shortTermStatusDelete] Delete failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\": \"Data not found.\"}");
        }

        try {
            // 삭제 성공시 삭제된 데이터의 id return
            shortTermStatusRepository.deleteById(id);
            return ResponseEntity.ok("{\"result\": \"" + id + "\"}");
        } catch (Exception e) {
            log.error("[shortTermStatusDelete] Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"result\": \"Exception occurred.\"}");
        }
    }
    
}
