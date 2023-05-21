package com.practice.weather.main;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
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
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    @BeforeEach
    public void setupExpectation() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("main 화면 테스트")
    public void mainViewTest() throws Exception {
        mockMvc.perform(get("/mainView"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("midTermList 화면 테스트")
    public void midTermListViewTest() throws Exception {
        mockMvc.perform(get("/mid-term"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("shortTermList 화면 테스트")
    public void shortTermListViewTest() throws Exception {
        mockMvc.perform(get("/short-term"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("shortTermLocation 화면 테스트")
    public void shortTermLocationTest() throws Exception {
        mockMvc.perform(get("/short-term/location"))
                .andExpect(status().isOk());
    }


}
