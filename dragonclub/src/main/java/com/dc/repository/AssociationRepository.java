package com.dc.repository;

import com.dc.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepository extends JpaRepository<Association,Integer> {
    /**
     * 获取社团信息
     * @param id
     * @return
     */
    public Association findAssociationByAssociationId(int id);
}
