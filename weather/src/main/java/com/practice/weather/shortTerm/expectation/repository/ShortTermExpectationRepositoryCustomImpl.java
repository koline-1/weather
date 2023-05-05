package com.practice.weather.shortTerm.expectation.repository;

import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.practice.weather.shortTerm.expectation.entity.QShortTermExpectationEntity.shortTermExpectationEntity;

@Repository
@RequiredArgsConstructor
public class ShortTermExpectationRepositoryCustomImpl implements ShortTermExpectationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean isExist(ShortTermExpectationDto dto) {

        return queryFactory
                .select(shortTermExpectationEntity.id).from(shortTermExpectationEntity)
                .where(
                        shortTermExpectationEntity.baseDate.eq(dto.getBaseDate())
                        .and(shortTermExpectationEntity.baseTime.eq(dto.getBaseTime()))
                        .and(shortTermExpectationEntity.forecastDate.eq(dto.getForecastDate()))
                        .and(shortTermExpectationEntity.forecastTime.eq(dto.getForecastTime()))
                        .and(shortTermExpectationEntity.nxValue.eq(dto.getNxValue()))
                        .and(shortTermExpectationEntity.nyValue.eq(dto.getNyValue()))
                ).fetch().size() > 0;
    }

}
