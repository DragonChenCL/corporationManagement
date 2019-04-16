package com.dc.dto;

import lombok.Data;

@Data
public class NewsCondition {
    private Integer currentPage;
    private Integer pageSize;
    private Integer associationId;
    private String publishDate;
    private String newsTitle;
}
