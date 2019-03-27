package com.dc.DSLEntity;

import com.dc.entity.College;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCollege is a Querydsl query type for College
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCollege extends EntityPathBase<College> {

    private static final long serialVersionUID = 1212488846L;

    public static final QCollege college = new QCollege("college");

    public final NumberPath<Integer> collegeId = createNumber("collegeId", Integer.class);

    public final StringPath collegeName = createString("collegeName");

    public QCollege(String variable) {
        super(College.class, forVariable(variable));
    }

    public QCollege(Path<? extends College> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCollege(PathMetadata metadata) {
        super(College.class, metadata);
    }

}

