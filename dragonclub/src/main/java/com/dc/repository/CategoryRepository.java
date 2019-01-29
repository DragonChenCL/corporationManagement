package com.dc.repository;

import com.dc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    public Category findCategoryByCategoryId(int id);
}
