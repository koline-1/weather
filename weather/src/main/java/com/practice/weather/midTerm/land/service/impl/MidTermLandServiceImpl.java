package com.practice.weather.midTerm.land.service.impl;

import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
import com.practice.weather.midTerm.land.service.MidTermLandService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

@Service
public class MidTermLandServiceImpl implements MidTermLandService {

    @Autowired
    MidTermLandRepository midTermLandRepository;


    @Override
    public boolean existCheck(String regId, LocalDateTime localDateTime) {
        return midTermLandRepository.existCheck(regId, localDateTime);
    }

    @Override
    public MidTermLandEntity save(MidTermLandDto midTermLandDto) {
        return midTermLandRepository.save(midTermLandDto.toEntity());
    }

    @Override
    public MidTermLandDto buildMidTermLandDto(String str) {
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

        MidTermLandDto midTermLandDto = new MidTermLandDto();

        midTermLandDto.setRegId(map.get("regId").toString());
        midTermLandDto.setRnSt3Am(map.get("rnSt3Am").toString());
        midTermLandDto.setRnSt3Pm(map.get("rnSt3Pm").toString());
        midTermLandDto.setRnSt4Am(map.get("rnSt4Am").toString());
        midTermLandDto.setRnSt4Pm(map.get("rnSt4Pm").toString());
        midTermLandDto.setRnSt5Am(map.get("rnSt5Am").toString());
        midTermLandDto.setRnSt5Pm(map.get("rnSt5Pm").toString());
        midTermLandDto.setRnSt6Am(map.get("rnSt6Am").toString());
        midTermLandDto.setRnSt6Pm(map.get("rnSt6Pm").toString());
        midTermLandDto.setRnSt7Am(map.get("rnSt7Am").toString());
        midTermLandDto.setRnSt7Pm(map.get("rnSt7Pm").toString());
        midTermLandDto.setRnSt8(map.get("rnSt8").toString());
        midTermLandDto.setRnSt9(map.get("rnSt9").toString());
        midTermLandDto.setRnSt10(map.get("rnSt10").toString());
        midTermLandDto.setWf3Am(map.get("wf3Am").toString());
        midTermLandDto.setWf3Pm(map.get("wf3Pm").toString());
        midTermLandDto.setWf4Am(map.get("wf4Am").toString());
        midTermLandDto.setWf4Pm(map.get("wf4Pm").toString());
        midTermLandDto.setWf5Am(map.get("wf5Am").toString());
        midTermLandDto.setWf5Pm(map.get("wf5Pm").toString());
        midTermLandDto.setWf6Am(map.get("wf6Am").toString());
        midTermLandDto.setWf6Pm(map.get("wf6Pm").toString());
        midTermLandDto.setWf7Am(map.get("wf7Am").toString());
        midTermLandDto.setWf7Pm(map.get("wf7Pm").toString());
        midTermLandDto.setWf8(map.get("wf8").toString());
        midTermLandDto.setWf9(map.get("wf9").toString());
        midTermLandDto.setWf10(map.get("wf10").toString());

        return midTermLandDto;

    }

}