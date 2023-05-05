package com.practice.weather.shortTerm.expectation.repository;

import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortTermExpectationRepository extends JpaRepository<ShortTermExpectationEntity, Long>, ShortTermExpectationRepositoryCustom {

}