package com.practice.weather.midTerm.expectation.service;

import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermExpectationService {

    public MidTermExpectationDto parseMapToMidTermExpectationDto(HashMap<String, String> map, String stnId);

}
