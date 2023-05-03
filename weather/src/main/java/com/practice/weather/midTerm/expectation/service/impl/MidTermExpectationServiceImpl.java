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
    public MidTermExpectationDto parseMapToMidTermExpectationDto(HashMap<String, String> map, String stnId) {

        MidTermExpectationDto midTermExpectationDto = new MidTermExpectationDto();

        midTermExpectationDto.setStnId(stnId);
        midTermExpectationDto.setWfSv(map.get("wfSv"));

        return midTermExpectationDto;

    }

}