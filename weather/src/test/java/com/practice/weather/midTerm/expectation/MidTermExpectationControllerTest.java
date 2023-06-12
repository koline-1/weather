package com.practice.weather.midTerm.expectation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.expectation.controller.MidTermExpectationController;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import com.practice.weather.midTerm.expectation.repository.MidTermExpectationRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MidTermExpectationControllerTest {

    @Mock
    private MidTermExpectationEntity midTermExpectationEntity;

    @MockBean
    private MidTermExpectationController midTermExpectationController;

    @Autowired
    private MidTermExpectationRepository midTermExpectationRepository;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupExpectation() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            midTermExpectationEntity = MidTermExpectationEntity.builder().id(i).stnId("stnId"+i).wfSv("wfSv"+i).build();
            midTermExpectationRepository.save(midTermExpectationEntity);
        }

   }

    @Test
    @DisplayName("중기 예보 조회 저장 테스트")
    public void saveMidTermExpectationTest() throws Exception {

        midTermExpectationEntity = MidTermExpectationEntity.builder()
                .stnId("stnId")
                .wfSv("wfSv")
                .build();

        // given
        given(midTermExpectationController.saveMidTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(midTermExpectationEntity)
                );

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermExpectationEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/mid-term/expectation/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(jObject))
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stnId").value("stnId"))
            .andExpect(jsonPath("$.wfSv").value("wfSv"));

    }

    @Test
    @DisplayName("중기 예보 조회 리스트 테스트")
    public void getMidTermExpectationListTest() throws Exception {

        List<MidTermExpectationEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            midTermExpectationEntity = MidTermExpectationEntity.builder().id(i).stnId("stnId"+i).wfSv("wfSv"+i).build();
            list.add(midTermExpectationEntity);
        }

        // given
        given(midTermExpectationController.getMidTermExpectationList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/expectation/list"));

        // then
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 리스트 테스트")
    public void getMidTermExpectationListTest2() throws Exception {

        List<MidTermExpectationEntity> list = new ArrayList<>();

        long id = 4;

        midTermExpectationEntity = MidTermExpectationEntity.builder().id(id).stnId("stnId"+id).wfSv("wfSv"+id).build();
        list.add(midTermExpectationEntity);

        // given
        given(midTermExpectationController.getMidTermExpectationList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/expectation/list")
                .param("location", "stnId"+id));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 예보 조회 카운트 테스트")
    public void countMidTermExpectationTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        //given
        given(midTermExpectationController.countMidTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/expectation/count"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 카운트 테스트")
    public void countMidTermExpectationTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (stnId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        //given
        given(midTermExpectationController.countMidTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/expectation/count")
                .param("location", "stnId1"));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 예보 조회 조회 테스트")
    public void readMidTermExpectationTest() throws Exception {

        long id = 8;

        midTermExpectationEntity = MidTermExpectationEntity.builder().id(id).stnId("stnId"+id).wfSv("wfSv"+id).build();

        // given
        given(midTermExpectationController.readMidTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(midTermExpectationEntity)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/expectation/"+id));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.stnId").value("stnId"+id))
                .andExpect(jsonPath("$.wfSv").value("wfSv"+id));
    }

    @Test
    @DisplayName("중기 예보 조회 수정 테스트")
    public void patchMidTermExpectationTest() throws Exception {

        long id = 3;

        midTermExpectationEntity = MidTermExpectationEntity.builder().id(id).stnId("stnId" + id + "updated").wfSv("wfSv" + id + "updated").build();

        // given
        given(midTermExpectationController.patchMidTermExpectation(any(), any()))
                .willReturn(
                        ResponseEntity.ok(midTermExpectationEntity)
                );

        // when
        final ResultActions actions = mockMvc.perform(patch("/mid-term/expectation/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(midTermExpectationEntity))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.stnId").value("stnId" + id + "updated"))
                .andExpect(jsonPath("$.wfSv").value("wfSv" + id + "updated"));
    }

    @Test
    @DisplayName("중기 예보 조회 삭제 테스트")
    public void deleteMidTermExpectationTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        // given
        given(midTermExpectationController.deleteMidTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(delete("/mid-term/expectation/"+id)
                .param("id", String.valueOf(id)));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
