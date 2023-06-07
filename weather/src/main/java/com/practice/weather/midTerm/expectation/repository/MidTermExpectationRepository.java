package com.practice.weather.midTerm.expectation.repository;

import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidTermExpectationRepository extends JpaRepository<MidTermExpectationEntity, Long>, MidTermExpectationRepositoryCustom {

    @Query("SELECT e FROM MidTermExpectationEntity e WHERE e.id = :id")
    MidTermExpectationEntity selectById(Long id);

    @Query("SELECT e FROM MidTermExpectationEntity e ORDER BY e.id DESC")
    List<MidTermExpectationEntity> selectList(Pageable pageable);

    @Query("SELECT e FROM MidTermExpectationEntity e WHERE e.stnId = :location ORDER BY e.id DESC")
    List<MidTermExpectationEntity> selectListByLocation(Pageable pageable, String location);

    @Query(value = "SELECT COUNT(1) FROM MID_TERM_EXPECTATION WHERE STN_ID = :location", nativeQuery = true)
    long countByLocation(String location);

}
