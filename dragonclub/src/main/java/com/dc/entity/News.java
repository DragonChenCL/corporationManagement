package com.dc.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class News {
    private int newsId;
    private Integer associationId;
    private String newsTitle;
    private byte[] newsContent;
    private String publishDate;
    private String newsImg;
    private String author;

    @Id
    @Column(name = "news_id")
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "association_id")
    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    @Basic
    @Column(name = "news_title")
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Lob
    @Basic
    @Column(name = "news_content",columnDefinition = "BLOB",nullable=true)
    public byte[] getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(byte[] newsContent) {
        this.newsContent = newsContent;
    }

    @Basic
    @Column(name = "publish_date")
    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "news_img")
    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return newsId == news.newsId &&
                Objects.equals(associationId, news.associationId) &&
                Objects.equals(newsTitle, news.newsTitle) &&
                Objects.equals(newsContent, news.newsContent) &&
                Objects.equals(publishDate, news.publishDate) &&
                Objects.equals(newsImg, news.newsImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, associationId, newsTitle, newsContent, publishDate, newsImg);
    }
}
