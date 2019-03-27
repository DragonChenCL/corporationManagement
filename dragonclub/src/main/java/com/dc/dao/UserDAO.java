package com.dc.dao;

import com.dc.DSLEntity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UserDAO {

    @Autowired
    private JPAQueryFactory queryFactory;

    public QueryResults<Tuple> findpage(Predicate predicate, Pageable pageable) {
        QUser qUser = QUser.user;
        QUserAssoc qUserAssoc = QUserAssoc.userAssoc;
        QAssociation qAssociation = QAssociation.association;
        QCollege qCollege = QCollege.college;
        QMyclass qMyclass = QMyclass.myclass;

        QueryResults<Tuple> pageDTO = queryFactory
                .select(qUser,qUserAssoc.status,qAssociation.assName,qCollege.collegeName,qMyclass.className)
                .from(qUser)
                .leftJoin(qUserAssoc)
                .on(qUser.userId.eq(qUserAssoc.userId))
                .leftJoin(qAssociation)
                .on(qUserAssoc.associationId.eq(qAssociation.associationId))
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
}
