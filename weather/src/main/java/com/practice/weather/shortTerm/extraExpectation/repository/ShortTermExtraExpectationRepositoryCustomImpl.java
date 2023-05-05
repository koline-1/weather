package com.practice.weather.shortTerm.extraExpectation.repository;

import com.practice.weather.shortTerm.extraExpectation.dto.ShortTermExtraExpectationDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.practice.weather.shortTerm.extraExpectation.entity.QShortTermExtraExpectationEntity.shortTermExtraExpectationEntity;

@Repository
@RequiredArgsConstructor
public class ShortTermExtraExpectationRepositoryCustomImpl implements ShortTermExtraExpectationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExist(ShortTermExtraExpectationDto dto) {
        return queryFactory
                .select(shortTermExtraExpectationEntity.id).from(shortTermExtraExpectationEntity)
                .where(
                        shortTermExtraExpectationEntity.baseDate.eq(dto.getBaseDate())
                        .and(shortTermExtraExpectationEntity.baseTime.eq(dto.getBaseTime()))
                        .and(shortTermExtraExpectationEntity.forecastDate.eq(dto.getForecastDate()))
                        .and(shortTermExtraExpectationEntity.forecastTime.eq(dto.getForecastTime()))
                        .and(shortTermExtraExpectationEntity.nxValue.eq(dto.getNxValue()))
                        .and(shortTermExtraExpectationEntity.nyValue.eq(dto.getNyValue()))
                ).fetch().size() > 0;
    }
}
