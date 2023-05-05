package com.practice.weather.shortTerm.extraExpectation.entity;

import com.practice.weather.main.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "short_term_extra_expectation")
@Entity(name = "ShortTermExtraExpectationEntity")
@SequenceGenerator(
        name = "SHORT_TERM_EXTRA_EXPECTATION_ID_GENERATOR",
        sequenceName = "SHORT_TERM_EXTRA_EXPECTATION_ID",
        initialValue = 1,
        allocationSize = 1)
public class ShortTermExtraExpectationEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHORT_TERM_EXTRA_EXPECTATION_ID_GENERATOR")
    private long id;

    @Column
    private String baseDate;

    @Column
    private String baseTime;

    @Column
    private String forecastDate;

    @Column
    private String forecastTime;

    @Column
    private String nxValue;

    @Column
    private String nyValue;

    @Column
    private String temperature;

    @Column
    private String hourPrecipitation;

    @Column
    private String skyStatus;

    @Column
    private String horizontalWind;

    @Column
    private String verticalWind;

    @Column
    private String humidity;

    @Column
    private String rainType;

    @Column
    private String lightning;

    @Column
    private String windDirection;

    @Column
    private String windSpeed;

    @Column
    private String version;

}
