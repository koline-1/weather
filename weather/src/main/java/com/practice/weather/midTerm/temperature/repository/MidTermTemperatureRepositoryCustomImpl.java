package com.practice.weather.midTerm.temperature.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.practice.weather.midTerm.temperature.entity.QMidTermTemperatureEntity.midTermTemperatureEntity;

@Repository
@RequiredArgsConstructor
public class MidTermTemperatureRepositoryCustomImpl implements MidTermTemperatureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public boolean isExist(String regId, LocalDateTime localDateTime) {

        return queryFactory
                .select(midTermTemperatureEntity.id).from(midTermTemperatureEntity)
                .where(
                        midTermTemperatureEntity.regId.eq(regId)
                        .and(midTermTemperatureEntity.date.goe(localDateTime))
                ).fetch().size() > 0;
    }
}
