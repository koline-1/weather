package com.practice.weather.midTerm.ocean.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMidTermOceanEntity is a Querydsl query type for MidTermOceanEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMidTermOceanEntity extends EntityPathBase<MidTermOceanEntity> {

    private static final long serialVersionUID = 885929006L;

    public static final QMidTermOceanEntity midTermOceanEntity = new QMidTermOceanEntity("midTermOceanEntity");

    public final com.practice.weather.baseEntity.QBaseEntity _super = new com.practice.weather.baseEntity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath regId = createString("regId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

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

    public final StringPath wh10A = createString("wh10A");

    public final StringPath wh10B = createString("wh10B");

    public final StringPath wh3AAm = createString("wh3AAm");

    public final StringPath wh3APm = createString("wh3APm");

    public final StringPath wh3BAm = createString("wh3BAm");

    public final StringPath wh3BPm = createString("wh3BPm");

    public final StringPath wh4AAm = createString("wh4AAm");

    public final StringPath wh4APm = createString("wh4APm");

    public final StringPath wh4BAm = createString("wh4BAm");

    public final StringPath wh4BPm = createString("wh4BPm");

    public final StringPath wh5AAm = createString("wh5AAm");

    public final StringPath wh5APm = createString("wh5APm");

    public final StringPath wh5BAm = createString("wh5BAm");

    public final StringPath wh5BPm = createString("wh5BPm");

    public final StringPath wh6AAm = createString("wh6AAm");

    public final StringPath wh6APm = createString("wh6APm");

    public final StringPath wh6BAm = createString("wh6BAm");

    public final StringPath wh6BPm = createString("wh6BPm");

    public final StringPath wh7AAm = createString("wh7AAm");

    public final StringPath wh7APm = createString("wh7APm");

    public final StringPath wh7BAm = createString("wh7BAm");

    public final StringPath wh7BPm = createString("wh7BPm");

    public final StringPath wh8A = createString("wh8A");

    public final StringPath wh8B = createString("wh8B");

    public final StringPath wh9A = createString("wh9A");

    public final StringPath wh9B = createString("wh9B");

    public QMidTermOceanEntity(String variable) {
        super(MidTermOceanEntity.class, forVariable(variable));
    }

    public QMidTermOceanEntity(Path<? extends MidTermOceanEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMidTermOceanEntity(PathMetadata metadata) {
        super(MidTermOceanEntity.class, metadata);
    }

}

