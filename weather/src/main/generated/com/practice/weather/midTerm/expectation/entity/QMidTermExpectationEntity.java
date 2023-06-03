package com.practice.weather.midTerm.expectation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.practice.weather.baseEntity.QBaseEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMidTermExpectationEntity is a Querydsl query type for MidTermExpectationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMidTermExpectationEntity extends EntityPathBase<MidTermExpectationEntity> {

    private static final long serialVersionUID = 177188330L;

    public static final QMidTermExpectationEntity midTermExpectationEntity = new QMidTermExpectationEntity("midTermExpectationEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> date = _super.date;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath stnId = createString("stnId");

    public final StringPath wfSv = createString("wfSv");

    public QMidTermExpectationEntity(String variable) {
        super(MidTermExpectationEntity.class, forVariable(variable));
    }

    public QMidTermExpectationEntity(Path<? extends MidTermExpectationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMidTermExpectationEntity(PathMetadata metadata) {
        super(MidTermExpectationEntity.class, metadata);
    }

}

