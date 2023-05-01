package com.practice.weather.midTerm.land.repository;

import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidTermLandRepository extends JpaRepository<MidTermLandEntity, Integer>, MidTermLandRepositoryCustom {

}
