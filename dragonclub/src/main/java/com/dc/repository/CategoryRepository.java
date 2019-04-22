package com.dc.repository;

import com.dc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    public Category findCategoryByCategoryId(int id);

    public List<Category> findCategoriesByStatus(String status);

    public Category findCategoryByCategoryName(String categoryName);
}
