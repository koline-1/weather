package com.practice.weather.shortTerm.extra.repository;

import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;

public interface ShortTermExtraRepositoryCustom {

    // 데이터 중복 확인
    boolean isExist(ShortTermExtraDto dto);
}
