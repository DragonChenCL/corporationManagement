package com.dc.DSLEntity;

import com.dc.entity.Apply;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QApply is a Querydsl query type for Apply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApply extends EntityPathBase<Apply> {

    private static final long serialVersionUID = -594964443L;

    public static final QApply apply = new QApply("apply");

    public final DateTimePath<java.sql.Timestamp> applyDate = createDateTime("applyDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> applyId = createNumber("applyId", Integer.class);

    public final NumberPath<Integer> eventId = createNumber("eventId", Integer.class);

    public final StringPath status = createString("status");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QApply(String variable) {
        super(Apply.class, forVariable(variable));
    }

    public QApply(Path<? extends Apply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApply(PathMetadata metadata) {
        super(Apply.class, metadata);
    }

}

