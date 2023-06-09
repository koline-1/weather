package com.practice.weather.midTerm.land.repository;

import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidTermLandRepository extends JpaRepository<MidTermLandEntity, Long>, MidTermLandRepositoryCustom {

    @Query("SELECT e FROM MidTermLandEntity e ORDER BY e.id DESC")
    List<MidTermLandEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM MidTermLandEntity e WHERE e.regId = :location ORDER BY e.id DESC")
    List<MidTermLandEntity> selectListByLocation(Pageable pageable, String location);

    @Query(value = "SELECT COUNT(1) FROM MID_TERM_LAND WHERE REG_ID = :location", nativeQuery = true)
    long countByLocation(String location);
    
}
