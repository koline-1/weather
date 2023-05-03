package com.practice.weather.shortTerm.expectation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.repository.ShortTermExpectationRepository;
import com.practice.weather.shortTerm.expectation.service.ShortTermExpectationService;
import com.practice.weather.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class ShortTermExpectationController {


    @Autowired
    ShortTermExpectationService shortTermExpectationService;

    @Autowired
    ShortTermExpectationRepository shortTermExpectationRepository;

    @Autowired
    private Utility utility;

    @Value("${service.key}")
    private String serviceKey;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 중기해상예보조회
    @GetMapping("/short-term/expectation/data")
    public String shortTermExpectationController(
            @RequestParam(value = "nxValue", required = false) String nxValue,
            @RequestParam(value = "nyValue", required = false) String nyValue,
            Model model
    ) throws JsonProcessingException {

        String[] dateTime = getBaseDateTime();

        // 서비스 URL
        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + dateTime[0] + "&base_time=" + dateTime[1] +
                "&nx=" + (nxValue != null && !nxValue.equals("") ? nxValue : "55") +
                "&ny=" + (nyValue != null && !nyValue.equals("") ? nyValue : "127");

        JSONArray jArray = utility.getDataAsJsonArray(urlStr);

        if (!jArray.isEmpty()) {
            List<ShortTermExpectationDto> shortTermExpectationDtoList = shortTermExpectationService.parseMapToShortTermExpectationDto(jArray);
            model.addAttribute("shortTermExpectationDtoList", shortTermExpectationDtoList);
            model.addAttribute("shortTermExpectationDtoListJson", objectMapper.writeValueAsString(shortTermExpectationDtoList));
        }else {
            model.addAttribute("shortTermExpectationDtoList", null);
            model.addAttribute("shortTermExpectationDtoListJson", null);
        }

        return "/shortTerm/expectation/shortTermExpectationData";
    }

    @ResponseBody
    @PostMapping("/short-term/expectation/data")
    public String saveShortTermExpectation (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        List<ShortTermExpectationDto> shortTermExpectationDtoList = objectMapper.readValue(jObject.get("data").toString(), new TypeReference<List<ShortTermExpectationDto>>() {});

        String[] dateTime = getBaseDateTime();

        if (!shortTermExpectationRepository.isExist(dateTime[0], dateTime[1])) {
            for (ShortTermExpectationDto dto : shortTermExpectationDtoList) {
                shortTermExpectationRepository.save(dto.toEntity());
            }
            return "saved";
        }

        return "exists";
    }

    private String[] getBaseDateTime() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH");

        Calendar current = Calendar.getInstance();

        int[] releaseTimeArray = {2,5,8,11,14,17,20,23};

        int hour = current.get(Calendar.HOUR_OF_DAY);
        int minute = current.get(Calendar.MINUTE);

        int quotient = (hour-2)/3;

        int remainder = (hour-2)%3;

        if (remainder < 0 || (remainder == 0 && quotient == 0 && minute < 11)) {
            current.add(Calendar.DATE, -1);
            current.set(Calendar.HOUR_OF_DAY, 23);
        } else if (remainder >= 0 && (quotient != 0 && minute < 11)) {
            current.set(Calendar.HOUR_OF_DAY, releaseTimeArray[quotient - 1]);
        } else if (quotient == 0 && ((remainder == 0 && minute >= 11) || (remainder > 0 && minute < 11))) {
            current.set(Calendar.HOUR_OF_DAY, 2);
        } else {
            current.set(Calendar.HOUR_OF_DAY, releaseTimeArray[quotient]);
        }

        return new String[]{sdfDate.format(current.getTime()), sdfTime.format(current.getTime()) + "00"};
    }

}
