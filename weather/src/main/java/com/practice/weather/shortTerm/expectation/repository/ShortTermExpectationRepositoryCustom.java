package com.practice.weather.shortTerm.expectation.repository;

import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;


public interface ShortTermExpectationRepositoryCustom {

    // 데이터 중복 확인
    boolean isExist(ShortTermExpectationDto dto);

}
