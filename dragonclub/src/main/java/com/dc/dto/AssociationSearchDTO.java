package com.dc.dto;

import lombok.Data;

@Data
public class AssociationSearchDTO {
    private String assName;

    private Integer categoryId;

    private String createdDate;

    private String status;

    private Integer currentPage;

    private Integer pageSize;

}
