package com.dc.dao;

import com.dc.DSLEntity.*;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAO {

    @Autowired
    private JPAQueryFactory queryFactory;

    public QueryResults<Tuple> findpage(Predicate predicate, Pageable pageable) {
        QUser qUser = QUser.user;
        QUserEvent qUserEvent = QUserEvent.userEvent;
        QEvent qEvent = QEvent.event;
        QCollege qCollege = QCollege.college;
        QMyclass qMyclass = QMyclass.myclass;

        QueryResults<Tuple> pageDTO = queryFactory
                .select(qUser, qEvent.eventName, qCollege.collegeName, qMyclass.className,
                        qUserEvent.status, qEvent.startDate, qUserEvent.id, qUserEvent.eventId,
                        qEvent.address)
                .from(qUser)
                .leftJoin(qUserEvent)
                .on(qUser.userId.eq(qUserEvent.userId))
                .leftJoin(qEvent)
                .on(qEvent.eventId.eq(qUserEvent.eventId))
                .leftJoin(qCollege)
                .on(qUser.collegeId.eq(qCollege.collegeId))
                .leftJoin(qMyclass)
                .on(qUser.myclassId.eq(qMyclass.classId))
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return pageDTO;
    }

    /**
     * 查询所有活动列表
     * @param predicate
     * @param pageable
     * @return
     */
    public QueryResults<Tuple> findEvents(Predicate predicate, Pageable pageable){
        QAssociation association = QAssociation.association;
        QEvent event =QEvent.event;

        QueryResults<Tuple> pageDTO = queryFactory
                .select(event,association.assName)
                .from(event)
                .leftJoin(association)
                .on(event.associationId.eq(association.associationId))
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return pageDTO;
    }

}
