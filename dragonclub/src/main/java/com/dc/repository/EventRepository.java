package com.dc.repository;

import com.dc.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface EventRepository extends JpaRepository<Event,Integer> {


    Page<Event> findAll(Specification<Event> specification, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Event set status =?1 , message =?2 where eventId = ?3")
    public int updateStatus(String status,String message,Integer eventId );
}
