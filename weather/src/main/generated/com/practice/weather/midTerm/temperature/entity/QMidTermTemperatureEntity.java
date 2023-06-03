package com.practice.weather.midTerm.temperature.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.practice.weather.baseEntity.QBaseEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMidTermTemperatureEntity is a Querydsl query type for MidTermTemperatureEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMidTermTemperatureEntity extends EntityPathBase<MidTermTemperatureEntity> {

    private static final long serialVersionUID = -1324101030L;

    public static final QMidTermTemperatureEntity midTermTemperatureEntity = new QMidTermTemperatureEntity("midTermTemperatureEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> date = _super.date;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath regId = createString("regId");

    public final StringPath taMax10 = createString("taMax10");

    public final StringPath taMax10High = createString("taMax10High");

    public final StringPath taMax10Low = createString("taMax10Low");

    public final StringPath taMax3 = createString("taMax3");

    public final StringPath taMax3High = createString("taMax3High");

    public final StringPath taMax3Low = createString("taMax3Low");

    public final StringPath taMax4 = createString("taMax4");

    public final StringPath taMax4High = createString("taMax4High");

    public final StringPath taMax4Low = createString("taMax4Low");

    public final StringPath taMax5 = createString("taMax5");

    public final StringPath taMax5High = createString("taMax5High");

    public final StringPath taMax5Low = createString("taMax5Low");

    public final StringPath taMax6 = createString("taMax6");

    public final StringPath taMax6High = createString("taMax6High");

    public final StringPath taMax6Low = createString("taMax6Low");

    public final StringPath taMax7 = createString("taMax7");

    public final StringPath taMax7High = createString("taMax7High");

    public final StringPath taMax7Low = createString("taMax7Low");

    public final StringPath taMax8 = createString("taMax8");

    public final StringPath taMax8High = createString("taMax8High");

    public final StringPath taMax8Low = createString("taMax8Low");

    public final StringPath taMax9 = createString("taMax9");

    public final StringPath taMax9High = createString("taMax9High");

    public final StringPath taMax9Low = createString("taMax9Low");

    public final StringPath taMin10 = createString("taMin10");

    public final StringPath taMin10High = createString("taMin10High");

    public final StringPath taMin10Low = createString("taMin10Low");

    public final StringPath taMin3 = createString("taMin3");

    public final StringPath taMin3High = createString("taMin3High");

    public final StringPath taMin3Low = createString("taMin3Low");

    public final StringPath taMin4 = createString("taMin4");

    public final StringPath taMin4High = createString("taMin4High");

    public final StringPath taMin4Low = createString("taMin4Low");

    public final StringPath taMin5 = createString("taMin5");

    public final StringPath taMin5High = createString("taMin5High");

    public final StringPath taMin5Low = createString("taMin5Low");

    public final StringPath taMin6 = createString("taMin6");

    public final StringPath taMin6High = createString("taMin6High");

    public final StringPath taMin6Low = createString("taMin6Low");

    public final StringPath taMin7 = createString("taMin7");

    public final StringPath taMin7High = createString("taMin7High");

    public final StringPath taMin7Low = createString("taMin7Low");

    public final StringPath taMin8 = createString("taMin8");

    public final StringPath taMin8High = createString("taMin8High");

    public final StringPath taMin8Low = createString("taMin8Low");

    public final StringPath taMin9 = createString("taMin9");

    public final StringPath taMin9High = createString("taMin9High");

    public final StringPath taMin9Low = createString("taMin9Low");

    public QMidTermTemperatureEntity(String variable) {
        super(MidTermTemperatureEntity.class, forVariable(variable));
    }

    public QMidTermTemperatureEntity(Path<? extends MidTermTemperatureEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMidTermTemperatureEntity(PathMetadata metadata) {
        super(MidTermTemperatureEntity.class, metadata);
    }

}

