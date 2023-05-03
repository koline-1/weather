package com.practice.weather.shortTerm.expectation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QShortTermExpectationEntity is a Querydsl query type for ShortTermExpectationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShortTermExpectationEntity extends EntityPathBase<ShortTermExpectationEntity> {

    private static final long serialVersionUID = -786215510L;

    public static final QShortTermExpectationEntity shortTermExpectationEntity = new QShortTermExpectationEntity("shortTermExpectationEntity");

    public final com.practice.weather.main.entity.QBaseEntity _super = new com.practice.weather.main.entity.QBaseEntity(this);

    public final StringPath baseDate = createString("baseDate");

    public final StringPath baseTime = createString("baseTime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> date = _super.date;

    public final StringPath forecastDate = createString("forecastDate");

    public final StringPath forecastTime = createString("forecastTime");

    public final StringPath horizontalWind = createString("horizontalWind");

    public final StringPath hourPrecipitation = createString("hourPrecipitation");

    public final StringPath hourTemperature = createString("hourTemperature");

    public final StringPath humidity = createString("humidity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath maximumTemperature = createString("maximumTemperature");

    public final StringPath minimumTemperature = createString("minimumTemperature");

    public final StringPath nxValue = createString("nxValue");

    public final StringPath nyValue = createString("nyValue");

    public final StringPath rainPossibility = createString("rainPossibility");

    public final StringPath rainType = createString("rainType");

    public final StringPath skyStatus = createString("skyStatus");

    public final StringPath snowDepth = createString("snowDepth");

    public final StringPath verticalWind = createString("verticalWind");

    public final StringPath waveHeight = createString("waveHeight");

    public final StringPath windDirection = createString("windDirection");

    public final StringPath windSpeed = createString("windSpeed");

    public QShortTermExpectationEntity(String variable) {
        super(ShortTermExpectationEntity.class, forVariable(variable));
    }

    public QShortTermExpectationEntity(Path<? extends ShortTermExpectationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShortTermExpectationEntity(PathMetadata metadata) {
        super(ShortTermExpectationEntity.class, metadata);
    }

}

