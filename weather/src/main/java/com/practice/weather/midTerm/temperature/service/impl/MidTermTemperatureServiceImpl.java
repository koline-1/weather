package com.practice.weather.midTerm.temperature.service.impl;

import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import com.practice.weather.midTerm.temperature.repository.MidTermTemperatureRepository;
import com.practice.weather.midTerm.temperature.service.MidTermTemperatureService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

@Service
public class MidTermTemperatureServiceImpl implements MidTermTemperatureService {

    @Autowired
    MidTermTemperatureRepository midTermTemperatureRepository;


    @Override
    public boolean existCheck(String regId, LocalDateTime localDateTime) {
        return midTermTemperatureRepository.existCheck(regId, localDateTime);
    }

    @Override
    public MidTermTemperatureEntity save(MidTermTemperatureDto midTermTemperatureDto) {
        return midTermTemperatureRepository.save(midTermTemperatureDto.toEntity());
    }

    @Override
    public MidTermTemperatureDto buildMidTermTemperatureDto(String str) {
        JSONObject jObject = new JSONObject(str);

        jObject = (JSONObject) jObject.get("response");
        jObject = (JSONObject) jObject.get("body");
        jObject = (JSONObject) jObject.get("items");

        JSONArray jArray = (JSONArray) jObject.get("item");

        HashMap<String, Object> map = new HashMap<String, Object>();

        // 데이터 hashmap으로 처리
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject j = (JSONObject) jArray.get(i);
            Set<String> keySet = j.keySet();

            for (String s : keySet) {
                map.put(s, j.get(s));
            }
        }

        MidTermTemperatureDto midTermTemperatureDto = new MidTermTemperatureDto();

        midTermTemperatureDto.setRegId(map.get("regId").toString());
        midTermTemperatureDto.setTaMin3(map.get("taMin3").toString());
        midTermTemperatureDto.setTaMin3Low(map.get("taMin3Low").toString());
        midTermTemperatureDto.setTaMin3High(map.get("taMin3High").toString());
        midTermTemperatureDto.setTaMax3(map.get("taMax3").toString());
        midTermTemperatureDto.setTaMax3Low(map.get("taMax3Low").toString());
        midTermTemperatureDto.setTaMax3High(map.get("taMax3High").toString());
        midTermTemperatureDto.setTaMin4(map.get("taMin4").toString());
        midTermTemperatureDto.setTaMin4Low(map.get("taMin4Low").toString());
        midTermTemperatureDto.setTaMin4High(map.get("taMin4High").toString());
        midTermTemperatureDto.setTaMax4(map.get("taMax4").toString());
        midTermTemperatureDto.setTaMax4Low(map.get("taMax4Low").toString());
        midTermTemperatureDto.setTaMax4High(map.get("taMax4High").toString());
        midTermTemperatureDto.setTaMin5(map.get("taMin5").toString());
        midTermTemperatureDto.setTaMin5Low(map.get("taMin5Low").toString());
        midTermTemperatureDto.setTaMin5High(map.get("taMin5High").toString());
        midTermTemperatureDto.setTaMax5(map.get("taMax5").toString());
        midTermTemperatureDto.setTaMax5Low(map.get("taMax5Low").toString());
        midTermTemperatureDto.setTaMax5High(map.get("taMax5High").toString());
        midTermTemperatureDto.setTaMin6(map.get("taMin6").toString());
        midTermTemperatureDto.setTaMin6Low(map.get("taMin6Low").toString());
        midTermTemperatureDto.setTaMin6High(map.get("taMin6High").toString());
        midTermTemperatureDto.setTaMax6(map.get("taMax6").toString());
        midTermTemperatureDto.setTaMax6Low(map.get("taMax6Low").toString());
        midTermTemperatureDto.setTaMax6High(map.get("taMax6High").toString());
        midTermTemperatureDto.setTaMin7(map.get("taMin7").toString());
        midTermTemperatureDto.setTaMin7Low(map.get("taMin7Low").toString());
        midTermTemperatureDto.setTaMin7High(map.get("taMin7High").toString());
        midTermTemperatureDto.setTaMax7(map.get("taMax7").toString());
        midTermTemperatureDto.setTaMax7Low(map.get("taMax7Low").toString());
        midTermTemperatureDto.setTaMax7High(map.get("taMax7High").toString());
        midTermTemperatureDto.setTaMin8(map.get("taMin8").toString());
        midTermTemperatureDto.setTaMin8Low(map.get("taMin8Low").toString());
        midTermTemperatureDto.setTaMin8High(map.get("taMin8High").toString());
        midTermTemperatureDto.setTaMax8(map.get("taMax8").toString());
        midTermTemperatureDto.setTaMax8Low(map.get("taMax8Low").toString());
        midTermTemperatureDto.setTaMax8High(map.get("taMax8High").toString());
        midTermTemperatureDto.setTaMin9(map.get("taMin9").toString());
        midTermTemperatureDto.setTaMin9Low(map.get("taMin9Low").toString());
        midTermTemperatureDto.setTaMin9High(map.get("taMin9High").toString());
        midTermTemperatureDto.setTaMax9(map.get("taMax9").toString());
        midTermTemperatureDto.setTaMax9Low(map.get("taMax9Low").toString());
        midTermTemperatureDto.setTaMax9High(map.get("taMax9High").toString());
        midTermTemperatureDto.setTaMin10(map.get("taMin10").toString());
        midTermTemperatureDto.setTaMin10Low(map.get("taMin10Low").toString());
        midTermTemperatureDto.setTaMin10High(map.get("taMin10High").toString());
        midTermTemperatureDto.setTaMax10(map.get("taMax10").toString());
        midTermTemperatureDto.setTaMax10Low(map.get("taMax10Low").toString());
        midTermTemperatureDto.setTaMax10High(map.get("taMax10High").toString());

        return midTermTemperatureDto;

    }

}
