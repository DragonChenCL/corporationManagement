package com.dc.dto;

import lombok.Data;

@Data
public class UserEventDTO {
    private Integer id;
    private Integer userId;
    private Integer eventId;
    private String eventName;
    private String status;
    private String message;
}
