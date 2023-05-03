package com.practice.weather.midTerm.expectation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.practice.weather.midTerm.expectation.entity.QMidTermExpectationEntity.midTermExpectationEntity;

@Repository
@RequiredArgsConstructor
public class MidTermExpectationRepositoryCustomImpl implements MidTermExpectationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean isExist(String stnId, LocalDateTime localDateTime) {

        return queryFactory
                .select(midTermExpectationEntity.id).from(midTermExpectationEntity)
                .where(
                        midTermExpectationEntity.stnId.eq(stnId)
                        .and(midTermExpectationEntity.date.goe(localDateTime))
                ).fetch().size() > 0;
    }
}
