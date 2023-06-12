package com.practice.weather.shortTerm.extra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.shortTerm.extra.entity.ShortTermExtraEntity;
import com.practice.weather.shortTerm.extra.controller.ShortTermExtraController;
import com.practice.weather.shortTerm.extra.repository.ShortTermExtraRepository;
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
import org.springframework.transaction.annotation.Transactional;

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
public class ShortTermExtraControllerTest {

    @Mock
    private ShortTermExtraEntity shortTermExtraEntity;

    @MockBean
    private ShortTermExtraController shortTermExtraController;

    @Autowired
    private ShortTermExtraRepository shortTermExtraRepository;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupExtra() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            shortTermExtraEntity = ShortTermExtraEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i).forecastDate("forecastDate"+i)
                    .forecastTime("forecastTime"+i).nxValue("nxValue"+i).nyValue("nyValue"+i).temperature("temperature"+i)
                    .hourPrecipitation("hourPrecipitation"+i).skyStatus("skyStatus"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .humidity("humidity"+i).rainType("rainType"+i).lightning("lightning"+i).windDirection("windDirection"+i).windSpeed("windSpeed"+i)
                    .version("version"+i).build();
            shortTermExtraRepository.save(shortTermExtraEntity);
        }
    }

    @Test
    @DisplayName("shortTermExtra data DB save 테스트")
    public void saveShortTermExtraTest() throws Exception {

        List<ShortTermExtraEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            shortTermExtraEntity = ShortTermExtraEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i).forecastDate("forecastDate"+i)
                    .forecastTime("forecastTime"+i).nxValue("nxValue"+i).nyValue("nyValue"+i).temperature("temperature"+i)
                    .hourPrecipitation("hourPrecipitation"+i).skyStatus("skyStatus"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .humidity("humidity"+i).rainType("rainType"+i).lightning("lightning"+i).windDirection("windDirection"+i).windSpeed("windSpeed"+i)
                    .version("version"+i).build();
            list.add(shortTermExtraEntity);
        }

        HashMap<String, Object> map = new HashMap<>();

        map.put("data", list);

        JSONObject jObject = new JSONObject(map);

        HashMap<String, String> expectedResult = new HashMap<>();

        expectedResult.put("count", "10");

        // given
        given(shortTermExtraController.saveShortTermExtra(any()))
                .willReturn(
                        ResponseEntity.ok(objectMapper.writeValueAsString(expectedResult))
                );

        // when
        final ResultActions actions = mockMvc.perform(post("/short-term/extra/current")
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
    public void getShortTermExtraListTest() throws Exception {

        List<ShortTermExtraEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            shortTermExtraEntity = ShortTermExtraEntity.builder().id(i).baseDate("baseDate"+i).baseTime("baseTime"+i).forecastDate("forecastDate"+i)
                    .forecastTime("forecastTime"+i).nxValue("nxValue"+i).nyValue("nyValue"+i).temperature("temperature"+i)
                    .hourPrecipitation("hourPrecipitation"+i).skyStatus("skyStatus"+i).horizontalWind("horizontalWind"+i).verticalWind("verticalWind"+i)
                    .humidity("humidity"+i).rainType("rainType"+i).lightning("lightning"+i).windDirection("windDirection"+i).windSpeed("windSpeed"+i)
                    .version("version"+i).build();
            list.add(shortTermExtraEntity);
        }

        //given
        given(shortTermExtraController.getShortTermExtraList(any(Pageable.class), any(), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/extra/list"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 리스트 테스트")
    public void getShortTermExtraListTest2() throws Exception {

        List<ShortTermExtraEntity> list = new ArrayList<>();

        long id = 4;

        shortTermExtraEntity = ShortTermExtraEntity.builder().id(id).baseDate("baseDate"+id).baseTime("baseTime"+id)
                .forecastDate("forecastDate"+id).forecastTime("forecastTime"+id).nxValue("nxValue"+id).nyValue("nyValue"+id)
                .temperature("temperature"+id).hourPrecipitation("hourPrecipitation"+id).skyStatus("skyStatus"+id)
                .horizontalWind("horizontalWind"+id).verticalWind("verticalWind"+id).humidity("humidity"+id).rainType("rainType"+id)
                .lightning("lightning"+id).windDirection("windDirection"+id).windSpeed("windSpeed"+id).version("version"+id).build();
        list.add(shortTermExtraEntity);

        //given
        given(shortTermExtraController.getShortTermExtraList(any(Pageable.class), any(), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/extra/list")
                .param("nxValue", "nxValue"+id)
                .param("nyValue", "nyValue"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 예보 조회 카운트 테스트")
    public void countShortTermExtraTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        //given
        given(shortTermExtraController.countShortTermExtra(any(), any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/extra/count"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 카운트 테스트")
    public void countShortTermExtraTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (stnId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        //given
        given(shortTermExtraController.countShortTermExtra(any(), any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/extra/count")
                .param("nxValue", "nxValue1")
                .param("nyValue", "nyValue1"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 예보 조회 조회 테스트")
    public void readShortTermExtraTest() throws Exception {

        long id = 8;

        shortTermExtraEntity = ShortTermExtraEntity.builder().id(id).baseDate("baseDate"+id).baseTime("baseTime"+id)
                .forecastDate("forecastDate"+id).forecastTime("forecastTime"+id).nxValue("nxValue"+id).nyValue("nyValue"+id)
                .temperature("temperature"+id).hourPrecipitation("hourPrecipitation"+id).skyStatus("skyStatus"+id)
                .horizontalWind("horizontalWind"+id).verticalWind("verticalWind"+id).humidity("humidity"+id).rainType("rainType"+id)
                .lightning("lightning"+id).windDirection("windDirection"+id).windSpeed("windSpeed"+id).version("version"+id).build();

        //given
        given(shortTermExtraController.readShortTermExtra(any()))
                .willReturn(
                        ResponseEntity.ok(shortTermExtraEntity)
                );

        final ResultActions actions = mockMvc.perform(get("/short-term/extra/"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.baseDate").value("baseDate"+id))
                .andExpect(jsonPath("$.baseTime").value("baseTime"+id))
                .andExpect(jsonPath("$.forecastDate").value("forecastDate"+id))
                .andExpect(jsonPath("$.forecastTime").value("forecastTime"+id))
                .andExpect(jsonPath("$.nxValue").value("nxValue"+id))
                .andExpect(jsonPath("$.nyValue").value("nyValue"+id))
                .andExpect(jsonPath("$.temperature").value("temperature"+id))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"+id))
                .andExpect(jsonPath("$.skyStatus").value("skyStatus"+id))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"+id))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"+id))
                .andExpect(jsonPath("$.humidity").value("humidity"+id))
                .andExpect(jsonPath("$.rainType").value("rainType"+id))
                .andExpect(jsonPath("$.lightning").value("lightning"+id))
                .andExpect(jsonPath("$.windDirection").value("windDirection"+id))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"+id))
                .andExpect(jsonPath("$.version").value("version"+id));
    }

    @Test
    @DisplayName("중기 예보 조회 수정 테스트")
    public void patchShortTermExtraTest() throws Exception {

        long id = 3;

        shortTermExtraEntity = ShortTermExtraEntity.builder().id(id).baseDate("baseDate"+id+"updated").baseTime("baseTime"+id+"updated")
                .forecastDate("forecastDate"+id+"updated").forecastTime("forecastTime"+id+"updated").nxValue("nxValue"+id+"updated")
                .nyValue("nyValue"+id+"updated").temperature("temperature"+id+"updated").hourPrecipitation("hourPrecipitation"+id+"updated")
                .skyStatus("skyStatus"+id+"updated").horizontalWind("horizontalWind"+id+"updated").verticalWind("verticalWind"+id+"updated")
                .humidity("humidity"+id+"updated").rainType("rainType"+id+"updated").lightning("lightning"+id+"updated")
                .windDirection("windDirection"+id+"updated").windSpeed("windSpeed"+id+"updated").version("version"+id+"updated").build();

        //given
        given(shortTermExtraController.patchShortTermExtra(any(), any()))
                .willReturn(
                        ResponseEntity.ok(shortTermExtraEntity)
                );

        final ResultActions actions = mockMvc.perform(patch("/short-term/extra/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(shortTermExtraEntity))
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
                .andExpect(jsonPath("$.temperature").value("temperature"+id+"updated"))
                .andExpect(jsonPath("$.hourPrecipitation").value("hourPrecipitation"+id+"updated"))
                .andExpect(jsonPath("$.skyStatus").value("skyStatus"+id+"updated"))
                .andExpect(jsonPath("$.horizontalWind").value("horizontalWind"+id+"updated"))
                .andExpect(jsonPath("$.verticalWind").value("verticalWind"+id+"updated"))
                .andExpect(jsonPath("$.humidity").value("humidity"+id+"updated"))
                .andExpect(jsonPath("$.rainType").value("rainType"+id+"updated"))
                .andExpect(jsonPath("$.lightning").value("lightning"+id+"updated"))
                .andExpect(jsonPath("$.windDirection").value("windDirection"+id+"updated"))
                .andExpect(jsonPath("$.windSpeed").value("windSpeed"+id+"updated"))
                .andExpect(jsonPath("$.version").value("version"+id+"updated"));
    }

    @Test
    @DisplayName("중기 예보 조회 삭제 테스트")
    public void deleteShortTermExtraTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        //given
        given(shortTermExtraController.deleteShortTermExtra(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(delete("/short-term/extra/"+id)
                .param("id", String.valueOf(id)));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
