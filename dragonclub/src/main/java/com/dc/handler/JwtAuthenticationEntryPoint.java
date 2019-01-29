package com.dc.handler;

import com.alibaba.fastjson.JSONObject;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 认证失败处理类，返回401
 * Author: JoeTao
 * createAt: 2018/9/20
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //验证为未登陆状态会进入此方法，认证错误
        logger.debug("尚未登录，无法访问！" + request.getRequestURI());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        ResponseEntity body = ResponseEntity.res(ResultEnum.USER_NEED_AUTHORITIES.getCode(),null);
        printWriter.write(JSONObject.toJSONString(body));
        printWriter.flush();
    }
}