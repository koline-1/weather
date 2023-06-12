package com.practice.weather.midTerm.temperature;

import com.practice.weather.configuration.QueryDslConfig;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import com.practice.weather.midTerm.temperature.repository.MidTermTemperatureRepository;
import com.practice.weather.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Import({QueryDslConfig.class, Utility.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class MidTermTemperatureRepositoryTest {

    @Autowired
    private Utility utility;

    @Autowired
    private MidTermTemperatureRepository midTermTemperatureRepository;

    @Mock
    private MidTermTemperatureEntity midTermTemperatureEntity;


    @BeforeEach
    void createTest() {
        // 모든 테스트 메소드의 when 은 BeforeEach 로 대체한다
        // 위치정보(regId)는 결과의 유효성 검토를 위해 같은값을 2개씩 입력
        for (int i = 1; i <= 10; i++) {
            midTermTemperatureEntity = MidTermTemperatureEntity.builder().id(i).regId("regId"+(i/2)).taMin3("taMin3"+i).taMin3Low("taMin3Low"+i)
                    .taMin3High("taMin3High"+i).taMax3("taMax3").taMax3Low("taMax3Low"+i).taMax3High("taMax3High"+i).taMin4("taMin4"+i)
                    .taMin4Low("taMin4Low"+i).taMin4High("taMin4High"+i).taMax4("taMax4").taMax4Low("taMax4Low"+i).taMax4High("taMax4High"+i)
                    .taMin5("taMin5"+i).taMin5Low("taMin5Low"+i).taMin5High("taMin5High"+i).taMax5("taMax5").taMax5Low("taMax5Low"+i)
                    .taMax5High("taMax5High"+i).taMin6("taMin6"+i).taMin6Low("taMin6Low"+i).taMin6High("taMin6High"+i).taMax6("taMax6")
                    .taMax6Low("taMax6Low"+i).taMax6High("taMax6High"+i).taMin7("taMin7"+i).taMin7Low("taMin7Low"+i).taMin7High("taMin7High"+i)
                    .taMax7("taMax7").taMax7Low("taMax7Low"+i).taMax7High("taMax7High"+i).taMin8("taMin8"+i).taMin8Low("taMin8Low"+i)
                    .taMin8High("taMin8High"+i).taMax8("taMax8").taMax8Low("taMax8Low"+i).taMax8High("taMax8High"+i).taMin9("taMin9"+i)
                    .taMin9Low("taMin9Low"+i).taMin9High("taMin9High"+i).taMax9("taMax9").taMax9Low("taMax9Low"+i).taMax9High("taMax9High"+i)
                    .taMin10("taMin10"+i).taMin10Low("taMin10Low"+i).taMin10High("taMin10High").taMax10("taMax10"+i).taMax10Low("taMax10Low"+i)
                    .taMax10High("taMax10High"+i).build();

            midTermTemperatureRepository.save(midTermTemperatureEntity);
        }
    }

    @Test
    @DisplayName("MidTermTemperature selectList 테스트")
    void selectListTest() {
        // given
        int expectedSize = 10;

        // then
        assertThat(midTermTemperatureRepository.selectList(Pageable.ofSize(100)), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermTemperature selectListByLocation 테스트")
    void selectListByLocationTest() {
        // given
        String targetLocation = "regId1";
        int expectedSize = 2;

        // then
        assertThat(midTermTemperatureRepository.selectListByLocation(Pageable.ofSize(100),targetLocation), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermTemperature countByLocation 테스트")
    void countByLocationTest() {
        // given
        String targetLocation = "regId2";
        long expectedResult = 2;

        // then
        assertThat(midTermTemperatureRepository.countByLocation(targetLocation), equalTo(expectedResult));
    }

    @Test
    @DisplayName("MidTermTemperature isExist 테스트")
    void isExistTest() {
        // given
        String targetLocation = "regId3";
        boolean expectedResult = true;

        // then
        assertThat(midTermTemperatureRepository.isExist(targetLocation, utility.getMidTermBaseDateTimeAsLocalDateTime()), is(expectedResult));
    }

}
