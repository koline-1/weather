package com.practice.weather.midTerm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MidTermLandControllerTest {

    @Mock
    private MidTermLandEntity midTermLandEntity;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupLand() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("midTermLand location 화면 테스트")
    public void midTermLandLocationControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/land/location"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermLand data 화면 테스트")
    public void midTermLandDataControllerTest() throws Exception {
        mockMvc.perform(get("/mid-term/land/data"))
                .andExpect(status().isOk());
    }

}
