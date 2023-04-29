package com.practice.weather.controller;

import com.practice.weather.entity.MidTermOceanEntity;
import com.practice.weather.service.MidTermOceanService;
import com.practice.weather.weatherDto.MidTermOceanDto;
import com.practice.weather.weatherDto.WeatherRequestDto;
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

    @Autowired
    MidTermOceanService midTermOceanService;

    String serviceKey = "MURd%2Fv%2F0Sx9wcaIQJYwwnVC%2FQngp6H1j40ewDO6IBk8LO6E2XQsFkzPkrxFcVchzfHDrdXzXxVyamCeQphkq0Q%3D%3D";

    // 메인페이지
    @GetMapping("/")
    public String mainController(Model model) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" +
                "?serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON&base_date=" +
                dateFormat.format(cal.getTime()) + "&base_time=" + timeFormat.format(cal.getTime()) +"&nx=61&ny=120";

        model.addAttribute("url", url);

        return "mainView";
    }

    // 중기예보조회 List
    @GetMapping("/mid-term")
    public String midTermController(Model model) {

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

        String time = sdf.format(Calendar.getInstance().getTime());

        model.addAttribute("time", time);

        return "midTermList";
    }

    // 중기해상예보조회
    @GetMapping("/mid-term/ocean")
    public String midTermOceanController(
            @RequestParam("time") String time,
            Model model
        ) throws IOException {

        // API 날짜 설정 위해 오늘 날짜 가지고옴(최근 24시간 데이터만 접근 가능)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        // 오전 6시 이전일 경우 전날 18시 데이터 호출
        int clock = Integer.parseInt(sdf2.format(Calendar.getInstance().getTime()));

        if (clock <= 600) {
            cal.add(Calendar.DATE, -1);
            time = "1800";
        }

        String today = sdf.format(cal.getTime());

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidSeaFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON" +
                "&regId=12A10000&tmFc=" + today + time;

        String data = connectUrl(urlStr);

        if (data != null && !data.equals("")) {
            MidTermOceanDto midTermOceanDto = midTermOceanService.buildMidTermOceanDto(data, today);

            model.addAttribute("midTermOceanDto", midTermOceanDto);
        } else {
            model.addAttribute("midTermOceanDto", null);
        }

        return "midTermOceanView";
    }

    // URL 연결 메소드
    public String connectUrl(String urlStr) throws IOException {

        try {
            // API url 연결
            URL url = new URL(urlStr);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            // 타입 설정
            connection.setRequestProperty("CONTENT-TYPE", "application/json");

            //openStream() : URL페이지 정보를 읽어온다.
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

            String inputLine;
            String buffer = "";

            // 페이지의 정보를 저장한다.
            while ((inputLine = in.readLine()) != null){
                buffer += inputLine.trim();
            }

            in.close();

            return buffer;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 중기기온조회
    @GetMapping("/mid-term/temperature")
    public String midTermTemperatureController(
            @RequestParam("time") String time,
            Model model
        ) throws IOException {

        // API 날짜 설정 위해 오늘 날짜 가지고옴(최근 24시간 데이터만 접근 가능)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        // 오전 6시 이전일 경우 전날 18시 데이터 호출
        int clock = Integer.parseInt(sdf2.format(Calendar.getInstance().getTime()));

        if (clock <= 600) {
            cal.add(Calendar.DATE, -1);
            time = "1800";
        }

        String today = sdf.format(cal.getTime());

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"+
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON"+
                "&regId=11D20501&tmFc=" + today + time;

        List<HashMap<String, Object>> resultList = connectUrl(urlStr, "midTermTemperature");

        model.addAttribute("resultList", resultList);


        return "resultView";
    }

    // 중기육상예보조회
    @GetMapping("/mid-term/land")
    public String midTermLandController(
            @RequestParam("time") String time,
            Model model
    ) throws IOException {

        // API 날짜 설정 위해 오늘 날짜 가지고옴(최근 24시간 데이터만 접근 가능)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        // 오전 6시 이전일 경우 전날 18시 데이터 호출
        int clock = Integer.parseInt(sdf2.format(Calendar.getInstance().getTime()));

        if (clock <= 600) {
            cal.add(Calendar.DATE, -1);
            time = "1800";
        }

        String today = sdf.format(cal.getTime());

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON" +
                "&regId=11B00000&tmFc=" + today + time;
        

        List<HashMap<String, Object>> resultList = connectUrl(urlStr, "midTermLand");

        model.addAttribute("resultList", resultList);


        return "resultView";
    }

    // 중기전망조회
    @GetMapping("/mid-term/expectation")
    public String midTermExpectationController(
            @RequestParam("time") String time,
            Model model
    ) throws IOException {

        // API 날짜 설정 위해 오늘 날짜 가지고옴(최근 24시간 데이터만 접근 가능)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();

        // 오전 6시 이전일 경우 전날 18시 데이터 호출
        int clock = Integer.parseInt(sdf2.format(Calendar.getInstance().getTime()));

        if (clock <= 600) {
            cal.add(Calendar.DATE, -1);
            time = "1800";
        }

        String today = sdf.format(cal.getTime());

        // 서비스 URL
        String urlStr = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst" +
                "?serviceKey=" + serviceKey + "&numOfRows=10&pageNo=1&dataType=JSON" +
                "&stnId=133&tmFc=" + today + time;


        List<HashMap<String, Object>> resultList = connectUrl(urlStr, "midTermExpectation");

        model.addAttribute("resultList", resultList);


        return "resultView";
    }

    // 단기 예보 조회 List
    @GetMapping("/short-term")
    public String shortTermController() {
        return "shortTermList";
    }

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

        int abc = Integer.parseInt(jObject.toString());

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

    public MidTermOceanDto connect(@RequestBody WeatherRequestDto weatherRequestDto) {

        return null;
    }

}