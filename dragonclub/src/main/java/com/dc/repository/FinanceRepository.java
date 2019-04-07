package com.dc.repository;

import com.dc.entity.Finance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FinanceRepository extends JpaRepository<Finance,Integer> {

    @Query(value = "select sum(money) from Finance where type = '收入'")
    public Integer findInFinance();

    @Query(value = "select sum(money) from Finance where type = '支出'")
    public Integer findOutFinance();

    Page<Finance> findAll(Specification<Finance> specification , Pageable pageable);

}
