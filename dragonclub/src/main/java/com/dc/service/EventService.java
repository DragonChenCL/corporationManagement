package com.dc.service;

import com.dc.dto.EventCondition;
import com.dc.entity.Event;
import com.dc.repository.EventRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Event> findEvents(EventCondition condition) {
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize());
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
                return builder.and(list.toArray(new Predicate[0]));
            }
        };
        return eventRepository.findAll(pageable,specification);
    }
}
