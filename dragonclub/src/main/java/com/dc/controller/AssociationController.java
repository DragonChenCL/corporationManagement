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
    public ResponseEntity updateLogo(@RequestParam MultipartFile file , @RequestParam int assocId ,@RequestParam String logo){
        if(file == null || StringUtils.isBlank(String.valueOf(assocId)) || StringUtils.isBlank(logo)){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        String outPath = associationService.updateLogo(file, assocId ,logo);
        if (StringUtils.isBlank(outPath)){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"logo更新成功！",outPath);
    }

    @ApiOperation(value = "更新社团信息")
    @PostMapping("/detail")
    public ResponseEntity updateUserInfo(@RequestBody AssociationInfoDTO associationInfoDTO) throws Exception{
        if(StringUtils.isBlank(associationInfoDTO.toString())){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),null);
        }
        Boolean flag = associationService.updateAssoInfo(associationInfoDTO);
        if (flag == false){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"更新社团信息成功！");
    }
}
