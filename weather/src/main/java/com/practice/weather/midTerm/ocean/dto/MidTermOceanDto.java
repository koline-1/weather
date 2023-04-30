package com.practice.weather.midTerm.ocean.dto;

import com.practice.weather.midTerm.ocean.entity.MidTermOceanEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MidTermOceanDto {

    private long id;

    private String regId;

    private String wf8;

    private String wh4AAm;

    private String wh4BAm;

    private String wh6BAm;

    private String wf9;

    private String wh6AAm;

    private String wh9A;

    private String wh9B;

    private String wf6Pm;

    private String wf7Pm;

    private String wf10;

    private String wh4APm;

    private String wh6BPm;

    private String wh6APm;

    private String wf3Pm;

    private String wf4Pm;

    private String wh4BPm;

    private String wf5Pm;

    private String wh7AAm;

    private String wf7Am;

    private String wh3AAm;

    private String wh5BAm;

    private String wh5AAm;

    private String wf3Am;

    private String wh7BAm;

    private String wh8B;

    private String wf4Am;

    private String wh8A;

    private String wh10A;

    private String wf5Am;

    private String wh3BAm;

    private String wh10B;

    private String wf6Am;

    private String wh5BPm;

    private String wh5APm;

    private String wh7APm;

    private String wh7BPm;

    private String wh3APm;

    private String wh3BPm;

    private LocalDateTime date;

    public MidTermOceanEntity toEntity() {
        return MidTermOceanEntity.builder().regId(regId).wf8(wf8).wh4AAm(wh4AAm).wh4BAm(wh4BAm).wh6BAm(wh6BAm)
                .wf9(wf9).wh6AAm(wh6AAm).wh9B(wh9B).wh9A(wh9A).wf6Pm(wf6Pm).wf7Pm(wf7Pm).wf10(wf10).wh4APm(wh4APm)
                .wh6BPm(wh6BPm).wh6APm(wh6APm).wf3Pm(wf3Pm).wf4Pm(wf4Pm).wh4BPm(wh4BPm).wf5Pm(wf5Pm).wh7AAm(wh7AAm)
                .wf7Am(wh7AAm).wh3AAm(wh3AAm).wh5BAm(wh5BAm).wh5AAm(wh5AAm).wf3Am(wf3Am).wh7BAm(wh7BAm).wh8B(wh8B)
                .wf4Am(wf4Am).wh8A(wh8A).wh10A(wh10A).wf5Am(wf5Am).wh3BAm(wh3BAm).wh10B(wh10B).wf6Am(wf6Am).wh5BPm(wh5BPm)
                .wh5APm(wh5APm).wh7APm(wh7APm).wh7BPm(wh7BPm).wh3APm(wh3APm).wh3BPm(wh3BPm).build();
    }
}
