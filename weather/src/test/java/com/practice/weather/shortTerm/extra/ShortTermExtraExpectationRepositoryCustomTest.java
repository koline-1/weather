package com.practice.weather.shortTerm.extra;

import com.practice.weather.shortTerm.extra.entity.ShortTermExtraEntity;
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

import static com.practice.weather.shortTerm.extra.entity.QShortTermExtraExpectationEntity.shortTermExtraExpectationEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShortTermExtraExpectationRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        ShortTermExtraEntity entity =   ShortTermExtraEntity.builder().version("version")
                .forecastDate("forecastDate").forecastTime("forecastTime").nxValue("nxValue").nyValue("nyValue").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("ShortTermExtraExpectation isExist 테스트")
    void isExistTest() {

        boolean isExistTest =  queryFactory
                .select(shortTermExtraExpectationEntity.id).from(shortTermExtraExpectationEntity)
                .where(
                        shortTermExtraExpectationEntity.version.eq("version")
                                .and(shortTermExtraExpectationEntity.forecastDate.eq("forecastDate"))
                                .and(shortTermExtraExpectationEntity.forecastTime.eq("forecastTime"))
                                .and(shortTermExtraExpectationEntity.nxValue.eq("nxValue"))
                                .and(shortTermExtraExpectationEntity.nyValue.eq("nyValue"))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
