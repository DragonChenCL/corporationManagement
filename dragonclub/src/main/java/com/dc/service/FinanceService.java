package com.dc.service;

import com.dc.dto.FinanceCondition;
import com.dc.entity.Finance;
import com.dc.repository.FinanceRepository;
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
public class FinanceService {
    @Autowired
    private FinanceRepository financeRepository;

    public Integer findInFinance() {
        return financeRepository.findInFinance();
    }

    public Integer findOutFinance() {
        return financeRepository.findOutFinance();
    }

    public Finance create(Finance finance) {
        return financeRepository.save(finance);
    }

    public Page<Finance> findAll(FinanceCondition condition) {
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC,"financeId");

        Specification<Finance> specification = new Specification<Finance>() {

            @Override
            public Predicate toPredicate(Root<Finance> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(condition.getReason())) {
                    list.add(cb.like(root.get("reason").as(String.class), "%" + condition.getReason() + "%"));
                }
                if (StringUtils.isNotBlank(condition.getStartDate())) {
                    list.add(cb.like(root.get("startDate").as(String.class), "%" + condition.getStartDate() + "%"));
                }
                list.add(cb.equal(root.get("associationId").as(String.class), condition.getAssociationId()));
                list.add(cb.equal(root.get("type").as(String.class), condition.getType()));
                return cb.and(list.toArray(new Predicate[0]));
            }
        };
        return financeRepository.findAll(specification, pageable);
    }

    public void delFinance(Integer financeId) throws Exception{
        financeRepository.deleteById(financeId);
    }
}
