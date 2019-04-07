package com.dc.DSLEntity;

import com.dc.entity.Finance;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QFinance is a Querydsl query type for Finance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFinance extends EntityPathBase<Finance> {

    private static final long serialVersionUID = -590214447L;

    public static final QFinance finance = new QFinance("finance");

    public final NumberPath<Integer> associationId = createNumber("associationId", Integer.class);

    public final NumberPath<Integer> financeId = createNumber("financeId", Integer.class);

    public final NumberPath<Integer> money = createNumber("money",Integer.class);

    public final StringPath reason = createString("reason");

    public final StringPath startDate = createString("startDate");

    public final StringPath type = createString("type");

    public QFinance(String variable) {
        super(Finance.class, forVariable(variable));
    }

    public QFinance(Path<? extends Finance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFinance(PathMetadata metadata) {
        super(Finance.class, metadata);
    }

}

