package com.practice.weather.shortTerm.status.dto;

import com.practice.weather.shortTerm.status.entity.ShortTermStatusEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShortTermStatusDto {

    private long id;

    private String baseDate;

    private String baseTime;

    private String nxValue;

    private String nyValue;

    private String temperature;

    private String hourPrecipitation;

    private String horizontalWind;

    private String verticalWind;

    private String humidity;

    private String rainType;

    private String windDirection;

    private String windSpeed;

    private String version;

    private LocalDateTime created;

    private LocalDateTime updated;

    public ShortTermStatusEntity toEntity() {
        return ShortTermStatusEntity.builder().id(id).baseDate(baseDate).baseTime(baseTime).nxValue(nxValue).nyValue(nyValue)
                .temperature(temperature).hourPrecipitation(hourPrecipitation).horizontalWind(horizontalWind).verticalWind(verticalWind)
                .humidity(humidity).rainType(rainType).windDirection(windDirection).windSpeed(windSpeed).version(version).build();
    }

}
