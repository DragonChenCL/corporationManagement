package com.dc.DSLEntity;

import com.dc.entity.Myclass;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMyclass is a Querydsl query type for Myclass
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMyclass extends EntityPathBase<Myclass> {

    private static final long serialVersionUID = 1775567427L;

    public static final QMyclass myclass = new QMyclass("myclass");

    public final NumberPath<Integer> classId = createNumber("classId", Integer.class);

    public final StringPath className = createString("className");

    public final NumberPath<Integer> collegeId = createNumber("collegeId", Integer.class);

    public QMyclass(String variable) {
        super(Myclass.class, forVariable(variable));
    }

    public QMyclass(Path<? extends Myclass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMyclass(PathMetadata metadata) {
        super(Myclass.class, metadata);
    }

}

