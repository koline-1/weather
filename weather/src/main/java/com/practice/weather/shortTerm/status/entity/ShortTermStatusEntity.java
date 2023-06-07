package com.practice.weather.shortTerm.status.entity;

import com.practice.weather.baseEntity.BaseEntity;
import com.practice.weather.shortTerm.status.dto.ShortTermStatusDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "short_term_status")
@Entity(name = "ShortTermStatusEntity")
@SequenceGenerator(
        name = "SHORT_TERM_STATUS_ID_GENERATOR",
        sequenceName = "SHORT_TERM_STATUS_ID",
        initialValue = 1,
        allocationSize = 1)
public class ShortTermStatusEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHORT_TERM_STATUS_ID_GENERATOR")
    private long id;

    @Column
    private String baseDate;

    @Column
    private String baseTime;

    @Column
    private String nxValue;

    @Column
    private String nyValue;

    @Column
    private String temperature;

    @Column
    private String hourPrecipitation;

    @Column
    private String horizontalWind;

    @Column
    private String verticalWind;

    @Column
    private String humidity;

    @Column
    private String rainType;

    @Column
    private String windDirection;

    @Column
    private String windSpeed;

    @Column
    private String version;

    public ShortTermStatusDto toDto() {
        return ShortTermStatusDto.builder().id(id).baseDate(baseDate).baseTime(baseTime).nxValue(nxValue).nyValue(nyValue)
                .temperature(temperature).hourPrecipitation(hourPrecipitation).horizontalWind(horizontalWind).verticalWind(verticalWind)
                .humidity(humidity).rainType(rainType).windDirection(windDirection).windSpeed(windSpeed).version(version).build();
    }

    public void updateFromDto (ShortTermStatusDto dto) {
        this.baseDate = dto.getBaseDate();
        this.baseTime = dto.getBaseTime();
        this.nxValue = dto.getNxValue();
        this.nyValue = dto.getNyValue();
        this.temperature = dto.getTemperature();
        this.hourPrecipitation = dto.getHourPrecipitation();
        this.horizontalWind = dto.getHorizontalWind();
        this.verticalWind = dto.getVerticalWind();
        this.humidity = dto.getHumidity();
        this.rainType = dto.getRainType();
        this.windDirection = dto.getWindDirection();
        this.windSpeed = dto.getWindSpeed();
        this.version = dto.getVersion();
    }

}
