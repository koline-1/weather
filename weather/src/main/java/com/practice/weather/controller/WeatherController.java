package com.practice.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.ocean.service.MidTermOceanService;
import org.json.JSONArray;
import org.json.JSONObject;

import com.practice.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WeatherController {

    @Autowired
    WeatherService weatherService;


    private ObjectMapper objectMapper = new ObjectMapper();

    String serviceKey = "MURd%2Fv%2F0Sx9wcaIQJYwwnVC%2FQngp6H1j40ewDO6IBk8LO6E2XQsFkzPkrxFcVchzfHDrdXzXxVyamCeQphkq0Q%3D%3D";


    // 초단기실황조회
    @GetMapping("/short-term/status")
    public String shortTermStatusController(Model model) {

        // DB 저장용 ID
        model.addAttribute("id", "shortTermStatus");

        // 6시 이전일 경우 전날 기준으로 데이터 조회
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        int time = Integer.parseInt(sdf2.format(cal.getTime()));

        if (time <= 600) {
            cal.add(Calendar.DATE, -1);
        }

        String today = sdf.format(cal.getTime());

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" +
                "?serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + today + "&base_time=0600&nx=55&ny=127";

        model.addAttribute("url", url);


        return "resultView2";
    }

    // 초단기예보조회
    @GetMapping("/short-term/extraExpectation")
    public String shortTermExtraExpectationController(Model model) {

        // DB 저장용 ID
        model.addAttribute("id", "shortTermExtraExpectation");

        // 6시 이전일 경우 전날 기준으로 데이터 조회
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        int time = Integer.parseInt(sdf2.format(cal.getTime()));

        if (time <= 600) {
            cal.add(Calendar.DATE, -1);
        }

        String today = sdf.format(cal.getTime());

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + today + "&base_time=0630&nx=55&ny=127";


        model.addAttribute("url", url);


        return "resultView2";
    }

    // 단기예보조회
    @GetMapping("/short-term/expectation")
    public String shortTermExpectationController(Model model) {

        // DB 저장용 ID
        model.addAttribute("id", "shortTermExpectation");

        // 6시 이전일 경우 전날 기준으로 데이터 조회
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        int time = Integer.parseInt(sdf2.format(cal.getTime()));

        if (time <= 600) {
            cal.add(Calendar.DATE, -1);
        }

        String today = sdf.format(cal.getTime());

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&base_date=" + today + "&base_time=0500&nx=55&ny=127";


        model.addAttribute("url", url);


        return "resultView2";
    }

    // 예보버전조회
    @GetMapping("/short-term/version")
    public String shortTermVersionController(Model model) {

        // DB 저장용 ID
        model.addAttribute("id", "shortTermVersion");

        // 6시 이전일 경우 전날 기준으로 데이터 조회
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        int time = Integer.parseInt(sdf2.format(cal.getTime()));

        if (time <= 600) {
            cal.add(Calendar.DATE, -1);
        }

        String today = sdf.format(cal.getTime());

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getFcstVersion?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&ftype=ODAM&basedatetime=" + today + "0800";

        model.addAttribute("url", url);


        return "resultView2";
    }

    // fetch API의 POST 요청을 받아서 DB에 데이터 저장
    @ResponseBody
    @PostMapping("/short-term/data")
    public String shortTermSaveFetch(
        @RequestBody String res
        ){
        JSONObject jsonObject = new JSONObject(res);
        String id = (String) jsonObject.get("id");
        String data = (String) jsonObject.get("data");
        weatherService.saveData(id, data);
        return "SUCCESS";
    }

    // URL 연결 메소드
    public List<HashMap<String, Object>> connectUrl(String urlStr, String id) throws IOException {

        // API url 연결
        URL url = new URL(urlStr);

        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        // 타입 설정
        connection.setRequestProperty("CONTENT-TYPE","application/json");

        //openStream() : URL페이지 정보를 읽어온다.
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

        String inputLine;
        String buffer = "";

        // 페이지의 정보를 저장한다.
        while ((inputLine = in.readLine()) != null){
            buffer += inputLine.trim();
        }

        // 받아온 데이터 JSON 파싱
        JSONObject jObject = new JSONObject(buffer);

        jObject = (JSONObject) jObject.get("response");
        jObject = (JSONObject) jObject.get("body");
        jObject = (JSONObject) jObject.get("items");

        JSONArray jArray = (JSONArray) jObject.get("item");

        // DB에 입력
        weatherService.saveData(id, buffer);

        List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

        // 데이터 hashmap으로 처리
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject j = (JSONObject) jArray.get(i);
            Set<String> keySet = j.keySet();
            HashMap<String, Object> map = new HashMap<String, Object>();

            for (String str : keySet) {
                map.put(str, j.get(str));
            }

            resultList.add(map);
        }

        in.close();

        return resultList;
    }

}