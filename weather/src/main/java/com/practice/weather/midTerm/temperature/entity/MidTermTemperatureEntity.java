package com.practice.weather.midTerm.temperature.entity;

import com.practice.weather.main.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mid_term_temperature")
@Entity(name = "MidTermTemperatureEntity")
@SequenceGenerator(
        name = "MID_TERM_TEMPERATURE_ID_GENERATOR",
        sequenceName = "MID_TERM_TEMPERATURE_ID",
        initialValue = 1,
        allocationSize = 1)
public class MidTermTemperatureEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MID_TERM_TEMPERATURE_ID_GENERATOR")
    private Long id;

    @Column(name = "REG_ID")
    private String regId;

    @Column
    private String taMin3;

    @Column
    private String taMin3Low;

    @Column
    private String taMin3High;

    @Column
    private String taMax3;

    @Column
    private String taMax3Low;

    @Column
    private String taMax3High;

    @Column
    private String taMin4;

    @Column
    private String taMin4Low;

    @Column
    private String taMin4High;

    @Column
    private String taMax4;

    @Column
    private String taMax4Low;

    @Column
    private String taMax4High;

    @Column
    private String taMin5;

    @Column
    private String taMin5Low;

    @Column
    private String taMin5High;

    @Column
    private String taMax5;

    @Column
    private String taMax5Low;

    @Column
    private String taMax5High;

    @Column
    private String taMin6;

    @Column
    private String taMin6Low;

    @Column
    private String taMin6High;

    @Column
    private String taMax6;

    @Column
    private String taMax6Low;

    @Column
    private String taMax6High;

    @Column
    private String taMin7;

    @Column
    private String taMin7Low;

    @Column
    private String taMin7High;

    @Column
    private String taMax7;

    @Column
    private String taMax7Low;

    @Column
    private String taMax7High;

    @Column
    private String taMin8;

    @Column
    private String taMin8Low;

    @Column
    private String taMin8High;

    @Column
    private String taMax8;

    @Column
    private String taMax8Low;

    @Column
    private String taMax8High;

    @Column
    private String taMin9;

    @Column
    private String taMin9Low;

    @Column
    private String taMin9High;

    @Column
    private String taMax9;

    @Column
    private String taMax9Low;

    @Column
    private String taMax9High;

    @Column
    private String taMin10;

    @Column
    private String taMin10Low;

    @Column
    private String taMin10High;

    @Column
    private String taMax10;

    @Column
    private String taMax10Low;

    @Column
    private String taMax10High;

}
