package com.practice.weather.shortTerm.extra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QShortTermExtraEntity is a Querydsl query type for ShortTermExtraEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShortTermExtraEntity extends EntityPathBase<ShortTermExtraEntity> {

    private static final long serialVersionUID = 1248977938L;

    public static final QShortTermExtraEntity shortTermExtraEntity = new QShortTermExtraEntity("shortTermExtraEntity");

    public final com.practice.weather.baseEntity.QBaseEntity _super = new com.practice.weather.baseEntity.QBaseEntity(this);

    public final StringPath baseDate = createString("baseDate");

    public final StringPath baseTime = createString("baseTime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> date = _super.date;

    public final StringPath forecastDate = createString("forecastDate");

    public final StringPath forecastTime = createString("forecastTime");

    public final StringPath horizontalWind = createString("horizontalWind");

    public final StringPath hourPrecipitation = createString("hourPrecipitation");

    public final StringPath humidity = createString("humidity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lightning = createString("lightning");

    public final StringPath nxValue = createString("nxValue");

    public final StringPath nyValue = createString("nyValue");

    public final StringPath rainType = createString("rainType");

    public final StringPath skyStatus = createString("skyStatus");

    public final StringPath temperature = createString("temperature");

    public final StringPath version = createString("version");

    public final StringPath verticalWind = createString("verticalWind");

    public final StringPath windDirection = createString("windDirection");

    public final StringPath windSpeed = createString("windSpeed");

    public QShortTermExtraEntity(String variable) {
        super(ShortTermExtraEntity.class, forVariable(variable));
    }

    public QShortTermExtraEntity(Path<? extends ShortTermExtraEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShortTermExtraEntity(PathMetadata metadata) {
        super(ShortTermExtraEntity.class, metadata);
    }

}

