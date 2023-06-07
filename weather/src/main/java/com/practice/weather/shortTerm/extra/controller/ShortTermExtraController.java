package com.practice.weather.shortTerm.extra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import com.practice.weather.shortTerm.extra.entity.ShortTermExtraEntity;
import com.practice.weather.shortTerm.extra.repository.ShortTermExtraRepository;
import com.practice.weather.shortTerm.extra.service.ShortTermExtraService;
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
public class ShortTermExtraController {

    private final ShortTermExtraService shortTermExtraService;

    private final ShortTermExtraRepository shortTermExtraRepository;

    private final Utility utility;


    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper;

    // Dependency Injection - 생성자 주입
    @Autowired
    public ShortTermExtraController(
            ShortTermExtraService shortTermExtraService,
            ShortTermExtraRepository shortTermExtraRepository,
            ObjectMapper objectMapper,
            Utility utility
    ) {
        this.shortTermExtraService = shortTermExtraService;
        this.shortTermExtraRepository = shortTermExtraRepository;
        this.utility = utility;
        this.objectMapper = objectMapper.registerModule(new JavaTimeModule());
    }


    // 초단기 예보 조회 실시간
    @Deprecated
    @GetMapping("/short-term/extra/current/{nxValue}/{nyValue}")
    private ResponseEntity<List<ShortTermExtraDto>> shortTermExtraController(
            @PathVariable String nxValue,
            @PathVariable String nyValue
    ) {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String[] dateTime = utility.getShortTermBaseDateTime("extra");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return ResponseEntity.ok(shortTermExtraService.parseJsonArrayToShortTermExtraDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("VSRT", dateTime[0]+dateTime[1])));
    }


    // 초단기 예보 조회 데이터 DB 저장
    @PostMapping("/short-term/extra/current")
    public ResponseEntity<String> saveShortTermExtra (
            @RequestBody String data
    ) {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);
        
        try {
            // 필요한 data 부분만 추출하여 List<DTO>로 파싱
            List<ShortTermExtraDto> shortTermExtraDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExtraDto>>() {
            });

            int saveCount = 0;

            // 각 객체별로 중복 확인후 저장 & 카운트
            for (ShortTermExtraDto dto : shortTermExtraDtoList) {
                if (!shortTermExtraRepository.isExist(dto)) {
                    shortTermExtraRepository.save(dto.toEntity());
                    saveCount++;
                }
            }

            // 저장된 객체 수 return
            return ResponseEntity.ok("{ \"count\": \"" + saveCount + "\"}");
        
        } catch (JsonProcessingException e) {
            log.error("[saveShortTermExtra]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }


    // ShortTermExtraEntity 의 list 를  return
    @GetMapping("/short-term/extra/list")
    public ResponseEntity<List<ShortTermExtraEntity>> shortTermExtraList (
            final Pageable pageable,
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {

        // location 이 파라미터로 전달될경우 location 별 데이터 return
        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            return ResponseEntity.ok(shortTermExtraRepository.selectList(pageable));
        } else {
            return ResponseEntity.ok(shortTermExtraRepository.selectListByLocation(pageable, nxValue, nyValue));
        }
    }


    // ShortTermExtra 의 총 갯수를 return
    @GetMapping("/short-term/extra/count")
    public ResponseEntity<String> shortTermExtraCount (
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {
        long count;

        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            count = shortTermExtraRepository.count();
        } else {
            count = shortTermExtraRepository.countByLocation(nxValue, nyValue);
        }

        return ResponseEntity.ok("{\"count\": \"" + count + "\"}");
    }
    

    // 아이디로 데이터 조회
    @GetMapping("/short-term/extra/{id}")
    public ResponseEntity<ShortTermExtraEntity> shortTermExtraAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        return ResponseEntity.ok(shortTermExtraRepository.selectById(id));
    }


    // ShortTermExtra 데이터 수정
    @PatchMapping("/short-term/extra/{id}")
    public ResponseEntity<ShortTermExtraEntity> shortTermExtraPatch (
            @PathVariable Long id,
            @RequestBody String data
    ) {
        try {

            //JSONObject 로 받아서 dto 객체로 변환
            JSONObject jObject = new JSONObject(data);

            ShortTermExtraDto dto = objectMapper.readValue(jObject.get("data").toString(), ShortTermExtraDto.class);

            // 수정 대상에 변경사항 적용
            ShortTermExtraEntity entityToUpdate = shortTermExtraRepository.selectById(id);

            entityToUpdate.updateFromDto(dto);

            shortTermExtraRepository.save(entityToUpdate);

            return ResponseEntity.ok(entityToUpdate);

        } catch (JsonProcessingException e) {
            log.error("[shortTermExtraPatch]JSON processing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermExtraEntity());
        } catch (Exception e) {
            log.error("[shortTermExtraPatch]Exception Occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShortTermExtraEntity());
        }
    }

}
