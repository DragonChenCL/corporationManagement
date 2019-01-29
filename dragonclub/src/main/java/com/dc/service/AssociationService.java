package com.dc.service;

import com.dc.dto.AssociationInfoDTO;
import com.dc.entity.Association;
import com.dc.entity.Category;
import com.dc.repository.AssociationRepository;
import com.dc.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 社团service
 */
@Service
public class AssociationService {
    @Autowired
    private AssociationRepository associationRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 获取社团信息
     * @param id
     * @return
     */
    public AssociationInfoDTO getAssociationInfo(int id){
        AssociationInfoDTO associationInfoDTO = new AssociationInfoDTO();
        Association association = associationRepository.findAssociationByAssociationId(id);
        if (association != null){
            BeanUtils.copyProperties(association,associationInfoDTO);
            //将荣誉和精彩瞬间拆分成list返回(注意是英文符号的;)
            List<String> honor = Arrays.asList(association.getHonor().split(";"));
            associationInfoDTO.setHonors(honor);
            List<String> moment = Arrays.asList(association.getMomentImg().split(";"));
            associationInfoDTO.setMomentImgs(moment);

            //获取分类名称
            Category category = categoryRepository.findCategoryByCategoryId(association.getCategoryId());
            if (category != null){
                associationInfoDTO.setCategory(category.getCategoryName());
            }
        }
        return associationInfoDTO;
    }
}
