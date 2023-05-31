package com.practice.weather.shortTerm.status.service;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import org.json.JSONArray;

import java.util.HashMap;

public interface ShortTermStatusService {

    // Map형태의 데이터를 DTO 객체로 변환
    public ShortTermStatusDto parseJsonArrayToShortTermStatusDto(JSONArray jArray, String version);
}
