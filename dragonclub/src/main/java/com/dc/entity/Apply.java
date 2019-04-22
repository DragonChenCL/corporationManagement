package com.dc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Apply {
    private int applyId;
    private Integer userId;
    private Integer eventId;
    private String applyDate;
    private String status;

    @Id
    @Column(name = "apply_id")
    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "apply_date")
    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return applyId == apply.applyId &&
                Objects.equals(userId, apply.userId) &&
                Objects.equals(eventId, apply.eventId) &&
                Objects.equals(applyDate, apply.applyDate) &&
                Objects.equals(status, apply.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applyId, userId, eventId, applyDate, status);
    }
}
