package com.dc.service;

import com.dc.entity.Myclass;
import com.dc.repository.MyclassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyclassService {
    @Autowired
    private MyclassRepository myclassRepository;

    /**
     * 根据学院id获取班级信息
     *
     * @return
     */
    public List<Myclass> findMyclasses(Integer collegeId) {
        List<Myclass> myclasses = new ArrayList<Myclass>();
        myclasses = myclassRepository.findMyclassByCollegeId(collegeId);
        return myclasses;
    }

}
