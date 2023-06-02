package com.practice.weather.shortTerm.status.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.practice.weather.baseEntity.QBaseEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QShortTermStatusEntity is a Querydsl query type for ShortTermStatusEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShortTermStatusEntity extends EntityPathBase<ShortTermStatusEntity> {

    private static final long serialVersionUID = -2022949016L;

    public static final QShortTermStatusEntity shortTermStatusEntity = new QShortTermStatusEntity("shortTermStatusEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath baseDate = createString("baseDate");

    public final StringPath baseTime = createString("baseTime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> date = _super.date;

    public final StringPath horizontalWind = createString("horizontalWind");

    public final StringPath hourPrecipitation = createString("hourPrecipitation");

    public final StringPath humidity = createString("humidity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nxValue = createString("nxValue");

    public final StringPath nyValue = createString("nyValue");

    public final StringPath rainType = createString("rainType");

    public final StringPath temperature = createString("temperature");

    public final StringPath version = createString("version");

    public final StringPath verticalWind = createString("verticalWind");

    public final StringPath windDirection = createString("windDirection");

    public final StringPath windSpeed = createString("windSpeed");

    public QShortTermStatusEntity(String variable) {
        super(ShortTermStatusEntity.class, forVariable(variable));
    }

    public QShortTermStatusEntity(Path<? extends ShortTermStatusEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShortTermStatusEntity(PathMetadata metadata) {
        super(ShortTermStatusEntity.class, metadata);
    }

}

