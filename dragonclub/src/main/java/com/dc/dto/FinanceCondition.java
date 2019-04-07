package com.dc.dto;

import lombok.Data;

@Data
public class FinanceCondition {
    private Integer associationId;
    private String reason;
    private String startDate;
    private Integer currentPage;
    private Integer pageSize;
    private String type;
}
