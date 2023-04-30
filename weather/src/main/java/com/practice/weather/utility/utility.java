package com.practice.weather.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class utility {
    // URL 연결 메소드
    public static String connectUrl(String urlStr) {

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
}
