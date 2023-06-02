package com.practice.weather.midTerm.temperature.service;

import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;

import java.util.HashMap;

public interface MidTermTemperatureService {

    // Map 형태의 데이터를 DTO 객체로 변환
    MidTermTemperatureDto parseMapToMidTermTemperatureDto(HashMap<String, String> map);

}
