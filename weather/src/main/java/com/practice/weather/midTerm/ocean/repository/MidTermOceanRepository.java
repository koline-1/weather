package com.practice.weather.midTerm.ocean.repository;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MidTermOceanRepository extends JpaRepository<MidTermOceanEntity, Long>, MidTermOceanRepositoryCustom {

    @Query("SELECT e FROM MidTermOceanEntity e WHERE e.id = :id")
    MidTermOceanEntity selectById(Long id);
}
