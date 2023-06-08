package com.practice.weather.midTerm.ocean.repository;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidTermOceanRepository extends JpaRepository<MidTermOceanEntity, Long>, MidTermOceanRepositoryCustom {

    @Query("SELECT e FROM MidTermOceanEntity e WHERE e.id = :id")
    MidTermOceanEntity selectById(Long id);

    @Query("SELECT e FROM MidTermOceanEntity e ORDER BY e.id DESC")
    List<MidTermOceanEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM MidTermOceanEntity e WHERE e.regId = :location ORDER BY e.id DESC")
    List<MidTermOceanEntity> selectListByLocation(Pageable pageable, String location);

    @Query(value = "SELECT COUNT(1) FROM MID_TERM_OCEAN WHERE REG_ID = :location", nativeQuery = true)
    int countByLocation(String location);
    
}
