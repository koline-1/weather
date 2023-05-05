package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortTermStatusRepository extends JpaRepository<ShortTermStatusEntity,Long>, ShortTermStatusRepositoryCustom {

}
