package com.practice.weather.midTerm.expectation.service.impl;

import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import com.practice.weather.midTerm.expectation.repository.MidTermExpectationRepository;
import com.practice.weather.midTerm.expectation.service.MidTermExpectationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

@Service
public class MidTermExpectationServiceImpl implements MidTermExpectationService {

    @Autowired
    MidTermExpectationRepository midTermExpectationRepository;


    @Override
    public boolean existCheck(String stnId, LocalDateTime localDateTime) {
        return midTermExpectationRepository.existCheck(stnId, localDateTime);
    }

    @Override
    public MidTermExpectationEntity save(MidTermExpectationDto midTermExpectationDto) {
        return midTermExpectationRepository.save(midTermExpectationDto.toEntity());
    }

    @Override
    public MidTermExpectationDto buildMidTermExpectationDto(String str, String stnId) {
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

        MidTermExpectationDto midTermExpectationDto = new MidTermExpectationDto();

        midTermExpectationDto.setStnId(stnId);
        midTermExpectationDto.setWfSv(map.get("wfSv").toString());

        return midTermExpectationDto;

    }

}