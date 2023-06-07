package com.practice.weather.midTerm.ocean;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
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

import static com.practice.weather.midTerm.ocean.entity.QMidTermOceanEntity.midTermOceanEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MidTermOceanRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        MidTermOceanEntity entity =   MidTermOceanEntity.builder().regId("testRegId").wf8("wf8").wh4AAm("wh4AAm").wh4BAm("wh4BAm").wh6BAm("wh6BAm")
                .wf9("wf9").wh6AAm("wh6AAm").wh9B("wh9B").wh9A("wh9A").wf6Pm("wf6Pm").wf7Pm("wf7Pm").wf10("wf10").wh4APm("wh4APm")
                .wh6BPm("wh6BPm").wh6APm("wh6APm").wf3Pm("wf3Pm").wf4Pm("wf4Pm").wh4BPm("wh4BPm").wf5Pm("wf5Pm").wh7AAm("wh7AAm")
                .wf7Am("wf7Am").wh3AAm("wh3AAm").wh5BAm("wh5BAm").wh5AAm("wh5AAm").wf3Am("wf3Am").wh7BAm("wh7BAm").wh8B("wh8B")
                .wf4Am("wf4Am").wh8A("wh8A").wh10A("wh10A").wf5Am("wf5Am").wh3BAm("wh3BAm").wh10B("wh10B").wf6Am("wf6Am").wh5BPm("wh5BPm")
                .wh5APm("wh5APm").wh7APm("wh7APm").wh7BPm("wh7BPm").wh3APm("wh3APm").wh3BPm("wh3BPm").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("MidTermOcean isExist 테스트")
    void isExistTest() {

        String regId = "testRegId";
        LocalDateTime localDateTime = utility.getMidTermBaseDateTimeAsLocalDateTime();
        boolean isExistTest =  queryFactory
                .select(midTermOceanEntity.id).from(midTermOceanEntity)
                .where(
                        midTermOceanEntity.regId.eq(regId)
                                .and(midTermOceanEntity.date.goe(localDateTime))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
