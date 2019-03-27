package com.dc.DSLEntity;

import com.dc.entity.Authorities;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QAuthorities is a Querydsl query type for Authorities
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuthorities extends EntityPathBase<Authorities> {

    private static final long serialVersionUID = -420971880L;

    public static final QAuthorities authorities = new QAuthorities("authorities");

    public final NumberPath<Integer> authId = createNumber("authId", Integer.class);

    public final StringPath authority = createString("authority");

    public final StringPath authorityName = createString("authorityName");

    public QAuthorities(String variable) {
        super(Authorities.class, forVariable(variable));
    }

    public QAuthorities(Path<? extends Authorities> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorities(PathMetadata metadata) {
        super(Authorities.class, metadata);
    }

}

