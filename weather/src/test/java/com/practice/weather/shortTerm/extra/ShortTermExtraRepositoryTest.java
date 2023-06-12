package com.practice.weather.shortTerm.extra;

import com.practice.weather.configuration.QueryDslConfig;
import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import com.practice.weather.shortTerm.extra.entity.ShortTermExtraEntity;
import com.practice.weather.shortTerm.extra.repository.ShortTermExtraRepository;
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
public class ShortTermExtraRepositoryTest {

    @Autowired
    private Utility utility;

    @Autowired
    private ShortTermExtraRepository shortTermExtraRepository;

    @Mock
    private ShortTermExtraEntity shortTermExtraEntity;


    @BeforeEach
    void createTest() {
        // 모든 테스트 메소드의 when 은 BeforeEach 로 대체한다
        // 위치정보(nxValue, nyValue)는 결과의 유효성 검토를 위해 같은값을 2개씩 입력
        for (int i = 1; i <= 10; i++) {
            shortTermExtraEntity = ShortTermExtraEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i).forecastDate("forecastDate"+i)
                    .forecastTime("forecastTime"+i).nxValue("nxValue"+(i/2)).nyValue("nyValue"+(i/2)).temperature("temperature"+i)
                    .hourPrecipitation("hourPrecipitation"+i).skyStatus("skyStatus"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .humidity("humidity"+i).rainType("rainType"+i).lightning("lightning"+i).windDirection("windDirection"+i).windSpeed("windSpeed"+i)
                    .version("version"+i).build();

            shortTermExtraRepository.save(shortTermExtraEntity);
        }
    }

    @Test
    @DisplayName("ShortTermExtra selectList 테스트")
    void selectListTest() {
        // given
        int expectedSize = 10;

        // then
        assertThat(shortTermExtraRepository.selectList(Pageable.ofSize(100)), hasSize(expectedSize));
    }

    @Test
    @DisplayName("ShortTermExtra selectListByLocation 테스트")
    void selectListByLocationTest() {
        // given
        String targetNxValue = "nxValue1";
        String targetNyValue = "nyValue1";
        int expectedSize = 2;

        // then
        assertThat(shortTermExtraRepository.selectListByLocation(Pageable.ofSize(100), targetNxValue, targetNyValue),
                hasSize(expectedSize));
    }

    @Test
    @DisplayName("ShortTermExtra countListByLocation 테스트")
    void countByLocationTest() {
        // given
        String targetNxValue = "nxValue2";
        String targetNyValue = "nyValue2";
        long expectedResult = 2;

        // then
        assertThat(shortTermExtraRepository.countByLocation(targetNxValue, targetNyValue), equalTo(expectedResult));
    }

    @Test
    @DisplayName("ShortTermExtra isExist 테스트")
    void isExistTest() {
        // given
        int target = 5;
        ShortTermExtraDto targetDto = ShortTermExtraDto.builder().forecastDate("forecastDate"+target).forecastTime("forecastTime"+target)
                .version("version"+target).nxValue("nxValue"+(target/2)).nyValue("nyValue"+(target/2)).build();
        boolean expectedResult = true;

        // then
        assertThat(shortTermExtraRepository.isExist(targetDto), is(expectedResult));
    }

}
