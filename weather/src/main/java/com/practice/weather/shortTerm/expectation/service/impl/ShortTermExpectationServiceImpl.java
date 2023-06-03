package com.practice.weather.shortTerm.expectation.service.impl;

import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.service.ShortTermExpectationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortTermExpectationServiceImpl implements ShortTermExpectationService {

    @Override
    public List<ShortTermExpectationDto> parseJsonArrayToShortTermExpectationDto(JSONArray jArray, String version) {

        List<ShortTermExpectationDto> shortTermExpectationDtoList = new ArrayList<>();
        ShortTermExpectationDto tmpDto = new ShortTermExpectationDto();

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject j = (JSONObject) jArray.get(i);

            if (j.get("category").equals("TMP")) {
                if (i != 0) {
                    tmpDto.setVersion(version);
                    shortTermExpectationDtoList.add(tmpDto);
                    tmpDto = new ShortTermExpectationDto();
                }
                tmpDto.setBaseDate(j.get("baseDate").toString());
                tmpDto.setBaseTime(j.get("baseTime").toString());
                tmpDto.setForecastDate(j.get("fcstDate").toString());
                tmpDto.setForecastTime(j.get("fcstTime").toString());
                tmpDto.setNxValue(j.get("nx").toString());
                tmpDto.setNyValue(j.get("ny").toString());
                tmpDto.setHourTemperature(j.get("fcstValue").toString());
            } else if (j.get("category").equals("UUU")) {
                tmpDto.setHorizontalWind(j.get("fcstValue").toString());
            } else if (j.get("category").equals("VVV")) {
                tmpDto.setVerticalWind(j.get("fcstValue").toString());
            } else if (j.get("category").equals("WSD")) {
                tmpDto.setWindSpeed(j.get("fcstValue").toString());
            } else if (j.get("category").equals("VEC")) {
                tmpDto.setWindDirection(j.get("fcstValue").toString());
            } else if (j.get("category").equals("SKY")) {
                tmpDto.setSkyStatus(j.get("fcstValue").toString());
            } else if (j.get("category").equals("REH")) {
                tmpDto.setHumidity(j.get("fcstValue").toString());
            } else if (j.get("category").equals("POP")) {
                tmpDto.setRainPossibility(j.get("fcstValue").toString());
            } else if (j.get("category").equals("PTY")) {
                tmpDto.setRainType(j.get("fcstValue").toString());
            } else if (j.get("category").equals("PCP")) {
                tmpDto.setHourPrecipitation(j.get("fcstValue").toString());
            } else if (j.get("category").equals("SNO")) {
                tmpDto.setSnowDepth(j.get("fcstValue").toString());
            } else if (j.get("category").equals("WAV")) {
                tmpDto.setWaveHeight(j.get("fcstValue").toString());
            } else if (j.get("category").equals("TMN")) {
                tmpDto.setMinimumTemperature(j.get("fcstValue").toString());
            } else if (j.get("category").equals("TMX")) {
                tmpDto.setMaximumTemperature(j.get("fcstValue").toString());
            }
        }
        tmpDto.setVersion(version);
        shortTermExpectationDtoList.add(tmpDto);

        return shortTermExpectationDtoList;

    }
    
}
