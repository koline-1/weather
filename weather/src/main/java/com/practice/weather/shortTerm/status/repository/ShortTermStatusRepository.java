package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortTermStatusRepository extends JpaRepository<ShortTermStatusEntity,Long>, ShortTermStatusRepositoryCustom {

    @Query("SELECT e FROM ShortTermStatusEntity e WHERE e.id = :id")
    ShortTermStatusEntity selectById(Long id);
}
