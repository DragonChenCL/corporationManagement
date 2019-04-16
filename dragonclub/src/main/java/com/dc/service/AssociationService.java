package com.dc.service;

import com.dc.dto.AssociationInfoDTO;
import com.dc.dto.AssociationSearchDTO;
import com.dc.dto.PageDTO;
import com.dc.entity.Association;
import com.dc.entity.Category;
import com.dc.repository.AssociationRepository;
import com.dc.repository.CategoryRepository;
import com.dc.utils.BeanUtils;
import com.dc.utils.UpLoadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
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

    @Value("${imgUrl.logo}")
    private String logoUrl;


    public PageDTO<AssociationInfoDTO> findAssociationList(AssociationSearchDTO dto){
        Pageable pageable = PageRequest.of(dto.getCurrentPage() - 1,dto.getPageSize());
        Specification<Association> specification = new Specification<Association>() {
            @Override
            public Predicate toPredicate(Root<Association> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(dto.getAssName())) {
                    list.add(builder.like(root.get("assName").as(String.class), "%" + dto.getAssName() + "%"));
                }
                if (dto.getCreatedDate() != null) {
                    list.add(builder.like(root.get("createdDate").as(String.class), "%" + dto.getCreatedDate() + "%"));
                }
                if (dto.getCategoryId() != null) {
                    list.add(builder.equal(root.get("categoryId").as(String.class), dto.getCategoryId()));
                }
                if (StringUtils.isNotBlank(dto.getStatus())) {
                    list.add(builder.equal(root.get("status").as(String.class), dto.getStatus()));
                }
                return builder.and(list.toArray(new Predicate[0]));
            }
        };
        Page<Association> all = associationRepository.findAll(specification, pageable);
        PageDTO<AssociationInfoDTO> associationInfoDTOPageDTO = new PageDTO<AssociationInfoDTO>();
        BeanUtils.copyPropertiesExcludeNull(all,associationInfoDTOPageDTO);
        List<AssociationInfoDTO> list = new ArrayList<AssociationInfoDTO>();
        for (Association association : all.getContent()) {
            AssociationInfoDTO associationInfoDTO = getAssociationInfo(association.getAssociationId());
            BeanUtils.copyPropertiesExcludeNull(association,associationInfoDTO);
            list.add(associationInfoDTO);
        }
        associationInfoDTOPageDTO.setContents(list);
        return associationInfoDTOPageDTO;
    }
    /**
     * 获取社团信息
     */
    public AssociationInfoDTO getAssociationInfo(int id){
        AssociationInfoDTO associationInfoDTO = new AssociationInfoDTO();
        Association association = associationRepository.findAssociationByAssociationId(id);
        if (association != null){
            BeanUtils.copyPropertiesExcludeNull(association, associationInfoDTO);
            //将荣誉和精彩瞬间拆分成list返回(注意是英文符号的;)
            if (association.getHonor() != null && !association.getHonor().equals("")){
                List<String> honor = Arrays.asList(association.getHonor().split(";"));
                associationInfoDTO.setHonors(honor);
            }
            if (association.getMomentImg() != null && !association.getMomentImg().equals("")){
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
            outPath = UpLoadUtil.upload(file,logo,logoUrl);
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
        BeanUtils.copyPropertiesExcludeNull(associationInfoDTO,association);
        try {
            associationRepository.save(association);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新建社团
     * @param associationInfoDTO
     */
    public void createAssociation(AssociationInfoDTO associationInfoDTO){
        Association association = new Association();
        BeanUtils.copyPropertiesExcludeNull(associationInfoDTO,association);
        Association save = associationRepository.save(association);
    }

    /**
     * 解散社团
     * @param id
     */
    public void disAssociation(int id){
        Association association = associationRepository.findAssociationByAssociationId(id);
        association.setStatus("1");
        associationRepository.save(association);
    }


}
