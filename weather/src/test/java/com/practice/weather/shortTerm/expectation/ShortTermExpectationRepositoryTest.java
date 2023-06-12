package com.practice.weather.shortTerm.expectation;

import com.practice.weather.configuration.QueryDslConfig;
import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import com.practice.weather.shortTerm.expectation.repository.ShortTermExpectationRepository;
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
public class ShortTermExpectationRepositoryTest {

    @Autowired
    private Utility utility;

    @Autowired
    private ShortTermExpectationRepository shortTermExpectationRepository;

    @Mock
    private ShortTermExpectationEntity shortTermExpectationEntity;


    @BeforeEach
    void createTest() {
        // 모든 테스트 메소드의 when 은 BeforeEach 로 대체한다
        // 위치정보(nxValue, nyValue)는 결과의 유효성 검토를 위해 같은값을 2개씩 입력
        for (int i = 1; i <= 10; i++) {
            shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i)
                    .forecastDate("forecastDate"+i).forecastTime("forecastTime"+i).nxValue("nxValue"+(i/2)).nyValue("nyValue"+(i/2))
                    .hourTemperature("hourTemperature"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .windDirection("windDirection"+i).windSpeed("windSpeed"+i).skyStatus("skyStatus"+i).rainType("rainType"+i)
                    .rainPossibility("rainPossibility"+i).waveHeight("waveHeight"+i).hourPrecipitation("hourPrecipitation"+i)
                    .snowDepth("snowDepth"+i).humidity("humidity"+i).minimumTemperature("minimumTemperature"+i)
                    .maximumTemperature("maximumTemperature"+i).version("version"+i).build();

            shortTermExpectationRepository.save(shortTermExpectationEntity);
        }
    }

    @Test
    @DisplayName("ShortTermExpectation selectList 테스트")
    void selectListTest() {
        // given
        int expectedSize = 10;

        // then
        assertThat(shortTermExpectationRepository.selectList(Pageable.ofSize(100)), hasSize(expectedSize));
    }

    @Test
    @DisplayName("ShortTermExpectation selectListByLocation 테스트")
    void selectListByLocationTest() {
        // given
        String targetNxValue = "nxValue1";
        String targetNyValue = "nyValue1";
        int expectedSize = 2;

        // then
        assertThat(shortTermExpectationRepository.selectListByLocation(Pageable.ofSize(100), targetNxValue, targetNyValue),
                hasSize(expectedSize));
    }

    @Test
    @DisplayName("ShortTermExpectation countListByLocation 테스트")
    void countByLocationTest() {
        // given
        String targetNxValue = "nxValue2";
        String targetNyValue = "nyValue2";
        long expectedResult = 2;

        // then
        assertThat(shortTermExpectationRepository.countByLocation(targetNxValue, targetNyValue), equalTo(expectedResult));
    }

    @Test
    @DisplayName("ShortTermExpectation isExist 테스트")
    void isExistTest() {
        // given
        int target = 5;
        ShortTermExpectationDto targetDto = ShortTermExpectationDto.builder().forecastDate("forecastDate"+target).forecastTime("forecastTime"+target)
                .version("version"+target).nxValue("nxValue"+(target/2)).nyValue("nyValue"+(target/2)).build();
        boolean expectedResult = true;

        // then
        assertThat(shortTermExpectationRepository.isExist(targetDto), is(expectedResult));
    }

}
