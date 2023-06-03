package com.practice.weather.shortTerm.extra.repository;

import com.practice.weather.shortTerm.extra.entity.QShortTermExtraEntity;
import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShortTermExtraRepositoryCustomImpl implements ShortTermExtraRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExist(ShortTermExtraDto dto) {
        return queryFactory
                .select(QShortTermExtraEntity.shortTermExtraEntity.id).from(QShortTermExtraEntity.shortTermExtraEntity)
                .where(
                        QShortTermExtraEntity.shortTermExtraEntity.version.eq(dto.getVersion())
                        .and(QShortTermExtraEntity.shortTermExtraEntity.forecastDate.eq(dto.getForecastDate()))
                        .and(QShortTermExtraEntity.shortTermExtraEntity.forecastTime.eq(dto.getForecastTime()))
                        .and(QShortTermExtraEntity.shortTermExtraEntity.nxValue.eq(dto.getNxValue()))
                        .and(QShortTermExtraEntity.shortTermExtraEntity.nyValue.eq(dto.getNyValue()))
                ).fetch().size() > 0;
    }
}
