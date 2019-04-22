package com.dc.service;

import com.dc.entity.Myclass;
import com.dc.entity.User;
import com.dc.repository.MyclassRepository;
import com.dc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyclassService {
    @Autowired
    private MyclassRepository myclassRepository;

    @Autowired
    private UserRepository userRepository;

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

    /**
     * 删除班级信息
     * @param classId
     * @return
     */
    public String deleteClass(int classId){
        List<User> usersByMyclassId = userRepository.findUsersByMyclassId(classId);
        if (usersByMyclassId != null && usersByMyclassId.size() != 0){
            return "此班级下有人员绑定，无法删除！";
        }
        myclassRepository.deleteById(classId);
        return "删除成功";
    }

    /**
     * 新建班级信息
     * @param myclass
     * @return
     */
    public String createClass(Myclass myclass){
        Myclass myclassByClassName = myclassRepository.findMyclassByClassName(myclass.getClassName());
        if (myclassByClassName != null){
            return "此名称已存在，新增失败!";
        }
        myclassRepository.save(myclass);
        return "新增成功";
    }
}
