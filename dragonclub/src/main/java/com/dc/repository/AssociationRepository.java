package com.dc.repository;

import com.dc.dto.AssociationInfoDTO;
import com.dc.entity.Association;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AssociationRepository extends JpaRepository<Association,Integer> {
    /**
     * 获取社团信息
     * @param id
     * @return
     */
    public Association findAssociationByAssociationId(int id);

    /**
     * 更新社团logo
     * @param path
     * @param assocId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update Association set logo = ?1 where associationId = ?2")
    int updateLogo(String path ,int assocId);

    public Page<Association> findAll(Specification<Association> specification , Pageable pageable);

    public List<Association> findAssociationsByStatus(String status);

    public List<Association> findAssociationsByCategoryIdAndStatus(int categoryId ,String status);


}
