package com.dc.repository;

import com.dc.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Integer> {

    public College findCollegeByCollegeId(Integer id);

    public College findCollegeByCollegeName(String collegeName);
}
