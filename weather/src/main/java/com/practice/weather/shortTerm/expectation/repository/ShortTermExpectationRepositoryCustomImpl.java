package com.practice.weather.shortTerm.expectation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.practice.weather.shortTerm.expectation.entity.QShortTermExpectationEntity.shortTermExpectationEntity;

@Repository
@RequiredArgsConstructor
public class ShortTermExpectationRepositoryCustomImpl implements ShortTermExpectationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean isExist(String baseDate, String baseTime) {

        return queryFactory
                .select(shortTermExpectationEntity.id).from(shortTermExpectationEntity)
                .where(
                        shortTermExpectationEntity.baseDate.eq(baseDate)
                        .and(shortTermExpectationEntity.baseTime.eq(baseTime))
                ).fetch().size() > 0;
    }

}
