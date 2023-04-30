package com.practice.weather.midTerm.temperature.service;

import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;

import java.time.LocalDateTime;

public interface MidTermTemperatureService {

    public boolean existCheck(String regId, LocalDateTime localDateTime);

    public MidTermTemperatureEntity save(MidTermTemperatureDto midTermTemperatureDto);

    public MidTermTemperatureDto buildMidTermTemperatureDto(String str);

}
