package com.practice.weather.midTerm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.expectation.entity.MidTermExpectationEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MidTermExpectationControllerTest {

    @Mock
    private MidTermExpectationEntity midTermExpectationEntity;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupExpectation() {
        openMocks = MockitoAnnotations.openMocks(this);
   }

    @Test
    @DisplayName("midTermExpectation location 화면 테스트")
    public void midTermExpectationLocationControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/expectation/location"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermExpectation data 화면 테스트")
    public void midTermExpectationDataControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/expectation/data"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermExpectation data DB save 테스트")
    public void saveMidTermExpectationTest() throws Exception {

        midTermExpectationEntity = MidTermExpectationEntity.builder().stnId("testId").wfSv("testWfSv").build();

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermExpectationEntity);

        JSONObject jObject = new JSONObject(map);
        System.out.println(">>>>>>>>>"+ jObject);

        mockMvc.perform(post("/mid-term/expectation/data")
                        .content(objectMapper.writeValueAsString(jObject))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                // 추후에 추가하기
//                .andExpect(jsonPath("$.stnId").value("testId"))
//                .andExpect(jsonPath("$.wfSv").value("testWfSv"));
    }

}
