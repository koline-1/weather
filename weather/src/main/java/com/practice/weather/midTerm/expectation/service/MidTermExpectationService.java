package com.practice.weather.midTerm.expectation.service;

import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermExpectationService {

    // Map형태의 데이터를 DTO 객체로 변환
    public MidTermExpectationDto parseMapToMidTermExpectationDto(HashMap<String, String> map, String stnId);

}
