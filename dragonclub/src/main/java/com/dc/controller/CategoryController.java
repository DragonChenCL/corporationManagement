package com.dc.controller;

import com.dc.dto.CategoryCondition;
import com.dc.dto.UserInfoDTO;
import com.dc.entity.Category;
import com.dc.service.CategoryService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！",categorys);
    }

    @ApiOperation(value = "删除分类信息")
    @DeleteMapping("/delete")
    public ResponseEntity deleteCategory(@RequestParam("id") Integer id){
        int i = categoryService.deleteCategory(id);
        if (i == 0){
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"分类下有社团，无法删除",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"删除分类成功！",null);
    }

//    @ApiOperation(value = "禁用分类信息")
////    @PostMapping("/forbidden")
////    public ResponseEntity forbiddenCategory(@RequestParam("id") Integer id){
////        int i = categoryService.deleteCategory(id);
////        if (i == 0){
////            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"分类下有社团，无法禁用",null);
////        }
////        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"禁用分类成功！",null);
////    }

    @ApiOperation(value = "创建分类信息")
    @PostMapping("/create")
    public ResponseEntity createCategory(@RequestBody Category category){
        String message = categoryService.createCategory(category);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),message,null);
    }

    @ApiOperation(value = "编辑分类信息")
    @PostMapping("/edit")
    public ResponseEntity editCategory(@RequestBody Category category){
        String message = categoryService.editCategory(category);
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),message,null);
    }

    @ApiOperation(value = "获取分类信息")
    @PostMapping("/list")
    public ResponseEntity findAllByPage(@RequestBody CategoryCondition categoryCondition){
        Page<Category> allByPage = categoryService.findAllByPage(categoryCondition);
        if (allByPage == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取数据成功！",allByPage);
    }
}
