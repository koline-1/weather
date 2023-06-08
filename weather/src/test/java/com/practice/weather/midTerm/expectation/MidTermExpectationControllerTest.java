package com.practice.weather.midTerm.expectation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.expectation.controller.MidTermExpectationController;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MidTermExpectationControllerTest {

    @Mock
    private MidTermExpectationEntity midTermExpectationEntity;

    @MockBean
    private MidTermExpectationController midTermExpectationController;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupExpectation() {
        openMocks = MockitoAnnotations.openMocks(this);
   }


    @Test
    @DisplayName("midTermExpectation data 화면 테스트")
    public void midTermExpectationCurrentTest() throws Exception {
        mockMvc.perform(get("/mid-term/expectation/current"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermExpectation data DB save 테스트")
    public void saveMidTermExpectationTest() throws Exception {

        midTermExpectationEntity = MidTermExpectationEntity.builder()
                .stnId("testId")
                .wfSv("testWfSv")
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
            .andExpect(jsonPath("$.stnId").value("testId"))
            .andExpect(jsonPath("$.wfSv").value("testWfSv"));
    }

}
