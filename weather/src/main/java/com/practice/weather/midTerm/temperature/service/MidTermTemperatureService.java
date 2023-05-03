package com.practice.weather.midTerm.temperature.service;

import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermTemperatureService {

    public MidTermTemperatureDto parseMapToMidTermTemperatureDto(HashMap<String, String> map);

}
