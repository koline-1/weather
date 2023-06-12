package com.practice.weather.shortTerm.expectation.entity;

import com.practice.weather.baseEntity.BaseEntity;
import com.practice.weather.shortTerm.expectation.dto.ShortTermExpectationDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "short_term_expectation")
@Entity(name = "ShortTermExpectationEntity")
@SequenceGenerator(
        name = "SHORT_TERM_EXPECTATION_ID_GENERATOR",
        sequenceName = "SHORT_TERM_EXPECTATION_ID",
        initialValue = 1,
        allocationSize = 1)
public class ShortTermExpectationEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHORT_TERM_EXPECTATION_ID_GENERATOR")
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
    private String hourTemperature;

    @Column
    private String horizontalWind;

    @Column
    private String verticalWind;

    @Column
    private String windDirection;

    @Column
    private String windSpeed;

    @Column
    private String skyStatus;

    @Column
    private String rainPossibility;

    @Column
    private String rainType;

    @Column
    private String waveHeight;

    @Column
    private String hourPrecipitation;

    @Column
    private String snowDepth;

    @Column
    private String humidity;

    @Column
    private String minimumTemperature;

    @Column
    private String maximumTemperature;

    @Column
    private String version;

    public ShortTermExpectationDto toDto() {
        return ShortTermExpectationDto.builder().id(id).baseDate(baseDate).baseTime(baseTime).forecastDate(forecastDate)
                .forecastTime(forecastTime).nxValue(nxValue).nyValue(nyValue).hourTemperature(hourTemperature).humidity(humidity)
                .horizontalWind(horizontalWind).verticalWind(verticalWind).windDirection(windDirection).windSpeed(windSpeed)
                .skyStatus(skyStatus).rainType(rainType).rainPossibility(rainPossibility).waveHeight(waveHeight)
                .hourPrecipitation(hourPrecipitation).snowDepth(snowDepth).minimumTemperature(minimumTemperature)
                .maximumTemperature(maximumTemperature).version(version).created(getCreated()).updated(getUpdated()).build();
    }

    public void updateFromDto(ShortTermExpectationDto dto) {
        this.baseDate = dto.getBaseDate();
        this.baseTime = dto.getBaseTime();
        this.forecastDate = dto.getForecastDate();
        this.forecastTime = dto.getForecastTime();
        this.nxValue = dto.getNxValue();
        this.nyValue = dto.getNyValue();
        this.hourTemperature = dto.getHourTemperature();
        this.horizontalWind = dto.getHorizontalWind();
        this.verticalWind = dto.getVerticalWind();
        this.windDirection = dto.getWindDirection();
        this.windSpeed = dto.getWindSpeed();
        this.skyStatus = dto.getSkyStatus();
        this.rainType = dto.getRainType();
        this.rainPossibility = dto.getRainPossibility();
        this.waveHeight = dto.getWaveHeight();
        this.hourPrecipitation = dto.getHourPrecipitation();
        this.snowDepth = dto.getSnowDepth();
        this.humidity = dto.getHumidity();
        this.minimumTemperature = dto.getMinimumTemperature();
        this.maximumTemperature = dto.getMaximumTemperature();
        this.version = dto.getVersion();
    }

}
