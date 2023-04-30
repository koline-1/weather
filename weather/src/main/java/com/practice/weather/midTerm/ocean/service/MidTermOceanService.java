package com.practice.weather.midTerm.ocean.service;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;

import java.time.LocalDateTime;

public interface MidTermOceanService {

    public boolean existCheck(String regId, LocalDateTime localDateTime);

    public MidTermOceanEntity save(MidTermOceanDto midTermOceanDto);

    public MidTermOceanDto buildMidTermOceanDto(String str);

}