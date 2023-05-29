package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShortTermStatusRepository extends JpaRepository<ShortTermStatusEntity,Long>, ShortTermStatusRepositoryCustom {

    @Query("SELECT e FROM ShortTermStatusEntity e WHERE e.id = :id")
    ShortTermStatusEntity selectById(Long id);

    @Query("SELECT e FROM ShortTermStatusEntity e ORDER BY e.id DESC")
    List<ShortTermStatusEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM ShortTermStatusEntity e WHERE e.nxValue = :nxValue AND e.nyValue = :nyValue ORDER BY e.id DESC")
    List<ShortTermStatusEntity> selectListByLocation(Pageable pageable, String nxValue, String nyValue);
}
