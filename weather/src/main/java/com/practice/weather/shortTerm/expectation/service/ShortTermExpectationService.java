package com.practice.weather.shortTerm.expectation.service;

import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import org.json.JSONArray;

import java.util.List;

public interface ShortTermExpectationService {

    public List<ShortTermExpectationDto> parseJsonArrayToShortTermExpectationDto(JSONArray jArray, String version);
    
}
