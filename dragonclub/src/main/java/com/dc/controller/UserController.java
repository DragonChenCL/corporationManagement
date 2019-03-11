package com.dc.controller;

import com.dc.dto.UserInfoDTO;
import com.dc.service.UserService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/detail")
    public ResponseEntity getUserInfo(@RequestParam("username") String username) throws Exception{
        if(StringUtils.isBlank(username)){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        UserInfoDTO user = userService.getUserInfo(username);
        if (user == null){
            return ResponseEntity.res(ResultEnum.USER_NOT_EXIST.getCode(),null);
        }
        //判断用户是否被禁用
        if (user.getEnable() == 0){
            return ResponseEntity.res(ResultEnum.TOKEN_IS_BLACKLIST.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！",user);
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/detail")
    public ResponseEntity updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) throws Exception{
        if(StringUtils.isBlank(userInfoDTO.toString())){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        Boolean flag = userService.updateUserInfo(userInfoDTO);
        if (flag == false){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！");
    }

    @PostMapping("/updateHeadPortrait")
    @ApiOperation(value = "更新头像logo")
    public ResponseEntity updateLogo(@RequestParam MultipartFile file , @RequestParam int userId , @RequestParam String headPortrait){
        if(file == null || StringUtils.isBlank(String.valueOf(userId)) || StringUtils.isBlank(headPortrait)){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        String outPath = userService.updateLogo(file, userId ,headPortrait);
        if (StringUtils.isBlank(outPath)){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"头像更新成功！",outPath);
    }
}
