package com.practice.weather.midTerm.land.dto;

import com.practice.weather.midTerm.land.entity.MidTermLandEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MidTermLandDto {

    private long id;

    private String regId;

    private String rnSt3Am;

    private String rnSt3Pm;

    private String rnSt4Am;

    private String rnSt4Pm;

    private String rnSt5Am;

    private String rnSt5Pm;

    private String rnSt6Am;

    private String rnSt6Pm;

    private String rnSt7Am;

    private String rnSt7Pm;

    private String rnSt8;

    private String rnSt9;

    private String rnSt10;

    private String wf3Am;

    private String wf3Pm;

    private String wf4Am;

    private String wf4Pm;

    private String wf5Am;

    private String wf5Pm;

    private String wf6Am;

    private String wf6Pm;

    private String wf7Am;

    private String wf7Pm;

    private String wf8;

    private String wf9;

    private String wf10;

    private LocalDateTime created;

    private LocalDateTime updated;

    public MidTermLandEntity toEntity() {
        return MidTermLandEntity.builder().id(id).regId(regId).rnSt3Am(rnSt3Am).rnSt3Pm(rnSt3Pm).rnSt4Am(rnSt4Am).rnSt4Pm(rnSt4Pm)
                .rnSt5Am(rnSt5Am).rnSt5Pm(rnSt5Pm).rnSt6Am(rnSt6Am).rnSt6Pm(rnSt6Pm).rnSt7Am(rnSt7Am).rnSt7Pm(rnSt7Pm)
                .rnSt8(rnSt8).rnSt9(rnSt9).rnSt10(rnSt10).wf3Am(wf3Am).wf3Pm(wf3Pm).wf4Am(wf4Am).wf4Pm(wf4Pm)
                .wf5Am(wf5Am).wf5Pm(wf5Pm).wf6Am(wf6Am).wf6Pm(wf6Pm).wf7Am(wf7Am).wf7Pm(wf7Pm).wf8(wf8).wf9(wf9).wf10(wf10).build();
    }

}
