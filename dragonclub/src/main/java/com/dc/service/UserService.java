package com.dc.service;

import com.dc.dto.MemberListCondition;
import com.dc.dto.PageDTO;
import com.dc.dto.UserInfoDTO;
import com.dc.entity.*;
import com.dc.repository.*;
import com.dc.utils.BeanUtils;
import com.dc.utils.UpLoadUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private AssociationRepository associationRepository;
    @Autowired
    private MyclassRepository myclassRepository;
    @Autowired
    private CollegeRepository collegeRepository;

    @Value("${imgUrl.headPortrait}")
    private String headPortraitUrl;

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     */
    public UserInfoDTO getUserInfo(String username) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            BeanUtils.copyProperties(user, userInfoDTO);
            Integer authId = user.getAuthId();
            //获取用户roles,角色名称
            Authorities authorities = authoritiesRepository.findAuthoritiesByAuthId(authId);
            if (authorities != null) {
                userInfoDTO.setRoles(authorities.getAuthority().substring(5));
                userInfoDTO.setPosition(authorities.getAuthorityName());
            }
            Integer associationId = user.getAssociationId();
            //获取社团名称
            Association association = associationRepository.findAssociationByAssociationId(associationId);
            if (association != null) {
                userInfoDTO.setAssociation(association.getAssName());
            }
            //获取学院信息
            if (user.getCollegeId() != null) {
                College college = collegeRepository.findCollegeByCollegeId(user.getCollegeId());
                if (college != null) {
                    userInfoDTO.setCollege(college.getCollegeName());
                }
            }
            //获取班级信息
            if (user.getMyclassId() != null) {
                Myclass myclass = myclassRepository.findMyclassByClassId(user.getMyclassId());
                if (myclass != null) {
                    userInfoDTO.setMyClass(myclass.getClassName());
                }
            }
        }
        return userInfoDTO;
    }

    /**
     * 更新用户信息
     *
     * @param userInfoDTO
     * @return
     */

    public Boolean updateUserInfo(UserInfoDTO userInfoDTO) {
        //暂时没有对数据进行校验 二期增加校验

        boolean flag = false;
        //先从数据库取出数据
        User user = new User();
        if (StringUtils.isNotBlank(String.valueOf(userInfoDTO.getUserId()))) {
            user = userRepository.findUserByUserId(userInfoDTO.getUserId());
        }
        BeanUtils.copyPropertiesExcludeNull(userInfoDTO, user);
        try {
            userRepository.save(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新用户头像
     */
    public String updateLogo(MultipartFile file, int userId, String avatar) {
        String outPath = "";

        try {
            outPath = UpLoadUtil.upload(file, userId, avatar, headPortraitUrl);
            userRepository.updateheadPortrait(outPath, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPath;
    }

    public PageDTO<UserInfoDTO> getMemberList(MemberListCondition condition) {
        //CurrentPage从0开始
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.ASC, "userId");
        Page<User> userPage = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != condition.getRealName() && !"".equals(condition.getRealName())) {
                    list.add(criteriaBuilder.like(root.get("realName").as(String.class), "%" + condition.getRealName() + "%"));
                }
                if (null != condition.getCollegeId() &&  !"".equals(condition.getCollegeId())) {
                    list.add(criteriaBuilder.equal(root.get("collegeId").as(Integer.class),condition.getCollegeId()));
                }
                if (null != condition.getAssociationId() &&  0 != condition.getAssociationId()) {
                    list.add(criteriaBuilder.equal(root.get("associationId").as(Integer.class),condition.getAssociationId()));
                }
                if (null != condition.getMyclassId() && !"".equals(condition.getMyclassId())) {
                    list.add(criteriaBuilder.equal(root.get("myclassId").as(Integer.class), condition.getMyclassId()));
                }
                if (null != condition.getEnable() && !"".equals(condition.getEnable())) {
                    list.add(criteriaBuilder.equal(root.get("enable").as(Integer.class), Integer.valueOf(condition.getEnable())));
                }
                //判断是否加入社团
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), 0));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        PageDTO<UserInfoDTO> pageDTO = new PageDTO<>();
        BeanUtils.copyPropertiesExcludeNull(userPage, pageDTO);
        List<UserInfoDTO> list = new ArrayList<>();
        if (userPage.getContent() != null && userPage.getContent().size() != 0){
            for (User user : userPage.getContent()) {
                UserInfoDTO userInfo = getUserInfo(user.getUsername());
                list.add(userInfo);
            }
        }
        pageDTO.setContents(list);
        return pageDTO;
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

}
