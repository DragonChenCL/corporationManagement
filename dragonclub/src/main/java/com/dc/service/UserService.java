package com.dc.service;

import com.dc.dto.UserInfoDTO;
import com.dc.entity.Association;
import com.dc.entity.Authorities;
import com.dc.entity.User;
import com.dc.repository.AssociationRepository;
import com.dc.repository.AuthoritiesRepository;
import com.dc.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private AssociationRepository associationRepository;

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
}
