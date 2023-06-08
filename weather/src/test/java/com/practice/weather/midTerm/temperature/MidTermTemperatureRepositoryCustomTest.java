package com.practice.weather.midTerm.temperature;

import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
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

import static com.practice.weather.midTerm.temperature.entity.QMidTermTemperatureEntity.midTermTemperatureEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MidTermTemperatureRepositoryCustomTest {

    @Autowired
    Utility utility;

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void createTest() {
        queryFactory = new JPAQueryFactory(em);

        MidTermTemperatureEntity entity =   MidTermTemperatureEntity.builder().regId("testRegId").taMin3("taMin3").taMin3Low("taMin3Low").taMin3High("taMin3High")
                .taMax3("taMax3").taMax3Low("taMax3Low").taMax3High("taMax3High").taMin4("taMin4").taMin4Low("taMin4Low").taMin4High("taMin4High")
                .taMax4("taMax4").taMax4Low("taMax4Low").taMax4High("taMax4High").taMin5("taMin5").taMin5Low("taMin5Low").taMin5High("taMin5High")
                .taMax5("taMax5").taMax5Low("taMax5Low").taMax5High("taMax5High").taMin6("taMin6").taMin6Low("taMin6Low").taMin6High("taMin6High")
                .taMax6("taMax6").taMax6Low("taMax6Low").taMax6High("taMax6High").taMin7("taMin7").taMin7Low("taMin7Low").taMin7High("taMin7High")
                .taMax7("taMax7").taMax7Low("taMax7Low").taMax7High("taMax7High").taMin8("taMin8").taMin8Low("taMin8Low").taMin8High("taMin8High")
                .taMax8("taMax8").taMax8Low("taMax8Low").taMax8High("taMax8High").taMin9("taMin9").taMin9Low("taMin9Low").taMin9High("taMin9High")
                .taMax9("taMax9").taMax9Low("taMax9Low").taMax9High("taMax9High").taMin10("taMin10").taMin10Low("taMin10Low")
                .taMin10High("taMin10High").taMax10("taMax10").taMax10Low("taMax10Low").taMax10High("taMax10High").build();

        em.persist(entity);
    }

    @Test
    @DisplayName("MidTermTemperature isExist 테스트")
    void isExistTest() {

        String regId = "testRegId";
        LocalDateTime localDateTime = utility.getMidTermBaseDateTimeAsLocalDateTime();
        boolean isExistTest =  queryFactory
                .select(midTermTemperatureEntity.id).from(midTermTemperatureEntity)
                .where(
                        midTermTemperatureEntity.regId.eq(regId)
                                .and(midTermTemperatureEntity.created.goe(localDateTime))
                ).fetch().size() > 0;

        assertThat(isExistTest, is(true));
    }

}
