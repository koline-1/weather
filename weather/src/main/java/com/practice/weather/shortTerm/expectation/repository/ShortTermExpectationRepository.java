package com.practice.weather.shortTerm.expectation.repository;

import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShortTermExpectationRepository extends JpaRepository<ShortTermExpectationEntity, Long>, ShortTermExpectationRepositoryCustom {

    @Query("SELECT e FROM ShortTermExpectationEntity e ORDER BY e.id DESC")
    List<ShortTermExpectationEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM ShortTermExpectationEntity e WHERE e.nxValue = :nxValue AND e.nyValue = :nyValue ORDER BY e.id DESC")
    List<ShortTermExpectationEntity> selectListByLocation(Pageable pageable, String nxValue, String nyValue);

    @Query(value = "SELECT COUNT(1) FROM SHORT_TERM_EXPECTATION WHERE NX_VALUE = :nxValue AND NY_VALUE = :nyValue", nativeQuery = true)
    long countByLocation(String nxValue, String nyValue);

}