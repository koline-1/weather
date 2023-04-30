package com.practice.weather.midTerm.ocean.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.main.controller.MainController;
import com.practice.weather.midTerm.ocean.service.MidTermOceanService;
import com.practice.weather.utility.utility;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;
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
public class MidTermOceanController {

    @Autowired
    MidTermOceanService midTermOceanService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/mid-term/ocean/location")
    public String midTermOceanLocationController (
            Model model
    ) {
        return "/midTerm/midTermOcean/midTermOceanLocation";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/ocean/data")
    public String midTermOceanController(
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
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidSeaFcst" +
                "?serviceKey=" + MainController.serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON&regId=" +
                (location != null && !location.equals("") ? location : "12A20000") + "&tmFc=" + sdf.format(cal.getTime()) + time;

        String data = utility.connectUrl(urlStr);

        MidTermOceanDto midTermOceanDto = midTermOceanService.buildMidTermOceanDto(data);

        if (data != null && !data.equals("")) {
            model.addAttribute("midTermOceanDto", midTermOceanDto);
            model.addAttribute("midTermOceanDtoJson", objectMapper.writeValueAsString(midTermOceanDto));
        } else {
            model.addAttribute("midTermOceanDto", null);
            model.addAttribute("midTermOceanDtoJson", null);
        }

        return "/midTerm/midTermOcean/midTermOceanData";
    }

    @ResponseBody
    @PostMapping("/mid-term/ocean/data")
    public String saveMidTermOcean (@RequestBody String data) throws JsonProcessingException {

        JSONObject jObject = new JSONObject(data);

        MidTermOceanDto midTermOceanDto = objectMapper.readValue(jObject.get("data").toString(), MidTermOceanDto.class);

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

        if (!midTermOceanService.existCheck(midTermOceanDto.getRegId(), localDateTime)) {
            midTermOceanService.save(midTermOceanDto);
            return "saved";
        }

        return "exists";
    }
}
