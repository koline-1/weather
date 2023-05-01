package com.practice.weather.midTerm.land.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.practice.weather.midTerm.land.entity.QMidTermLandEntity.midTermLandEntity;

@Repository
@RequiredArgsConstructor
public class MidTermLandRepositoryCustomImpl implements MidTermLandRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean existCheck(String regId, LocalDateTime localDateTime) {

        return queryFactory
                .select(midTermLandEntity.id).from(midTermLandEntity)
                .where(
                        midTermLandEntity.regId.eq(regId)
                        .and(midTermLandEntity.date.goe(localDateTime))
                ).fetch().size() > 0;
    }
}
