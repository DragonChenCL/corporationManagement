package com.dc.service;

import com.dc.dto.UserInfoDTO;
import com.dc.entity.Association;
import com.dc.entity.Authorities;
import com.dc.entity.User;
import com.dc.repository.AssociationRepository;
import com.dc.repository.AuthoritiesRepository;
import com.dc.repository.UserRepository;
import com.dc.utils.BeanUtils;
import com.dc.utils.UpLoadUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private AssociationRepository associationRepository;

    @Value("${imgUrl.headPortrait}")
    private String headPortraitUrl;
    /**
     * 获取用户信息
     * @param username
     * @return
     */
    public UserInfoDTO getUserInfo(String username){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            BeanUtils.copyProperties(user,userInfoDTO);
            Integer authId = user.getAuthId();
            //获取用户roles,角色名称
            Authorities authorities = authoritiesRepository.findAuthoritiesByAuthId(authId);
            if (authorities != null){
                userInfoDTO.setRoles(authorities.getAuthority().substring(5));
                userInfoDTO.setPosition(authorities.getAuthorityName());
            }
            Integer associationId = user.getAssociationId();
            //获取社团名称
            Association association = associationRepository.findAssociationByAssociationId(associationId);
            if (association != null){
                userInfoDTO.setAssociation(association.getAssName());
            }
        }
        return userInfoDTO;
    }

    /**
     * 更新用户信息
     * @param userInfoDTO
     * @return
     */

    public Boolean updateUserInfo(UserInfoDTO userInfoDTO){
        //暂时没有对数据进行校验 二期增加校验

        boolean flag = false;
        //先从数据库取出数据
        User user = new User();
        if (StringUtils.isNotBlank(String.valueOf(userInfoDTO.getUserId()))){
           user = userRepository.findUserByUserId(userInfoDTO.getUserId());
        }
        BeanUtils.copyPropertiesExcludeNull(userInfoDTO,user);
        try {
            userRepository.save(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新社团logo
     */
    public String updateLogo(MultipartFile file, int userId , String avatar){
        String outPath = "";

        try {
            outPath = UpLoadUtil.upload(file,userId,avatar,headPortraitUrl);
            userRepository.updateheadPortrait(outPath, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPath;
    }
}
