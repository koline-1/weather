package com.practice.weather.midTerm.land;

import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
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

import static com.practice.weather.midTerm.land.entity.QMidTermLandEntity.midTermLandEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MidTermLandRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        MidTermLandEntity entity =   MidTermLandEntity.builder().regId("testRegId").rnSt3Am("rnSt3Am").rnSt3Pm("rnSt3Pm").rnSt4Am("rnSt4Am")
                .rnSt4Pm("rnSt4Pm").rnSt5Am("rnSt5Am").rnSt5Pm("rnSt5Pm").rnSt6Am("rnSt6Am").rnSt6Pm("rnSt6Pm").rnSt7Am("rnSt7Am")
                .rnSt7Pm("rnSt7Pm").rnSt8("rnSt8").rnSt9("rnSt9").rnSt10("rnSt10").wf3Am("wf3Am").wf3Pm("wf3Pm").wf4Am("wf4Am").wf4Pm("wf4Pm")
                .wf5Am("wf5Am").wf5Pm("wf5Pm").wf6Am("wf6Am").wf6Pm("wf6Pm").wf7Am("wf7Am").wf7Pm("wf7Pm").wf8("wf8").wf9("wf9").wf10("wf10").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("MidTermLand isExist 테스트")
    void isExistTest() {

        String regId = "testRegId";
        LocalDateTime localDateTime = utility.getMidTermBaseDateTimeAsLocalDateTime();
        boolean isExistTest =  queryFactory
                .select(midTermLandEntity.id).from(midTermLandEntity)
                .where(
                        midTermLandEntity.regId.eq(regId)
                                .and(midTermLandEntity.created.goe(localDateTime))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
