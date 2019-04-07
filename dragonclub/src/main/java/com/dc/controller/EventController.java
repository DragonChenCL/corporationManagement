package com.dc.controller;

import com.dc.dto.EventCondition;
import com.dc.dto.MemberListCondition;
import com.dc.dto.PageDTO;
import com.dc.dto.UserApplyDTO;
import com.dc.entity.Event;
import com.dc.entity.Myclass;
import com.dc.entity.UserEvent;
import com.dc.service.EventService;
import com.dc.utils.ResponseEntity;
import com.dc.utils.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @ApiOperation(value = "获取活动信息")
    @PostMapping("/details")
    public ResponseEntity getEvents(@RequestBody EventCondition condition){
        Page<Event> events = eventService.findEvents(condition);
        if (events == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"获取活动信息失败",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取活动信息成功！",events);
    }

    @ApiOperation(value = "社长申请活动")
    @PostMapping("/apply")
    public ResponseEntity applyEvent(@RequestBody Event event){
        Event event1 = eventService.applyEvent(event);
        if (event1 == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"申请失败",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"申请成功！",null);
    }

    @ApiOperation(value = "查看社员申请活动")
    @PostMapping("/userApplys")
    public ResponseEntity userApplyEvent(@RequestBody MemberListCondition condition){
        PageDTO<UserApplyDTO> userApplyDTOPageDTO = eventService.geteUserApply(condition);
        if (userApplyDTOPageDTO == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"获取申请信息失败",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"获取申请信息成功！",userApplyDTOPageDTO);
    }

    @ApiOperation(value = "更新社员申请活动状态")
    @PostMapping("/userEventStatus")
    public ResponseEntity userEventStatus(@RequestBody UserEvent event){
        UserEvent userEvent = eventService.updateUserEvent(event);
        if (userEvent == null){
            return ResponseEntity.res(ResultEnum.FAILURE.getCode(),"更新社员申请活动状态失败",null);
        }
        return ResponseEntity.res(ResultEnum.SUCCESS.getCode(),"更新社员申请活动状态成功！",userEvent);
    }
}
