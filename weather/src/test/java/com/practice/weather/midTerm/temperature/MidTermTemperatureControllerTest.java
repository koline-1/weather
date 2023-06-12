package com.practice.weather.midTerm.temperature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.temperature.controller.MidTermTemperatureController;
import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import com.practice.weather.midTerm.temperature.repository.MidTermTemperatureRepository;
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
public class MidTermTemperatureControllerTest {

    @Mock
    private MidTermTemperatureEntity midTermTemperatureEntity;

    @MockBean
    private MidTermTemperatureController midTermTemperatureController;

    @Autowired
    private MidTermTemperatureRepository midTermTemperatureRepository;
    
    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupTemperature() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            midTermTemperatureEntity = MidTermTemperatureEntity.builder().id(i).regId("regId"+i).taMin3("taMin3"+i).taMin3Low("taMin3Low"+i)
                    .taMin3High("taMin3High"+i).taMax3("taMax3").taMax3Low("taMax3Low"+i).taMax3High("taMax3High"+i).taMin4("taMin4"+i)
                    .taMin4Low("taMin4Low"+i).taMin4High("taMin4High"+i).taMax4("taMax4").taMax4Low("taMax4Low"+i).taMax4High("taMax4High"+i)
                    .taMin5("taMin5"+i).taMin5Low("taMin5Low"+i).taMin5High("taMin5High"+i).taMax5("taMax5").taMax5Low("taMax5Low"+i)
                    .taMax5High("taMax5High"+i).taMin6("taMin6"+i).taMin6Low("taMin6Low"+i).taMin6High("taMin6High"+i).taMax6("taMax6")
                    .taMax6Low("taMax6Low"+i).taMax6High("taMax6High"+i).taMin7("taMin7"+i).taMin7Low("taMin7Low"+i).taMin7High("taMin7High"+i)
                    .taMax7("taMax7").taMax7Low("taMax7Low"+i).taMax7High("taMax7High"+i).taMin8("taMin8"+i).taMin8Low("taMin8Low"+i)
                    .taMin8High("taMin8High"+i).taMax8("taMax8").taMax8Low("taMax8Low"+i).taMax8High("taMax8High"+i).taMin9("taMin9"+i)
                    .taMin9Low("taMin9Low"+i).taMin9High("taMin9High"+i).taMax9("taMax9").taMax9Low("taMax9Low"+i).taMax9High("taMax9High"+i)
                    .taMin10("taMin10"+i).taMin10Low("taMin10Low"+i).taMin10High("taMin10High").taMax10("taMax10"+i).taMax10Low("taMax10Low"+i)
                    .taMax10High("taMax10High"+i).build();
            midTermTemperatureRepository.save(midTermTemperatureEntity);
        }
    }

    @Test
    @DisplayName("midTermTemperature data DB save 테스트")
    public void saveMidTermTemperatureTest() throws Exception {

        midTermTemperatureEntity = MidTermTemperatureEntity.builder().regId("regId").taMin3("taMin3").taMin3Low("taMin3Low").taMin3High("taMin3High")
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
                        ResponseEntity.ok(midTermTemperatureEntity)
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
                .andExpect(jsonPath("$.regId").value("regId"))
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

    @Test
    @DisplayName("중기 예보 조회 리스트 테스트")
    public void getMidTermTemperatureListTest() throws Exception {

        List<MidTermTemperatureEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            midTermTemperatureEntity = MidTermTemperatureEntity.builder().id(i).regId("regId"+i).taMin3("taMin3"+i).taMin3Low("taMin3Low"+i)
                    .taMin3High("taMin3High"+i).taMax3("taMax3").taMax3Low("taMax3Low"+i).taMax3High("taMax3High"+i).taMin4("taMin4"+i)
                    .taMin4Low("taMin4Low"+i).taMin4High("taMin4High"+i).taMax4("taMax4").taMax4Low("taMax4Low"+i).taMax4High("taMax4High"+i)
                    .taMin5("taMin5"+i).taMin5Low("taMin5Low"+i).taMin5High("taMin5High"+i).taMax5("taMax5").taMax5Low("taMax5Low"+i)
                    .taMax5High("taMax5High"+i).taMin6("taMin6"+i).taMin6Low("taMin6Low"+i).taMin6High("taMin6High"+i).taMax6("taMax6")
                    .taMax6Low("taMax6Low"+i).taMax6High("taMax6High"+i).taMin7("taMin7"+i).taMin7Low("taMin7Low"+i).taMin7High("taMin7High"+i)
                    .taMax7("taMax7").taMax7Low("taMax7Low"+i).taMax7High("taMax7High"+i).taMin8("taMin8"+i).taMin8Low("taMin8Low"+i)
                    .taMin8High("taMin8High"+i).taMax8("taMax8").taMax8Low("taMax8Low"+i).taMax8High("taMax8High"+i).taMin9("taMin9"+i)
                    .taMin9Low("taMin9Low"+i).taMin9High("taMin9High"+i).taMax9("taMax9").taMax9Low("taMax9Low"+i).taMax9High("taMax9High"+i)
                    .taMin10("taMin10"+i).taMin10Low("taMin10Low"+i).taMin10High("taMin10High").taMax10("taMax10"+i).taMax10Low("taMax10Low"+i)
                    .taMax10High("taMax10High"+i).build();
            list.add(midTermTemperatureEntity);
        }

        // given
        given(midTermTemperatureController.getMidTermTemperatureList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/temperature/list"));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 리스트 테스트")
    public void getMidTermTemperatureListTest2() throws Exception {

        List<MidTermTemperatureEntity> list = new ArrayList<>();

        long id = 4;

        midTermTemperatureEntity = MidTermTemperatureEntity.builder().id(id).regId("regId"+id).taMin3("taMin3"+id).taMin3Low("taMin3Low"+id)
                .taMin3High("taMin3High"+id).taMax3("taMax3"+id).taMax3Low("taMax3Low"+id).taMax3High("taMax3High"+id).taMin4("taMin4"+id)
                .taMin4Low("taMin4Low"+id).taMin4High("taMin4High"+id).taMax4("taMax4"+id).taMax4Low("taMax4Low"+id).taMax4High("taMax4High"+id)
                .taMin5("taMin5"+id).taMin5Low("taMin5Low"+id).taMin5High("taMin5High"+id).taMax5("taMax5"+id).taMax5Low("taMax5Low"+id)
                .taMax5High("taMax5High"+id).taMin6("taMin6"+id).taMin6Low("taMin6Low"+id).taMin6High("taMin6High"+id).taMax6("taMax6"+id)
                .taMax6Low("taMax6Low"+id).taMax6High("taMax6High"+id).taMin7("taMin7"+id).taMin7Low("taMin7Low"+id).taMin7High("taMin7High"+id)
                .taMax7("taMax7"+id).taMax7Low("taMax7Low"+id).taMax7High("taMax7High"+id).taMin8("taMin8"+id).taMin8Low("taMin8Low"+id)
                .taMin8High("taMin8High"+id).taMax8("taMax8"+id).taMax8Low("taMax8Low"+id).taMax8High("taMax8High"+id).taMin9("taMin9"+id)
                .taMin9Low("taMin9Low"+id).taMin9High("taMin9High"+id).taMax9("taMax9"+id).taMax9Low("taMax9Low"+id).taMax9High("taMax9High"+id)
                .taMin10("taMin10"+id).taMin10Low("taMin10Low"+id).taMin10High("taMin10High"+id).taMax10("taMax10"+id).taMax10Low("taMax10Low"+id)
                .taMax10High("taMax10High"+id).build();
        list.add(midTermTemperatureEntity);

        // given
        given(midTermTemperatureController.getMidTermTemperatureList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/temperature/list")
                .param("location", "regId"+id));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 예보 조회 카운트 테스트")
    public void countMidTermTemperatureTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        // given
        given(midTermTemperatureController.countMidTermTemperature(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/temperature/count"));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 카운트 테스트")
    public void countMidTermTemperatureTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (regId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        //given
        given(midTermTemperatureController.countMidTermTemperature(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/temperature/count")
                .param("location", "regId1"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 예보 조회 조회 테스트")
    public void readMidTermTemperatureTest() throws Exception {

        long id = 8;

        midTermTemperatureEntity = MidTermTemperatureEntity.builder().id(id).regId("regId"+id).taMin3("taMin3"+id).taMin3Low("taMin3Low"+id)
                .taMin3High("taMin3High"+id).taMax3("taMax3"+id).taMax3Low("taMax3Low"+id).taMax3High("taMax3High"+id).taMin4("taMin4"+id)
                .taMin4Low("taMin4Low"+id).taMin4High("taMin4High"+id).taMax4("taMax4"+id).taMax4Low("taMax4Low"+id).taMax4High("taMax4High"+id)
                .taMin5("taMin5"+id).taMin5Low("taMin5Low"+id).taMin5High("taMin5High"+id).taMax5("taMax5"+id).taMax5Low("taMax5Low"+id)
                .taMax5High("taMax5High"+id).taMin6("taMin6"+id).taMin6Low("taMin6Low"+id).taMin6High("taMin6High"+id).taMax6("taMax6"+id)
                .taMax6Low("taMax6Low"+id).taMax6High("taMax6High"+id).taMin7("taMin7"+id).taMin7Low("taMin7Low"+id).taMin7High("taMin7High"+id)
                .taMax7("taMax7"+id).taMax7Low("taMax7Low"+id).taMax7High("taMax7High"+id).taMin8("taMin8"+id).taMin8Low("taMin8Low"+id)
                .taMin8High("taMin8High"+id).taMax8("taMax8"+id).taMax8Low("taMax8Low"+id).taMax8High("taMax8High"+id).taMin9("taMin9"+id)
                .taMin9Low("taMin9Low"+id).taMin9High("taMin9High"+id).taMax9("taMax9"+id).taMax9Low("taMax9Low"+id).taMax9High("taMax9High"+id)
                .taMin10("taMin10"+id).taMin10Low("taMin10Low"+id).taMin10High("taMin10High"+id).taMax10("taMax10"+id).taMax10Low("taMax10Low"+id)
                .taMax10High("taMax10High"+id).build();

        // given
        given(midTermTemperatureController.readMidTermTemperature(any()))
                .willReturn(
                        ResponseEntity.ok(midTermTemperatureEntity)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/temperature/"+id));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.regId").value("regId"+id))
                .andExpect(jsonPath("$.taMin3").value("taMin3"+id))
                .andExpect(jsonPath("$.taMin3Low").value("taMin3Low"+id))
                .andExpect(jsonPath("$.taMin3High").value("taMin3High"+id))
                .andExpect(jsonPath("$.taMax3").value("taMax3"+id))
                .andExpect(jsonPath("$.taMax3Low").value("taMax3Low"+id))
                .andExpect(jsonPath("$.taMax3High").value("taMax3High"+id))
                .andExpect(jsonPath("$.taMin4").value("taMin4"+id))
                .andExpect(jsonPath("$.taMin4Low").value("taMin4Low"+id))
                .andExpect(jsonPath("$.taMin4High").value("taMin4High"+id))
                .andExpect(jsonPath("$.taMax4").value("taMax4"+id))
                .andExpect(jsonPath("$.taMax4Low").value("taMax4Low"+id))
                .andExpect(jsonPath("$.taMax4High").value("taMax4High"+id))
                .andExpect(jsonPath("$.taMin5").value("taMin5"+id))
                .andExpect(jsonPath("$.taMin5Low").value("taMin5Low"+id))
                .andExpect(jsonPath("$.taMin5High").value("taMin5High"+id))
                .andExpect(jsonPath("$.taMax5").value("taMax5"+id))
                .andExpect(jsonPath("$.taMax5Low").value("taMax5Low"+id))
                .andExpect(jsonPath("$.taMax5High").value("taMax5High"+id))
                .andExpect(jsonPath("$.taMin6").value("taMin6"+id))
                .andExpect(jsonPath("$.taMin6Low").value("taMin6Low"+id))
                .andExpect(jsonPath("$.taMin6High").value("taMin6High"+id))
                .andExpect(jsonPath("$.taMax6").value("taMax6"+id))
                .andExpect(jsonPath("$.taMax6Low").value("taMax6Low"+id))
                .andExpect(jsonPath("$.taMax6High").value("taMax6High"+id))
                .andExpect(jsonPath("$.taMin7").value("taMin7"+id))
                .andExpect(jsonPath("$.taMin7Low").value("taMin7Low"+id))
                .andExpect(jsonPath("$.taMin7High").value("taMin7High"+id))
                .andExpect(jsonPath("$.taMax7").value("taMax7"+id))
                .andExpect(jsonPath("$.taMax7Low").value("taMax7Low"+id))
                .andExpect(jsonPath("$.taMax7High").value("taMax7High"+id))
                .andExpect(jsonPath("$.taMin8").value("taMin8"+id))
                .andExpect(jsonPath("$.taMin8Low").value("taMin8Low"+id))
                .andExpect(jsonPath("$.taMin8High").value("taMin8High"+id))
                .andExpect(jsonPath("$.taMax8").value("taMax8"+id))
                .andExpect(jsonPath("$.taMax8Low").value("taMax8Low"+id))
                .andExpect(jsonPath("$.taMax8High").value("taMax8High"+id))
                .andExpect(jsonPath("$.taMin9").value("taMin9"+id))
                .andExpect(jsonPath("$.taMin9Low").value("taMin9Low"+id))
                .andExpect(jsonPath("$.taMin9High").value("taMin9High"+id))
                .andExpect(jsonPath("$.taMax9").value("taMax9"+id))
                .andExpect(jsonPath("$.taMax9Low").value("taMax9Low"+id))
                .andExpect(jsonPath("$.taMax9High").value("taMax9High"+id))
                .andExpect(jsonPath("$.taMin10").value("taMin10"+id))
                .andExpect(jsonPath("$.taMin10Low").value("taMin10Low"+id))
                .andExpect(jsonPath("$.taMin10High").value("taMin10High"+id))
                .andExpect(jsonPath("$.taMax10").value("taMax10"+id))
                .andExpect(jsonPath("$.taMax10Low").value("taMax10Low"+id))
                .andExpect(jsonPath("$.taMax10High").value("taMax10High"+id));
    }

    @Test
    @DisplayName("중기 예보 조회 수정 테스트")
    public void patchMidTermTemperatureTest() throws Exception {

        long id = 3;

        midTermTemperatureEntity = MidTermTemperatureEntity.builder().id(id).regId("regId"+id+"updated").taMin3("taMin3"+id+"updated")
                .taMin3Low("taMin3Low"+id+"updated").taMin3High("taMin3High"+id+"updated").taMax3("taMax3"+id+"updated")
                .taMax3Low("taMax3Low"+id+"updated").taMax3High("taMax3High"+id+"updated").taMin4("taMin4"+id+"updated")
                .taMin4Low("taMin4Low"+id+"updated").taMin4High("taMin4High"+id+"updated").taMax4("taMax4"+id+"updated")
                .taMax4Low("taMax4Low"+id+"updated").taMax4High("taMax4High"+id+"updated").taMin5("taMin5"+id+"updated")
                .taMin5Low("taMin5Low"+id+"updated").taMin5High("taMin5High"+id+"updated").taMax5("taMax5"+id+"updated")
                .taMax5Low("taMax5Low"+id+"updated").taMax5High("taMax5High"+id+"updated").taMin6("taMin6"+id+"updated")
                .taMin6Low("taMin6Low"+id+"updated").taMin6High("taMin6High"+id+"updated").taMax6("taMax6"+id+"updated")
                .taMax6Low("taMax6Low"+id+"updated").taMax6High("taMax6High"+id+"updated").taMin7("taMin7"+id+"updated")
                .taMin7Low("taMin7Low"+id+"updated").taMin7High("taMin7High"+id+"updated").taMax7("taMax7"+id+"updated")
                .taMax7Low("taMax7Low"+id+"updated").taMax7High("taMax7High"+id+"updated").taMin8("taMin8"+id+"updated")
                .taMin8Low("taMin8Low"+id+"updated").taMin8High("taMin8High"+id+"updated").taMax8("taMax8"+id+"updated")
                .taMax8Low("taMax8Low"+id+"updated").taMax8High("taMax8High"+id+"updated").taMin9("taMin9"+id+"updated")
                .taMin9Low("taMin9Low"+id+"updated").taMin9High("taMin9High"+id+"updated").taMax9("taMax9"+id+"updated")
                .taMax9Low("taMax9Low"+id+"updated").taMax9High("taMax9High"+id+"updated").taMin10("taMin10"+id+"updated")
                .taMin10Low("taMin10Low"+id+"updated").taMin10High("taMin10High"+id+"updated").taMax10("taMax10"+id+"updated")
                .taMax10Low("taMax10Low"+id+"updated").taMax10High("taMax10High"+id+"updated").build();

        // given
        given(midTermTemperatureController.patchMidTermTemperature(any(), any()))
                .willReturn(
                        ResponseEntity.ok(midTermTemperatureEntity)
                );

        // when
        final ResultActions actions = mockMvc.perform(patch("/mid-term/temperature/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(midTermTemperatureEntity))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.regId").value("regId"+id+"updated"))
                .andExpect(jsonPath("$.taMin3").value("taMin3"+id+"updated"))
                .andExpect(jsonPath("$.taMin3Low").value("taMin3Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin3High").value("taMin3High"+id+"updated"))
                .andExpect(jsonPath("$.taMax3").value("taMax3"+id+"updated"))
                .andExpect(jsonPath("$.taMax3Low").value("taMax3Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax3High").value("taMax3High"+id+"updated"))
                .andExpect(jsonPath("$.taMin4").value("taMin4"+id+"updated"))
                .andExpect(jsonPath("$.taMin4Low").value("taMin4Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin4High").value("taMin4High"+id+"updated"))
                .andExpect(jsonPath("$.taMax4").value("taMax4"+id+"updated"))
                .andExpect(jsonPath("$.taMax4Low").value("taMax4Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax4High").value("taMax4High"+id+"updated"))
                .andExpect(jsonPath("$.taMin5").value("taMin5"+id+"updated"))
                .andExpect(jsonPath("$.taMin5Low").value("taMin5Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin5High").value("taMin5High"+id+"updated"))
                .andExpect(jsonPath("$.taMax5").value("taMax5"+id+"updated"))
                .andExpect(jsonPath("$.taMax5Low").value("taMax5Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax5High").value("taMax5High"+id+"updated"))
                .andExpect(jsonPath("$.taMin6").value("taMin6"+id+"updated"))
                .andExpect(jsonPath("$.taMin6Low").value("taMin6Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin6High").value("taMin6High"+id+"updated"))
                .andExpect(jsonPath("$.taMax6").value("taMax6"+id+"updated"))
                .andExpect(jsonPath("$.taMax6Low").value("taMax6Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax6High").value("taMax6High"+id+"updated"))
                .andExpect(jsonPath("$.taMin7").value("taMin7"+id+"updated"))
                .andExpect(jsonPath("$.taMin7Low").value("taMin7Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin7High").value("taMin7High"+id+"updated"))
                .andExpect(jsonPath("$.taMax7").value("taMax7"+id+"updated"))
                .andExpect(jsonPath("$.taMax7Low").value("taMax7Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax7High").value("taMax7High"+id+"updated"))
                .andExpect(jsonPath("$.taMin8").value("taMin8"+id+"updated"))
                .andExpect(jsonPath("$.taMin8Low").value("taMin8Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin8High").value("taMin8High"+id+"updated"))
                .andExpect(jsonPath("$.taMax8").value("taMax8"+id+"updated"))
                .andExpect(jsonPath("$.taMax8Low").value("taMax8Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax8High").value("taMax8High"+id+"updated"))
                .andExpect(jsonPath("$.taMin9").value("taMin9"+id+"updated"))
                .andExpect(jsonPath("$.taMin9Low").value("taMin9Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin9High").value("taMin9High"+id+"updated"))
                .andExpect(jsonPath("$.taMax9").value("taMax9"+id+"updated"))
                .andExpect(jsonPath("$.taMax9Low").value("taMax9Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax9High").value("taMax9High"+id+"updated"))
                .andExpect(jsonPath("$.taMin10").value("taMin10"+id+"updated"))
                .andExpect(jsonPath("$.taMin10Low").value("taMin10Low"+id+"updated"))
                .andExpect(jsonPath("$.taMin10High").value("taMin10High"+id+"updated"))
                .andExpect(jsonPath("$.taMax10").value("taMax10"+id+"updated"))
                .andExpect(jsonPath("$.taMax10Low").value("taMax10Low"+id+"updated"))
                .andExpect(jsonPath("$.taMax10High").value("taMax10High"+id+"updated"));
    }

    @Test
    @DisplayName("중기 예보 조회 삭제 테스트")
    public void deleteMidTermTemperatureTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        // given
        given(midTermTemperatureController.deleteMidTermTemperature(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(delete("/mid-term/temperature/"+id)
                .param("id", String.valueOf(id)));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
