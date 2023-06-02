package com.practice.weather.midTerm.expectation.service.impl;

import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.service.MidTermExpectationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MidTermExpectationServiceImpl implements MidTermExpectationService {


    @Override
    public MidTermExpectationDto parseMapToMidTermExpectationDto(HashMap<String, String> map, String stnId) {

        MidTermExpectationDto midTermExpectationDto = new MidTermExpectationDto();

        midTermExpectationDto.setStnId(stnId);
        midTermExpectationDto.setWfSv(map.get("wfSv"));

        return midTermExpectationDto;

    }

}