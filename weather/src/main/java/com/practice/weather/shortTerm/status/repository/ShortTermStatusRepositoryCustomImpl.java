package com.practice.weather.shortTerm.status.repository;

import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.practice.weather.shortTerm.status.entity.QShortTermStatusEntity.shortTermStatusEntity;

@Repository
@RequiredArgsConstructor
public class ShortTermStatusRepositoryCustomImpl implements ShortTermStatusRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean isExist(ShortTermStatusDto dto) {

        return queryFactory
                .select(shortTermStatusEntity.id).from(shortTermStatusEntity)
                .where(
                        shortTermStatusEntity.baseDate.eq(dto.getBaseDate())
                        .and(shortTermStatusEntity.baseTime.eq(dto.getBaseTime()))
                        .and(shortTermStatusEntity.nxValue.eq(dto.getNxValue()))
                        .and(shortTermStatusEntity.nyValue.eq(dto.getNyValue()))
                ).fetch().size() > 0;
    }
}
