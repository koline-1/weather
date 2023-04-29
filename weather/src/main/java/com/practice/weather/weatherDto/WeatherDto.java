package com.practice.weather.weatherDto;

import com.practice.weather.entity.WeatherEntity;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WeatherDto {

    private String id;

    private String data;

    public WeatherEntity toEntity(){
        return WeatherEntity.builder().id(id).data(data).build();
    }
}
