package com.practice.weather.shortTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.repository.ShortTermExpectationRepository;
import com.practice.weather.shortTerm.expectation.service.ShortTermExpectationService;
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
public class ShortTermExpectationController {

    private final ShortTermExpectationService shortTermExpectationService;

    private final ShortTermExpectationRepository shortTermExpectationRepository;

    private final Utility utility;


    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public ShortTermExpectationController(
            ShortTermExpectationService shortTermExpectationService,
            ShortTermExpectationRepository shortTermExpectationRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.shortTermExpectationService = shortTermExpectationService;
        this.shortTermExpectationRepository = shortTermExpectationRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }


    // 단기 예보 조회 실시간
    @Deprecated
    @GetMapping("/short-term/expectation/current/{nxValue}/{nyValue}")
    private ResponseEntity<List<ShortTermExpectationDto>> shortTermExpectationController(
            @PathVariable String nxValue,
            @PathVariable String nyValue
    ) {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String[] dateTime = utility.getShortTermBaseDateTime("expectation");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return ResponseEntity.ok(shortTermExpectationService.parseJsonArrayToShortTermExpectationDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("SHRT", dateTime[0]+dateTime[1])));
    }


    // 단기 예보 조회 데이터 DB 저장
    @PostMapping("/short-term/expectation/current")
    public ResponseEntity<String> saveShortTermExpectation (
            @RequestBody String data
    ) {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        try {
            // 필요한 data 부분만 추출하여 List<DTO>로 파싱
            List<ShortTermExpectationDto> shortTermExpectationDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExpectationDto>>() {
            });

            int saveCount = 0;

            // 각 객체별로 중복 확인후 저장 & 카운트
            for (ShortTermExpectationDto dto : shortTermExpectationDtoList) {
                if (!shortTermExpectationRepository.isExist(dto)) {
                    shortTermExpectationRepository.save(dto.toEntity());
                    saveCount++;
                }
            }

            // 저장된 객체 수 return
            return ResponseEntity.ok("{ \"count\": \"" + saveCount + "\"}");

        } catch (JsonProcessingException e) {
            log.error("[saveShortTermExpectation]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }


    // ShortTermExpectationEntity 의 list 를  return
    @GetMapping("/short-term/expectation/list")
    public ResponseEntity<List<ShortTermExpectationEntity>> shortTermExpectationList (
            final Pageable pageable,
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {

        // location 이 파라미터로 전달될경우 location 별 데이터 return
        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            return ResponseEntity.ok(shortTermExpectationRepository.selectList(pageable));
        } else {
            return ResponseEntity.ok(shortTermExpectationRepository.selectListByLocation(pageable, nxValue, nyValue));
        }
    }


    // ShortTermExpectation 의 총 갯수를 return
    @GetMapping("/short-term/expectation/count")
    public ResponseEntity<String> shortTermExpectationCount (
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {
        long count;

        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            count = shortTermExpectationRepository.count();
        } else {
            count = shortTermExpectationRepository.countByLocation(nxValue, nyValue);
        }

        return ResponseEntity.ok("{\"count\": \"" + count + "\"}");
    }


    // 아이디로 데이터 조회
    @GetMapping("/short-term/expectation/{id}")
    public ResponseEntity<Optional<ShortTermExpectationEntity>> shortTermExpectationData (
            @PathVariable Long id
    ) {

        if (!shortTermExpectationRepository.existsById(id)) {
            log.error("[shortTermExpectationData] Get failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.of(new ShortTermExpectationEntity()));
        }

        return ResponseEntity.ok(shortTermExpectationRepository.findById(id));
    }


    // ShortTermExpectation 데이터 수정
    @PatchMapping("/short-term/expectation/{id}")
    public ResponseEntity<ShortTermExpectationEntity> shortTermExpectationPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) {
        try {

            //JSONObject 로 받아서 dto 객체로 변환
            JSONObject jObject = new JSONObject(data);

            ShortTermExpectationDto dto = objectMapper.readValue(jObject.get("data").toString(), ShortTermExpectationDto.class);

            // 수정 대상 찾기
            Optional<ShortTermExpectationEntity> optionalEntity = shortTermExpectationRepository.findById(id);

            // 수정 대상이 없을 시 NOT_FOUND return
            if (optionalEntity.isPresent()) {

                ShortTermExpectationEntity entityToUpdate = optionalEntity.get();

                entityToUpdate.updateFromDto(dto);

                shortTermExpectationRepository.save(entityToUpdate);

                return ResponseEntity.ok(entityToUpdate);

            } else {
                log.error("[shortTermExpectationPatch] Data not found: shortTermExpectationRepository.findById({})", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShortTermExpectationEntity());
            }
        } catch (JsonProcessingException e) {
            log.error("[shortTermExpectationPatch]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermExpectationEntity());
        } catch (Exception e) {
            log.error("[shortTermExpectationPatch]Exception Occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermExpectationEntity());
        }
    }


    // ShortTermExpectation 데이터 삭제
    @DeleteMapping("/short-term/expectation/{id}")
    public ResponseEntity<String> shortTermExpectationDelete (
            @PathVariable Long id
    ) {

        Optional<ShortTermExpectationEntity> optionalEntity = shortTermExpectationRepository.findById(id);

        // 삭제 대상이 없을 시 NOT_FOUND return
        if (optionalEntity.isPresent()) {
            // 삭제 성공시 삭제된 데이터의 id return
            shortTermExpectationRepository.deleteById(id);
            return ResponseEntity.ok("{\"result\": \"" + id + "\"}");
        } else {
            log.error("[shortTermExpectationDelete] Delete failed: Data not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\": \"Data not found.\"}");
        }
    }

}
