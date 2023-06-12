package com.practice.weather.midTerm.ocean;

import com.practice.weather.configuration.QueryDslConfig;
import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.repository.MidTermOceanRepository;
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
public class MidTermOceanRepositoryTest {

    @Autowired
    private Utility utility;

    @Autowired
    private MidTermOceanRepository midTermOceanRepository;

    @Mock
    private MidTermOceanEntity midTermOceanEntity;


    @BeforeEach
    void createTest() {
        // 모든 테스트 메소드의 when 은 BeforeEach 로 대체한다
        // 위치정보(regId)는 결과의 유효성 검토를 위해 같은값을 2개씩 입력
        for (int i = 1; i <= 10; i++) {
            midTermOceanEntity = MidTermOceanEntity.builder().id(i).regId("regId"+(i/2)).wf8("wf8"+i).wh4AAm("wh4AAm"+i).wh4BAm("wh4BAm"+i)
                    .wh6BAm("wh6BAm"+i).wf9("wf9"+i).wh6AAm("wh6AAm"+i).wh9B("wh9B"+i).wh9A("wh9A"+i).wf6Pm("wf6Pm"+i).wf7Pm("wf7Pm"+i)
                    .wf10("wf10"+i).wh4APm("wh4APm"+i).wh6BPm("wh6BPm"+i).wh6APm("wh6APm"+i).wf3Pm("wf3Pm"+i).wf4Pm("wf4Pm"+i).wh4BPm("wh4BPm"+i)
                    .wf5Pm("wf5Pm"+i).wh7AAm("wh7AAm"+i).wf7Am("wf7Am"+i).wh3AAm("wh3AAm"+i).wh5BAm("wh5BAm"+i).wh5AAm("wh5AAm"+i).wf3Am("wf3Am"+i)
                    .wh7BAm("wh7BAm"+i).wh8B("wh8B"+i).wf4Am("wf4Am"+i).wh8A("wh8A"+i).wh10A("wh10A"+i).wf5Am("wf5Am"+i).wh3BAm("wh3BAm"+i)
                    .wh10B("wh10B"+i).wf6Am("wf6Am"+i).wh5BPm("wh5BPm"+i).wh5APm("wh5APm"+i).wh7APm("wh7APm"+i).wh7BPm("wh7BPm"+i)
                    .wh3APm("wh3APm"+i).wh3BPm("wh3BPm"+i).build();

            midTermOceanRepository.save(midTermOceanEntity);
        }
    }

    @Test
    @DisplayName("MidTermOcean selectList 테스트")
    void selectListTest() {
        // given
        int expectedSize = 10;

        // then
        assertThat(midTermOceanRepository.selectList(Pageable.ofSize(100)), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermOcean selectListByLocation 테스트")
    void selectListByLocationTest() {
        // given
        String targetLocation = "regId1";
        int expectedSize = 2;

        // then
        assertThat(midTermOceanRepository.selectListByLocation(Pageable.ofSize(100),targetLocation), hasSize(expectedSize));
    }

    @Test
    @DisplayName("MidTermOcean countByLocation 테스트")
    void countByLocationTest() {
        // given
        String targetLocation = "regId2";
        long expectedResult = 2;

        // then
        assertThat(midTermOceanRepository.countByLocation(targetLocation), equalTo(expectedResult));
    }

    @Test
    @DisplayName("MidTermOcean isExist 테스트")
    void isExistTest() {
        // given
        String targetLocation = "regId3";
        boolean expectedResult = true;

        // then
        assertThat(midTermOceanRepository.isExist(targetLocation, utility.getMidTermBaseDateTimeAsLocalDateTime()), is(expectedResult));
    }

}
