package com.dc.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserApplyDTO {
    //学生信息
    private int userId;
    private String username;
    private String phoneNumber;
    private String email;
    private Timestamp birthday;
    private String headPortrait;
    private Integer enable;
    private Timestamp registeryDate;
    private String address;
    private String realName;
    private String qq;
    private String college;
    private String myClass;
    private String sex;
    private String introduction;
    private String position;
    private String roles;
    private Integer collegeId;
    private Integer myclassId;
    //活动名称
    private String eventName;
    //状态
    private String status;
    //开始时间
    private String startDate;
    //userEvent的id
    private Integer id;

    private Integer eventId;

    private String eventAddress;
}
