package com.practice.weather.utility;

import org.json.JSONArray;
import org.json.JSONObject;
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

@Component
public class Utility {
    // URL 연결 메소드
    public JSONArray getDataAsJsonArray(String urlStr) {

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

            JSONObject jObject = new JSONObject(buffer);

            jObject = (JSONObject) jObject.get("response");
            jObject = (JSONObject) jObject.get("body");
            jObject = (JSONObject) jObject.get("items");

            JSONArray jArray = (JSONArray) jObject.get("item");

            return jArray;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public String getMidTermBaseDateTimeAsString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        return sdf.format(getMidTermBaseDateTimeAsCalendar().getTime());
    }

    public LocalDateTime getMidTermBaseDateTimeAsLocalDateTime() {

        return getMidTermBaseDateTimeAsCalendar().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

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
}
