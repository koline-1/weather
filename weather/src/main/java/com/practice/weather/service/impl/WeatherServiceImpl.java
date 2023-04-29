package com.practice.weather.service.impl;

import com.practice.weather.entity.WeatherEntity;
import com.practice.weather.service.WeatherService;
import com.practice.weather.weatherRepository.WeatherRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public WeatherEntity saveData(String id, String data) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setId(id);
        weatherEntity.setData(data);
        WeatherEntity saveResult = weatherRepository.save(weatherEntity);
        return saveResult;
    }


}
