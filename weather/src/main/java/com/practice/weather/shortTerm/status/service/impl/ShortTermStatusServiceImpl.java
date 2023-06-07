package com.practice.weather.shortTerm.status.service.impl;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import com.practice.weather.shortTerm.status.service.ShortTermStatusService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ShortTermStatusServiceImpl implements ShortTermStatusService {

    @Deprecated
    @Override
    public ShortTermStatusDto parseJsonArrayToShortTermStatusDto(JSONArray jArray, String version) {

        ShortTermStatusDto shortTermStatusDto = new ShortTermStatusDto();

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject j = (JSONObject) jArray.get(i);
            if (j.get("category").equals("PTY")) {
                shortTermStatusDto.setBaseDate(j.get("baseDate").toString());
                shortTermStatusDto.setBaseTime(j.get("baseTime").toString());
                shortTermStatusDto.setNxValue(j.get("nx").toString());
                shortTermStatusDto.setNyValue(j.get("ny").toString());
                shortTermStatusDto.setRainType(j.get("obsrValue").toString());
            } else if (j.get("category").equals("REH")) {
                shortTermStatusDto.setHumidity(j.get("obsrValue").toString());
            } else if (j.get("category").equals("RN1")) {
                shortTermStatusDto.setHourPrecipitation(j.get("obsrValue").toString());
            } else if (j.get("category").equals("T1H")) {
                shortTermStatusDto.setTemperature(j.get("obsrValue").toString());
            } else if (j.get("category").equals("UUU")) {
                shortTermStatusDto.setHorizontalWind(j.get("obsrValue").toString());
            } else if (j.get("category").equals("VVV")) {
                shortTermStatusDto.setVerticalWind(j.get("obsrValue").toString());
            } else if (j.get("category").equals("VEC")) {
                shortTermStatusDto.setWindDirection(j.get("obsrValue").toString());
            } else if (j.get("category").equals("WSD")) {
                shortTermStatusDto.setWindSpeed(j.get("obsrValue").toString());
            }
        }

        shortTermStatusDto.setVersion(version);

        return shortTermStatusDto;
    }
}
