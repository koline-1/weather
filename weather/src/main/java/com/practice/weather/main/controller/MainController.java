package com.practice.weather.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class MainController {

    public static String serviceKey = "MURd%2Fv%2F0Sx9wcaIQJYwwnVC%2FQngp6H1j40ewDO6IBk8LO6E2XQsFkzPkrxFcVchzfHDrdXzXxVyamCeQphkq0Q%3D%3D";

    // TODO: 초단기 실황 데이터 말고 초단기 예보로 변경
    // 메인페이지
    @GetMapping("/")
    public String mainController(Model model) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

        // 30분이전 데이터 이용가능
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, -30);

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" +
                "?serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON&base_date=" +
                dateFormat.format(cal.getTime()) + "&base_time=" + timeFormat.format(cal.getTime()) +"&nx=61&ny=120";

        model.addAttribute("url", url);

        return "/main/mainView";
    }

    // 중기예보조회 List
    @GetMapping("/mid-term")
    public String midTermController(Model model) {

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

        String time = sdf.format(Calendar.getInstance().getTime());

        model.addAttribute("time", time);

        return "/midTerm/midTermList";
    }

    // 단기 예보 조회 List
    @GetMapping("/short-term")
    public String shortTermController() {
        return "/shortTerm/shortTermList";
    }

}
