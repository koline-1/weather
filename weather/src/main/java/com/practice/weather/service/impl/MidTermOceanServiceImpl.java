package com.practice.weather.service.impl;

import com.practice.weather.entity.MidTermOceanEntity;
import com.practice.weather.entity.WeatherEntity;
import com.practice.weather.service.MidTermOceanService;
import com.practice.weather.weatherDto.MidTermOceanDto;
import com.practice.weather.weatherRepository.MidTermOceanRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class MidTermOceanServiceImpl implements MidTermOceanService {

    @Autowired
    MidTermOceanRepository midTermOceanRepository;

    @Override
    public MidTermOceanEntity save(MidTermOceanDto midTermOceanDto) {

        return midTermOceanRepository.save(midTermOceanDto.toEntity());
    }

    // API로 받아온 데이터를 DTO객체로 변환
    @Override
    public MidTermOceanDto buildMidTermOceanDto(String str) {

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

        MidTermOceanDto midTermOceanDto = new MidTermOceanDto();

        midTermOceanDto.setRegId(map.get("regId").toString());
        midTermOceanDto.setWf8(map.get("wf8").toString());
        midTermOceanDto.setWh4AAm(map.get("wh4AAm").toString());
        midTermOceanDto.setWh4BAm(map.get("wh4BAm").toString());
        midTermOceanDto.setWh6BAm(map.get("wh6BAm").toString());
        midTermOceanDto.setWf9(map.get("wf9").toString());
        midTermOceanDto.setWh6AAm(map.get("wh6AAm").toString());
        midTermOceanDto.setWh9A(map.get("wh9A").toString());
        midTermOceanDto.setWh9B(map.get("wh9B").toString());
        midTermOceanDto.setWf6Pm(map.get("wf6Pm").toString());
        midTermOceanDto.setWf7Pm(map.get("wf7Pm").toString());
        midTermOceanDto.setWf10(map.get("wf10").toString());
        midTermOceanDto.setWh4APm(map.get("wh4APm").toString());
        midTermOceanDto.setWh6BPm(map.get("wh6BPm").toString());
        midTermOceanDto.setWh6APm(map.get("wh6APm").toString());
        midTermOceanDto.setWf3Pm(map.get("wf3Pm").toString());
        midTermOceanDto.setWf4Pm(map.get("wf4Pm").toString());
        midTermOceanDto.setWh4BPm(map.get("wh4BPm").toString());
        midTermOceanDto.setWf5Pm(map.get("wf5Pm").toString());
        midTermOceanDto.setWh7AAm(map.get("wh7AAm").toString());
        midTermOceanDto.setWf7Am(map.get("wf7Am").toString());
        midTermOceanDto.setWh3AAm(map.get("wh3AAm").toString());
        midTermOceanDto.setWh5BAm(map.get("wh5BAm").toString());
        midTermOceanDto.setWh5AAm(map.get("wh5AAm").toString());
        midTermOceanDto.setWf3Am(map.get("wf3Am").toString());
        midTermOceanDto.setWh7BAm(map.get("wh7BAm").toString());
        midTermOceanDto.setWh8B(map.get("wh8B").toString());
        midTermOceanDto.setWf4Am(map.get("wf4Am").toString());
        midTermOceanDto.setWh8A(map.get("wh8A").toString());
        midTermOceanDto.setWh10A(map.get("wh10A").toString());
        midTermOceanDto.setWf5Am(map.get("wf5Am").toString());
        midTermOceanDto.setWh3BAm(map.get("wh3BAm").toString());
        midTermOceanDto.setWh10B(map.get("wh10B").toString());
        midTermOceanDto.setWf6Am(map.get("wf6Am").toString());
        midTermOceanDto.setWh5BPm(map.get("wh5BPm").toString());
        midTermOceanDto.setWh5APm(map.get("wh5APm").toString());
        midTermOceanDto.setWh7APm(map.get("wh7APm").toString());
        midTermOceanDto.setWh7BPm(map.get("wh7BPm").toString());
        midTermOceanDto.setWh3APm(map.get("wh3APm").toString());
        midTermOceanDto.setWh3BPm(map.get("wh3BPm").toString());

        return midTermOceanDto;
    }

}
