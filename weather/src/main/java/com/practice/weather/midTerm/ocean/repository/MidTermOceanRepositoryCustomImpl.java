package com.practice.weather.midTerm.ocean.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.practice.weather.midTerm.ocean.entity.QMidTermOceanEntity.midTermOceanEntity;

@Repository
@RequiredArgsConstructor
public class MidTermOceanRepositoryCustomImpl implements MidTermOceanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean isExist(String regId, LocalDateTime localDateTime) {

        return queryFactory
                .select(midTermOceanEntity.id).from(midTermOceanEntity)
                .where(
                        midTermOceanEntity.regId.eq(regId)
                        .and(midTermOceanEntity.date.goe(localDateTime))
                ).fetch().size() > 0;
    }

}
