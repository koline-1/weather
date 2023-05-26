package com.practice.weather.midTerm.ocean.service;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermOceanService {

    // Map형태의 데이터를 DTO 객체로 변환
    public MidTermOceanDto parseMapToMidTermOceanDto(HashMap<String, String> map);

}