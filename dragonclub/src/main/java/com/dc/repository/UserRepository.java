package com.dc.repository;

import com.dc.dto.UserInfoDTO;
import com.dc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 查询用户
     * @param id
     * @return
     */
    public User findUserByUserId(int id);

    /**
     * 查询用户
     * @param username
     * @return
     */
    public User findUserByUsername(String username);

    /**
     * 更新个人头像
     * @param path
     * @param userId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update User set headPortrait = ?1 where userId = ?2")
    int updateheadPortrait(String path ,int userId);

    /**
     * 获取成员列表
     * @param userSpecification
     * @param pageable
     * @return
     */
    Page<User> findAll(Specification<User> userSpecification, Pageable pageable);

    public List<User> findUsersByCollegeId(int collegeId);

    public List<User> findUsersByMyclassId(int myclassId);
}
