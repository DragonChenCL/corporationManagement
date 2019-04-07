package com.dc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Finance {
    private int financeId;
    private Integer associationId;
    private String startDate;
    private String type;
    private String reason;
    private Integer money;

    @Id
    @Column(name = "finance_id")
    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
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
    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Finance finance = (Finance) o;
        return financeId == finance.financeId &&
                Objects.equals(associationId, finance.associationId) &&
                Objects.equals(startDate, finance.startDate) &&
                Objects.equals(type, finance.type) &&
                Objects.equals(reason, finance.reason) &&
                Objects.equals(money, finance.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(financeId, associationId, startDate, type, reason, money);
    }
}
