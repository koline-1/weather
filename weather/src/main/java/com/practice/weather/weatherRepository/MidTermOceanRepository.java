package com.practice.weather.weatherRepository;

import com.practice.weather.entity.MidTermOceanEntity;
import com.practice.weather.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MidTermOceanRepository extends JpaRepository<MidTermOceanEntity, Integer>, WeatherRepositoryCustom {

}
