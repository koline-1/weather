package com.practice.weather.shortTerm.expectation.repository;

import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortTermExpectationRepository extends JpaRepository<ShortTermExpectationEntity, Long>, ShortTermExpectationRepositoryCustom {

    @Query("SELECT e FROM ShortTermExpectationEntity e WHERE e.id = :id")
    ShortTermExpectationEntity selectById(Long id);
}