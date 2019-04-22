package com.dc.controller;

import com.dc.entity.College;
import com.dc.entity.Myclass;
import com.dc.service.CollegeService;
import com.dc.service.MyclassService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @ApiOperation(value = "获取学院信息")
    @GetMapping("/details")
    public ResponseEntity getColleges(){
        List<College> colleges = collegeService.findColleges();
        if (colleges == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"获取学院信息失败",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取学院信息成功！",colleges);
    }

    @ApiOperation(value = "删除学院信息")
    @DeleteMapping("/delete")
    public ResponseEntity deleteCollege(@RequestParam("id") int id){
        String message = collegeService.deleteCollege(id);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),message,null);
    }

    @ApiOperation(value = "新增学院信息")
    @PostMapping("/create")
    public ResponseEntity createCollege(@RequestBody College college){
        String message = collegeService.createCollege(college);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),message,null);
    }

    @ApiOperation(value = "编辑学院信息")
    @PostMapping("/edit")
    public ResponseEntity editCollege(@RequestBody College college){
        String message = collegeService.editCollege(college);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),message,null);
    }
}
