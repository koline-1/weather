package com.practice.weather.midTerm.temperature.repository;

import java.time.LocalDateTime;

public interface MidTermTemperatureRepositoryCustom {

    public boolean existCheck(String regId, LocalDateTime localDateTime);

}
