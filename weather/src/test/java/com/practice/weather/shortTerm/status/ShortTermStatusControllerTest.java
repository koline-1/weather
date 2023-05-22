package com.practice.weather.shortTerm.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.status.controller.ShortTermStatusController;
import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
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
public class ShortTermStatusControllerTest {

    @Mock
    private ShortTermStatusEntity shortTermStatusEntity;

    @MockBean
    private ShortTermStatusController shortTermStatusController;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupStatus() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("shortTermStatus data 화면 테스트")
    public void shortTermStatusControllerTest() throws Exception {
        mockMvc.perform(get("/short-term/status/data"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("shortTermStatus data DB save 테스트")
    public void saveShortTermStatusTest() throws Exception {

        shortTermStatusEntity = ShortTermStatusEntity.builder().baseDate("baseDate").baseTime("baseTime").nxValue("nxValue").nyValue("nyValue")
                .temperature("temperature").hourPrecipitation("hourPrecipitation").horizontalWind("horizontalWind").verticalWind("verticalWind")
                .humidity("humidity").rainType("rainType").windDirection("windDirection").windSpeed("windSpeed").version("version").build();

        // given
        given(shortTermStatusController.saveShortTermStatus(any()))
                .willReturn(
                        shortTermStatusEntity
                );

        HashMap<String, Object> map = new HashMap<>();

        map.put("data", shortTermStatusEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/short-term/status/data")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.baseDate").value("baseDate"))
                .andExpect(jsonPath("$.baseTime").value("baseTime"))
                .andExpect(jsonPath("$.nxValue").value("nxValue"))
                .andExpect(jsonPath("$.nyValue").value("nyValue"))
                .andExpect(jsonPath("$.temperature").value("temperature"))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"))
                .andExpect(jsonPath("$.humidity").value("humidity"))
                .andExpect(jsonPath("$.rainType").value("rainType"))
                .andExpect(jsonPath("$.windDirection").value("windDirection"))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"))
                .andExpect(jsonPath("$.version").value("version"));

    }

}
