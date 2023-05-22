package com.practice.weather.shortTerm.expectation;

import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import com.practice.weather.utility.Utility;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static com.practice.weather.shortTerm.expectation.entity.QShortTermExpectationEntity.shortTermExpectationEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShortTermExpectationRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        ShortTermExpectationEntity entity =   ShortTermExpectationEntity.builder().version("version")
                .forecastDate("forecastDate").forecastTime("forecastTime").nxValue("nxValue").nyValue("nyValue").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("ShortTermExpectation isExist 테스트")
    void isExistTest() {

        boolean isExistTest =  queryFactory
                .select(shortTermExpectationEntity.id).from(shortTermExpectationEntity)
                .where(
                        shortTermExpectationEntity.version.eq("version")
                                .and(shortTermExpectationEntity.forecastDate.eq("forecastDate"))
                                .and(shortTermExpectationEntity.forecastTime.eq("forecastTime"))
                                .and(shortTermExpectationEntity.nxValue.eq("nxValue"))
                                .and(shortTermExpectationEntity.nyValue.eq("nyValue"))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
