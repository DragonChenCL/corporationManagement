package com.dc.jwtutils;

import com.dc.entity.Authorities;
import com.dc.entity.User;
import com.dc.exception.MyException;
import com.dc.repository.AuthoritiesRepository;
import com.dc.repository.UserRepository;
import com.dc.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 登陆身份认证
 */
@Component(value="userDetailService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException(ResultEnum.USER_NOT_EXIST.getMessage());
        }else {
            Authorities authorities = authoritiesRepository.findAuthoritiesByAuthId(user.getAuthId());
            String authName = authorities.getAuthority();
            return JwtUserFactory.create(user,authName);
        }
    }
}
