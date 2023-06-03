package com.practice.weather.midTerm.ocean.repository;

import java.time.LocalDateTime;

public interface MidTermOceanRepositoryCustom {

    // 데이터 중복 확인
    boolean isExist(String regId, LocalDateTime localDateTime);
}
