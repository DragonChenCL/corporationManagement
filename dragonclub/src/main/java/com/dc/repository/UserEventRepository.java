package com.dc.repository;

import com.dc.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventRepository extends JpaRepository<UserEvent,Integer> {

    List<UserEvent> findUserEventByUserId(Integer userId);

    UserEvent findUserEventByUserIdAndEventId(Integer userId, Integer eventId);
}
