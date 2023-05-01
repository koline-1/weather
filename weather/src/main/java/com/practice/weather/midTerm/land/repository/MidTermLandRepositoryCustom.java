package com.practice.weather.midTerm.land.repository;

import java.time.LocalDateTime;

public interface MidTermLandRepositoryCustom {

    public boolean existCheck(String regId, LocalDateTime localDateTime);

}
