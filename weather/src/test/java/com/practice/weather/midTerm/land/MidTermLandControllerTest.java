package com.practice.weather.midTerm.land;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weather.midTerm.land.controller.MidTermLandController;
import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import com.practice.weather.midTerm.land.repository.MidTermLandRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MidTermLandControllerTest {

    @Mock
    private MidTermLandEntity midTermLandEntity;

    @MockBean
    private MidTermLandController midTermLandController;

    @Autowired
    MidTermLandRepository midTermLandRepository;

    @Autowired
    private MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupLand() {
        openMocks = MockitoAnnotations.openMocks(this);

        for (int i = 1; i <= 10; i++) {
            midTermLandEntity = MidTermLandEntity.builder().id(i).regId("regId"+i).rnSt3Am("rnSt3Am"+i).rnSt3Pm("rnSt3Pm"+i).rnSt4Am("rnSt4Am"+i)
                    .rnSt4Pm("rnSt4Pm"+i).rnSt5Am("rnSt5Am"+i).rnSt5Pm("rnSt5Pm"+i).rnSt6Am("rnSt6Am"+i).rnSt6Pm("rnSt6Pm"+i).rnSt7Am("rnSt7Am"+i)
                    .rnSt7Pm("rnSt7Pm"+i).rnSt8("rnSt8"+i).rnSt9("rnSt9"+i).rnSt10("rnSt10"+i).wf3Am("wf3Am"+i).wf3Pm("wf3Pm"+i).wf4Am("wf4Am"+i).wf4Pm("wf4Pm"+i)
                    .wf5Am("wf5Am"+i).wf5Pm("wf5Pm"+i).wf6Am("wf6Am"+i).wf6Pm("wf6Pm"+i).wf7Am("wf7Am"+i).wf7Pm("wf7Pm"+i).wf8("wf8"+i).wf9("wf9"+i).wf10("wf10"+i).build();
            midTermLandRepository.save(midTermLandEntity);
        }

    }

    @Test
    @DisplayName("중기 육상 예보 저장 테스트")
    public void saveMidTermLandTest() throws Exception {

        long id = 3;

        midTermLandEntity = MidTermLandEntity.builder().id(id).regId("regId"+id).rnSt3Am("rnSt3Am"+id).rnSt3Pm("rnSt3Pm"+id).rnSt4Am("rnSt4Am"+id)
                .rnSt4Pm("rnSt4Pm"+id).rnSt5Am("rnSt5Am"+id).rnSt5Pm("rnSt5Pm"+id).rnSt6Am("rnSt6Am"+id).rnSt6Pm("rnSt6Pm"+id).rnSt7Am("rnSt7Am"+id)
                .rnSt7Pm("rnSt7Pm"+id).rnSt8("rnSt8"+id).rnSt9("rnSt9"+id).rnSt10("rnSt10"+id).wf3Am("wf3Am"+id).wf3Pm("wf3Pm"+id).wf4Am("wf4Am"+id).wf4Pm("wf4Pm"+id)
                .wf5Am("wf5Am"+id).wf5Pm("wf5Pm"+id).wf6Am("wf6Am"+id).wf6Pm("wf6Pm"+id).wf7Am("wf7Am"+id).wf7Pm("wf7Pm"+id).wf8("wf8"+id).wf9("wf9"+id).wf10("wf10"+id).build();

        // given
        given(midTermLandController.saveMidTermLand(any()))
                .willReturn(
                        ResponseEntity.ok(midTermLandEntity)
                );

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", midTermLandEntity);

        JSONObject jObject = new JSONObject(map);

        // when
        final ResultActions actions = mockMvc.perform(post("/mid-term/land/current")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(jObject))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.regId").value("regId"+id))
            .andExpect(jsonPath("$.rnSt3Am").value("rnSt3Am"+id))
            .andExpect(jsonPath("$.rnSt3Pm").value("rnSt3Pm"+id))
            .andExpect(jsonPath("$.rnSt4Am").value("rnSt4Am"+id))
            .andExpect(jsonPath("$.rnSt4Pm").value("rnSt4Pm"+id))
            .andExpect(jsonPath("$.rnSt5Am").value("rnSt5Am"+id))
            .andExpect(jsonPath("$.rnSt5Pm").value("rnSt5Pm"+id))
            .andExpect(jsonPath("$.rnSt6Am").value("rnSt6Am"+id))
            .andExpect(jsonPath("$.rnSt6Pm").value("rnSt6Pm"+id))
            .andExpect(jsonPath("$.rnSt7Am").value("rnSt7Am"+id))
            .andExpect(jsonPath("$.rnSt7Pm").value("rnSt7Pm"+id))
            .andExpect(jsonPath("$.rnSt8").value("rnSt8"+id))
            .andExpect(jsonPath("$.rnSt9").value("rnSt9"+id))
            .andExpect(jsonPath("$.rnSt10").value("rnSt10"+id))
            .andExpect(jsonPath("$.wf3Am").value("wf3Am"+id))
            .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"+id))
            .andExpect(jsonPath("$.wf4Am").value("wf4Am"+id))
            .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"+id))
            .andExpect(jsonPath("$.wf5Am").value("wf5Am"+id))
            .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"+id))
            .andExpect(jsonPath("$.wf6Am").value("wf6Am"+id))
            .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"+id))
            .andExpect(jsonPath("$.wf7Am").value("wf7Am"+id))
            .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"+id))
            .andExpect(jsonPath("$.wf8").value("wf8"+id))
            .andExpect(jsonPath("$.wf9").value("wf9"+id))
            .andExpect(jsonPath("$.wf10").value("wf10"+id));

    }

    @Test
    @DisplayName("중기 육상 예보 리스트 테스트")
    public void getMidTermLandListTest() throws Exception {

        List<MidTermLandEntity> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            midTermLandEntity = MidTermLandEntity.builder().id(i).regId("regId"+i).rnSt3Am("rnSt3Am"+i).rnSt3Pm("rnSt3Pm"+i).rnSt4Am("rnSt4Am"+i)
                    .rnSt4Pm("rnSt4Pm"+i).rnSt5Am("rnSt5Am"+i).rnSt5Pm("rnSt5Pm"+i).rnSt6Am("rnSt6Am"+i).rnSt6Pm("rnSt6Pm"+i).rnSt7Am("rnSt7Am"+i)
                    .rnSt7Pm("rnSt7Pm"+i).rnSt8("rnSt8"+i).rnSt9("rnSt9"+i).rnSt10("rnSt10"+i).wf3Am("wf3Am"+i).wf3Pm("wf3Pm"+i).wf4Am("wf4Am"+i).wf4Pm("wf4Pm"+i)
                    .wf5Am("wf5Am"+i).wf5Pm("wf5Pm"+i).wf6Am("wf6Am"+i).wf6Pm("wf6Pm"+i).wf7Am("wf7Am"+i).wf7Pm("wf7Pm"+i).wf8("wf8"+i).wf9("wf9"+i).wf10("wf10"+i).build();
            list.add(midTermLandEntity);
        }

        //given
        given(midTermLandController.getMidTermLandList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/land/list"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @DisplayName("중기 육상 예보 지역별 리스트 테스트")
    public void getMidTermLandListTest2() throws Exception {

        List<MidTermLandEntity> list = new ArrayList<>();

        long id = 3;

        midTermLandEntity = MidTermLandEntity.builder().id(id).regId("regId"+id).rnSt3Am("rnSt3Am"+id).rnSt3Pm("rnSt3Pm"+id).rnSt4Am("rnSt4Am"+id)
                .rnSt4Pm("rnSt4Pm"+id).rnSt5Am("rnSt5Am"+id).rnSt5Pm("rnSt5Pm"+id).rnSt6Am("rnSt6Am"+id).rnSt6Pm("rnSt6Pm"+id).rnSt7Am("rnSt7Am"+id)
                .rnSt7Pm("rnSt7Pm"+id).rnSt8("rnSt8"+id).rnSt9("rnSt9"+id).rnSt10("rnSt10"+id).wf3Am("wf3Am"+id).wf3Pm("wf3Pm"+id).wf4Am("wf4Am"+id).wf4Pm("wf4Pm"+id)
                .wf5Am("wf5Am"+id).wf5Pm("wf5Pm"+id).wf6Am("wf6Am"+id).wf6Pm("wf6Pm"+id).wf7Am("wf7Am"+id).wf7Pm("wf7Pm"+id).wf8("wf8"+id).wf9("wf9"+id).wf10("wf10"+id).build();
        list.add(midTermLandEntity);

        //given
        given(midTermLandController.getMidTermLandList(any(Pageable.class), any()))
                .willReturn(
                        ResponseEntity.ok(list)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/land/list")
                .param("location", "regId"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("중기 육상 예보 카운트 테스트")
    public void countMidTermLandTest() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 : 10개
        String expectedResult = "{\"count\": \"10\"}";

        //given
        given(midTermLandController.countMidTermLand(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/land/count"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("10"));
    }

    @Test
    @DisplayName("중기 육상 예보 지역별 카운트 테스트")
    public void countMidTermLandTest2() throws Exception {

        // BeforeEach 에서 추가한 데이터의 수 (지역별) : 1개 (regId 모두 다르게 넣음)
        String expectedResult = "{\"count\": \"1\"}";

        //given
        given(midTermLandController.countMidTermLand(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/land/count")
                .param("location", "regId3"));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("1"));
    }

    @Test
    @DisplayName("중기 육상 예보 조회 테스트")
    public void readMidTermLandTest() throws Exception {

        long id = 8;

        midTermLandEntity = MidTermLandEntity.builder().id(id).regId("regId"+id).rnSt3Am("rnSt3Am"+id).rnSt3Pm("rnSt3Pm"+id).rnSt4Am("rnSt4Am"+id)
                .rnSt4Pm("rnSt4Pm"+id).rnSt5Am("rnSt5Am"+id).rnSt5Pm("rnSt5Pm"+id).rnSt6Am("rnSt6Am"+id).rnSt6Pm("rnSt6Pm"+id).rnSt7Am("rnSt7Am"+id)
                .rnSt7Pm("rnSt7Pm"+id).rnSt8("rnSt8"+id).rnSt9("rnSt9"+id).rnSt10("rnSt10"+id).wf3Am("wf3Am"+id).wf3Pm("wf3Pm"+id).wf4Am("wf4Am"+id).wf4Pm("wf4Pm"+id)
                .wf5Am("wf5Am"+id).wf5Pm("wf5Pm"+id).wf6Am("wf6Am"+id).wf6Pm("wf6Pm"+id).wf7Am("wf7Am"+id).wf7Pm("wf7Pm"+id).wf8("wf8"+id).wf9("wf9"+id).wf10("wf10"+id).build();

        //given
        given(midTermLandController.readMidTermLand(any()))
                .willReturn(
                        ResponseEntity.ok(midTermLandEntity)
                );

        final ResultActions actions = mockMvc.perform(get("/mid-term/land/"+id));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value( id))
                .andExpect(jsonPath("$.regId").value("regId"+id))
                .andExpect(jsonPath("$.rnSt3Am").value("rnSt3Am"+id))
                .andExpect(jsonPath("$.rnSt3Pm").value("rnSt3Pm"+id))
                .andExpect(jsonPath("$.rnSt4Am").value("rnSt4Am"+id))
                .andExpect(jsonPath("$.rnSt4Pm").value("rnSt4Pm"+id))
                .andExpect(jsonPath("$.rnSt5Am").value("rnSt5Am"+id))
                .andExpect(jsonPath("$.rnSt5Pm").value("rnSt5Pm"+id))
                .andExpect(jsonPath("$.rnSt6Am").value("rnSt6Am"+id))
                .andExpect(jsonPath("$.rnSt6Pm").value("rnSt6Pm"+id))
                .andExpect(jsonPath("$.rnSt7Am").value("rnSt7Am"+id))
                .andExpect(jsonPath("$.rnSt7Pm").value("rnSt7Pm"+id))
                .andExpect(jsonPath("$.rnSt8").value("rnSt8"+id))
                .andExpect(jsonPath("$.rnSt9").value("rnSt9"+id))
                .andExpect(jsonPath("$.rnSt10").value("rnSt10"+id))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"+id))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"+id))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"+id))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"+id))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"+id))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"+id))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"+id))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"+id))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"+id))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"+id))
                .andExpect(jsonPath("$.wf8").value("wf8"+id))
                .andExpect(jsonPath("$.wf9").value("wf9"+id))
                .andExpect(jsonPath("$.wf10").value("wf10"+id));
    }

    @Test
    @DisplayName("중기 육상 예보 수정 테스트")
    public void patchMidTermLandTest() throws Exception {

        long id = 3;

        midTermLandEntity = MidTermLandEntity.builder().id(id).regId("regId"+id+"updated").rnSt3Am("rnSt3Am"+id+"updated").rnSt3Pm("rnSt3Pm"+id+"updated")
                .rnSt4Am("rnSt4Am"+id+"updated").rnSt4Pm("rnSt4Pm"+id+"updated").rnSt5Am("rnSt5Am"+id+"updated").rnSt5Pm("rnSt5Pm"+id+"updated")
                .rnSt6Am("rnSt6Am"+id+"updated").rnSt6Pm("rnSt6Pm"+id+"updated").rnSt7Am("rnSt7Am"+id+"updated").rnSt7Pm("rnSt7Pm"+id+"updated")
                .rnSt8("rnSt8"+id+"updated").rnSt9("rnSt9"+id+"updated").rnSt10("rnSt10"+id+"updated").wf3Am("wf3Am"+id+"updated").wf3Pm("wf3Pm"+id+"updated")
                .wf4Am("wf4Am"+id+"updated").wf4Pm("wf4Pm"+id+"updated").wf5Am("wf5Am"+id+"updated").wf5Pm("wf5Pm"+id+"updated").wf6Am("wf6Am"+id+"updated")
                .wf6Pm("wf6Pm"+id+"updated").wf7Am("wf7Am"+id+"updated").wf7Pm("wf7Pm"+id+"updated").wf8("wf8"+id+"updated").wf9("wf9"+id+"updated")
                .wf10("wf10"+id+"updated").build();

        //given
        given(midTermLandController.patchMidTermLand(any(), any()))
                .willReturn(
                        ResponseEntity.ok(midTermLandEntity)
                );

        final ResultActions actions = mockMvc.perform(patch("/mid-term/land/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(midTermLandEntity))
                .contentType(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.regId").value("regId"+id+"updated"))
                .andExpect(jsonPath("$.rnSt3Am").value("rnSt3Am"+id+"updated"))
                .andExpect(jsonPath("$.rnSt3Pm").value("rnSt3Pm"+id+"updated"))
                .andExpect(jsonPath("$.rnSt4Am").value("rnSt4Am"+id+"updated"))
                .andExpect(jsonPath("$.rnSt4Pm").value("rnSt4Pm"+id+"updated"))
                .andExpect(jsonPath("$.rnSt5Am").value("rnSt5Am"+id+"updated"))
                .andExpect(jsonPath("$.rnSt5Pm").value("rnSt5Pm"+id+"updated"))
                .andExpect(jsonPath("$.rnSt6Am").value("rnSt6Am"+id+"updated"))
                .andExpect(jsonPath("$.rnSt6Pm").value("rnSt6Pm"+id+"updated"))
                .andExpect(jsonPath("$.rnSt7Am").value("rnSt7Am"+id+"updated"))
                .andExpect(jsonPath("$.rnSt7Pm").value("rnSt7Pm"+id+"updated"))
                .andExpect(jsonPath("$.rnSt8").value("rnSt8"+id+"updated"))
                .andExpect(jsonPath("$.rnSt9").value("rnSt9"+id+"updated"))
                .andExpect(jsonPath("$.rnSt10").value("rnSt10"+id+"updated"))
                .andExpect(jsonPath("$.wf3Am").value("wf3Am"+id+"updated"))
                .andExpect(jsonPath("$.wf3Pm").value("wf3Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf4Am").value("wf4Am"+id+"updated"))
                .andExpect(jsonPath("$.wf4Pm").value("wf4Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf5Am").value("wf5Am"+id+"updated"))
                .andExpect(jsonPath("$.wf5Pm").value("wf5Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf6Am").value("wf6Am"+id+"updated"))
                .andExpect(jsonPath("$.wf6Pm").value("wf6Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf7Am").value("wf7Am"+id+"updated"))
                .andExpect(jsonPath("$.wf7Pm").value("wf7Pm"+id+"updated"))
                .andExpect(jsonPath("$.wf8").value("wf8"+id+"updated"))
                .andExpect(jsonPath("$.wf9").value("wf9"+id+"updated"))
                .andExpect(jsonPath("$.wf10").value("wf10"+id+"updated"));
    }

    @Test
    @DisplayName("중기 육상 예보 삭제 테스트")
    public void deleteMidTermLandTest() throws Exception {

        // 삭제 할 객체 ID
        long id = 5;

        // 삭제된 객체의 ID 리턴
        String expectedResult = "{\"result\": \"" + id + "\"}";

        //given
        given(midTermLandController.deleteMidTermLand(any()))
                .willReturn(
                        ResponseEntity.ok(expectedResult)
                );

        final ResultActions actions = mockMvc.perform(delete("/mid-term/land/"+id)
                .param("id", String.valueOf(id)));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(id));
    }

}
