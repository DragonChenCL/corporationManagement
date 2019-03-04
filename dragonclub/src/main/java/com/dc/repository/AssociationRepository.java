package com.dc.repository;

import com.dc.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

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
}
