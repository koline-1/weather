package com.practice.weather.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeatherEntity is a Querydsl query type for WeatherEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeatherEntity extends EntityPathBase<WeatherEntity> {

    private static final long serialVersionUID = 2014045420L;

    public static final QWeatherEntity weatherEntity = new QWeatherEntity("weatherEntity");

    public final StringPath data = createString("data");

    public final StringPath id = createString("id");

    public QWeatherEntity(String variable) {
        super(WeatherEntity.class, forVariable(variable));
    }

    public QWeatherEntity(Path<? extends WeatherEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeatherEntity(PathMetadata metadata) {
        super(WeatherEntity.class, metadata);
    }

}

