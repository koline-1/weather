package com.practice.weather.midTerm.expectation;

import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.practice.weather.midTerm.expectation.entity.QMidTermExpectationEntity.midTermExpectationEntity;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MidTermExpectationRepositoryCustomTest {

    @Autowired
    TestEntityManager testEntityManager;

    EntityManager em;

    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void createTest() {
        em = testEntityManager.getEntityManager();
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Test
    void useQueryDSLTest() {

        List<MidTermExpectationEntity> entityList =  jpaQueryFactory
                .select(midTermExpectationEntity).from(midTermExpectationEntity).fetch();

        for (MidTermExpectationEntity entity : entityList) {
            System.out.println(">>>>>>>>>>"+entity.getId());
        }

//        assertThat(weather.getId(), is(notNullValue()));
    }

}
