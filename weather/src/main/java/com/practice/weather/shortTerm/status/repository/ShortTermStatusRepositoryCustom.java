package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;

public interface ShortTermStatusRepositoryCustom {

    // 데이터 중복 확인
    boolean isExist(ShortTermStatusDto dto);

}
