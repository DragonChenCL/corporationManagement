package com.dc.DSLEntity;

import com.dc.entity.News;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QNews is a Querydsl query type for News
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNews extends EntityPathBase<News> {

    private static final long serialVersionUID = 1366657852L;

    public static final QNews news = new QNews("news");

    public final NumberPath<Integer> associationId = createNumber("associationId", Integer.class);

    public final StringPath newsContent = createString("newsContent");

    public final NumberPath<Integer> newsId = createNumber("newsId", Integer.class);

    public final StringPath newsImg = createString("newsImg");

    public final StringPath newsTitle = createString("newsTitle");

    public final DateTimePath<java.sql.Timestamp> publishDate = createDateTime("publishDate", java.sql.Timestamp.class);

    public QNews(String variable) {
        super(News.class, forVariable(variable));
    }

    public QNews(Path<? extends News> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNews(PathMetadata metadata) {
        super(News.class, metadata);
    }

}

