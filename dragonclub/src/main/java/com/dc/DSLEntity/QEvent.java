package com.dc.DSLEntity;

import com.dc.entity.Event;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -591102127L;

    public static final QEvent event = new QEvent("event");

    public final StringPath actualFunds = createString("actualFunds");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> associationId = createNumber("associationId", Integer.class);

    public final StringPath content = createString("content");

    public final StringPath endDate = createString("endDate");

    public final StringPath enentImg = createString("enentImg");

    public final NumberPath<Integer> eventId = createNumber("eventId", Integer.class);

    public final StringPath eventName = createString("eventName");

    public final StringPath exceptFunds = createString("exceptFunds");

    public final StringPath level = createString("level");

    public final StringPath message = createString("message");

    public final StringPath responsiblePerson = createString("responsiblePerson");

    public final StringPath startDate = createString("startDate");

    public final StringPath status = createString("status");

    public final StringPath undertakingCompany = createString("undertakingCompany");

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

