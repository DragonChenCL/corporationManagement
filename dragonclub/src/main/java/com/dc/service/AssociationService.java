package com.dc.service;

import com.dc.dto.AssociationInfoDTO;
import com.dc.dto.UserInfoDTO;
import com.dc.entity.Association;
import com.dc.entity.Category;
import com.dc.entity.User;
import com.dc.repository.AssociationRepository;
import com.dc.repository.CategoryRepository;
import com.dc.utils.UpLoadUtil;
import org.apache.commons.lang3.StringUtils;
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
            if (association.getHonor() != null && association.getHonor().equals("")){
                List<String> honor = Arrays.asList(association.getHonor().split(";"));
                associationInfoDTO.setHonors(honor);
            }
            if (association.getMomentImg() != null && association.getMomentImg().equals("")){
                List<String> moment = Arrays.asList(association.getMomentImg().split(";"));
                associationInfoDTO.setMomentImgs(moment);
            }

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

    /**
     * 更新社团信息
     * @param associationInfoDTO
     * @return
     */

    public Boolean updateAssoInfo(AssociationInfoDTO associationInfoDTO){
        //暂时没有对数据进行校验 二期增加校验

        boolean flag = false;
        //先从数据库取出数据
        Association association = new Association();
        if (StringUtils.isNotBlank(String.valueOf(associationInfoDTO.getAssociationId()))){
            association = associationRepository.findAssociationByAssociationId(associationInfoDTO.getAssociationId());
        }
        com.dc.utils.BeanUtils.copyPropertiesExcludeNull(associationInfoDTO,association);
        try {
            associationRepository.save(association);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
