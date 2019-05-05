package com.dc.controller;

import com.dc.dto.MemberListCondition;
import com.dc.dto.PageDTO;
import com.dc.dto.UserInfoDTO;
import com.dc.dto.UserStatusDTO;
import com.dc.entity.User;
import com.dc.service.UserService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/detail")
    public ResponseEntity getUserInfo(@RequestParam("username") String username) throws Exception {
        if (StringUtils.isBlank(username)) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
        UserInfoDTO user = userService.getUserInfo(username);
        if (user == null) {
            return ResponseEntity.res(ResultEnum.USER_NOT_EXIST.getCode(), null);
        }
        //判断用户是否被禁用
        if (user.getEnable() == 0) {
            return ResponseEntity.res(ResultEnum.TOKEN_IS_BLACKLIST.getCode(), null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "获取数据成功！", user);
    }

    @ApiOperation(value = "获取成员列表")
    @PostMapping("/memberList")
    public ResponseEntity getMemberList(@RequestBody MemberListCondition memberListCondition) throws Exception {
        if (memberListCondition == null) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
//        PageDTO<UserInfoDTO> memberList = userService.getMemberList(memberListCondition);
        PageDTO<UserInfoDTO> memberList = userService.getMemberList(memberListCondition);
        if (memberList == null) {
            return ResponseEntity.res(ResultEnum.USER_NOT_EXIST.getCode(), null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "获取数据成功！", memberList);
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/detail")
    public ResponseEntity updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) throws Exception {
        if (StringUtils.isBlank(userInfoDTO.toString())) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
        Boolean flag = userService.updateUserInfo(userInfoDTO);
        if (flag == false) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "更新用户数据成功！");
    }

    @PostMapping("/updateHeadPortrait")
    @ApiOperation(value = "更新头像logo")
    public ResponseEntity updateLogo(@RequestParam MultipartFile file, @RequestParam int userId, @RequestParam String headPortrait) {
        if (file == null || StringUtils.isBlank(String.valueOf(userId)) || StringUtils.isBlank(headPortrait)) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
        String outPath = userService.updateLogo(file, userId, headPortrait);
        if (StringUtils.isBlank(outPath)) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "头像更新成功！", outPath);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "用户退出社团")
    public ResponseEntity deleteUser(@RequestParam("userId") Integer userId , @RequestParam("assocId") Integer assocId) {
        if (userId == null || assocId == null) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
        try {
            userService.deleteUser(userId,assocId);
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "删除成功！", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "删除失败！", null);
        }
    }

    @PostMapping("/status")
    @ApiOperation(value = "更新用户加入社团的状态")
    public ResponseEntity deleteUser(@RequestBody UserStatusDTO userStatusDTO) {
        if (userStatusDTO == null) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
        int i = userService.updateStatus(userStatusDTO);
        if (i == 0) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "更新失败！", null);
        }else {
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "更新成功！", null);
        }
    }

    @PostMapping("/apply")
    @ApiOperation(value = "用户申请加入社团")
    public ResponseEntity applyAssoc(@RequestParam("userId") Integer userId ,@RequestParam("assocId") Integer assocId) {
        String message = userService.applyAssoc(userId, assocId);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), message, null);
    }

    @PostMapping("/applyEvent")
    @ApiOperation(value = "用户申请加入活动")
    public ResponseEntity applyEvent(@RequestParam("userId") Integer userId ,@RequestParam("eventId") Integer eventId) {
        String message = userService.applyEvent(userId, eventId);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), message, null);
    }
}
