package com.practice.weather.shortTerm.extraExpectation.service.impl;

import com.practice.weather.shortTerm.extraExpectation.dto.ShortTermExtraExpectationDto;
import com.practice.weather.shortTerm.extraExpectation.service.ShortTermExtraExpectationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortTermExtraExpectationServiceImpl implements ShortTermExtraExpectationService {

    @Override
    public List<ShortTermExtraExpectationDto> parseJsonArrayToShortTermExtraExpectationDto(JSONArray jArray, String version) {

        List<ShortTermExtraExpectationDto> ShortTermExtraExpectationDtoList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ShortTermExtraExpectationDto tmpDto = new ShortTermExtraExpectationDto();
            for (int j = i; j < 60; j = j+6) {
                JSONObject jObject = (JSONObject) jArray.get(j);
                if (jObject.get("category").equals("LGT")) {
                    tmpDto.setBaseDate(jObject.get("baseDate").toString());
                    tmpDto.setBaseTime(jObject.get("baseTime").toString());
                    tmpDto.setForecastDate(jObject.get("fcstDate").toString());
                    tmpDto.setForecastTime(jObject.get("fcstTime").toString());
                    tmpDto.setNxValue(jObject.get("nx").toString());
                    tmpDto.setNyValue(jObject.get("ny").toString());
                    tmpDto.setLightning(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("PTY")) {
                    tmpDto.setRainType(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("T1H")) {
                    tmpDto.setTemperature(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("RN1")) {
                    tmpDto.setHourPrecipitation(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("SKY")) {
                    tmpDto.setSkyStatus(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("UUU")) {
                    tmpDto.setHorizontalWind(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("VVV")) {
                    tmpDto.setVerticalWind(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("REH")) {
                    tmpDto.setHumidity(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("VEC")) {
                    tmpDto.setWindDirection(jObject.get("fcstValue").toString());
                } else if (jObject.get("category").equals("WSD")) {
                    tmpDto.setWindSpeed(jObject.get("fcstValue").toString());
                }
            }

            tmpDto.setVersion(version);

            ShortTermExtraExpectationDtoList.add(tmpDto);
        }


        return ShortTermExtraExpectationDtoList;
    }
}
