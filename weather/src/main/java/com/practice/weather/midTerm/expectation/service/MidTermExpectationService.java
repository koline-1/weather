package com.practice.weather.midTerm.expectation.service;

import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;

import java.time.LocalDateTime;

public interface MidTermExpectationService {

    public boolean existCheck(String stnId, LocalDateTime localDateTime);

    public MidTermExpectationEntity save(MidTermExpectationDto midTermExpectationDto);

    public MidTermExpectationDto buildMidTermExpectationDto(String str, String stnId);
}
