package com.practice.weather.midTerm.ocean.repository;

import java.time.LocalDateTime;

public interface MidTermOceanRepositoryCustom {

    public boolean existCheck(String regId, LocalDateTime localDateTime);
}
