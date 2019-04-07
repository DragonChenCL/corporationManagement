package com.dc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventCondition {
    private Integer assocId;
    private Integer currentPage;
    private Integer pageSize;
    private String eventName;
    private String startDate;
    private String status;
}
