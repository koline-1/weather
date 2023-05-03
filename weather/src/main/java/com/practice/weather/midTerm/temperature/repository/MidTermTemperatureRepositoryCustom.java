package com.practice.weather.midTerm.temperature.repository;

import java.time.LocalDateTime;

public interface MidTermTemperatureRepositoryCustom {

    public boolean isExist(String regId, LocalDateTime localDateTime);

}
