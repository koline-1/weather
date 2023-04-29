package com.practice.weather.service;

import com.practice.weather.entity.MidTermOceanEntity;
import com.practice.weather.weatherDto.MidTermOceanDto;

public interface MidTermOceanService {

    public MidTermOceanEntity saveData(String id, String data);

    public MidTermOceanDto buildMidTermOceanDto(String str, String date);

}
