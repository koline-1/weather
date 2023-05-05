package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;

public interface ShortTermStatusRepositoryCustom {

    public boolean isExist(ShortTermStatusDto dto);

}
