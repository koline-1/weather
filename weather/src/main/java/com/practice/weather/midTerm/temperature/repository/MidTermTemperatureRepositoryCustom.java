package com.practice.weather.midTerm.temperature.repository;

import java.time.LocalDateTime;

public interface MidTermTemperatureRepositoryCustom {

    // 데이터 중복 확인
    boolean isExist(String regId, LocalDateTime localDateTime);

}
