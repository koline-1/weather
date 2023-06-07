package com.practice.weather.midTerm.temperature.repository;

import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidTermTemperatureRepository extends JpaRepository<MidTermTemperatureEntity, Long>, MidTermTemperatureRepositoryCustom {

    @Query("SELECT e FROM MidTermTemperatureEntity e WHERE e.id = :id")
    MidTermTemperatureEntity selectById(Long id);

    @Query("SELECT e FROM MidTermTemperatureEntity e ORDER BY e.id DESC")
    List<MidTermTemperatureEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM MidTermTemperatureEntity e WHERE e.regId = :location ORDER BY e.id DESC")
    List<MidTermTemperatureEntity> selectListByLocation(Pageable pageable, String location);

    @Query(value = "SELECT COUNT(1) FROM MID_TERM_TEMPERATURE WHERE REG_ID = :location", nativeQuery = true)
    long countByLocation(String location);

}
