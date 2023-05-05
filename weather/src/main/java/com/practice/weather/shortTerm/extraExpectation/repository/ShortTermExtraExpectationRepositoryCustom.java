package com.practice.weather.shortTerm.extraExpectation.repository;

import com.practice.weather.shortTerm.extraExpectation.dto.ShortTermExtraExpectationDto;

public interface ShortTermExtraExpectationRepositoryCustom {

    public boolean isExist(ShortTermExtraExpectationDto dto);
}
