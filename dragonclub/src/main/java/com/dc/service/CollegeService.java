package com.dc.service;

import com.dc.entity.College;
import com.dc.entity.Myclass;
import com.dc.entity.User;
import com.dc.repository.CollegeRepository;
import com.dc.repository.MyclassRepository;
import com.dc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyclassRepository myclassRepository;

    /**
     * 获取所有院系信息
     * @return
     */
    public List<College> findColleges(){
        return collegeRepository.findAll();
    }

    /**
     * 删除院系信息
     * @param id
     * @return
     */
    public String deleteCollege(int id){
        List<User> usersByCollegeId = userRepository.findUsersByCollegeId(id);
        if (usersByCollegeId != null && usersByCollegeId.size() != 0){
            return "此院系下有人员绑定，无法删除！";
        }
        List<Myclass> myclassByCollegeId = myclassRepository.findMyclassByCollegeId(id);
        if (myclassByCollegeId != null && myclassByCollegeId.size() != 0){
            return "此院系下有班级绑定，无法删除！";
        }
        collegeRepository.deleteById(id);
        return "删除成功";
    }

    /**
     * 新增院系信息
     * @param college
     */
    public String createCollege(College college){
        College college1 = collegeRepository.findCollegeByCollegeName(college.getCollegeName());
        if (college1 != null){
            return "此名称已存在，新增失败";
        }
        collegeRepository.save(college);
        return "新增成功";
    }

    /**
     * 编辑院系信息
     * @param college
     */
    public String editCollege(College college){
        College collegeByCollegeName = collegeRepository.findCollegeByCollegeName(college.getCollegeName());
        College collegeByCollegeId = collegeRepository.findCollegeByCollegeId(college.getCollegeId());
        if (collegeByCollegeName != null){
            if (!collegeByCollegeName.getCollegeName().equals(collegeByCollegeId.getCollegeName())){
                return "此名称已存在，编辑失败";
            }
        }
        collegeRepository.save(college);
        return "编辑成功";
    }
}
