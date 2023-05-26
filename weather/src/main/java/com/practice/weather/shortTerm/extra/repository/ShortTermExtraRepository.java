package com.practice.weather.shortTerm.extra.repository;

import com.practice.weather.shortTerm.extra.entity.ShortTermExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortTermExtraRepository extends JpaRepository<ShortTermExtraEntity, Long>, ShortTermExtraRepositoryCustom {

    @Query("SELECT e FROM ShortTermExtraEntity e WHERE e.id = :id")
    ShortTermExtraEntity selectById(Long id);
}
