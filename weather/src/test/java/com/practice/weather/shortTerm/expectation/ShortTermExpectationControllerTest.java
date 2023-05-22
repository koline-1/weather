package com.practice.weather.shortTerm.expectation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.expectation.controller.ShortTermExpectationController;
import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShortTermExpectationControllerTest {

    @Mock
    private ShortTermExpectationEntity shortTermExpectationEntity;

    @MockBean
    private ShortTermExpectationController shortTermExpectationController;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupExpectation() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("shortTermExpectation data 화면 테스트")
    public void shortTermExpectationControllerTest() throws Exception {
        mockMvc.perform(get("/short-term/expectation/data"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("shortTermExpectation data DB save 테스트")
    public void saveShortTermExpectationTest() throws Exception {

        shortTermExpectationEntity = ShortTermExpectationEntity.builder().baseDate("baseDate").build();

        HashMap<String, String> expectedResult = new HashMap<>();

        expectedResult.put("count", "1");

        // given
        given(shortTermExpectationController.saveShortTermExpectation(any()))
                .willReturn(
                        objectMapper.writeValueAsString(expectedResult)
                );

        HashMap<String, Object> map = new HashMap<>();

        map.put("data", shortTermExpectationEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/short-term/expectation/data")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));

    }

}
