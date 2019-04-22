package com.dc.dao;

import com.dc.DSLEntity.QApply;
import com.dc.DSLEntity.QEvent;
import com.dc.DSLEntity.QUser;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ApplyDAO {

    @Autowired
    private JPAQueryFactory queryFactory;

    public QueryResults<Tuple> findApplys(Predicate predicate, Pageable pageable) {
        QApply apply = QApply.apply;
        QEvent event = QEvent.event;
        QUser user = QUser.user;

        QueryResults<Tuple> queryResults = queryFactory
                .select(apply, event.eventName, user.realName)
                .from(apply)
                .leftJoin(event)
                .on(apply.eventId.eq(event.eventId))
                .leftJoin(user)
                .on(apply.userId.eq(user.userId))
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return queryResults;
    }
}
