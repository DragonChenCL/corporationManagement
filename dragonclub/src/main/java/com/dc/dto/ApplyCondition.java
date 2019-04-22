package com.dc.dto;

import lombok.Data;

@Data
public class ApplyCondition {
    private Integer currentPage;
    private Integer pageSize;
    private String eventName;
    private String applyDate;
}
