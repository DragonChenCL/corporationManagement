package com.dc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventCondition {
    private Integer currentPage;
    private Integer pageSize;
    private String eventName;
    private Date startDate;
    private String status;
}
