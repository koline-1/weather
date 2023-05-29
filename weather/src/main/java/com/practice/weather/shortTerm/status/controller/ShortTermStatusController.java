package com.practice.weather.shortTerm.status.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import com.practice.weather.shortTerm.status.repository.ShortTermStatusRepository;
import com.practice.weather.shortTerm.status.service.ShortTermStatusService;
import com.practice.weather.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/short-term/status/current/{nxValue}/{nyValue}")
    public String shortTermStatusController(
            @PathVariable String nxValue,
            @PathVariable String nyValue
    ) throws JsonProcessingException {

        // 현재 시간 기준 baseDate 와 baseTime 값 받아오기
        String[] dateTime = utility.getShortTermBaseDateTime("status");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        // DTO 객체로 변환후 String 으로 파싱하여 return
        return objectMapper.writeValueAsString(shortTermStatusService.parseJsonArrayToShortTermStatusDto(
                utility.getDataAsJsonArray(urlStr), utility.getShortTermVersion("ODAM", dateTime[0]+dateTime[1])));
    }

    // 초단기 실황 조회 데이터 DB 저장
    @ResponseBody
    @PostMapping("/short-term/status/current")
    public ShortTermStatusEntity saveShortTermStatus (
            @RequestBody String data
    ) throws JsonProcessingException {

        // 받아온 data JSONObject 로 파싱
        JSONObject jObject = new JSONObject(data);

        // 필요한 data 부분만 추출하여 DTO 로 파싱
        ShortTermStatusDto shortTermStatusDto = objectMapper.readValue(jObject.get("data").toString(), ShortTermStatusDto.class);

        // 중복 확인후 저장 & return
        if (!shortTermStatusRepository.isExist(shortTermStatusDto)) {
            return shortTermStatusRepository.save(shortTermStatusDto.toEntity());
        }

        // 중복된 데이터일 경우 빈 Entity return
        return ShortTermStatusEntity.builder().baseDate("").build();

    }


    // ShortTermStatusEntity 의 list 를  return
    // location 이 파라미터로 전달될경우 location 별 데이터 return
    @GetMapping("/short-term/status/list")
    public String shortTermStatusList (
            final Pageable pageable,
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
    ) throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());

        if ((nxValue == null || nxValue.equals("")) || (nyValue == null || nyValue.equals(""))) {
            return objectMapper.writeValueAsString(shortTermStatusRepository.selectList(pageable));
        } else {
            return objectMapper.writeValueAsString(shortTermStatusRepository.selectListByLocation(pageable, nxValue, nyValue));
        }
    }


    // ShortTermStatus 의 총 갯수를 return
    @GetMapping("/short-term/status/count")
    public String shortTermStatusCount () {

        return "{\"count\": \"" + shortTermStatusRepository.count()+"\"}";
    }


    // 아이디로 데이터 조회
    @GetMapping("/short-term/status/{id}")
    public String shortTermStatusAllData (
            @PathVariable Long id
    ) throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(shortTermStatusRepository.selectById(id));
    }
    
}
