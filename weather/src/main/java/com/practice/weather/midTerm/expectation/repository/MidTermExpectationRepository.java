package com.practice.weather.midTerm.expectation.repository;

import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MidTermExpectationRepository extends JpaRepository<MidTermExpectationEntity, Long>, MidTermExpectationRepositoryCustom {

    @Query("SELECT e FROM MidTermExpectationEntity e WHERE e.id = :id")
    MidTermExpectationEntity selectById(Long id);

}
