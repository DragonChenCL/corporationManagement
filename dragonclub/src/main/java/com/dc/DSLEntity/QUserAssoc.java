package com.dc.DSLEntity;

import com.dc.entity.UserAssoc;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUserAssoc is a Querydsl query type for UserAssoc
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAssoc extends EntityPathBase<UserAssoc> {

    private static final long serialVersionUID = -306147263L;

    public static final QUserAssoc userAssoc = new QUserAssoc("userAssoc");

    public final NumberPath<Integer> associationId = createNumber("associationId", Integer.class);

    public final StringPath status = createString("status");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserAssoc(String variable) {
        super(UserAssoc.class, forVariable(variable));
    }

    public QUserAssoc(Path<? extends UserAssoc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAssoc(PathMetadata metadata) {
        super(UserAssoc.class, metadata);
    }

}

