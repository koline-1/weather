package com.practice.weather.shortTerm.extra.repository;

import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.practice.weather.shortTerm.extra.entity.QShortTermExtraEntity.shortTermExtraEntity;

@Repository
@RequiredArgsConstructor
public class ShortTermExtraRepositoryCustomImpl implements ShortTermExtraRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExist(ShortTermExtraDto dto) {
        return queryFactory
                .select(shortTermExtraEntity.shortTermExtraEntity.id).from(shortTermExtraEntity.shortTermExtraEntity)
                .where(
                        shortTermExtraEntity.shortTermExtraEntity.version.eq(dto.getVersion())
                        .and(shortTermExtraEntity.shortTermExtraEntity.forecastDate.eq(dto.getForecastDate()))
                        .and(shortTermExtraEntity.shortTermExtraEntity.forecastTime.eq(dto.getForecastTime()))
                        .and(shortTermExtraEntity.shortTermExtraEntity.nxValue.eq(dto.getNxValue()))
                        .and(shortTermExtraEntity.shortTermExtraEntity.nyValue.eq(dto.getNyValue()))
                ).fetch().size() > 0;
    }
}
