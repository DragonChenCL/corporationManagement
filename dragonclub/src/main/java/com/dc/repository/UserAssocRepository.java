package com.dc.repository;

import com.dc.entity.UserAssoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserAssocRepository extends JpaRepository<UserAssoc,Integer> {

    public List<UserAssoc> findUserAssocByAssociationId(Integer assocId);

    public List<UserAssoc> findUserAssocByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "update UserAssoc set status =?1 , message =?2 where userId = ?3 and associationId = ?4")
    public int updateStatus(String status , String message, Integer userId , Integer assocId );

    public int deleteUserAssocByUserIdAndAssociationId(Integer userId, Integer assocId);

    public UserAssoc findUserAssocByUserIdAndAssociationId(Integer userId , Integer assocId);

}
