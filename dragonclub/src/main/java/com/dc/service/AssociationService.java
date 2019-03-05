package com.dc.service;

import com.dc.dto.AssociationInfoDTO;
import com.dc.entity.Association;
import com.dc.entity.Category;
import com.dc.repository.AssociationRepository;
import com.dc.repository.CategoryRepository;
import com.dc.utils.UpLoadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
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

    @Value("${imgUrl.logo}")
    private String logoUrl;


    /**
     * 获取社团信息
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

    /**
     * 更新社团logo
     */
    public String updateLogo(MultipartFile file, int assocId , String logo){
        String outPath = "";

        try {
            outPath = UpLoadUtil.upload(file,assocId,logo,logoUrl);
            associationRepository.updateLogo(outPath, assocId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPath;
    }
}
