package com.practice.weather.midTerm.land;

import com.practice.weather.configuration.QueryDslConfig;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
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
public class MidTermLandRepositoryTest {

    @Autowired
    private Utility utility;

    @Autowired
    private MidTermLandRepository midTermLandRepository;

    @Mock
    private MidTermLandEntity midTermLandEntity;


    @BeforeEach
    void createTest() {
        // 모든 테스트 메소드의 when 은 BeforeEach 로 대체한다
        // 위치정보(regId)는 결과의 유효성 검토를 위해 같은값을 2개씩 입력
        for (int i = 1; i <= 10; i++) {
            midTermLandEntity = MidTermLandEntity.builder().id(i).regId("regId"+(i/2)).rnSt3Am("rnSt3Am"+i).rnSt3Pm("rnSt3Pm"+i).rnSt4Am("rnSt4Am"+i)
                    .rnSt4Pm("rnSt4Pm"+i).rnSt5Am("rnSt5Am"+i).rnSt5Pm("rnSt5Pm"+i).rnSt6Am("rnSt6Am"+i).rnSt6Pm("rnSt6Pm"+i).rnSt7Am("rnSt7Am"+i)
                    .rnSt7Pm("rnSt7Pm"+i).rnSt8("rnSt8"+i).rnSt9("rnSt9"+i).rnSt10("rnSt10"+i).wf3Am("wf3Am"+i).wf3Pm("wf3Pm"+i).wf4Am("wf4Am"+i)
                    .wf4Pm("wf4Pm"+i).wf5Am("wf5Am"+i).wf5Pm("wf5Pm"+i).wf6Am("wf6Am"+i).wf6Pm("wf6Pm"+i).wf7Am("wf7Am"+i).wf7Pm("wf7Pm"+i)
                    .wf8("wf8"+i).wf9("wf9"+i).wf10("wf10"+i).build();

            midTermLandRepository.save(midTermLandEntity);
        }
    }

    @Test
    @DisplayName("MidTermLand selectList 테스트")
    void selectListTest() {
        // given
        int expectedSize = 10;

        // then
        assertThat(midTermLandRepository.selectList(Pageable.ofSize(100)), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermLand selectListByLocation 테스트")
    void selectListByLocationTest() {
        // given
        String targetLocation = "regId1";
        int expectedSize = 2;

        // then
        assertThat(midTermLandRepository.selectListByLocation(Pageable.ofSize(100),targetLocation), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermLand countByLocation 테스트")
    void countByLocationTest() {
        // given
        String targetLocation = "regId2";
        long expectedResult = 2;

        // then
        assertThat(midTermLandRepository.countByLocation(targetLocation), equalTo(expectedResult));
    }

    @Test
    @DisplayName("MidTermLand isExist 테스트")
    void isExistTest() {
        // given
        String targetLocation = "regId3";
        boolean expectedResult = true;

        // then
        assertThat(midTermLandRepository.isExist(targetLocation, utility.getMidTermBaseDateTimeAsLocalDateTime()), is(expectedResult));
    }

}
