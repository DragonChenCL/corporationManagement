package com.dc.service;

import com.dc.entity.College;
import com.dc.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    public List<College> findColleges(){
        return collegeRepository.findAll();
    }
}
