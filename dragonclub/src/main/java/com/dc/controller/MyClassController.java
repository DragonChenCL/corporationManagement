package com.dc.controller;

import com.dc.entity.Category;
import com.dc.entity.Myclass;
import com.dc.service.MyclassService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myclass")
public class MyClassController {
    @Autowired
    private MyclassService myclassService;

    @ApiOperation(value = "获取班级信息")
    @GetMapping("/details")
    public ResponseEntity getMyclasses(@RequestParam("collegeId") Integer collegeId){
//        if (collegeId == null){
//            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"请先选择学院",null);
//        }
        List<Myclass> myclasses = myclassService.findMyclasses(collegeId);
        if (myclasses == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"获取班级信息失败",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取班级信息成功！",myclasses);
    }
}
