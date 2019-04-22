package com.dc.controller;

import com.dc.dto.ApplyCondition;
import com.dc.dto.ApplyDTO;
import com.dc.dto.PageDTO;
import com.dc.entity.Apply;
import com.dc.entity.Association;
import com.dc.service.ApplyService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @ApiOperation(value = "获取所有评论信息")
    @PostMapping("/details")
    public ResponseEntity findApplys(@RequestBody ApplyCondition condition) {
        PageDTO<ApplyDTO> applys = applyService.findApplys(condition);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),applys);
    }

    @ApiOperation(value = "创建评论信息")
    @PostMapping("/create")
    public ResponseEntity createApply(@RequestBody Apply apply) {
        applyService.createApply(apply);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),null);
    }

    @ApiOperation(value = "删除评论信息")
    @DeleteMapping("/delete")
    public ResponseEntity deleteApply(@RequestParam("id") Integer id) {
        applyService.deleteApply(id);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),null);
    }
}
