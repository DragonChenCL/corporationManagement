package com.dc.dto;

import lombok.Data;

@Data
public class EventStatusDTO {
    private Integer eventId;
    private String status;
    private String message;
}
