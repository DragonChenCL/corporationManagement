package com.dc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class ApplyDTO {
    private Integer applyId;
    private Integer userId;
    private String realName;
    private Integer eventId;
    private String eventName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private String applyDate;
    private String status;
}
