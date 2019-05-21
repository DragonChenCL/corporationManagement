package com.dc.service;

import com.dc.dto.NewsCondition;
import com.dc.dto.NewsDTO;
import com.dc.entity.Association;
import com.dc.entity.Event;
import com.dc.entity.News;
import com.dc.repository.AssociationRepository;
import com.dc.repository.NewsRepository;
import com.dc.utils.BeanUtils;
import com.dc.utils.UpLoadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AssociationRepository associationRepository;

    @Value("${imgUrl.newsImg}")
    private String news;

    public Page<News> findNewsList(NewsCondition condition) {
        Pageable pageable = PageRequest.of(condition.getCurrentPage() - 1, condition.getPageSize(), Sort.Direction.DESC,"publishDate");
        Specification<News> specification = new Specification<News>() {
            @Override
            // Root 用于获取属性字段，CriteriaQuery可以用于简单条件查询，CriteriaBuilder 用于构造复杂条件查询
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                //集合 用于封装查询条件
                List<Predicate> list = new ArrayList<Predicate>();
                //简单单表查询
                if (StringUtils.isNotBlank(condition.getNewsTitle())) {
                    list.add(builder.like(root.get("newsTitle").as(String.class), "%" + condition.getNewsTitle() + "%"));
                }
                if (StringUtils.isNotBlank(condition.getPublishDate())) {
                    list.add(builder.like(root.get("publishDate").as(String.class), "%" + condition.getPublishDate() + "%"));
                }
                if(condition.getAssociationId() != null){
                    list.add(builder.equal(root.get("associationId").as(Integer.class), condition.getAssociationId()));
                }
                return builder.and(list.toArray(new Predicate[0]));
            }
        };
        return newsRepository.findAll(specification, pageable);
    }

    public News updateOrCreateNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Integer newsId) throws Exception{
        newsRepository.deleteById(newsId);
    }

    public String updateNewsImg(MultipartFile file, String newsImg) {
        String outPath = "";

        try {
            outPath = UpLoadUtil.upload(file, newsImg, news);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPath;
    }

    public NewsDTO getNewsById(Integer id){
        News news = new News();
        news = newsRepository.findNewsByNewsId(id);
        NewsDTO newsDTO = new NewsDTO();
        BeanUtils.copyPropertiesExcludeNull(news,newsDTO);
        //获取社团名称
        Association association = associationRepository.findAssociationByAssociationId(news.getAssociationId());
        if (association != null){
            newsDTO.setAssName(association.getAssName());

        }
        return newsDTO;
    }
}
