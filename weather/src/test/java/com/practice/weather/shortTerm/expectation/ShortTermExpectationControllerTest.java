package com.practice.weather.shortTerm.expectation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.expectation.controller.ShortTermExpectationController;
import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import com.practice.weather.shortTerm.expectation.repository.ShortTermExpectationRepository;
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

import javax.transaction.Transactional;
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
@Transactional
public class ShortTermExpectationControllerTest {

    @Mock
    private ShortTermExpectationEntity shortTermExpectationEntity;

    @MockBean
    private ShortTermExpectationController shortTermExpectationController;

    @Autowired
    private ShortTermExpectationRepository shortTermExpectationRepository;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupExpectation() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i)
                    .forecastDate("forecastDate"+i).forecastTime("forecastTime"+i).nxValue("nxValue"+i).nyValue("nyValue"+i)
                    .hourTemperature("hourTemperature"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .windDirection("windDirection"+i).windSpeed("windSpeed"+i).skyStatus("skyStatus"+i).rainType("rainType"+i)
                    .rainPossibility("rainPossibility"+i).waveHeight("waveHeight"+i).hourPrecipitation("hourPrecipitation"+i)
                    .snowDepth("snowDepth"+i).humidity("humidity"+i).minimumTemperature("minimumTemperature"+i)
                    .maximumTemperature("maximumTemperature"+i).version("version"+i).build();
            shortTermExpectationRepository.save(shortTermExpectationEntity);
        }
    }

    @Test
    @DisplayName("shortTermExpectation data DB save 테스트")
    public void saveShortTermExpectationTest() throws Exception {

        List<ShortTermExpectationEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i)
                    .forecastDate("forecastDate"+i).forecastTime("forecastTime"+i).nxValue("nxValue"+i).nyValue("nyValue"+i)
                    .hourTemperature("hourTemperature"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .windDirection("windDirection"+i).windSpeed("windSpeed"+i).skyStatus("skyStatus"+i).rainType("rainType"+i)
                    .rainPossibility("rainPossibility"+i).waveHeight("waveHeight"+i).hourPrecipitation("hourPrecipitation"+i)
                    .snowDepth("snowDepth"+i).humidity("humidity"+i).minimumTemperature("minimumTemperature"+i)
                    .maximumTemperature("maximumTemperature"+i).version("version"+i).build();
            list.add(shortTermExpectationEntity);
        }

        HashMap<String, Object> map = new HashMap<>();

        map.put("data", list);

        JSONObject jObject = new JSONObject(map);

        HashMap<String, String> expectedResult = new HashMap<>();

        expectedResult.put("count", "10");

        // given
        given(shortTermExpectationController.saveShortTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(objectMapper.writeValueAsString(expectedResult))
                );

        // when
        final ResultActions actions = mockMvc.perform(post("/short-term/expectation/current")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));

    }

    @Test
    @DisplayName("중기 예보 조회 리스트 테스트")
    public void getShortTermExpectationListTest() throws Exception {

        List<ShortTermExpectationEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i)
                    .forecastDate("forecastDate"+i).forecastTime("forecastTime"+i).nxValue("nxValue"+i).nyValue("nyValue"+i)
                    .hourTemperature("hourTemperature"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .windDirection("windDirection"+i).windSpeed("windSpeed"+i).skyStatus("skyStatus"+i).rainType("rainType"+i)
                    .rainPossibility("rainPossibility"+i).waveHeight("waveHeight"+i).hourPrecipitation("hourPrecipitation"+i)
                    .snowDepth("snowDepth"+i).humidity("humidity"+i).minimumTemperature("minimumTemperature"+i)
                    .maximumTemperature("maximumTemperature"+i).version("version"+i).build();
            list.add(shortTermExpectationEntity);
        }

        //given
        given(shortTermExpectationController.getShortTermExpectationList(any(Pageable.class), any(), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/expectation/list"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 리스트 테스트")
    public void getShortTermExpectationListTest2() throws Exception {

        List<ShortTermExpectationEntity> list = new ArrayList<>();

        long id = 4;

        shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(id).baseDate("baseDate"+id).baseTime("baseTime"+id)
                .forecastDate("forecastDate"+id).forecastTime("forecastTime"+id).nxValue("nxValue"+id).nyValue("nyValue"+id)
                .hourTemperature("hourTemperature"+id).horizontalWind("horizontalWind"+id).verticalWind("verticalWind"+id)
                .windDirection("windDirection"+id).windSpeed("windSpeed"+id).skyStatus("skyStatus"+id).rainType("rainType"+id)
                .rainPossibility("rainPossibility"+id).waveHeight("waveHeight"+id).hourPrecipitation("hourPrecipitation"+id)
                .snowDepth("snowDepth"+id).humidity("humidity"+id).minimumTemperature("minimumTemperature"+id)
                .maximumTemperature("maximumTemperature"+id).version("version"+id).build();
        list.add(shortTermExpectationEntity);

        //given
        given(shortTermExpectationController.getShortTermExpectationList(any(Pageable.class), any(), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/expectation/list")
                .param("nxValue", "nxValue"+id)
                .param("nyValue", "nyValue"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 예보 조회 카운트 테스트")
    public void countShortTermExpectationTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        //given
        given(shortTermExpectationController.countShortTermExpectation(any(), any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/expectation/count"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 카운트 테스트")
    public void countShortTermExpectationTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (stnId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        //given
        given(shortTermExpectationController.countShortTermExpectation(any(), any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/expectation/count")
                .param("nxValue", "nxValue1")
                .param("nyValue", "nyValue1"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 예보 조회 조회 테스트")
    public void readShortTermExpectationTest() throws Exception {

        long id = 8;

        shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(id).baseDate("baseDate"+id).baseTime("baseTime"+id)
                .forecastDate("forecastDate"+id).forecastTime("forecastTime"+id).nxValue("nxValue"+id).nyValue("nyValue"+id)
                .hourTemperature("hourTemperature"+id).horizontalWind("horizontalWind"+id).verticalWind("verticalWind"+id)
                .windDirection("windDirection"+id).windSpeed("windSpeed"+id).skyStatus("skyStatus"+id).rainType("rainType"+id)
                .rainPossibility("rainPossibility"+id).waveHeight("waveHeight"+id).hourPrecipitation("hourPrecipitation"+id)
                .snowDepth("snowDepth"+id).humidity("humidity"+id).minimumTemperature("minimumTemperature"+id)
                .maximumTemperature("maximumTemperature"+id).version("version"+id).build();

        //given
        given(shortTermExpectationController.readShortTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(shortTermExpectationEntity)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/expectation/"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.baseDate").value("baseDate"+id))
                .andExpect(jsonPath("$.baseTime").value("baseTime"+id))
                .andExpect(jsonPath("$.forecastDate").value("forecastDate"+id))
                .andExpect(jsonPath("$.forecastTime").value("forecastTime"+id))
                .andExpect(jsonPath("$.nxValue").value("nxValue"+id))
                .andExpect(jsonPath("$.nyValue").value("nyValue"+id))
                .andExpect(jsonPath("$.hourTemperature").value("hourTemperature"+id))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"+id))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"+id))
                .andExpect(jsonPath("$.windDirection").value("windDirection"+id))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"+id))
                .andExpect(jsonPath("$.skyStatus").value("skyStatus"+id))
                .andExpect(jsonPath("$.rainType").value("rainType"+id))
                .andExpect(jsonPath("$.rainPossibility").value("rainPossibility"+id))
                .andExpect(jsonPath("$.waveHeight").value("waveHeight"+id))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"+id))
                .andExpect(jsonPath("$.snowDepth").value("snowDepth"+id))
                .andExpect(jsonPath("$.humidity").value("humidity"+id))
                .andExpect(jsonPath("$.minimumTemperature").value("minimumTemperature"+id))
                .andExpect(jsonPath("$.maximumTemperature").value("maximumTemperature"+id))
                .andExpect(jsonPath("$.version").value("version"+id));
    }

    @Test
    @DisplayName("중기 예보 조회 수정 테스트")
    public void patchShortTermExpectationTest() throws Exception {

        long id = 3;

        shortTermExpectationEntity = ShortTermExpectationEntity.builder().id(id).baseDate("baseDate"+id+"updated").baseTime("baseTime"+id+"updated")
                .forecastDate("forecastDate"+id+"updated").forecastTime("forecastTime"+id+"updated").nxValue("nxValue"+id+"updated")
                .nyValue("nyValue"+id+"updated").hourTemperature("hourTemperature"+id+"updated").horizontalWind("horizontalWind"+id+"updated")
                .verticalWind("verticalWind"+id+"updated").windDirection("windDirection"+id+"updated").windSpeed("windSpeed"+id+"updated")
                .skyStatus("skyStatus"+id+"updated").rainType("rainType"+id+"updated").rainPossibility("rainPossibility"+id+"updated")
                .waveHeight("waveHeight"+id+"updated").hourPrecipitation("hourPrecipitation"+id+"updated").snowDepth("snowDepth"+id+"updated")
                .humidity("humidity"+id+"updated").minimumTemperature("minimumTemperature"+id+"updated")
                .maximumTemperature("maximumTemperature"+id+"updated").version("version"+id+"updated").build();

        //given
        given(shortTermExpectationController.patchShortTermExpectation(any(), any()))
                .willReturn(
                        ResponseEntity.ok(shortTermExpectationEntity)
                );

        final ResultActions actions = mockMvc.perform(patch("/short-term/expectation/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(shortTermExpectationEntity))
                .contentType(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.baseDate").value("baseDate"+id+"updated"))
                .andExpect(jsonPath("$.baseTime").value("baseTime"+id+"updated"))
                .andExpect(jsonPath("$.forecastDate").value("forecastDate"+id+"updated"))
                .andExpect(jsonPath("$.forecastTime").value("forecastTime"+id+"updated"))
                .andExpect(jsonPath("$.nxValue").value("nxValue"+id+"updated"))
                .andExpect(jsonPath("$.nyValue").value("nyValue"+id+"updated"))
                .andExpect(jsonPath("$.hourTemperature").value("hourTemperature"+id+"updated"))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"+id+"updated"))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"+id+"updated"))
                .andExpect(jsonPath("$.windDirection").value("windDirection"+id+"updated"))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"+id+"updated"))
                .andExpect(jsonPath("$.skyStatus").value("skyStatus"+id+"updated"))
                .andExpect(jsonPath("$.rainType").value("rainType"+id+"updated"))
                .andExpect(jsonPath("$.rainPossibility").value("rainPossibility"+id+"updated"))
                .andExpect(jsonPath("$.waveHeight").value("waveHeight"+id+"updated"))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"+id+"updated"))
                .andExpect(jsonPath("$.snowDepth").value("snowDepth"+id+"updated"))
                .andExpect(jsonPath("$.humidity").value("humidity"+id+"updated"))
                .andExpect(jsonPath("$.minimumTemperature").value("minimumTemperature"+id+"updated"))
                .andExpect(jsonPath("$.maximumTemperature").value("maximumTemperature"+id+"updated"))
                .andExpect(jsonPath("$.version").value("version"+id+"updated"));
    }

    @Test
    @DisplayName("중기 예보 조회 삭제 테스트")
    public void deleteShortTermExpectationTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        //given
        given(shortTermExpectationController.deleteShortTermExpectation(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(delete("/short-term/expectation/"+id)
                .param("id", String.valueOf(id)));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
