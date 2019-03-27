package com.dc.DSLEntity;

import com.dc.entity.User;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1366879284L;

    public static final QUser user = new QUser("user");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> authId = createNumber("authId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> birthday = createDateTime("birthday", java.sql.Timestamp.class);

    public final NumberPath<Integer> collegeId = createNumber("collegeId", Integer.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> enable = createNumber("enable", Integer.class);

    public final StringPath headPortrait = createString("headPortrait");

    public final StringPath introduction = createString("introduction");

    public final NumberPath<Integer> myclassId = createNumber("myclassId", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath position = createString("position");

    public final StringPath qq = createString("qq");

    public final StringPath realName = createString("realName");

    public final DateTimePath<java.sql.Timestamp> registeryDate = createDateTime("registeryDate", java.sql.Timestamp.class);

    public final StringPath sex = createString("sex");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

