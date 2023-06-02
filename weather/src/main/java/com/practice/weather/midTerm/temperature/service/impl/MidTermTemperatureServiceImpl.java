package com.practice.weather.midTerm.temperature.service.impl;

import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import com.practice.weather.midTerm.temperature.service.MidTermTemperatureService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MidTermTemperatureServiceImpl implements MidTermTemperatureService {


    @Override
    public MidTermTemperatureDto parseMapToMidTermTemperatureDto(HashMap<String, String> map) {

        MidTermTemperatureDto midTermTemperatureDto = new MidTermTemperatureDto();

        midTermTemperatureDto.setRegId(map.get("regId"));
        midTermTemperatureDto.setTaMin3(map.get("taMin3"));
        midTermTemperatureDto.setTaMin3Low(map.get("taMin3Low"));
        midTermTemperatureDto.setTaMin3High(map.get("taMin3High"));
        midTermTemperatureDto.setTaMax3(map.get("taMax3"));
        midTermTemperatureDto.setTaMax3Low(map.get("taMax3Low"));
        midTermTemperatureDto.setTaMax3High(map.get("taMax3High"));
        midTermTemperatureDto.setTaMin4(map.get("taMin4"));
        midTermTemperatureDto.setTaMin4Low(map.get("taMin4Low"));
        midTermTemperatureDto.setTaMin4High(map.get("taMin4High"));
        midTermTemperatureDto.setTaMax4(map.get("taMax4"));
        midTermTemperatureDto.setTaMax4Low(map.get("taMax4Low"));
        midTermTemperatureDto.setTaMax4High(map.get("taMax4High"));
        midTermTemperatureDto.setTaMin5(map.get("taMin5"));
        midTermTemperatureDto.setTaMin5Low(map.get("taMin5Low"));
        midTermTemperatureDto.setTaMin5High(map.get("taMin5High"));
        midTermTemperatureDto.setTaMax5(map.get("taMax5"));
        midTermTemperatureDto.setTaMax5Low(map.get("taMax5Low"));
        midTermTemperatureDto.setTaMax5High(map.get("taMax5High"));
        midTermTemperatureDto.setTaMin6(map.get("taMin6"));
        midTermTemperatureDto.setTaMin6Low(map.get("taMin6Low"));
        midTermTemperatureDto.setTaMin6High(map.get("taMin6High"));
        midTermTemperatureDto.setTaMax6(map.get("taMax6"));
        midTermTemperatureDto.setTaMax6Low(map.get("taMax6Low"));
        midTermTemperatureDto.setTaMax6High(map.get("taMax6High"));
        midTermTemperatureDto.setTaMin7(map.get("taMin7"));
        midTermTemperatureDto.setTaMin7Low(map.get("taMin7Low"));
        midTermTemperatureDto.setTaMin7High(map.get("taMin7High"));
        midTermTemperatureDto.setTaMax7(map.get("taMax7"));
        midTermTemperatureDto.setTaMax7Low(map.get("taMax7Low"));
        midTermTemperatureDto.setTaMax7High(map.get("taMax7High"));
        midTermTemperatureDto.setTaMin8(map.get("taMin8"));
        midTermTemperatureDto.setTaMin8Low(map.get("taMin8Low"));
        midTermTemperatureDto.setTaMin8High(map.get("taMin8High"));
        midTermTemperatureDto.setTaMax8(map.get("taMax8"));
        midTermTemperatureDto.setTaMax8Low(map.get("taMax8Low"));
        midTermTemperatureDto.setTaMax8High(map.get("taMax8High"));
        midTermTemperatureDto.setTaMin9(map.get("taMin9"));
        midTermTemperatureDto.setTaMin9Low(map.get("taMin9Low"));
        midTermTemperatureDto.setTaMin9High(map.get("taMin9High"));
        midTermTemperatureDto.setTaMax9(map.get("taMax9"));
        midTermTemperatureDto.setTaMax9Low(map.get("taMax9Low"));
        midTermTemperatureDto.setTaMax9High(map.get("taMax9High"));
        midTermTemperatureDto.setTaMin10(map.get("taMin10"));
        midTermTemperatureDto.setTaMin10Low(map.get("taMin10Low"));
        midTermTemperatureDto.setTaMin10High(map.get("taMin10High"));
        midTermTemperatureDto.setTaMax10(map.get("taMax10"));
        midTermTemperatureDto.setTaMax10Low(map.get("taMax10Low"));
        midTermTemperatureDto.setTaMax10High(map.get("taMax10High"));

        return midTermTemperatureDto;

    }

}
