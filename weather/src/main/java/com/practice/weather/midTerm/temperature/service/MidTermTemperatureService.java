package com.practice.weather.midTerm.temperature.service;

import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermTemperatureService {

    // Map형태의 데이터를 DTO 객체로 변환
    public MidTermTemperatureDto parseMapToMidTermTemperatureDto(HashMap<String, String> map);

}
