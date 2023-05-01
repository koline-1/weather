package com.practice.weather.midTerm.land.entity;

import com.practice.weather.main.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mid_term_land")
@Entity(name = "MidTermLandEntity")
@SequenceGenerator(
        name = "MID_TERM_LAND_ID_GENERATOR",
        sequenceName = "MID_TERM_LAND_ID",
        initialValue = 1,
        allocationSize = 1)
public class MidTermLandEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MID_TERM_LAND_ID_GENERATOR")
    private long id;

    @Column(name = "REG_ID")
    private String regId;

    @Column
    private String rnSt3Am;

    @Column
    private String rnSt3Pm;

    @Column
    private String rnSt4Am;

    @Column
    private String rnSt4Pm;

    @Column
    private String rnSt5Am;

    @Column
    private String rnSt5Pm;

    @Column
    private String rnSt6Am;

    @Column
    private String rnSt6Pm;

    @Column
    private String rnSt7Am;

    @Column
    private String rnSt7Pm;

    @Column
    private String rnSt8;

    @Column
    private String rnSt9;

    @Column
    private String rnSt10;

    @Column
    private String wf3Am;

    @Column
    private String wf3Pm;

    @Column
    private String wf4Am;

    @Column
    private String wf4Pm;

    @Column
    private String wf5Am;

    @Column
    private String wf5Pm;

    @Column
    private String wf6Am;

    @Column
    private String wf6Pm;

    @Column
    private String wf7Am;

    @Column
    private String wf7Pm;

    @Column
    private String wf8;

    @Column
    private String wf9;

    @Column
    private String wf10;

}
