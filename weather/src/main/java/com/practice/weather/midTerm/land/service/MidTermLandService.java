package com.practice.weather.midTerm.land.service;

import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface MidTermLandService {

    public MidTermLandDto parseMapToMidTermLandDto(HashMap<String, String> data);

}
