package com.practice.weather.service;

import com.practice.weather.entity.MidTermOceanEntity;
import com.practice.weather.weatherDto.MidTermOceanDto;

public interface MidTermOceanService {

    public MidTermOceanEntity save(MidTermOceanDto midTermOceanDto);

    public MidTermOceanDto buildMidTermOceanDto(String str);

}