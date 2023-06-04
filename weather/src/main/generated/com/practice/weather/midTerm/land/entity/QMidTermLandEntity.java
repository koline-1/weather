package com.practice.weather.midTerm.land.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMidTermLandEntity is a Querydsl query type for MidTermLandEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMidTermLandEntity extends EntityPathBase<MidTermLandEntity> {

    private static final long serialVersionUID = 289090656L;

    public static final QMidTermLandEntity midTermLandEntity = new QMidTermLandEntity("midTermLandEntity");

    public final com.practice.weather.baseEntity.QBaseEntity _super = new com.practice.weather.baseEntity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> date = _super.date;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath regId = createString("regId");

    public final StringPath rnSt10 = createString("rnSt10");

    public final StringPath rnSt3Am = createString("rnSt3Am");

    public final StringPath rnSt3Pm = createString("rnSt3Pm");

    public final StringPath rnSt4Am = createString("rnSt4Am");

    public final StringPath rnSt4Pm = createString("rnSt4Pm");

    public final StringPath rnSt5Am = createString("rnSt5Am");

    public final StringPath rnSt5Pm = createString("rnSt5Pm");

    public final StringPath rnSt6Am = createString("rnSt6Am");

    public final StringPath rnSt6Pm = createString("rnSt6Pm");

    public final StringPath rnSt7Am = createString("rnSt7Am");

    public final StringPath rnSt7Pm = createString("rnSt7Pm");

    public final StringPath rnSt8 = createString("rnSt8");

    public final StringPath rnSt9 = createString("rnSt9");

    public final StringPath wf10 = createString("wf10");

    public final StringPath wf3Am = createString("wf3Am");

    public final StringPath wf3Pm = createString("wf3Pm");

    public final StringPath wf4Am = createString("wf4Am");

    public final StringPath wf4Pm = createString("wf4Pm");

    public final StringPath wf5Am = createString("wf5Am");

    public final StringPath wf5Pm = createString("wf5Pm");

    public final StringPath wf6Am = createString("wf6Am");

    public final StringPath wf6Pm = createString("wf6Pm");

    public final StringPath wf7Am = createString("wf7Am");

    public final StringPath wf7Pm = createString("wf7Pm");

    public final StringPath wf8 = createString("wf8");

    public final StringPath wf9 = createString("wf9");

    public QMidTermLandEntity(String variable) {
        super(MidTermLandEntity.class, forVariable(variable));
    }

    public QMidTermLandEntity(Path<? extends MidTermLandEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMidTermLandEntity(PathMetadata metadata) {
        super(MidTermLandEntity.class, metadata);
    }

}

