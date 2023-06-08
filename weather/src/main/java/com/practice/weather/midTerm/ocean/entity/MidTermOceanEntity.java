package com.practice.weather.midTerm.ocean.entity;

import com.practice.weather.baseEntity.BaseEntity;
import com.practice.weather.midTerm.ocean.dto.MidTermOceanDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mid_term_ocean")
@Entity(name = "MidTermOceanEntity")
@SequenceGenerator(
        name = "MID_TERM_OCEAN_ID_GENERATOR",
        sequenceName = "MID_TERM_OCEAN_ID",
        initialValue = 1,
        allocationSize = 1)
public class MidTermOceanEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MID_TERM_OCEAN_ID_GENERATOR")
    private Long id;

    @Column(name = "REG_ID")
    private String regId;

    @Column
    private String wf8;

    @Column
    private String wh4AAm;

    @Column
    private String wh4BAm;

    @Column
    private String wh6BAm;

    @Column
    private String wf9;

    @Column
    private String wh6AAm;

    @Column
    private String wh9A;

    @Column
    private String wh9B;

    @Column
    private String wf6Pm;

    @Column
    private String wf7Pm;

    @Column
    private String wf10;

    @Column
    private String wh4APm;

    @Column
    private String wh6BPm;

    @Column
    private String wh6APm;

    @Column
    private String wf3Pm;

    @Column
    private String wf4Pm;

    @Column
    private String wh4BPm;

    @Column
    private String wf5Pm;

    @Column
    private String wh7AAm;

    @Column
    private String wf7Am;

    @Column
    private String wh3AAm;

    @Column
    private String wh5BAm;

    @Column
    private String wh5AAm;

    @Column
    private String wf3Am;

    @Column
    private String wh7BAm;

    @Column
    private String wh8B;

    @Column
    private String wf4Am;

    @Column
    private String wh8A;

    @Column
    private String wh10A;

    @Column
    private String wf5Am;

    @Column
    private String wh3BAm;

    @Column
    private String wh10B;

    @Column
    private String wf6Am;

    @Column
    private String wh5BPm;

    @Column
    private String wh5APm;

    @Column
    private String wh7APm;

    @Column
    private String wh7BPm;

    @Column
    private String wh3APm;

    @Column
    private String wh3BPm;

    public MidTermOceanDto toDto() {
        return MidTermOceanDto.builder().id(id).regId(regId).wf8(wf8).wh4AAm(wh4AAm).wh4BAm(wh4BAm).wh6BAm(wh6BAm)
                .wf9(wf9).wh6AAm(wh6AAm).wh9B(wh9B).wh9A(wh9A).wf6Pm(wf6Pm).wf7Pm(wf7Pm).wf10(wf10).wh4APm(wh4APm)
                .wh6BPm(wh6BPm).wh6APm(wh6APm).wf3Pm(wf3Pm).wf4Pm(wf4Pm).wh4BPm(wh4BPm).wf5Pm(wf5Pm).wh7AAm(wh7AAm)
                .wf7Am(wf7Am).wh3AAm(wh3AAm).wh5BAm(wh5BAm).wh5AAm(wh5AAm).wf3Am(wf3Am).wh7BAm(wh7BAm).wh8B(wh8B)
                .wf4Am(wf4Am).wh8A(wh8A).wh10A(wh10A).wf5Am(wf5Am).wh3BAm(wh3BAm).wh10B(wh10B).wf6Am(wf6Am).wh5BPm(wh5BPm)
                .wh5APm(wh5APm).wh7APm(wh7APm).wh7BPm(wh7BPm).wh3APm(wh3APm).wh3BPm(wh3BPm).build();
    }
    
    public void updateFromDto(MidTermOceanDto dto) {
        this.regId = dto.getRegId();
        this.wf8 = dto.getWf8();
        this.wh4AAm = dto.getWh4AAm();
        this.wh4BAm = dto.getWh4BAm();
        this.wh6BAm = dto.getWh6BAm();
        this.wf9 = dto.getWf9();
        this.wh6AAm = dto.getWh6AAm();
        this.wh9A = dto.getWh9A();
        this.wh9B = dto.getWh9B();
        this.wf6Pm = dto.getWf6Pm();
        this.wf7Pm = dto.getWf7Pm();
        this.wf10 = dto.getWf10();
        this.wh4APm = dto.getWh4APm();
        this.wh6BPm = dto.getWh6BPm();
        this.wh6APm = dto.getWh6APm();
        this.wf3Pm = dto.getWf3Pm();
        this.wf4Pm = dto.getWf4Pm();
        this.wh4BPm = dto.getWh4BPm();
        this.wf5Pm = dto.getWf5Pm();
        this.wh7AAm = dto.getWh7AAm();
        this.wf7Am = dto.getWf7Am();
        this.wh3AAm = dto.getWh3AAm();
        this.wh5BAm = dto.getWh5BAm();
        this.wh5AAm = dto.getWh5AAm();
        this.wf3Am = dto.getWf3Am();
        this.wh7BAm = dto.getWh7BAm();
        this.wh8B = dto.getWh8B();
        this.wf4Am = dto.getWf4Am();
        this.wh8A = dto.getWh8A();
        this.wh10A = dto.getWh10A();
        this.wf5Am = dto.getWf5Am();
        this.wh3BAm = dto.getWh3BAm();
        this.wh10B = dto.getWh10B();
        this.wf6Am = dto.getWf6Am();
        this.wh5BPm = dto.getWh5BPm();
        this.wh5APm = dto.getWh5APm();
        this.wh7APm = dto.getWh7APm();
        this.wh7BPm = dto.getWh7BPm();
        this.wh3APm = dto.getWh3APm();
        this.wh3BPm = dto.getWh3BPm();
    }

}
