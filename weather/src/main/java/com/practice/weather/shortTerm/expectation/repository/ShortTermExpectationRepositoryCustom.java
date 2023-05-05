package com.practice.weather.shortTerm.expectation.repository;

import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;


public interface ShortTermExpectationRepositoryCustom {

    public boolean isExist(ShortTermExpectationDto dto);

}
