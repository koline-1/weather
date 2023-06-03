package com.practice.weather.midTerm.expectation.service;

import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;

import java.util.HashMap;

public interface MidTermExpectationService {

    // Map 형태의 데이터를 DTO 객체로 변환
    MidTermExpectationDto parseMapToMidTermExpectationDto(HashMap<String, String> map, String stnId);

}
