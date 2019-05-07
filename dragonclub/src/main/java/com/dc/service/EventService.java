package com.dc.service;

import com.dc.DSLEntity.*;
import com.dc.dao.EventDAO;
import com.dc.dto.*;
import com.dc.entity.Association;
import com.dc.entity.Event;
import com.dc.entity.UserEvent;
import com.dc.repository.AssociationRepository;
import com.dc.repository.EventRepository;
import com.dc.repository.UserEventRepository;
import com.dc.utils.BeanUtils;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.sun.org.apache.regexp.internal.REUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private UserEventRepository userEventRepository;
    @Autowired
    private AssociationRepository associationRepository;

    /**
     * 获取活动列表
     * @param condition
     * @return
     */
    public Page<Event> findEvents(EventCondition condition) {
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC,"eventId");
        //封装条件查询对象Specification
        Specification<Event> specification = new Specification<Event>() {
            @Override
            // Root 用于获取属性字段，CriteriaQuery可以用于简单条件查询，CriteriaBuilder 用于构造复杂条件查询
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                //集合 用于封装查询条件
                List<Predicate> list = new ArrayList<Predicate>();
                //简单单表查询
                if (StringUtils.isNotBlank(condition.getEventName())) {
                    list.add(builder.like(root.get("eventName").as(String.class), "%" + condition.getEventName() + "%"));
                }
                if (condition.getStartDate() != null) {
                    list.add(builder.like(root.get("startDate").as(String.class), "%" + condition.getStartDate() + "%"));
                }
                if (StringUtils.isNotBlank(condition.getStatus())) {
                    list.add(builder.equal(root.get("status").as(String.class), condition.getStatus()));
                }
                list.add(builder.equal(root.get("associationId").as(Integer.class), condition.getAssocId()));
                return builder.and(list.toArray(new Predicate[0]));
            }
        };
        return eventRepository.findAll(specification, pageable);
    }

    /**
     * 社团管理员查看活动
     * @param condition
     * @return
     */
    public PageDTO<Event> findEventsBySys(EventCondition condition) {
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC,"eventId");

        QEvent event = QEvent.event;
        QAssociation association = QAssociation.association;

        //初始化组装条件(类似where 1=1)
        com.querydsl.core.types.Predicate predicate = event.isNotNull().or(event.isNull());

        if (StringUtils.isNotBlank(condition.getEventName())) {
            predicate = ExpressionUtils.and(predicate, event.eventName.like("%" + condition.getEventName() + "%"));
        }
        if (condition.getStartDate() != null) {
            predicate = ExpressionUtils.and(predicate, event.startDate.like("%" + condition.getStartDate() + "%"));
        }
        if (condition.getAssocId() != null) {
            predicate = ExpressionUtils.and(predicate, event.associationId.eq(condition.getAssocId()));
        }
        if (StringUtils.isNotBlank(condition.getStatus())) {
            predicate = ExpressionUtils.and(predicate, event.status.eq(condition.getStatus()));
        }
        PageDTO<Event> pageDTO = new PageDTO<Event>();
        QueryResults<Tuple> findEvents = eventDAO.findEvents(predicate, pageable);
        List<Event> list = new ArrayList<Event>();
        for (Tuple result : findEvents.getResults()) {
            Event event1 = new Event();
            //这个数组得到就是查询出的每一个属性（或者类）的集合
            Object[] objects = result.toArray();
            BeanUtils.copyPropertiesExcludeNull(objects[0], event1);
            event1.setAssName(objects[1].toString());
            list.add(event1);
        }
        pageDTO.setContents(list);
        pageDTO.setTotalElements(findEvents.getTotal());
        return pageDTO;
    }

    /**
     * 社长申请活动
     *
     * @param event
     * @return
     */
    public Event applyEvent(Event event) {
        event.setStatus("待审核");
        return eventRepository.save(event);
    }

    /**
     * 活动状态更新
     * @return
     */
    public int eventStatus(EventStatusDTO dto){
        return eventRepository.updateStatus(dto.getStatus(),dto.getMessage(),dto.getEventId());
    }

    /**
     * 获取申请活动的成员列表
     *
     * @param condition
     * @return
     */
    public PageDTO<UserApplyDTO> geteUserApply(MemberListCondition condition) {
        //CurrentPage从0开始
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC, "userId");

        QEvent event = QEvent.event;
        QUser user = QUser.user;
        QUserEvent userEvent = QUserEvent.userEvent;

        //初始化组装条件(类似where 1=1)
        com.querydsl.core.types.Predicate predicate = user.isNotNull().or(user.isNull());
        //执行动态条件拼装
        if (null != condition.getAssociationId()) {
            predicate = ExpressionUtils.and(predicate, event.associationId.eq(condition.getAssociationId()));
        }
        if (null != condition.getStartDate() && !"".equals(condition.getStartDate())) {
            predicate = ExpressionUtils.and(predicate, event.startDate.like("%" + condition.getStartDate() + "%"));
        }
        if (null != condition.getEventName() && !"".equals(condition.getEventName())) {
            predicate = ExpressionUtils.and(predicate, event.eventName.like("%" + condition.getEventName() + "%"));
        }
        if (null != condition.getStatus() && !"".equals(condition.getStatus())) {
            predicate = ExpressionUtils.and(predicate, userEvent.status.eq(condition.getStatus()));
        }
        //使用queryDSL框架
        PageDTO<UserApplyDTO> pageDTO = new PageDTO<UserApplyDTO>();
        QueryResults<Tuple> findpage = eventDAO.findpage(predicate, pageable);
        List<UserApplyDTO> list = new ArrayList<>();
        for (Tuple result : findpage.getResults()) {
            UserApplyDTO userApplyDTO = UserApplyDTO.builder().build();
            //这个数组得到就是查询出的每一个属性（或者类）的集合
            Object[] objects = result.toArray();
            BeanUtils.copyPropertiesExcludeNull(objects[0], userApplyDTO);
            userApplyDTO.setEventName(objects[1].toString());
            userApplyDTO.setCollege(objects[2].toString());
            userApplyDTO.setMyClass(objects[3].toString());
            userApplyDTO.setStatus(objects[4].toString());
            userApplyDTO.setStartDate(objects[5].toString());
            userApplyDTO.setId(Integer.valueOf(objects[6].toString()));
            userApplyDTO.setEventId(Integer.valueOf(objects[7].toString()));
            userApplyDTO.setEventAddress(objects[8].toString());
            list.add(userApplyDTO);
        }
        pageDTO.setContents(list);
        pageDTO.setTotalElements(findpage.getTotal());
        return pageDTO;
    }

    /**
     * 更新用户参加活动信息
     * @param userEvent
     * @return
     */
    public UserEvent updateUserEvent(UserEvent userEvent){
        return userEventRepository.save(userEvent);
    }

    /**
     * 根据id获取活动详细信息
     * @param id
     * @return
     */
    public EventDTO findEventById(Integer id){
        Event eventByEventId = eventRepository.findEventByEventId(id);
        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyPropertiesExcludeNull(eventByEventId,eventDTO);
        Association association = associationRepository.findAssociationByAssociationId(eventByEventId.getAssociationId());
        eventDTO.setAssName(association.getAssName());
        return eventDTO;
    }

}
