package com.practice.weather.midTerm.ocean.service;

import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;

import java.util.HashMap;

public interface MidTermOceanService {

    // Map 형태의 데이터를 DTO 객체로 변환
    MidTermOceanDto parseMapToMidTermOceanDto(HashMap<String, String> map);

}