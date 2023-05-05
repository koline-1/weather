package com.practice.weather.shortTerm.status.service;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import org.json.JSONArray;

import java.util.HashMap;

public interface ShortTermStatusService {

    public ShortTermStatusDto parseJsonArrayToShortTermStatusDto(JSONArray jArray, String version);
}
