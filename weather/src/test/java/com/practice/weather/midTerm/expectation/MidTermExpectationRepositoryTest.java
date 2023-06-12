package com.practice.weather.midTerm.expectation;

import com.practice.weather.configuration.QueryDslConfig;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import com.practice.weather.midTerm.expectation.repository.MidTermExpectationRepository;
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
public class MidTermExpectationRepositoryTest {

    @Autowired
    private Utility utility;

    @Autowired
    private MidTermExpectationRepository midTermExpectationRepository;

    @Mock
    private MidTermExpectationEntity midTermExpectationEntity;


    @BeforeEach
    void createTest() {
        // 모든 테스트 메소드의 when 은 BeforeEach 로 대체한다
        // 위치정보(stnId)는 결과의 유효성 검토를 위해 같은값을 2개씩 입력
        for (int i = 1; i <= 10; i++) {
            midTermExpectationEntity = MidTermExpectationEntity.builder().id(i).stnId("stnId"+(i/2)).wfSv("wfSv"+i).build();

            midTermExpectationRepository.save(midTermExpectationEntity);
        }
    }

    @Test
    @DisplayName("MidTermExpectation selectList 테스트")
    void selectListTest() {
        // given
        int expectedSize = 10;

        // then
        assertThat(midTermExpectationRepository.selectList(Pageable.ofSize(100)), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermExpectation selectListByLocation 테스트")
    void selectListByLocationTest() {
        // given
        String targetLocation = "stnId1";
        int expectedSize = 2;

        // then
        assertThat(midTermExpectationRepository.selectListByLocation(Pageable.ofSize(100),targetLocation), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermExpectation countListByLocation 테스트")
    void countByLocationTest() {
        // given
        String targetLocation = "stnId2";
        long expectedResult = 2;

        // then
        assertThat(midTermExpectationRepository.countByLocation(targetLocation), equalTo(expectedResult));
    }

    @Test
    @DisplayName("MidTermExpectation isExist 테스트")
    void isExistTest() {
        // given
        String targetLocation = "stnId3";
        boolean expectedResult = true;

        // then
        assertThat(midTermExpectationRepository.isExist(targetLocation, utility.getMidTermBaseDateTimeAsLocalDateTime()), is(expectedResult));
    }

}
