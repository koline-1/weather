package com.practice.weather.midTerm.temperature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.temperature.controller.MidTermTemperatureController;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
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
public class MidTermTemperatureControllerTest {

    @Mock
    private MidTermTemperatureEntity midTermTemperatureEntity;

    @MockBean
    private MidTermTemperatureController midTermTemperatureController;
    
    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupTemperature() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("midTermTemperature data 화면 테스트")
    public void midTermTemperatureDataControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/temperature/current"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermTemperature data DB save 테스트")
    public void saveMidTermTemperatureTest() throws Exception {

        midTermTemperatureEntity = MidTermTemperatureEntity.builder().regId("testId").taMin3("taMin3").taMin3Low("taMin3Low").taMin3High("taMin3High")
                .taMax3("taMax3").taMax3Low("taMax3Low").taMax3High("taMax3High").taMin4("taMin4").taMin4Low("taMin4Low").taMin4High("taMin4High")
                .taMax4("taMax4").taMax4Low("taMax4Low").taMax4High("taMax4High").taMin5("taMin5").taMin5Low("taMin5Low").taMin5High("taMin5High")
                .taMax5("taMax5").taMax5Low("taMax5Low").taMax5High("taMax5High").taMin6("taMin6").taMin6Low("taMin6Low").taMin6High("taMin6High")
                .taMax6("taMax6").taMax6Low("taMax6Low").taMax6High("taMax6High").taMin7("taMin7").taMin7Low("taMin7Low").taMin7High("taMin7High")
                .taMax7("taMax7").taMax7Low("taMax7Low").taMax7High("taMax7High").taMin8("taMin8").taMin8Low("taMin8Low").taMin8High("taMin8High")
                .taMax8("taMax8").taMax8Low("taMax8Low").taMax8High("taMax8High").taMin9("taMin9").taMin9Low("taMin9Low").taMin9High("taMin9High")
                .taMax9("taMax9").taMax9Low("taMax9Low").taMax9High("taMax9High").taMin10("taMin10").taMin10Low("taMin10Low")
                .taMin10High("taMin10High").taMax10("taMax10").taMax10Low("taMax10Low").taMax10High("taMax10High").build();

        // given
        given(midTermTemperatureController.saveMidTermTemperature(any()))
                .willReturn(
                        ResponseEntity.ok().body(midTermTemperatureEntity)
                );

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermTemperatureEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/mid-term/temperature/current")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.regId").value("testId"))
                .andExpect(jsonPath("$.taMin3").value("taMin3"))
                .andExpect(jsonPath("$.taMin3Low").value("taMin3Low"))
                .andExpect(jsonPath("$.taMin3High").value("taMin3High"))
                .andExpect(jsonPath("$.taMax3").value("taMax3"))
                .andExpect(jsonPath("$.taMax3Low").value("taMax3Low"))
                .andExpect(jsonPath("$.taMax3High").value("taMax3High"))
                .andExpect(jsonPath("$.taMin4").value("taMin4"))
                .andExpect(jsonPath("$.taMin4Low").value("taMin4Low"))
                .andExpect(jsonPath("$.taMin4High").value("taMin4High"))
                .andExpect(jsonPath("$.taMax4").value("taMax4"))
                .andExpect(jsonPath("$.taMax4Low").value("taMax4Low"))
                .andExpect(jsonPath("$.taMax4High").value("taMax4High"))
                .andExpect(jsonPath("$.taMin5").value("taMin5"))
                .andExpect(jsonPath("$.taMin5Low").value("taMin5Low"))
                .andExpect(jsonPath("$.taMin5High").value("taMin5High"))
                .andExpect(jsonPath("$.taMax5").value("taMax5"))
                .andExpect(jsonPath("$.taMax5Low").value("taMax5Low"))
                .andExpect(jsonPath("$.taMax5High").value("taMax5High"))
                .andExpect(jsonPath("$.taMin6").value("taMin6"))
                .andExpect(jsonPath("$.taMin6Low").value("taMin6Low"))
                .andExpect(jsonPath("$.taMin6High").value("taMin6High"))
                .andExpect(jsonPath("$.taMax6").value("taMax6"))
                .andExpect(jsonPath("$.taMax6Low").value("taMax6Low"))
                .andExpect(jsonPath("$.taMax6High").value("taMax6High"))
                .andExpect(jsonPath("$.taMin7").value("taMin7"))
                .andExpect(jsonPath("$.taMin7Low").value("taMin7Low"))
                .andExpect(jsonPath("$.taMin7High").value("taMin7High"))
                .andExpect(jsonPath("$.taMax7").value("taMax7"))
                .andExpect(jsonPath("$.taMax7Low").value("taMax7Low"))
                .andExpect(jsonPath("$.taMax7High").value("taMax7High"))
                .andExpect(jsonPath("$.taMin8").value("taMin8"))
                .andExpect(jsonPath("$.taMin8Low").value("taMin8Low"))
                .andExpect(jsonPath("$.taMin8High").value("taMin8High"))
                .andExpect(jsonPath("$.taMax8").value("taMax8"))
                .andExpect(jsonPath("$.taMax8Low").value("taMax8Low"))
                .andExpect(jsonPath("$.taMax8High").value("taMax8High"))
                .andExpect(jsonPath("$.taMin9").value("taMin9"))
                .andExpect(jsonPath("$.taMin9Low").value("taMin9Low"))
                .andExpect(jsonPath("$.taMin9High").value("taMin9High"))
                .andExpect(jsonPath("$.taMax9").value("taMax9"))
                .andExpect(jsonPath("$.taMax9Low").value("taMax9Low"))
                .andExpect(jsonPath("$.taMax9High").value("taMax9High"))
                .andExpect(jsonPath("$.taMin10").value("taMin10"))
                .andExpect(jsonPath("$.taMin10Low").value("taMin10Low"))
                .andExpect(jsonPath("$.taMin10High").value("taMin10High"))
                .andExpect(jsonPath("$.taMax10").value("taMax10"))
                .andExpect(jsonPath("$.taMax10Low").value("taMax10Low"))
                .andExpect(jsonPath("$.taMax10High").value("taMax10High"));
    }

}
