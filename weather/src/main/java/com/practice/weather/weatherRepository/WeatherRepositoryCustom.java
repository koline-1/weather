package com.practice.weather.weatherRepository;

import com.practice.weather.entity.WeatherEntity;

public interface WeatherRepositoryCustom {
    WeatherEntity weatherReadById(String id);

}
