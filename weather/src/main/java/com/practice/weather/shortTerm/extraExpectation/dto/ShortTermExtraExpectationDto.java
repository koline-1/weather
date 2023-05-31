package com.practice.weather.shortTerm.extraExpectation.dto;

import com.practice.weather.shortTerm.extraExpectation.entity.ShortTermExtraExpectationEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShortTermExtraExpectationDto {

    private long id;

    private String baseDate;

    private String baseTime;

    private String forecastDate;

    private String forecastTime;

    private String nxValue;

    private String nyValue;

    private String temperature;

    private String hourPrecipitation;

    private String skyStatus;

    private String horizontalWind;

    private String verticalWind;

    private String humidity;

    private String rainType;

    private String lightning;

    private String windDirection;

    private String windSpeed;

    private String version;

    private LocalDateTime date;

    public ShortTermExtraExpectationEntity toEntity() {
        return ShortTermExtraExpectationEntity.builder().id(id).baseDate(baseDate).baseTime(baseTime).forecastDate(forecastDate)
                .forecastTime(forecastTime).nxValue(nxValue).nyValue(nyValue).temperature(temperature).hourPrecipitation(hourPrecipitation)
                .skyStatus(skyStatus).horizontalWind(horizontalWind).verticalWind(verticalWind).humidity(humidity).rainType(rainType)
                .lightning(lightning).windDirection(windDirection).windSpeed(windSpeed).version(version).build();
    }

}