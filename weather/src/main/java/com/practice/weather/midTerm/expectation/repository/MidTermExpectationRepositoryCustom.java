package com.practice.weather.midTerm.expectation.repository;

import java.time.LocalDateTime;

public interface MidTermExpectationRepositoryCustom {

    public boolean existCheck(String stnId, LocalDateTime localDateTime);

}
