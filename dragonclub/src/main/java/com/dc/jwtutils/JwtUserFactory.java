package com.dc.jwtutils;

import com.dc.entity.User;
import com.dc.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * 创建jwtUser的工厂
 */
public class JwtUserFactory{

    private JwtUserFactory() {
    }

    public static JwtUser create(User user , String auth) {
        JwtUser jwtUser = new JwtUser(user);
        jwtUser.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(auth)));
        return new JwtUser(user);
    }
}
