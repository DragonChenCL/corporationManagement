package com.dc.repository;

import com.dc.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface NewsRepository extends JpaRepository<News,Integer> {

    public Page<News> findAll(Specification<News> specification, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update News set newsImg = ?1 where newsId = ?2")
    int updateNewsImg(String path , int newsId);

    public News findNewsByNewsId(Integer id);
}
