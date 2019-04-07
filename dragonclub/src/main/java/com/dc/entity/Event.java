package com.dc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Event {
    private int eventId;
    private Integer associationId;
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

    @Id
    @Column(name = "event_id")
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
    @Column(name = "event_name")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "responsible_person")
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "except_funds")
    public String getExceptFunds() {
        return exceptFunds;
    }

    public void setExceptFunds(String exceptFunds) {
        this.exceptFunds = exceptFunds;
    }

    @Basic
    @Column(name = "undertaking_company")
    public String getUndertakingCompany() {
        return undertakingCompany;
    }

    public void setUndertakingCompany(String undertakingCompany) {
        this.undertakingCompany = undertakingCompany;
    }

    @Basic
    @Column(name = "actual_funds")
    public String getActualFunds() {
        return actualFunds;
    }

    public void setActualFunds(String actualFunds) {
        this.actualFunds = actualFunds;
    }

    @Basic
    @Column(name = "enent_img")
    public String getEnentImg() {
        return enentImg;
    }

    public void setEnentImg(String enentImg) {
        this.enentImg = enentImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId &&
                Objects.equals(associationId, event.associationId) &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(endDate, event.endDate) &&
                Objects.equals(address, event.address) &&
                Objects.equals(content, event.content) &&
                Objects.equals(status, event.status) &&
                Objects.equals(responsiblePerson, event.responsiblePerson) &&
                Objects.equals(level, event.level) &&
                Objects.equals(exceptFunds, event.exceptFunds) &&
                Objects.equals(undertakingCompany, event.undertakingCompany) &&
                Objects.equals(actualFunds, event.actualFunds) &&
                Objects.equals(enentImg, event.enentImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, associationId, eventName, startDate, endDate, address, content, status, responsiblePerson, level, exceptFunds, undertakingCompany, actualFunds, enentImg);
    }
}
