package com.practice.weather.midTerm.land.service.impl;

import com.practice.weather.midTerm.land.dto.MidTermLandDto;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
import com.practice.weather.midTerm.land.service.MidTermLandService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

@Service
public class MidTermLandServiceImpl implements MidTermLandService {

    @Autowired
    MidTermLandRepository midTermLandRepository;

    @Override
    public MidTermLandDto parseMapToMidTermLandDto(HashMap<String, String> map) {

        MidTermLandDto midTermLandDto = new MidTermLandDto();

        midTermLandDto.setRegId(map.get("regId"));
        midTermLandDto.setRnSt3Am(map.get("rnSt3Am"));
        midTermLandDto.setRnSt3Pm(map.get("rnSt3Pm"));
        midTermLandDto.setRnSt4Am(map.get("rnSt4Am"));
        midTermLandDto.setRnSt4Pm(map.get("rnSt4Pm"));
        midTermLandDto.setRnSt5Am(map.get("rnSt5Am"));
        midTermLandDto.setRnSt5Pm(map.get("rnSt5Pm"));
        midTermLandDto.setRnSt6Am(map.get("rnSt6Am"));
        midTermLandDto.setRnSt6Pm(map.get("rnSt6Pm"));
        midTermLandDto.setRnSt7Am(map.get("rnSt7Am"));
        midTermLandDto.setRnSt7Pm(map.get("rnSt7Pm"));
        midTermLandDto.setRnSt8(map.get("rnSt8"));
        midTermLandDto.setRnSt9(map.get("rnSt9"));
        midTermLandDto.setRnSt10(map.get("rnSt10"));
        midTermLandDto.setWf3Am(map.get("wf3Am"));
        midTermLandDto.setWf3Pm(map.get("wf3Pm"));
        midTermLandDto.setWf4Am(map.get("wf4Am"));
        midTermLandDto.setWf4Pm(map.get("wf4Pm"));
        midTermLandDto.setWf5Am(map.get("wf5Am"));
        midTermLandDto.setWf5Pm(map.get("wf5Pm"));
        midTermLandDto.setWf6Am(map.get("wf6Am"));
        midTermLandDto.setWf6Pm(map.get("wf6Pm"));
        midTermLandDto.setWf7Am(map.get("wf7Am"));
        midTermLandDto.setWf7Pm(map.get("wf7Pm"));
        midTermLandDto.setWf8(map.get("wf8"));
        midTermLandDto.setWf9(map.get("wf9"));
        midTermLandDto.setWf10(map.get("wf10"));

        return midTermLandDto;

    }

}