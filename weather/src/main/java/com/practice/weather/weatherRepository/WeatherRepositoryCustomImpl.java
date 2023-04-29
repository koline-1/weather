package com.practice.weather.weatherRepository;

import com.practice.weather.entity.WeatherEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.practice.weather.entity.QWeatherEntity.weatherEntity;

@Repository
@RequiredArgsConstructor
public class WeatherRepositoryCustomImpl implements WeatherRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public WeatherEntity weatherReadById(String id) {
        return jpaQueryFactory.selectFrom(weatherEntity).where(weatherEntity.id.like(id)).fetchFirst();
    }

}
