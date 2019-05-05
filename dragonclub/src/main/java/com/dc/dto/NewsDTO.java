package com.dc.dto;

import lombok.Data;

@Data
public class NewsDTO {
    private int newsId;
    private Integer associationId;
    private String assName;
    private String newsTitle;
    private String newsContent;
    private String publishDate;
    private String newsImg;
    private String author;

}
