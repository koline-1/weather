package com.practice.weather.midTerm.expectation.repository;

import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidTermExpectationRepository extends JpaRepository<MidTermExpectationEntity, Integer>, MidTermExpectationRepositoryCustom {

}
