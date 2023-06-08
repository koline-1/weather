package com.practice.weather.midTerm.land;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.land.controller.MidTermLandController;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
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
public class MidTermLandControllerTest {

    @Mock
    private MidTermLandEntity midTermLandEntity;

    @MockBean
    private MidTermLandController midTermLandController;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupLand() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("midTermLand data 화면 테스트")
    public void midTermLandDataControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/land/current"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermLand data DB save 테스트")
    public void saveMidTermLandTest() throws Exception {

        midTermLandEntity = MidTermLandEntity.builder().regId("testId").rnSt3Am("rnSt3Am").rnSt3Pm("rnSt3Pm").rnSt4Am("rnSt4Am")
                .rnSt4Pm("rnSt4Pm").rnSt5Am("rnSt5Am").rnSt5Pm("rnSt5Pm").rnSt6Am("rnSt6Am").rnSt6Pm("rnSt6Pm").rnSt7Am("rnSt7Am")
                .rnSt7Pm("rnSt7Pm").rnSt8("rnSt8").rnSt9("rnSt9").rnSt10("rnSt10").wf3Am("wf3Am").wf3Pm("wf3Pm").wf4Am("wf4Am").wf4Pm("wf4Pm")
                .wf5Am("wf5Am").wf5Pm("wf5Pm").wf6Am("wf6Am").wf6Pm("wf6Pm").wf7Am("wf7Am").wf7Pm("wf7Pm").wf8("wf8").wf9("wf9").wf10("wf10").build();

        // given
        given(midTermLandController.saveMidTermLand(any()))
                .willReturn(
                        ResponseEntity.ok(midTermLandEntity)
                );

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermLandEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/mid-term/land/current")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.regId").value("testId"))
                .andExpect(jsonPath("$.rnSt3Am").value("rnSt3Am"))
                .andExpect(jsonPath("$.rnSt3Pm").value("rnSt3Pm"))
                .andExpect(jsonPath("$.rnSt4Am").value("rnSt4Am"))
                .andExpect(jsonPath("$.rnSt4Pm").value("rnSt4Pm"))
                .andExpect(jsonPath("$.rnSt5Am").value("rnSt5Am"))
                .andExpect(jsonPath("$.rnSt5Pm").value("rnSt5Pm"))
                .andExpect(jsonPath("$.rnSt6Am").value("rnSt6Am"))
                .andExpect(jsonPath("$.rnSt6Pm").value("rnSt6Pm"))
                .andExpect(jsonPath("$.rnSt7Am").value("rnSt7Am"))
                .andExpect(jsonPath("$.rnSt7Pm").value("rnSt7Pm"))
                .andExpect(jsonPath("$.rnSt8").value("rnSt8"))
                .andExpect(jsonPath("$.rnSt9").value("rnSt9"))
                .andExpect(jsonPath("$.rnSt10").value("rnSt10"))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"))
                .andExpect(jsonPath("$.wf8").value("wf8"))
                .andExpect(jsonPath("$.wf9").value("wf9"))
                .andExpect(jsonPath("$.wf10").value("wf10"));
    }

}
