package com.practice.weather.midTerm.land.repository;

import java.time.LocalDateTime;

public interface MidTermLandRepositoryCustom {

    public boolean isExist(String regId, LocalDateTime localDateTime);

}
