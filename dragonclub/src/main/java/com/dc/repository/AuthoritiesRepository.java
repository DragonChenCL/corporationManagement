package com.dc.repository;

import com.dc.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities,Integer> {
    /**
     * 获取权限
     * @param id
     * @return
     */
    public Authorities findAuthoritiesByAuthId(int id);


}
