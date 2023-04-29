package com.practice.weather.weatherRepository;

import com.practice.weather.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer>, WeatherRepositoryCustom {
    Optional<WeatherEntity> findById(long id);
}
