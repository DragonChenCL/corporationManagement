package com.dc.service;

import com.dc.entity.Category;
import com.dc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findCategorys(){
        List<Category> categorys = categoryRepository.findAll();
        return  categorys;
    }
}
