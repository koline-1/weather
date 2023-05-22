package com.practice.weather.midTerm.expectation;

import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
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

import static com.practice.weather.midTerm.expectation.entity.QMidTermExpectationEntity.midTermExpectationEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MidTermExpectationRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        MidTermExpectationEntity entity = MidTermExpectationEntity.builder().stnId("testStnId").wfSv("testWfSv").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("MidTermExpectation isExist 테스트")
    void isExistTest() {

        String stnId = "testStnId";
        LocalDateTime localDateTime = utility.getMidTermBaseDateTimeAsLocalDateTime();
        boolean isExistTest =  queryFactory
                .select(midTermExpectationEntity.id).from(midTermExpectationEntity)
                .where(
                        midTermExpectationEntity.stnId.eq(stnId)
                                .and(midTermExpectationEntity.date.goe(localDateTime))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
