package com.practice.weather.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.status.service.ShortTermStatusService;
import com.practice.weather.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
public class MainController {

    @Autowired
    ShortTermStatusService shortTermStatusService;

    @Autowired
    private Utility utility;

    // 기상청 API 서비스키
    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // TODO: 초단기 실황 데이터 말고 초단기 예보로 변경
    // 메인페이지
    @GetMapping("/mainView")
    public String mainView(
            @RequestParam(name = "nxValue", required = false) String nxValue,
            @RequestParam(name = "nyValue", required = false) String nyValue
        ) throws JsonProcessingException {

        String[] dateTime = utility.getShortTermBaseDateTime("status");

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        return objectMapper.writeValueAsString(shortTermStatusService.parseJsonArrayToShortTermStatusDto(
                        utility.getDataAsJsonArray(urlStr),
                        utility.getShortTermVersion("ODAM",dateTime[0]+dateTime[1])));

    }

}
