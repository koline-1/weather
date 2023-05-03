package com.practice.weather.midTerm.ocean.service;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermOceanService {

    public MidTermOceanDto parseMapToMidTermOceanDto(HashMap<String, String> map);

}