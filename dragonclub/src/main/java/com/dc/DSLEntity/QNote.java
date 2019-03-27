package com.dc.DSLEntity;

import com.dc.entity.Note;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QNote is a Querydsl query type for Note
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNote extends EntityPathBase<Note> {

    private static final long serialVersionUID = 1366667355L;

    public static final QNote note = new QNote("note");

    public final NumberPath<Integer> associationId = createNumber("associationId", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> eventId = createNumber("eventId", Integer.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.sql.Timestamp> noteDate = createDateTime("noteDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> noteId = createNumber("noteId", Integer.class);

    public final StringPath replay = createString("replay");

    public QNote(String variable) {
        super(Note.class, forVariable(variable));
    }

    public QNote(Path<? extends Note> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNote(PathMetadata metadata) {
        super(Note.class, metadata);
    }

}

