package com.practice.weather.shortTerm.extra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.extra.entity.ShortTermExtraEntity;
import com.practice.weather.shortTerm.extra.controller.ShortTermExtraController;
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
public class ShortTermExtraControllerTest {

    @Mock
    private ShortTermExtraEntity shortTermExtraEntity;

    @MockBean
    private ShortTermExtraController shortTermExtraController;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupExtra() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("shortTermExtra data 화면 테스트")
    public void shortTermExtraControllerTest() throws Exception {
        mockMvc.perform(get("/short-term/extra/current"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("shortTermExtra data DB save 테스트")
    public void saveShortTermExtraTest() throws Exception {

        shortTermExtraEntity = ShortTermExtraEntity.builder().baseDate("baseDate").build();

        HashMap<String, String> expectedResult = new HashMap<>();

        expectedResult.put("count", "1");

        // given
        given(shortTermExtraController.saveShortTermExtra(any()))
                .willReturn(
                        objectMapper.writeValueAsString(expectedResult)
                );

        HashMap<String, Object> map = new HashMap<>();

        map.put("data", shortTermExtraEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/short-term/extra/current")
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
