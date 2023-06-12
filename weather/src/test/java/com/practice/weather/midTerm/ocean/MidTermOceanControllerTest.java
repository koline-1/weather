package com.practice.weather.midTerm.ocean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import com.practice.weather.midTerm.ocean.controller.MidTermOceanController;
import com.practice.weather.midTerm.ocean.repository.MidTermOceanRepository;
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
public class MidTermOceanControllerTest {

    @Mock
    private MidTermOceanEntity midTermOceanEntity;

    @MockBean
    private MidTermOceanController midTermOceanController;

    @Autowired
    private MidTermOceanRepository midTermOceanRepository;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupOcean() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            midTermOceanEntity = MidTermOceanEntity.builder().id(i).regId("regId"+i).wf8("wf8"+i).wh4AAm("wh4AAm"+i).wh4BAm("wh4BAm"+i)
                    .wh6BAm("wh6BAm"+i).wf9("wf9"+i).wh6AAm("wh6AAm"+i).wh9B("wh9B"+i).wh9A("wh9A"+i).wf6Pm("wf6Pm"+i).wf7Pm("wf7Pm"+i)
                    .wf10("wf10"+i).wh4APm("wh4APm"+i).wh6BPm("wh6BPm"+i).wh6APm("wh6APm"+i).wf3Pm("wf3Pm"+i).wf4Pm("wf4Pm"+i).wh4BPm("wh4BPm"+i)
                    .wf5Pm("wf5Pm"+i).wh7AAm("wh7AAm"+i).wf7Am("wf7Am"+i).wh3AAm("wh3AAm"+i).wh5BAm("wh5BAm"+i).wh5AAm("wh5AAm"+i).wf3Am("wf3Am"+i)
                    .wh7BAm("wh7BAm"+i).wh8B("wh8B"+i).wf4Am("wf4Am"+i).wh8A("wh8A"+i).wh10A("wh10A"+i).wf5Am("wf5Am"+i).wh3BAm("wh3BAm"+i)
                    .wh10B("wh10B"+i).wf6Am("wf6Am"+i).wh5BPm("wh5BPm"+i).wh5APm("wh5APm"+i).wh7APm("wh7APm"+i).wh7BPm("wh7BPm"+i)
                    .wh3APm("wh3APm"+i).wh3BPm("wh3BPm"+i).build();
            midTermOceanRepository.save(midTermOceanEntity);
        }
    }

    @Test
    @DisplayName("midTermOcean data DB save 테스트")
    public void saveMidTermOceanTest() throws Exception {

        midTermOceanEntity = MidTermOceanEntity.builder().regId("regId")
                .wf8("wf8").wh4AAm("wh4AAm").wh4BAm("wh4BAm").wh6BAm("wh6BAm").wf9("wf9").wh6AAm("wh6AAm").wh9B("wh9B").wh9A("wh9A")
                .wf6Pm("wf6Pm").wf7Pm("wf7Pm").wf10("wf10").wh4APm("wh4APm").wh6BPm("wh6BPm").wh6APm("wh6APm").wf3Pm("wf3Pm").wf4Pm("wf4Pm")
                .wh4BPm("wh4BPm").wf5Pm("wf5Pm").wh7AAm("wh7AAm").wf7Am("wf7Am").wh3AAm("wh3AAm").wh5BAm("wh5BAm").wh5AAm("wh5AAm").wf3Am("wf3Am")
                .wh7BAm("wh7BAm").wh8B("wh8B").wf4Am("wf4Am").wh8A("wh8A").wh10A("wh10A").wf5Am("wf5Am").wh3BAm("wh3BAm").wh10B("wh10B")
                .wf6Am("wf6Am").wh5BPm("wh5BPm").wh5APm("wh5APm").wh7APm("wh7APm").wh7BPm("wh7BPm").wh3APm("wh3APm").wh3BPm("wh3BPm").build();

        // given
        given(midTermOceanController.saveMidTermOcean(any()))
                .willReturn(
                        ResponseEntity.ok(midTermOceanEntity)
                );

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermOceanEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/mid-term/ocean/current")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.regId").value("regId"))
                .andExpect(jsonPath("$.wf8").value("wf8"))
                .andExpect(jsonPath("$.wh4AAm").value("wh4AAm"))
                .andExpect(jsonPath("$.wh4BAm").value("wh4BAm"))
                .andExpect(jsonPath("$.wh6BAm").value("wh6BAm"))
                .andExpect(jsonPath("$.wf9").value("wf9"))
                .andExpect(jsonPath("$.wh6AAm").value("wh6AAm"))
                .andExpect(jsonPath("$.wh9B").value("wh9B"))
                .andExpect(jsonPath("$.wh9A").value("wh9A"))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"))
                .andExpect(jsonPath("$.wf10").value("wf10"))
                .andExpect(jsonPath("$.wh4APm").value("wh4APm"))
                .andExpect(jsonPath("$.wh6BPm").value("wh6BPm"))
                .andExpect(jsonPath("$.wh6APm").value("wh6APm"))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"))
                .andExpect(jsonPath("$.wh4BPm").value("wh4BPm"))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"))
                .andExpect(jsonPath("$.wh7AAm").value("wh7AAm"))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"))
                .andExpect(jsonPath("$.wh3AAm").value("wh3AAm"))
                .andExpect(jsonPath("$.wh5BAm").value("wh5BAm"))
                .andExpect(jsonPath("$.wh5AAm").value("wh5AAm"))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"))
                .andExpect(jsonPath("$.wh7BAm").value("wh7BAm"))
                .andExpect(jsonPath("$.wh8B").value("wh8B"))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"))
                .andExpect(jsonPath("$.wh8A").value("wh8A"))
                .andExpect(jsonPath("$.wh10A").value("wh10A"))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"))
                .andExpect(jsonPath("$.wh3BAm").value("wh3BAm"))
                .andExpect(jsonPath("$.wh10B").value("wh10B"))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"))
                .andExpect(jsonPath("$.wh5BPm").value("wh5BPm"))
                .andExpect(jsonPath("$.wh5APm").value("wh5APm"))
                .andExpect(jsonPath("$.wh7APm").value("wh7APm"))
                .andExpect(jsonPath("$.wh7BPm").value("wh7BPm"))
                .andExpect(jsonPath("$.wh3APm").value("wh3APm"))
                .andExpect(jsonPath("$.wh3BPm").value("wh3BPm"));
    }

    @Test
    @DisplayName("중기 예보 조회 리스트 테스트")
    public void getMidTermOceanListTest() throws Exception {

        List<MidTermOceanEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            midTermOceanEntity = MidTermOceanEntity.builder().id(i).regId("regId"+i).wf8("wf8"+i).wh4AAm("wh4AAm"+i).wh4BAm("wh4BAm"+i)
                    .wh6BAm("wh6BAm"+i).wf9("wf9"+i).wh6AAm("wh6AAm"+i).wh9B("wh9B"+i).wh9A("wh9A"+i).wf6Pm("wf6Pm"+i).wf7Pm("wf7Pm"+i)
                    .wf10("wf10"+i).wh4APm("wh4APm"+i).wh6BPm("wh6BPm"+i).wh6APm("wh6APm"+i).wf3Pm("wf3Pm"+i).wf4Pm("wf4Pm"+i).wh4BPm("wh4BPm"+i)
                    .wf5Pm("wf5Pm"+i).wh7AAm("wh7AAm"+i).wf7Am("wf7Am"+i).wh3AAm("wh3AAm"+i).wh5BAm("wh5BAm"+i).wh5AAm("wh5AAm"+i).wf3Am("wf3Am"+i)
                    .wh7BAm("wh7BAm"+i).wh8B("wh8B"+i).wf4Am("wf4Am"+i).wh8A("wh8A"+i).wh10A("wh10A"+i).wf5Am("wf5Am"+i).wh3BAm("wh3BAm"+i)
                    .wh10B("wh10B"+i).wf6Am("wf6Am"+i).wh5BPm("wh5BPm"+i).wh5APm("wh5APm"+i).wh7APm("wh7APm"+i).wh7BPm("wh7BPm"+i)
                    .wh3APm("wh3APm"+i).wh3BPm("wh3BPm"+i).build();
            list.add(midTermOceanEntity);
        }

        // given
        given(midTermOceanController.getMidTermOceanList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/ocean/list"));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 리스트 테스트")
    public void getMidTermOceanListTest2() throws Exception {

        List<MidTermOceanEntity> list = new ArrayList<>();

        long id = 4;

        midTermOceanEntity = MidTermOceanEntity.builder().id(id).regId("regId"+id).wf8("wf8"+id).wh4AAm("wh4AAm"+id).wh4BAm("wh4BAm"+id)
                .wh6BAm("wh6BAm"+id).wf9("wf9"+id).wh6AAm("wh6AAm"+id).wh9B("wh9B"+id).wh9A("wh9A"+id).wf6Pm("wf6Pm"+id).wf7Pm("wf7Pm"+id)
                .wf10("wf10"+id).wh4APm("wh4APm"+id).wh6BPm("wh6BPm"+id).wh6APm("wh6APm"+id).wf3Pm("wf3Pm"+id).wf4Pm("wf4Pm"+id).wh4BPm("wh4BPm"+id)
                .wf5Pm("wf5Pm"+id).wh7AAm("wh7AAm"+id).wf7Am("wf7Am"+id).wh3AAm("wh3AAm"+id).wh5BAm("wh5BAm"+id).wh5AAm("wh5AAm"+id).wf3Am("wf3Am"+id)
                .wh7BAm("wh7BAm"+id).wh8B("wh8B"+id).wf4Am("wf4Am"+id).wh8A("wh8A"+id).wh10A("wh10A"+id).wf5Am("wf5Am"+id).wh3BAm("wh3BAm"+id)
                .wh10B("wh10B"+id).wf6Am("wf6Am"+id).wh5BPm("wh5BPm"+id).wh5APm("wh5APm"+id).wh7APm("wh7APm"+id).wh7BPm("wh7BPm"+id)
                .wh3APm("wh3APm"+id).wh3BPm("wh3BPm"+id).build();
        list.add(midTermOceanEntity);

        // given
        given(midTermOceanController.getMidTermOceanList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/ocean/list")
                .param("location", "regId"+id));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 예보 조회 카운트 테스트")
    public void countMidTermOceanTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        // given
        given(midTermOceanController.countMidTermOcean(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/ocean/count"));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 예보 조회 지역별 카운트 테스트")
    public void countMidTermOceanTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (regId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        // given
        given(midTermOceanController.countMidTermOcean(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/ocean/count")
                .param("location", "regId1"));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 예보 조회 조회 테스트")
    public void readMidTermOceanTest() throws Exception {

        long id = 8;

        midTermOceanEntity = MidTermOceanEntity.builder().id(id).regId("regId"+id).wf8("wf8"+id).wh4AAm("wh4AAm"+id).wh4BAm("wh4BAm"+id)
                .wh6BAm("wh6BAm"+id).wf9("wf9"+id).wh6AAm("wh6AAm"+id).wh9B("wh9B"+id).wh9A("wh9A"+id).wf6Pm("wf6Pm"+id).wf7Pm("wf7Pm"+id)
                .wf10("wf10"+id).wh4APm("wh4APm"+id).wh6BPm("wh6BPm"+id).wh6APm("wh6APm"+id).wf3Pm("wf3Pm"+id).wf4Pm("wf4Pm"+id).wh4BPm("wh4BPm"+id)
                .wf5Pm("wf5Pm"+id).wh7AAm("wh7AAm"+id).wf7Am("wf7Am"+id).wh3AAm("wh3AAm"+id).wh5BAm("wh5BAm"+id).wh5AAm("wh5AAm"+id).wf3Am("wf3Am"+id)
                .wh7BAm("wh7BAm"+id).wh8B("wh8B"+id).wf4Am("wf4Am"+id).wh8A("wh8A"+id).wh10A("wh10A"+id).wf5Am("wf5Am"+id).wh3BAm("wh3BAm"+id)
                .wh10B("wh10B"+id).wf6Am("wf6Am"+id).wh5BPm("wh5BPm"+id).wh5APm("wh5APm"+id).wh7APm("wh7APm"+id).wh7BPm("wh7BPm"+id)
                .wh3APm("wh3APm"+id).wh3BPm("wh3BPm"+id).build();

        // given
        given(midTermOceanController.readMidTermOcean(any()))
                .willReturn(
                        ResponseEntity.ok(midTermOceanEntity)
                );

        // when
        final ResultActions actions = mockMvc.perform(get("/mid-term/ocean/"+id));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.regId").value("regId"+id))
                .andExpect(jsonPath("$.wf8").value("wf8"+id))
                .andExpect(jsonPath("$.wh4AAm").value("wh4AAm"+id))
                .andExpect(jsonPath("$.wh4BAm").value("wh4BAm"+id))
                .andExpect(jsonPath("$.wh6BAm").value("wh6BAm"+id))
                .andExpect(jsonPath("$.wf9").value("wf9"+id))
                .andExpect(jsonPath("$.wh6AAm").value("wh6AAm"+id))
                .andExpect(jsonPath("$.wh9B").value("wh9B"+id))
                .andExpect(jsonPath("$.wh9A").value("wh9A"+id))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"+id))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"+id))
                .andExpect(jsonPath("$.wf10").value("wf10"+id))
                .andExpect(jsonPath("$.wh4APm").value("wh4APm"+id))
                .andExpect(jsonPath("$.wh6BPm").value("wh6BPm"+id))
                .andExpect(jsonPath("$.wh6APm").value("wh6APm"+id))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"+id))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"+id))
                .andExpect(jsonPath("$.wh4BPm").value("wh4BPm"+id))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"+id))
                .andExpect(jsonPath("$.wh7AAm").value("wh7AAm"+id))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"+id))
                .andExpect(jsonPath("$.wh3AAm").value("wh3AAm"+id))
                .andExpect(jsonPath("$.wh5BAm").value("wh5BAm"+id))
                .andExpect(jsonPath("$.wh5AAm").value("wh5AAm"+id))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"+id))
                .andExpect(jsonPath("$.wh7BAm").value("wh7BAm"+id))
                .andExpect(jsonPath("$.wh8B").value("wh8B"+id))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"+id))
                .andExpect(jsonPath("$.wh8A").value("wh8A"+id))
                .andExpect(jsonPath("$.wh10A").value("wh10A"+id))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"+id))
                .andExpect(jsonPath("$.wh3BAm").value("wh3BAm"+id))
                .andExpect(jsonPath("$.wh10B").value("wh10B"+id))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"+id))
                .andExpect(jsonPath("$.wh5BPm").value("wh5BPm"+id))
                .andExpect(jsonPath("$.wh5APm").value("wh5APm"+id))
                .andExpect(jsonPath("$.wh7APm").value("wh7APm"+id))
                .andExpect(jsonPath("$.wh7BPm").value("wh7BPm"+id))
                .andExpect(jsonPath("$.wh3APm").value("wh3APm"+id))
                .andExpect(jsonPath("$.wh3BPm").value("wh3BPm"+id));
    }

    @Test
    @DisplayName("중기 예보 조회 수정 테스트")
    public void patchMidTermOceanTest() throws Exception {

        long id = 3;

        midTermOceanEntity = MidTermOceanEntity.builder().id(id).regId("regId"+id+"updated").wf8("wf8"+id+"updated").wh4AAm("wh4AAm"+id+"updated")
                .wh4BAm("wh4BAm"+id+"updated").wh6BAm("wh6BAm"+id+"updated").wf9("wf9"+id+"updated").wh6AAm("wh6AAm"+id+"updated")
                .wh9B("wh9B"+id+"updated").wh9A("wh9A"+id+"updated").wf6Pm("wf6Pm"+id+"updated").wf7Pm("wf7Pm"+id+"updated").wf10("wf10"+id+"updated")
                .wh4APm("wh4APm"+id+"updated").wh6BPm("wh6BPm"+id+"updated").wh6APm("wh6APm"+id+"updated").wf3Pm("wf3Pm"+id+"updated")
                .wf4Pm("wf4Pm"+id+"updated").wh4BPm("wh4BPm"+id+"updated").wf5Pm("wf5Pm"+id+"updated").wh7AAm("wh7AAm"+id+"updated")
                .wf7Am("wf7Am"+id+"updated").wh3AAm("wh3AAm"+id+"updated").wh5BAm("wh5BAm"+id+"updated").wh5AAm("wh5AAm"+id+"updated")
                .wf3Am("wf3Am"+id+"updated").wh7BAm("wh7BAm"+id+"updated").wh8B("wh8B"+id+"updated").wf4Am("wf4Am"+id+"updated")
                .wh8A("wh8A"+id+"updated").wh10A("wh10A"+id+"updated").wf5Am("wf5Am"+id+"updated").wh3BAm("wh3BAm"+id+"updated")
                .wh10B("wh10B"+id+"updated").wf6Am("wf6Am"+id+"updated").wh5BPm("wh5BPm"+id+"updated").wh5APm("wh5APm"+id+"updated")
                .wh7APm("wh7APm"+id+"updated").wh7BPm("wh7BPm"+id+"updated").wh3APm("wh3APm"+id+"updated").wh3BPm("wh3BPm"+id+"updated").build();

        // given
        given(midTermOceanController.patchMidTermOcean(any(), any()))
                .willReturn(
                        ResponseEntity.ok(midTermOceanEntity)
                );

        // when
        final ResultActions actions = mockMvc.perform(patch("/mid-term/ocean/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(midTermOceanEntity))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.regId").value("regId"+id+"updated"))
                .andExpect(jsonPath("$.wf8").value("wf8"+id+"updated"))
                .andExpect(jsonPath("$.wh4AAm").value("wh4AAm"+id+"updated"))
                .andExpect(jsonPath("$.wh4BAm").value("wh4BAm"+id+"updated"))
                .andExpect(jsonPath("$.wh6BAm").value("wh6BAm"+id+"updated"))
                .andExpect(jsonPath("$.wf9").value("wf9"+id+"updated"))
                .andExpect(jsonPath("$.wh6AAm").value("wh6AAm"+id+"updated"))
                .andExpect(jsonPath("$.wh9B").value("wh9B"+id+"updated"))
                .andExpect(jsonPath("$.wh9A").value("wh9A"+id+"updated"))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf10").value("wf10"+id+"updated"))
                .andExpect(jsonPath("$.wh4APm").value("wh4APm"+id+"updated"))
                .andExpect(jsonPath("$.wh6BPm").value("wh6BPm"+id+"updated"))
                .andExpect(jsonPath("$.wh6APm").value("wh6APm"+id+"updated"))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"+id+"updated"))
                .andExpect(jsonPath("$.wh4BPm").value("wh4BPm"+id+"updated"))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"+id+"updated"))
                .andExpect(jsonPath("$.wh7AAm").value("wh7AAm"+id+"updated"))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"+id+"updated"))
                .andExpect(jsonPath("$.wh3AAm").value("wh3AAm"+id+"updated"))
                .andExpect(jsonPath("$.wh5BAm").value("wh5BAm"+id+"updated"))
                .andExpect(jsonPath("$.wh5AAm").value("wh5AAm"+id+"updated"))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"+id+"updated"))
                .andExpect(jsonPath("$.wh7BAm").value("wh7BAm"+id+"updated"))
                .andExpect(jsonPath("$.wh8B").value("wh8B"+id+"updated"))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"+id+"updated"))
                .andExpect(jsonPath("$.wh8A").value("wh8A"+id+"updated"))
                .andExpect(jsonPath("$.wh10A").value("wh10A"+id+"updated"))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"+id+"updated"))
                .andExpect(jsonPath("$.wh3BAm").value("wh3BAm"+id+"updated"))
                .andExpect(jsonPath("$.wh10B").value("wh10B"+id+"updated"))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"+id+"updated"))
                .andExpect(jsonPath("$.wh5BPm").value("wh5BPm"+id+"updated"))
                .andExpect(jsonPath("$.wh5APm").value("wh5APm"+id+"updated"))
                .andExpect(jsonPath("$.wh7APm").value("wh7APm"+id+"updated"))
                .andExpect(jsonPath("$.wh7BPm").value("wh7BPm"+id+"updated"))
                .andExpect(jsonPath("$.wh3APm").value("wh3APm"+id+"updated"))
                .andExpect(jsonPath("$.wh3BPm").value("wh3BPm"+id+"updated"));
    }

    @Test
    @DisplayName("중기 예보 조회 삭제 테스트")
    public void deleteMidTermOceanTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        // given
        given(midTermOceanController.deleteMidTermOcean(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        // when
        final ResultActions actions = mockMvc.perform(delete("/mid-term/ocean/"+id)
                .param("id", String.valueOf(id)));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
