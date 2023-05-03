package com.practice.weather.shortTerm.expectation.repository;

import java.time.LocalDateTime;

public interface ShortTermExpectationRepositoryCustom {

    public boolean isExist(String baseDate, String baseTime);

}
