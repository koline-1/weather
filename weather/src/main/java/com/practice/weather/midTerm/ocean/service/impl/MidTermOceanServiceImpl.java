package com.practice.weather.midTerm.ocean.service.impl;

import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;
import com.practice.weather.midTerm.ocean.service.MidTermOceanService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MidTermOceanServiceImpl implements MidTermOceanService {

    @Override
    public MidTermOceanDto parseMapToMidTermOceanDto(HashMap<String, String> map) {

        MidTermOceanDto midTermOceanDto = new MidTermOceanDto();

        midTermOceanDto.setRegId(map.get("regId"));
        midTermOceanDto.setWf8(map.get("wf8"));
        midTermOceanDto.setWh4AAm(map.get("wh4AAm"));
        midTermOceanDto.setWh4BAm(map.get("wh4BAm"));
        midTermOceanDto.setWh6BAm(map.get("wh6BAm"));
        midTermOceanDto.setWf9(map.get("wf9"));
        midTermOceanDto.setWh6AAm(map.get("wh6AAm"));
        midTermOceanDto.setWh9A(map.get("wh9A"));
        midTermOceanDto.setWh9B(map.get("wh9B"));
        midTermOceanDto.setWf6Pm(map.get("wf6Pm"));
        midTermOceanDto.setWf7Pm(map.get("wf7Pm"));
        midTermOceanDto.setWf10(map.get("wf10"));
        midTermOceanDto.setWh4APm(map.get("wh4APm"));
        midTermOceanDto.setWh6BPm(map.get("wh6BPm"));
        midTermOceanDto.setWh6APm(map.get("wh6APm"));
        midTermOceanDto.setWf3Pm(map.get("wf3Pm"));
        midTermOceanDto.setWf4Pm(map.get("wf4Pm"));
        midTermOceanDto.setWh4BPm(map.get("wh4BPm"));
        midTermOceanDto.setWf5Pm(map.get("wf5Pm"));
        midTermOceanDto.setWh7AAm(map.get("wh7AAm"));
        midTermOceanDto.setWf7Am(map.get("wf7Am"));
        midTermOceanDto.setWh3AAm(map.get("wh3AAm"));
        midTermOceanDto.setWh5BAm(map.get("wh5BAm"));
        midTermOceanDto.setWh5AAm(map.get("wh5AAm"));
        midTermOceanDto.setWf3Am(map.get("wf3Am"));
        midTermOceanDto.setWh7BAm(map.get("wh7BAm"));
        midTermOceanDto.setWh8B(map.get("wh8B"));
        midTermOceanDto.setWf4Am(map.get("wf4Am"));
        midTermOceanDto.setWh8A(map.get("wh8A"));
        midTermOceanDto.setWh10A(map.get("wh10A"));
        midTermOceanDto.setWf5Am(map.get("wf5Am"));
        midTermOceanDto.setWh3BAm(map.get("wh3BAm"));
        midTermOceanDto.setWh10B(map.get("wh10B"));
        midTermOceanDto.setWf6Am(map.get("wf6Am"));
        midTermOceanDto.setWh5BPm(map.get("wh5BPm"));
        midTermOceanDto.setWh5APm(map.get("wh5APm"));
        midTermOceanDto.setWh7APm(map.get("wh7APm"));
        midTermOceanDto.setWh7BPm(map.get("wh7BPm"));
        midTermOceanDto.setWh3APm(map.get("wh3APm"));
        midTermOceanDto.setWh3BPm(map.get("wh3BPm"));

        return midTermOceanDto;
    }

}
