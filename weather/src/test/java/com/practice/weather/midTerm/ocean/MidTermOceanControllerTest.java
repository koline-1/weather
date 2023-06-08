package com.practice.weather.midTerm.ocean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.ocean.controller.MidTermOceanController;
import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MidTermOceanControllerTest {

    @Mock
    private MidTermOceanEntity midTermOceanEntity;

    @MockBean
    private MidTermOceanController midTermOceanController;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupOcean() {
        openMocks = MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("midTermOcean data 화면 테스트")
    public void midTermOceanDataControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/ocean/current"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermOcean data DB save 테스트")
    public void saveMidTermOceanTest() throws Exception {

        midTermOceanEntity = MidTermOceanEntity.builder().regId("testId").wf8("wf8").wh4AAm("wh4AAm").wh4BAm("wh4BAm").wh6BAm("wh6BAm")
                .wf9("wf9").wh6AAm("wh6AAm").wh9B("wh9B").wh9A("wh9A").wf6Pm("wf6Pm").wf7Pm("wf7Pm").wf10("wf10").wh4APm("wh4APm")
                .wh6BPm("wh6BPm").wh6APm("wh6APm").wf3Pm("wf3Pm").wf4Pm("wf4Pm").wh4BPm("wh4BPm").wf5Pm("wf5Pm").wh7AAm("wh7AAm")
                .wf7Am("wf7Am").wh3AAm("wh3AAm").wh5BAm("wh5BAm").wh5AAm("wh5AAm").wf3Am("wf3Am").wh7BAm("wh7BAm").wh8B("wh8B")
                .wf4Am("wf4Am").wh8A("wh8A").wh10A("wh10A").wf5Am("wf5Am").wh3BAm("wh3BAm").wh10B("wh10B").wf6Am("wf6Am").wh5BPm("wh5BPm")
                .wh5APm("wh5APm").wh7APm("wh7APm").wh7BPm("wh7BPm").wh3APm("wh3APm").wh3BPm("wh3BPm").build();

        // given
        given(midTermOceanController.saveMidTermOcean(any()))
                .willReturn(
                        ResponseEntity.ok(midTermOceanEntity)
                );

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermOceanEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/mid-term/ocean/current")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.regId").value("testId"))
                .andExpect(jsonPath("$.wf8").value("wf8"))
                .andExpect(jsonPath("$.wh4AAm").value("wh4AAm"))
                .andExpect(jsonPath("$.wh4BAm").value("wh4BAm"))
                .andExpect(jsonPath("$.wh6BAm").value("wh6BAm"))
                .andExpect(jsonPath("$.wf9").value("wf9"))
                .andExpect(jsonPath("$.wh6AAm").value("wh6AAm"))
                .andExpect(jsonPath("$.wh9B").value("wh9B"))
                .andExpect(jsonPath("$.wh9A").value("wh9A"))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"))
                .andExpect(jsonPath("$.wf10").value("wf10"))
                .andExpect(jsonPath("$.wh4APm").value("wh4APm"))
                .andExpect(jsonPath("$.wh6BPm").value("wh6BPm"))
                .andExpect(jsonPath("$.wh6APm").value("wh6APm"))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"))
                .andExpect(jsonPath("$.wh4BPm").value("wh4BPm"))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"))
                .andExpect(jsonPath("$.wh7AAm").value("wh7AAm"))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"))
                .andExpect(jsonPath("$.wh3AAm").value("wh3AAm"))
                .andExpect(jsonPath("$.wh5BAm").value("wh5BAm"))
                .andExpect(jsonPath("$.wh5AAm").value("wh5AAm"))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"))
                .andExpect(jsonPath("$.wh7BAm").value("wh7BAm"))
                .andExpect(jsonPath("$.wh8B").value("wh8B"))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"))
                .andExpect(jsonPath("$.wh8A").value("wh8A"))
                .andExpect(jsonPath("$.wh10A").value("wh10A"))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"))
                .andExpect(jsonPath("$.wh3BAm").value("wh3BAm"))
                .andExpect(jsonPath("$.wh10B").value("wh10B"))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"))
                .andExpect(jsonPath("$.wh5BPm").value("wh5BPm"))
                .andExpect(jsonPath("$.wh5APm").value("wh5APm"))
                .andExpect(jsonPath("$.wh7APm").value("wh7APm"))
                .andExpect(jsonPath("$.wh7BPm").value("wh7BPm"))
                .andExpect(jsonPath("$.wh3APm").value("wh3APm"))
                .andExpect(jsonPath("$.wh3BPm").value("wh3BPm"));
    }

}
