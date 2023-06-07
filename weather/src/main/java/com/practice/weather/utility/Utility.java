package com.practice.weather.utility;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

@Slf4j
@Component
public class Utility {

    // 기상청 API 서비스키
    @Value("${service.key}")
    private String serviceKey;

    // URL 연결 메소드
    @Deprecated
    public JSONArray getDataAsJsonArray(String urlStr) {

        try {
            // API url 연결
            URL url = new URL(urlStr);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            // 타입 설정
            connection.setRequestProperty("CONTENT-TYPE", "application/json");

            //openStream() : URL 페이지 정보를 읽어온다.
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

            String inputLine;
            String buffer = "";

            // 페이지의 정보를 저장한다.
            while ((inputLine = in.readLine()) != null){
                buffer += inputLine.trim();
            }

            in.close();

            JSONObject jObject = new JSONObject(buffer);

            jObject = (JSONObject) jObject.get("response");
            jObject = (JSONObject) jObject.get("body");
            jObject = (JSONObject) jObject.get("items");

            JSONArray jArray = (JSONArray) jObject.get("item");

            return jArray;

        } catch (IOException e) {
            log.error("[EXCEPTION] Utility.getDataAsJsonArray : {}", e);
            return null;
        }
    }

    // JSONArray > Map 파싱
    @Deprecated
    public HashMap<String, String> parseJsonArrayToMap (JSONArray jArray) {

        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject j = (JSONObject) jArray.get(i);
            Set<String> keySet = j.keySet();

            for (String s : keySet) {
                map.put(s, j.get(s).toString());
            }
        }

        return map;
    }

    // 중기예보 BaseDate, BaseTime String Type 으로 return
    public String getMidTermBaseDateTimeAsString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        return sdf.format(getMidTermBaseDateTimeAsCalendar().getTime());
    }

    // 중기예보 BaseDate, BaseTime LocalDateTime Type 으로 return
    public LocalDateTime getMidTermBaseDateTimeAsLocalDateTime() {

        return getMidTermBaseDateTimeAsCalendar().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    // 중기예보 BaseDate, BaseTime Calendar Type 으로 return
    public Calendar getMidTermBaseDateTimeAsCalendar() {

        Calendar cal = Calendar.getInstance();

        if (cal.get(Calendar.HOUR_OF_DAY) < 6) {
            cal.add(Calendar.DATE, -1);
            cal.set(Calendar.HOUR_OF_DAY, 18);
        } else if (6 <= cal.get(Calendar.HOUR_OF_DAY) && cal.get(Calendar.HOUR_OF_DAY) < 18) {
            cal.set(Calendar.HOUR_OF_DAY, 6);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 18);
        }

        cal.set(Calendar.MINUTE, 0);

        return cal;
    }

    // 단기예보 BaseDate, BaseTime String[] Type 으로 return
    @Deprecated
    public String[] getShortTermBaseDateTime(String serviceId) {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HHmm");

        Calendar current = Calendar.getInstance();

        if (serviceId.equals("extra")) {
            if (current.get(Calendar.MINUTE) <= 45 ) {
                current.add(Calendar.HOUR_OF_DAY, -1);
            }
        } else if (serviceId.equals("status")) {
            if (current.get(Calendar.MINUTE) <= 40 ) {
                current.add(Calendar.HOUR_OF_DAY, -1);
            }
        } else if (serviceId.equals("expectation")) {
            int[] releaseTimeArray = {2, 5, 8, 11, 14, 17, 20, 23};

            int hour = current.get(Calendar.HOUR_OF_DAY);
            int minute = current.get(Calendar.MINUTE);
            int quotient = (hour - 2) / 3;
            int remainder = (hour - 2) % 3;

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
        }

        return new String[]{sdfDate.format(current.getTime()), sdfTime.format(current.getTime())};
    }

    // 단기예보 버전 조회
    @Deprecated
    public String getShortTermVersion (String serviceId, String dateTime) {

        String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getFcstVersion?" +
                "serviceKey=" + serviceKey + "&pageNo=1&numOfRows=1000&dataType=JSON" +
                "&ftype=" + serviceId + "&basedatetime=" + dateTime;

        JSONArray jArray = getDataAsJsonArray(urlStr);

        return ((JSONObject) jArray.get(0)).get("version").toString();

    }
}
