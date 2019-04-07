package com.dc.DSLEntity;

import com.dc.entity.UserEvent;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUserEvent is a Querydsl query type for UserEvent
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserEvent extends EntityPathBase<UserEvent> {

    private static final long serialVersionUID = -302377274L;

    public static final QUserEvent userEvent = new QUserEvent("userEvent");

    public final NumberPath<Integer> eventId = createNumber("eventId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath message = createString("message");

    public final StringPath status = createString("status");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserEvent(String variable) {
        super(UserEvent.class, forVariable(variable));
    }

    public QUserEvent(Path<? extends UserEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEvent(PathMetadata metadata) {
        super(UserEvent.class, metadata);
    }

}

