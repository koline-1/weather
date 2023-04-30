package com.practice.weather.midTerm.temperature.repository;

import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidTermTemperatureRepository extends JpaRepository<MidTermTemperatureEntity, Integer>, MidTermTemperatureRepositoryCustom {

}
