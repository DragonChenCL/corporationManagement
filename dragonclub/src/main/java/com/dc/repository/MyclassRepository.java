package com.dc.repository;

import com.dc.entity.Myclass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyclassRepository extends JpaRepository<Myclass,Integer> {

    public Myclass findMyclassByClassId(Integer classId);
}
