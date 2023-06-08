package com.practice.weather.shortTerm.expectation.dto;

import com.practice.weather.shortTerm.expectation.entity.ShortTermExpectationEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShortTermExpectationDto {

    private long id;

    private String baseDate;

    private String baseTime;

    private String forecastDate;

    private String forecastTime;

    private String nxValue;

    private String nyValue;

    private String hourTemperature;

    private String horizontalWind;

    private String verticalWind;

    private String windDirection;

    private String windSpeed;

    private String skyStatus;

    private String rainType;

    private String rainPossibility;

    private String waveHeight;

    private String hourPrecipitation;

    private String snowDepth;

    private String humidity;

    private String minimumTemperature;

    private String maximumTemperature;

    private String version;

    private LocalDateTime date;


    public ShortTermExpectationEntity toEntity() {
        return ShortTermExpectationEntity.builder().id(id).baseDate(baseDate).baseTime(baseTime).forecastDate(forecastDate)
                .forecastTime(forecastTime).nxValue(nxValue).nyValue(nyValue).hourTemperature(hourTemperature).humidity(humidity)
                .horizontalWind(horizontalWind).verticalWind(verticalWind).windDirection(windDirection).windSpeed(windSpeed)
                .skyStatus(skyStatus).rainType(rainType).rainPossibility(rainPossibility).waveHeight(waveHeight)
                .hourPrecipitation(hourPrecipitation).snowDepth(snowDepth).minimumTemperature(minimumTemperature)
                .maximumTemperature(maximumTemperature).version(version).build();
    }

}