package com.practice.weather.midTerm.ocean.repository;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidTermOceanRepository extends JpaRepository<MidTermOceanEntity, Integer>, MidTermOceanRepositoryCustom {

}
