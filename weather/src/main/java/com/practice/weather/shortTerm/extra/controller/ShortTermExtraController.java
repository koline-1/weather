package com.practice.weather.shortTerm.extra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import com.practice.weather.shortTerm.extra.repository.ShortTermExtraRepository;
import com.practice.weather.shortTerm.extra.service.ShortTermExtraService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/short-term/extra/current/{nxValue}/{nyValue}")
    public String shortTermExtraController(
            @PathVariable String nxValue,
            @PathVariable String nyValue
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String[] dateTime = utility.getShortTermBaseDateTime("extra");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(shortTermExtraService.parseJsonArrayToShortTermExtraDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("VSRT", dateTime[0]+dateTime[1])));
    }


    // 초단기 예보 조회 데이터 DB 저장
    @PostMapping("/short-term/extra/current")
    public String saveShortTermExtra (
            @RequestBody String data
    ) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 List<DTO>로 파싱
        List<ShortTermExtraDto> shortTermExtraDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExtraDto>>() {});

        int saveCount = 0;

        // 각 객체별로 중복 확인후 저장 & 카운트
        for (ShortTermExtraDto dto : shortTermExtraDtoList) {
            if (!shortTermExtraRepository.isExist(dto)) {
                shortTermExtraRepository.save(dto.toEntity());
                saveCount++;
            }
        }

        // 저장된 객체 수 return
        return  "{ \"count\": \"" + saveCount + "\"}";

    }


    // ShortTermExtraEntity 의 list 를  return
    // location 이 파라미터로 전달될경우 location 별 데이터 return
    @GetMapping("/short-term/extra/list")
    public String shortTermExtraList (
            final Pageable pageable,
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) throws JsonProcessingException {

        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            return objectMapper.writeValueAsString(shortTermExtraRepository.selectList(pageable));
        } else {
            return objectMapper.writeValueAsString(shortTermExtraRepository.selectListByLocation(pageable, nxValue, nyValue));
        }
    }


    // ShortTermExtra 의 총 갯수를 return
    @GetMapping("/short-term/extra/count")
    public String shortTermExtraCount (
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) {

        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            return "{\"count\": \"" + shortTermExtraRepository.count()+"\"}";
        } else {
            return "{\"count\": \"" + shortTermExtraRepository.countByLocation(nxValue, nyValue)+"\"}";
        }
    }
    

    // 아이디로 데이터 조회
    @GetMapping("/short-term/extra/{id}")
    public String shortTermExtraAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        return objectMapper.writeValueAsString(shortTermExtraRepository.selectById(id));
    }

}
