package com.dc.controller;

import com.dc.dto.UserInfoDTO;
import com.dc.entity.Category;
import com.dc.service.CategoryService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取分类信息")
    @GetMapping("/details")
    public ResponseEntity getCategorys() throws Exception{

        List<Category> categorys = categoryService.findCategorys();
        if (categorys == null){
            return ResponseEntity.res(ResultEnum.USER_NOT_EXIST.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！",categorys);
    }
}
