package com.practice.weather.weatherRepository;

import com.practice.weather.entity.WeatherEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.practice.weather.entity.QWeatherEntity.weatherEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest
@Transactional
public class QueryDslTest {
    @Autowired
    private EntityManager em;

    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void createTest() {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    // QueryDSL Test
    @Test
    void useQueryDSLTest() {
        String id = "midTermLand";
        WeatherEntity weather = jpaQueryFactory.selectFrom(weatherEntity).where(weatherEntity.id.like(id)).fetchFirst();

        assertThat(weather.getId(), is(notNullValue()));
    }
}
