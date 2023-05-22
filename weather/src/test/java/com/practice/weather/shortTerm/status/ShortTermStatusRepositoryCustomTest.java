package com.practice.weather.shortTerm.status;

import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
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

import static com.practice.weather.shortTerm.status.entity.QShortTermStatusEntity.shortTermStatusEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShortTermStatusRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        ShortTermStatusEntity entity =   ShortTermStatusEntity.builder().version("version").nxValue("nxValue").nyValue("nyValue").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("ShortTermStatus isExist 테스트")
    void isExistTest() {

        boolean isExistTest =  queryFactory
                .select(shortTermStatusEntity.id).from(shortTermStatusEntity)
                .where(
                        shortTermStatusEntity.version.eq("version")
                                .and(shortTermStatusEntity.nxValue.eq("nxValue"))
                                .and(shortTermStatusEntity.nyValue.eq("nyValue"))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
