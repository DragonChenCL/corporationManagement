package com.dc.repository;

import com.dc.entity.UserAssoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAssocRepository extends JpaRepository<UserAssoc,Integer> {

    public List<UserAssoc> findUserAssocByAssociationId(Integer assocId);

    public List<UserAssoc> findUserAssocByUserId(Integer userId);


}
