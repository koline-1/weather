package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShortTermStatusRepository extends JpaRepository<ShortTermStatusEntity,Long>, ShortTermStatusRepositoryCustom {

    @Query("SELECT e FROM ShortTermStatusEntity e ORDER BY e.id DESC")
    List<ShortTermStatusEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM ShortTermStatusEntity e WHERE e.nxValue = :nxValue AND e.nyValue = :nyValue ORDER BY e.id DESC")
    List<ShortTermStatusEntity> selectListByLocation(Pageable pageable, @Param("nxValue") String nxValue, @Param("nyValue") String nyValue);

    @Query(value = "SELECT COUNT(1) FROM SHORT_TERM_STATUS WHERE NX_VALUE = :nxValue AND NY_VALUE = :nyValue", nativeQuery = true)
    long countByLocation(@Param("nxValue") String nxValue, @Param("nyValue") String nyValue);

}
