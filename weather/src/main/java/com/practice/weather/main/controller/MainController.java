package com.practice.weather.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@Controller
public class MainController {

    // 기상청 API 서비스키
    @Value("${service.key}")
    private String serviceKey;

    // 빈 URL로 접근시 /mainView로 redirect
    @GetMapping("/")
    public String mainRedirect() {
        return "redirect:/mainView";
    }

    // TODO: 초단기 실황 데이터 말고 초단기 예보로 변경
    // 메인페이지
    @GetMapping("/mainView")
    public String mainView(Model model) {

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
    public String midTermListView() {
        return "/midTerm/midTermList";
    }

    // 단기 예보 조회 List
    @GetMapping("/short-term")
    public String shortTermListView() {
        return "/shortTerm/shortTermList";
    }

    // 단기 예보 조회 위치 설정
    @GetMapping("/short-term/location")
    public String shortTermLocationView() {
        return "/shortTerm/shortTermLocation";
    }

}
