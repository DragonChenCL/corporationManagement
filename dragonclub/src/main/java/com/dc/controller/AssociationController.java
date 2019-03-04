package com.dc.controller;

import com.dc.dto.AssociationInfoDTO;
import com.dc.dto.UserInfoDTO;
import com.dc.service.AssociationService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/assoc")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    @GetMapping("/detail")
    @ApiOperation(value = "获取社团信息")
    public ResponseEntity getAssociationInfo(@RequestParam("id") Integer id){
        if(id == null || id == 0){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        AssociationInfoDTO associationInfo = associationService.getAssociationInfo(id);
        if (associationInfo == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        //判断社团状态
//        if (associationInfo.getStatus()){
//            return ResponseEntity.res(ResultEnum.TOKEN_IS_BLACKLIST.getCode(),null);
//        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！",associationInfo);
    }

    @PostMapping("/updateLogo")
    @ApiOperation(value = "更新社团logo")
    public ResponseEntity updateLogo(@RequestParam MultipartFile file , @RequestParam int assocId){
        System.out.println(file);
        System.out.println(assocId);
        if(file == null || StringUtils.isBlank(String.valueOf(assocId))){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        int flag = associationService.updateLogo(file, assocId);
        if (flag == 0){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！",null);
    }
}
