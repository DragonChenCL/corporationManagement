package com.dc.service;

import com.dc.DSLEntity.QUser;
import com.dc.DSLEntity.QUserAssoc;
import com.dc.dao.UserDAO;
import com.dc.dto.*;
import com.dc.entity.*;
import com.dc.repository.*;
import com.dc.utils.BeanUtils;
import com.dc.utils.UpLoadUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.builder;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final AssociationRepository associationRepository;
    private final MyclassRepository myclassRepository;
    private final CollegeRepository collegeRepository;
    private final UserAssocRepository userAssocRepository;

    @Autowired
    private UserEventRepository userEventRepository;
    @Autowired
    private EventRepository eventRepository;

    @Value("${imgUrl.headPortrait}")
    private String headPortraitUrl;

    @Autowired
    public UserService(UserRepository userRepository, AuthoritiesRepository authoritiesRepository, AssociationRepository associationRepository, MyclassRepository myclassRepository, CollegeRepository collegeRepository, UserAssocRepository userAssocRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.associationRepository = associationRepository;
        this.myclassRepository = myclassRepository;
        this.collegeRepository = collegeRepository;
        this.userAssocRepository = userAssocRepository;
    }

    /**
     * 用户申请加入社团
     * @param userId
     * @param assocId
     * @return
     */
    public String applyAssoc(Integer userId , Integer assocId){
        UserAssoc userIdAndAssociationId = userAssocRepository.findUserAssocByUserIdAndAssociationId(userId, assocId);
        if (userIdAndAssociationId != null){
            return "已经在社团中";
        }
        UserAssoc userAssoc = new UserAssoc();
        userAssoc.setUserId(userId);
        userAssoc.setAssociationId(assocId);
        userAssoc.setStatus("待审核");
        userAssocRepository.save(userAssoc);
        return "已提交申请，请等候";
    }

    /**
     * 用户申请加入活动
     * @param userId
     * @param eventId
     * @return
     */
    public String applyEvent(Integer userId , Integer eventId){
        UserEvent idAndEventId = userEventRepository.findUserEventByUserIdAndEventId(userId, eventId);
        if (idAndEventId != null){
            return "已经报名";
        }
        UserEvent userEvent = new UserEvent();
        userEvent.setUserId(userId);
        userEvent.setEventId(eventId);
        userEvent.setStatus("待审核");
        userEventRepository.save(userEvent);
        return "已提交申请，请等候";
    }

    /**
     * 获取用户信息
     */
    public UserInfoDTO getUserInfo(String username) {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().build();
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
            //获取社团和状态信息
            List<UserAssoc> userAssocs = new ArrayList<>();
            List<UserAssocDTO> userAssocDTOS = new ArrayList<>();
            userAssocs = userAssocRepository.findUserAssocByUserId(user.getUserId());
            if (userAssocs.size() != 0) {
                for (UserAssoc userAssoc : userAssocs) {
                    UserAssocDTO userAssocDTO = new UserAssocDTO();
                    Association associationByAssociationId = associationRepository.findAssociationByAssociationId(userAssoc.getAssociationId());
                    userAssocDTO.setAssocId(associationByAssociationId.getAssociationId());
                    userAssocDTO.setAssocName(associationByAssociationId.getAssName());
                    userAssocDTO.setStatus(userAssoc.getStatus());
                    userAssocDTOS.add(userAssocDTO);
                }
                userInfoDTO.setUserAssocs(userAssocDTOS);
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

            //获取参加的活动信息
            List<UserEventDTO> userEventDTOs = new ArrayList<>();
            List<UserEvent> userEvents = userEventRepository.findUserEventByUserId(userInfoDTO.getUserId());
            for (UserEvent userEvent : userEvents) {
                UserEventDTO userEventDTO = new UserEventDTO();
                BeanUtils.copyPropertiesExcludeNull(userEvent,userEventDTO);
                Event event = eventRepository.findEventByEventId(userEvent.getEventId());
                userEventDTO.setEventName(event.getEventName());
                userEventDTOs.add(userEventDTO);
            }
            userInfoDTO.setUserEvents(userEventDTOs);
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
            outPath = UpLoadUtil.upload(file, avatar, headPortraitUrl);
            userRepository.updateheadPortrait(outPath, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPath;
    }

    public PageDTO<UserInfoDTO> getMemberList(MemberListCondition condition) {
        //CurrentPage从0开始
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC, "userId");

        QUser user = QUser.user;
        QUserAssoc userAssoc = QUserAssoc.userAssoc;

        //初始化组装条件(类似where 1=1)
        Predicate predicate = user.isNotNull().or(user.isNull());
        //执行动态条件拼装
        if (null != condition.getRealName() && !"".equals(condition.getRealName())) {
            predicate = ExpressionUtils.and(predicate, user.realName.like("%" + condition.getRealName() + "%"));
        }
        if (null != condition.getAssociationId() && !"".equals(String.valueOf(condition.getAssociationId()))) {
            predicate = ExpressionUtils.and(predicate, userAssoc.associationId.eq(condition.getAssociationId()));
        }
        if (null != condition.getCollegeId() && !"".equals(String.valueOf(condition.getCollegeId()))) {
            predicate = ExpressionUtils.and(predicate, user.collegeId.eq(condition.getCollegeId()));
        }
        if (null != condition.getMyclassId() && !"".equals(String.valueOf(condition.getMyclassId()))) {
            predicate = ExpressionUtils.and(predicate, user.myclassId.eq(condition.getMyclassId()));
        }
        if (null != condition.getEnable() && !"".equals(String.valueOf(condition.getEnable()))) {
            predicate = ExpressionUtils.and(predicate, user.enable.eq(condition.getEnable()));
        }
        //管理员信息不拿出
        predicate = ExpressionUtils.and(predicate, user.authId.notIn(2));
        //查看是否通过审核
        predicate = ExpressionUtils.and(predicate, userAssoc.status.eq(condition.getStatus()));
        //使用queryDSL框架
        PageDTO<UserInfoDTO> pageDTO = new PageDTO<UserInfoDTO>();
        QueryResults<Tuple> findpage = userDAO.findpage(predicate, pageable);
        List<UserInfoDTO> list = new ArrayList<>();
        for (Tuple result : findpage.getResults()) {
            UserInfoDTO userInfoDTO = UserInfoDTO.builder().build();
            //这个数组得到就是查询出的每一个属性（或者类）的集合
            Object[] objects = result.toArray();
            BeanUtils.copyPropertiesExcludeNull(objects[0], userInfoDTO);
            userInfoDTO.setStatus(objects[1].toString());
            userInfoDTO.setCollege(objects[3].toString());
            userInfoDTO.setMyClass(objects[4].toString());
            list.add(userInfoDTO);
        }
        pageDTO.setContents(list);
        pageDTO.setTotalElements(findpage.getTotal());
        return pageDTO;
    }

    @Transactional
    public void deleteUser(Integer userId , Integer assocId) {
        userAssocRepository.deleteUserAssocByUserIdAndAssociationId(userId,assocId);
    }

    /**
     * 用户是否通过加入社团
     *
     * @return
     */
    public int updateStatus(UserStatusDTO dto) {
        return userAssocRepository.updateStatus(dto.getStatus(), dto.getMessage(), dto.getUserId(), dto.getAssocId());

    }
}
