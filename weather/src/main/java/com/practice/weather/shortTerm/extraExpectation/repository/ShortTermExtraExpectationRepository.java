package com.practice.weather.shortTerm.extraExpectation.repository;

import com.practice.weather.shortTerm.extraExpectation.entity.ShortTermExtraExpectationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortTermExtraExpectationRepository extends JpaRepository<ShortTermExtraExpectationEntity, Long>, ShortTermExtraExpectationRepositoryCustom {

}
