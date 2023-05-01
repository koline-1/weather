package com.practice.weather.midTerm.land.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.main.controller.MainController;
import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.service.MidTermLandService;
import com.practice.weather.utility.utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

@Controller
public class MidTermLandController {

    @Autowired
    MidTermLandService midTermLandService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/mid-term/land/location")
    public String midTermLandLocationController (
            Model model
    ) {
        return "/midTerm/midTermLand/midTermLandLocation";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/land/data")
    public String midTermLandController(
            @RequestParam(value = "location", required = false) String location,
            Model model
    ) throws JsonProcessingException {

        // API 날짜 설정 위해 오늘 날짜 가지고옴(최근 24시간 데이터만 접근 가능)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();

        String time = "";

        if (cal.get(Calendar.HOUR_OF_DAY) < 6) {
            cal.add(Calendar.DATE, -1);
            time = "1800";
        } else if (6 <= cal.get(Calendar.HOUR_OF_DAY) && cal.get(Calendar.HOUR_OF_DAY) < 18) {
            time = "0600";
        } else {
            time = "1800";
        }

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst" +
                "?serviceKey=" + MainController.serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "11B10101") + "&tmFc=" + sdf.format(cal.getTime()) + time;


        String data = utility.connectUrl(urlStr);

        MidTermLandDto midTermLandDto = midTermLandService.buildMidTermLandDto(data);

        if (data != null && !data.equals("")) {
            model.addAttribute("midTermLandDto", midTermLandDto);
            model.addAttribute("midTermLandDtoJson", objectMapper.writeValueAsString(midTermLandDto));
        } else {
            model.addAttribute("midTermLandDto", null);
            model.addAttribute("midTermLandDtoJson", null);
        }

        return "/midTerm/midTermLand/midTermLandData";
    }

    @ResponseBody
    @PostMapping("/mid-term/land/data")
    public String saveMidTermLand (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermLandDto midTermLandDto = objectMapper.readValue(jObject.get("data").toString(), MidTermLandDto.class);

        Calendar cal = Calendar.getInstance();

        if (cal.get(Calendar.HOUR_OF_DAY) < 6) {
            cal.add(Calendar.DATE, -1);
            cal.set(Calendar.HOUR_OF_DAY, 18);
            cal.set(Calendar.MINUTE, 0);
        } else if (6 <= cal.get(Calendar.HOUR_OF_DAY) && cal.get(Calendar.HOUR_OF_DAY) < 18) {
            cal.set(Calendar.HOUR_OF_DAY, 6);
            cal.set(Calendar.MINUTE, 0);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 18);
            cal.set(Calendar.MINUTE, 0);
        }

        LocalDateTime localDateTime = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (!midTermLandService.existCheck(midTermLandDto.getRegId(), localDateTime)) {
            midTermLandService.save(midTermLandDto);
            return "saved";
        }

        return "exists";
    }
    
}
