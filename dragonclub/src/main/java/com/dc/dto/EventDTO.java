package com.dc.dto;

import lombok.Data;

@Data
public class EventDTO {
    private int eventId;
    private Integer associationId;
    private String associationName;
    private String eventName;
    private String startDate;
    private String endDate;
    private String address;
    private String content;
    private String status;
    private String responsiblePerson;
    private String level;
    private String exceptFunds;
    private String undertakingCompany;
    private String actualFunds;
    private String enentImg;
    private String assName;
    private String message;
}
