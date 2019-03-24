package com.dc.repository;

import com.dc.entity.Myclass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyclassRepository extends JpaRepository<Myclass,Integer> {

    public List<Myclass> findMyclassByCollegeId(Integer collegeId);

    public Myclass findMyclassByClassId(Integer myclassId);
}
