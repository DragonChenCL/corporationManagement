package com.dc.DSLEntity;

import com.dc.entity.Association;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QAssociation is a Querydsl query type for Association
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAssociation extends EntityPathBase<Association> {

    private static final long serialVersionUID = -1230879752L;

    public static final QAssociation association = new QAssociation("association");

    public final StringPath address = createString("address");

    public final StringPath assName = createString("assName");

    public final NumberPath<Integer> associationId = createNumber("associationId", Integer.class);

    public final NumberPath<Integer> categoryId = createNumber("categoryId", Integer.class);

    public final StringPath createdDate = createString("createdDate");

    public final StringPath description = createString("description");

    public final StringPath founder = createString("founder");

    public final StringPath honor = createString("honor");

    public final StringPath logo = createString("logo");

    public final StringPath momentImg = createString("momentImg");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath responsiblePerson = createString("responsiblePerson");

    public final StringPath status = createString("status");

    public QAssociation(String variable) {
        super(Association.class, forVariable(variable));
    }

    public QAssociation(Path<? extends Association> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssociation(PathMetadata metadata) {
        super(Association.class, metadata);
    }

}

