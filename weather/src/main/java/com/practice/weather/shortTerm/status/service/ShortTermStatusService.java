package com.practice.weather.shortTerm.status.service;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import org.json.JSONArray;

public interface ShortTermStatusService {

    // Map 형태의 데이터를 DTO 객체로 변환
    ShortTermStatusDto parseJsonArrayToShortTermStatusDto(JSONArray jArray, String version);
}
