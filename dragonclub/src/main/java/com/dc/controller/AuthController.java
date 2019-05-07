package com.dc.controller;

import com.dc.dto.LoginDTO;
import com.dc.entity.User;
import com.dc.exception.MyException;
import com.dc.service.AuthService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import com.dc.utils.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



/**
 * 用户管理Controller
 *
 * @author hackyo
 * Created on 2017/12/3 11:53.
 */
@CrossOrigin
@Api(value = "用户权限操作")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 用户登录
     * @param loginDTO
     * @return
     * @throws AuthenticationException
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        try {
            if (StringUtils.isBlank(String.valueOf(loginDTO))){
                return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"用户名和密码不能为空",null);
            }
            String token = userService.login(loginDTO.getUsername(),loginDTO.getPassword());
            if(StringUtils.isBlank(token)){
                return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"token获取异常！",null);
            }
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"用户登录成功！",token);
        }catch (Exception e){
            logger.error(e.toString(),"用户名密码错误！");
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"用户名密码错误！",null);
        }
    }

    /**
     * 用户注册
     * @param user   用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/register")
    @ApiOperation("用户注册")
    public ResponseEntity register(@RequestBody User user) throws AuthenticationException{
            if (StringUtils.isBlank(String.valueOf(user))){
                return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"user不能为空！",null);
            }
            String mess = userService.register(user);
            if(StringUtils.isBlank(mess) || mess.equals("用户已存在")){
                return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"用户已存在！",mess);
            }
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"用户注册成功！",mess);

    }

    /**
     * 刷新密钥
     * @param Authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @ApiOperation("刷新token")
    @GetMapping(value = "/refreshToken")
    public ResponseEntity refreshToken(@RequestHeader("Authorization") String Authorization) throws AuthenticationException {
        try {
            if (StringUtils.isBlank(String.valueOf(Authorization))){
                return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"未传入原token！",null);
            }
            String refreshToken = userService.refreshToken(Authorization);
            if(StringUtils.isBlank(refreshToken)){
                return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"刷新token异常！");
            }
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),refreshToken);
        }catch (Exception e){
            logger.error(e.toString(),"刷新token异常");
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"刷新token异常！",null);
        }
    }

    @ApiOperation("用户退出")
    @PostMapping(value = "/logout")
    public ResponseEntity logout(){
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"用户登出成功！",null);
    }

}