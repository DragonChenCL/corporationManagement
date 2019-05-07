package com.dc.service;

import com.dc.DSLEntity.QApply;
import com.dc.DSLEntity.QEvent;
import com.dc.dao.ApplyDAO;
import com.dc.dto.ApplyCondition;
import com.dc.dto.ApplyDTO;
import com.dc.dto.PageDTO;
import com.dc.entity.Apply;
import com.dc.repository.ApplyRepository;
import com.dc.utils.BeanUtils;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private ApplyDAO applyDAO;

    public void createApply(Apply apply) {
        applyRepository.save(apply);
    }

    public void deleteApply(Integer id) {
        applyRepository.deleteById(id);
    }

    public PageDTO<ApplyDTO> findApplys(ApplyCondition condition) {
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC,"applyId");

        QApply apply = QApply.apply;
        QEvent event = QEvent.event;

        //初始化组装条件(类似where 1=1)
        com.querydsl.core.types.Predicate predicate = apply.isNotNull().or(apply.isNull());

        if (StringUtils.isNotBlank(condition.getApplyDate())) {
            //建立格式化模板
            StringTemplate dateFor = Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d %H')", apply.applyDate);
            predicate = ExpressionUtils.and(predicate, dateFor.like("%" + condition.getApplyDate() + "%"));
        }
        //多表查询的时候将右表eventName属性作为查询条件
        if (StringUtils.isNotBlank(condition.getEventName())) {
            predicate = ExpressionUtils.and(predicate, event.eventName.like("%" +condition.getEventName()+"%" ));
        }

        PageDTO<ApplyDTO> pageDTO = new PageDTO<>();
        QueryResults<Tuple> queryResults = applyDAO.findApplys(predicate, pageable);
        List<ApplyDTO> list = new ArrayList<>();
        for (Tuple result : queryResults.getResults()) {
            ApplyDTO applyDTO = new ApplyDTO();
            //这个数组得到就是查询出的每一个属性（或者 类）的集合
            Object[] objects = result.toArray();
            BeanUtils.copyPropertiesExcludeNull(objects[0], applyDTO);
            applyDTO.setEventName(objects[1].toString());
            applyDTO.setRealName(objects[2].toString());
            list.add(applyDTO);
        }
        pageDTO.setContents(list);
        pageDTO.setTotalElements(queryResults.getTotal());
        return pageDTO;
    }
}
