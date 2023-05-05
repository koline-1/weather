//package com.practice.weather.integrationTest;
//
//import com.practice.weather.entity.WeatherEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.core.IsNull.notNullValue;
//
//
//class ControllerTest extends BaseIntegrationTest{
//
//    @Test
//    public void 메인() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 중기() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/mid-term")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 중기해상예보조회() throws Exception {
//
//        String time = "0600";
//        ResultActions resultActions = mvc.perform(get("/mid-term/ocean?time={time}", time)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 중기기온조회() throws Exception {
//
//        String time = "0600";
//        ResultActions resultActions = mvc.perform(get("/mid-term/temperature?time={time}", time)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 중기육상예보조회() throws Exception {
//
//        String time = "0600";
//        ResultActions resultActions = mvc.perform(get("/mid-term/land?time={time}", time)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 중기전망조회() throws Exception {
//
//        String time = "0600";
//        ResultActions resultActions = mvc.perform(get("/mid-term/expectation?time={time}", time)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 단기() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/short-term")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 초단기실황조회() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/short-term/status")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 초단기예보조회() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/short-term/extraExpectation")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 단기예보조회() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/short-term/expectation")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 예보버전조회() throws Exception {
//
//        ResultActions resultActions = mvc.perform(get("/short-term/version")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void 유저생성() throws Exception {
//
//        WeatherEntity weatherEntity = new WeatherEntity();
//        weatherEntity.setId("testId");
//        weatherEntity.setData("testData");
//
//        ResultActions resultActions = mvc.perform(post("/short-term/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(weatherEntity))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id", is(notNullValue())));
//    }
//
//}
