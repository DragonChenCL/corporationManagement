package com.dc.service;

import com.dc.dto.CategoryCondition;
import com.dc.entity.Association;
import com.dc.entity.Category;
import com.dc.repository.AssociationRepository;
import com.dc.repository.CategoryRepository;
import com.dc.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AssociationRepository associationRepository;
    private Category categoryByCategoryId;

    /**
     * 获取所所有分类
     *
     * @return
     */
    public List<Category> findCategorys() {
        List<Category> categorys = categoryRepository.findCategoriesByStatus("1");
        return categorys;
    }

    /**
     * 删除分类
     *
     * @param id
     */
    public int deleteCategory(int id) {
        List<Association> list = associationRepository.findAssociationsByCategoryId(id);
        if (list != null && list.size() != 0) {
            return 0;
        }
        categoryRepository.deleteById(id);
        return 1;
    }

    /**
     * 禁用分类
     *
     * @param id
     */
    public int forbiddenCategory(int id) {
        List<Association> list = associationRepository.findAssociationsByCategoryId(id);
        if (list != null && list.size() != 0) {
            return 0;
        }
        Category categoryByCategoryId = categoryRepository.findCategoryByCategoryId(id);
        categoryByCategoryId.setStatus("0");
        categoryRepository.save(categoryByCategoryId);
        return 1;
    }

    /**
     * 创建分类
     */
    public String createCategory(Category category) {
        //重名无法修改
        if (StringUtils.isNotBlank(category.getCategoryName())) {
            Category categoryByCategoryName = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
            if (categoryByCategoryName != null) {
                if (category.getCategoryName().equals(categoryByCategoryName.getCategoryName())) {
                    return "分类名称重复，无法更改";
                }
            }
        }
        category.setStatus("1");
        Category save = categoryRepository.save(category);
        return "创建分类成功";
    }

    /**
     * 修改分类
     */
    public String editCategory(Category category) {
        //分类下有社团不能禁用
        if (category.getStatus().equals("0")) {
            List<Association> list = associationRepository.findAssociationsByCategoryId(category.getCategoryId());
            if (list != null && list.size() != 0) {
                return "分类下有社团不能禁用";
            }
        }
        Category categoryByCategoryId = categoryRepository.findCategoryByCategoryId(category.getCategoryId());
        //重名无法修改
        if (StringUtils.isNotBlank(category.getCategoryName())) {
            Category categoryByCategoryName = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
            if (categoryByCategoryName != null) {
                if (!categoryByCategoryId.getCategoryName().equals(categoryByCategoryName.getCategoryName())) {
                    return "分类名称重复，无法更改";
                }
            }
        }

        BeanUtils.copyPropertiesExcludeNull(category, categoryByCategoryId);
        Category save = categoryRepository.save(categoryByCategoryId);
        return "编辑成功";
    }

    /**
     * 分页获取分类
     *
     * @param categoryCondition
     * @return
     */
    public Page<Category> findAllByPage(CategoryCondition categoryCondition) {
        Pageable pageable = PageRequest.of(categoryCondition.getCurrentPage() - 1, categoryCondition.getPageSize());
        Page<Category> all = categoryRepository.findAll(pageable);
        return all;
    }
}
