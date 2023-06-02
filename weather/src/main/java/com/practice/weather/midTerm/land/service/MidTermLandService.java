package com.practice.weather.midTerm.land.service;

import com.practice.weather.midTerm.land.dto.MidTermLandDto;

import java.util.HashMap;

public interface MidTermLandService {

    // Map 형태의 데이터를 DTO 객체로 변환
    MidTermLandDto parseMapToMidTermLandDto(HashMap<String, String> data);

}
