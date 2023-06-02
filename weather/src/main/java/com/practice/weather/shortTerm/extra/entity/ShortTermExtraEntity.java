package com.practice.weather.shortTerm.extra.entity;

import com.practice.weather.baseEntity.BaseEntity;
import com.practice.weather.shortTerm.extra.dto.ShortTermExtraDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "short_term_extra")
@Entity(name = "ShortTermExtraEntity")
@SequenceGenerator(
        name = "SHORT_TERM_EXTRA_ID_GENERATOR",
        sequenceName = "SHORT_TERM_EXTRA_ID",
        initialValue = 1,
        allocationSize = 1)
public class ShortTermExtraEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHORT_TERM_EXTRA_ID_GENERATOR")
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

    public ShortTermExtraDto toDto() {
        return ShortTermExtraDto.builder().id(id).baseDate(baseDate).baseTime(baseTime).forecastDate(forecastDate)
                .forecastTime(forecastTime).nxValue(nxValue).nyValue(nyValue).temperature(temperature).hourPrecipitation(hourPrecipitation)
                .skyStatus(skyStatus).horizontalWind(horizontalWind).verticalWind(verticalWind).humidity(humidity).rainType(rainType)
                .lightning(lightning).windDirection(windDirection).windSpeed(windSpeed).version(version).build();
    }

}
