package com.practice.weather.entity;

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

}
