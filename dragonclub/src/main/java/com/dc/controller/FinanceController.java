package com.dc.controller;

import com.dc.dto.FinanceCondition;
import com.dc.dto.UserStatusDTO;
import com.dc.entity.Finance;
import com.dc.service.FinanceService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;

    @GetMapping("/in")
    @ApiOperation(value = "获取入账总额")
    public ResponseEntity getInFinance() {
        Integer inFinance = financeService.findInFinance();
        if (inFinance == 0) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "获取失败！", null);
        }else {
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "获取成功！", inFinance);
        }
    }

    @GetMapping("/out")
    @ApiOperation(value = "获取出账总额")
    public ResponseEntity getOutFinance() {
        Integer outFinance = financeService.findOutFinance();
        if (outFinance == 0) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "获取失败！", null);
        }else {
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "获取成功！", outFinance);
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "新增账单信息")
    public ResponseEntity createFinance(@RequestBody Finance finance){
        if (finance == null){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),"参数错误！",null);
        }
        Finance newFinance = financeService.create(finance);
        if (newFinance == null) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "获取失败！", null);
        }else {
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "获取成功！", null);
        }
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取账单信息")
    public ResponseEntity getFinanceList(@RequestBody FinanceCondition condition){
        if (condition == null){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),"参数错误！",null);
        }
        Page<Finance> all = financeService.findAll(condition);
        if (all == null) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "获取失败！", null);
        }else {
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "获取成功！", all);
        }
    }

    @DeleteMapping("/detail")
    @ApiOperation(value = "删除账单信息")
    public ResponseEntity getFinanceList(@RequestParam("financeId") Integer financeId){
        if (financeId == null){
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(),"参数错误！",null);
        }
        try {
            financeService.delFinance(financeId);
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "删除成功！", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "删除失败！", null);
        }
    }
}
