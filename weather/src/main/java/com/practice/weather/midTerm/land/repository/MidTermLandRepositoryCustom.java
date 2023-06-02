package com.practice.weather.midTerm.land.repository;

import java.time.LocalDateTime;

public interface MidTermLandRepositoryCustom {

    // 데이터 중복 확인
    boolean isExist(String regId, LocalDateTime localDateTime);

}
