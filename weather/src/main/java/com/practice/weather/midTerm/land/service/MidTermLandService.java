package com.practice.weather.midTerm.land.service;

import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;

import java.time.LocalDateTime;

public interface MidTermLandService {
    
    public boolean existCheck(String regId, LocalDateTime localDateTime);

    public MidTermLandEntity save(MidTermLandDto midTermLandDto);

    public MidTermLandDto buildMidTermLandDto(String str);
}
