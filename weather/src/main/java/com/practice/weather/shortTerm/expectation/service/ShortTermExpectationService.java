package com.practice.weather.shortTerm.expectation.service;

import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import org.json.JSONArray;

import java.util.List;

public interface ShortTermExpectationService {

    public List<ShortTermExpectationDto> parseMapToShortTermExpectationDto(JSONArray jArray);
    
}
