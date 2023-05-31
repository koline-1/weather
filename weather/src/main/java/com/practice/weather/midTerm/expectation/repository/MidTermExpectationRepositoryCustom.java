package com.practice.weather.midTerm.expectation.repository;

import java.time.LocalDateTime;

public interface MidTermExpectationRepositoryCustom {

    // 데이터 중복 확인
    public boolean isExist(String stnId, LocalDateTime localDateTime);

}
