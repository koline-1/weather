package com.practice.weather.shortTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.repository.ShortTermExpectationRepository;
import com.practice.weather.shortTerm.expectation.service.ShortTermExpectationService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShortTermExpectationController {

    private final ShortTermExpectationService shortTermExpectationService;

    private final ShortTermExpectationRepository shortTermExpectationRepository;

    private final Utility utility;


    @Value("${service.key}")
    private String serviceKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Dependency Injection - 생성자 주입
    @Autowired
    public ShortTermExpectationController(
            ShortTermExpectationService shortTermExpectationService,
            ShortTermExpectationRepository shortTermExpectationRepository,
            Utility utility
        ) {
        this.shortTermExpectationService = shortTermExpectationService;
        this.shortTermExpectationRepository = shortTermExpectationRepository;
        this.utility = utility;
    }


    // 단기 예보 조회 실시간
    @GetMapping("/short-term/expectation/current/{nxValue}/{nyValue}")
    public String shortTermExpectationController(
            @PathVariable String nxValue,
            @PathVariable String nyValue
        ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String[] dateTime = utility.getShortTermBaseDateTime("expectation");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(shortTermExpectationService.parseJsonArrayToShortTermExpectationDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("SHRT", dateTime[0]+dateTime[1])));
    }


    // 단기 예보 조회 데이터 DB 저장
    @PostMapping("/short-term/expectation/current")
    public String saveShortTermExpectation (@RequestBody String data) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 List<DTO>로 파싱
        List<ShortTermExpectationDto> shortTermExpectationDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExpectationDto>>() {});

        int saveCount = 0;

        // 각 객체별로 중복 확인후 저장 & 카운트
        for (ShortTermExpectationDto dto : shortTermExpectationDtoList) {
            if (!shortTermExpectationRepository.isExist(dto)) {
                shortTermExpectationRepository.save(dto.toEntity());
                saveCount++;
            }
        }

        // 저장된 객체 수 return
        return "{ \"count\": \"" + saveCount + "\"}";
    }

    
    // 아이디로 데이터 조회
    @GetMapping("/short-term/expectation/{id}")
    public String shortTermExpectationAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(shortTermExpectationRepository.selectById(id));
    }

}
