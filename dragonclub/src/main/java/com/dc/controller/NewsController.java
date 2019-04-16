package com.dc.controller;

import com.dc.dto.NewsCondition;
import com.dc.dto.UserInfoDTO;
import com.dc.entity.News;
import com.dc.service.NewsService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "更新或新建新闻信息")
    @PostMapping("/detail")
    public ResponseEntity updateNews(@RequestBody News news) {
        if (news == null) {
            return ResponseEntity.res(ResultEnum.MISSING_PARAM.getCode(), null);
        }
        News news1 = newsService.updateOrCreateNews(news);
        if (news1 == null) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "更新活新建新闻信息失败", null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "更新活新建新闻信息失败成功！", news1);
    }

    @ApiOperation(value = "删除新闻信息")
    @DeleteMapping("/detail")
    public ResponseEntity delteNews(@RequestParam(value = "newsId") Integer newsId) {
        try {
            newsService.deleteNews(newsId);
            return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "删除新闻成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "删除新闻失败", null);
        }
    }

    @ApiOperation(value = "更新新闻图片")
    @PostMapping("/newsImg")
    public ResponseEntity updateNewsImg(@RequestParam MultipartFile file, @RequestParam String newsImg) {
        String outPath = newsService.updateNewsImg(file, newsImg);
        if (outPath == null) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "更新新闻图片失败", null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "更新新闻图片成功！", outPath);
    }

    @ApiOperation(value = "查询新闻列表")
    @PostMapping("/list")
    public ResponseEntity findNewsList(@RequestBody NewsCondition condition) {
        Page<News> newsList = newsService.findNewsList(condition);
        if (newsList == null) {
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(), "查询新闻列表失败", null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(), "查询新闻列表成功！", newsList);
    }
}
