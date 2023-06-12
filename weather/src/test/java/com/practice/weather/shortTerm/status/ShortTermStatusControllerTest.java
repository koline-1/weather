package com.practice.weather.shortTerm.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.status.controller.ShortTermStatusController;
import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import com.practice.weather.shortTerm.status.repository.ShortTermStatusRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    private ShortTermStatusRepository shortTermStatusRepository;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupStatus() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            shortTermStatusEntity = ShortTermStatusEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i).nxValue("nxValue"+i)
                    .nyValue("nyValue"+i).temperature("temperature"+i).hourPrecipitation("hourPrecipitation"+i).horizontalWind("horizontalWind"+i)
                    .verticalWind("verticalWind"+i).humidity("humidity"+i).rainType("rainType"+i).windDirection("windDirection"+i)
                    .windSpeed("windSpeed"+i).version("version"+i).build();
            shortTermStatusRepository.save(shortTermStatusEntity);
        }
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
                        (ResponseEntity.ok(shortTermStatusEntity))
                );

        HashMap<String, Object> map = new HashMap<>();

        map.put("data", shortTermStatusEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/short-term/status/current")
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

    @Test
    @DisplayName("중기 예보 조회 리스트 테스트")
    public void getShortTermStatusListTest() throws Exception {

        List<ShortTermStatusEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            shortTermStatusEntity = ShortTermStatusEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i).nxValue("nxValue"+i)
                    .nyValue("nyValue"+i).temperature("temperature"+i).hourPrecipitation("hourPrecipitation"+i).horizontalWind("horizontalWind"+i)
                    .verticalWind("verticalWind"+i).humidity("humidity"+i).rainType("rainType"+i).windDirection("windDirection"+i)
                    .windSpeed("windSpeed"+i).version("version"+i).build();
            list.add(shortTermStatusEntity);
        }

        //given
        given(shortTermStatusController.getShortTermStatusList(any(Pageable.class), any(), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/status/list"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 리스트 테스트")
    public void getShortTermStatusListTest2() throws Exception {

        List<ShortTermStatusEntity> list = new ArrayList<>();

        long id = 4;

        shortTermStatusEntity = ShortTermStatusEntity.builder().id(id).baseDate("baseDate"+id).baseTime("baseTime"+id)
                .nxValue("nxValue"+id).nyValue("nyValue"+id).temperature("temperature"+id).hourPrecipitation("hourPrecipitation"+id)
                .horizontalWind("horizontalWind"+id).verticalWind("verticalWind"+id).humidity("humidity"+id).rainType("rainType"+id)
                .windDirection("windDirection"+id).windSpeed("windSpeed"+id).version("version"+id).build();
        list.add(shortTermStatusEntity);

        //given
        given(shortTermStatusController.getShortTermStatusList(any(Pageable.class), any(), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/status/list")
                .param("nxValue", "nxValue"+id)
                .param("nyValue", "nyValue"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 예보 조회 카운트 테스트")
    public void countShortTermStatusTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        //given
        given(shortTermStatusController.countShortTermStatus(any(), any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/status/count"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 카운트 테스트")
    public void countShortTermStatusTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (stnId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        //given
        given(shortTermStatusController.countShortTermStatus(any(), any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/status/count")
                .param("nxValue", "nxValue1")
                .param("nyValue", "nyValue1"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 예보 조회 조회 테스트")
    public void readShortTermStatusTest() throws Exception {

        long id = 8;

        shortTermStatusEntity = ShortTermStatusEntity.builder().id(id).baseDate("baseDate"+id).baseTime("baseTime"+id)
                .nxValue("nxValue"+id).nyValue("nyValue"+id).temperature("temperature"+id).hourPrecipitation("hourPrecipitation"+id)
                .horizontalWind("horizontalWind"+id).verticalWind("verticalWind"+id).humidity("humidity"+id).rainType("rainType"+id)
                .windDirection("windDirection"+id).windSpeed("windSpeed"+id).version("version"+id).build();

        //given
        given(shortTermStatusController.readShortTermStatus(any()))
                .willReturn(
                        ResponseEntity.ok(shortTermStatusEntity)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/status/"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.baseDate").value("baseDate"+id))
                .andExpect(jsonPath("$.baseTime").value("baseTime"+id))
                .andExpect(jsonPath("$.nxValue").value("nxValue"+id))
                .andExpect(jsonPath("$.nyValue").value("nyValue"+id))
                .andExpect(jsonPath("$.temperature").value("temperature"+id))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"+id))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"+id))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"+id))
                .andExpect(jsonPath("$.humidity").value("humidity"+id))
                .andExpect(jsonPath("$.rainType").value("rainType"+id))
                .andExpect(jsonPath("$.windDirection").value("windDirection"+id))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"+id))
                .andExpect(jsonPath("$.version").value("version"+id));
    }

    @Test
    @DisplayName("중기 예보 조회 수정 테스트")
    public void patchShortTermStatusTest() throws Exception {

        long id = 3;

        shortTermStatusEntity = ShortTermStatusEntity.builder().id(id).baseDate("baseDate"+id+"updated").baseTime("baseTime"+id+"updated")
                .nxValue("nxValue"+id+"updated").nyValue("nyValue"+id+"updated").temperature("temperature"+id+"updated")
                .hourPrecipitation("hourPrecipitation"+id+"updated").horizontalWind("horizontalWind"+id+"updated")
                .verticalWind("verticalWind"+id+"updated").humidity("humidity"+id+"updated").rainType("rainType"+id+"updated")
                .windDirection("windDirection"+id+"updated").windSpeed("windSpeed"+id+"updated").version("version"+id+"updated").build();

        //given
        given(shortTermStatusController.patchShortTermStatus(any(), any()))
                .willReturn(
                        ResponseEntity.ok(shortTermStatusEntity)
                );

        final ResultActions actions = mockMvc.perform(patch("/short-term/status/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(shortTermStatusEntity))
                .contentType(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.baseDate").value("baseDate"+id+"updated"))
                .andExpect(jsonPath("$.baseTime").value("baseTime"+id+"updated"))
                .andExpect(jsonPath("$.nxValue").value("nxValue"+id+"updated"))
                .andExpect(jsonPath("$.nyValue").value("nyValue"+id+"updated"))
                .andExpect(jsonPath("$.temperature").value("temperature"+id+"updated"))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"+id+"updated"))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"+id+"updated"))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"+id+"updated"))
                .andExpect(jsonPath("$.humidity").value("humidity"+id+"updated"))
                .andExpect(jsonPath("$.rainType").value("rainType"+id+"updated"))
                .andExpect(jsonPath("$.windDirection").value("windDirection"+id+"updated"))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"+id+"updated"))
                .andExpect(jsonPath("$.version").value("version"+id+"updated"));
    }

    @Test
    @DisplayName("중기 예보 조회 삭제 테스트")
    public void deleteShortTermStatusTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        //given
        given(shortTermStatusController.deleteShortTermStatus(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(delete("/short-term/status/"+id)
                .param("id", String.valueOf(id)));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
