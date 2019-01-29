package com.dc.repository;

import com.dc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
