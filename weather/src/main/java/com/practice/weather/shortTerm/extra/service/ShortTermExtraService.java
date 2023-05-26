package com.practice.weather.shortTerm.extra.service;

import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import org.json.JSONArray;

import java.util.List;

public interface ShortTermExtraService {

    // Map형태의 데이터를 DTO 객체 List 로 변환
    public List<ShortTermExtraDto> parseJsonArrayToShortTermExtraDto(JSONArray jArray, String version);
}
