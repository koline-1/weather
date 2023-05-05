package com.practice.weather.shortTerm.extraExpectation.service;

import com.practice.weather.shortTerm.extraExpectation.dto.ShortTermExtraExpectationDto;
import org.json.JSONArray;

import java.util.List;

public interface ShortTermExtraExpectationService {

    public List<ShortTermExtraExpectationDto> parseJsonArrayToShortTermExtraExpectationDto(JSONArray jArray, String version);
}
