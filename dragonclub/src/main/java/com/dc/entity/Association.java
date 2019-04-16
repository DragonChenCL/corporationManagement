package com.dc.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Association {
    private int associationId;
    private Integer categoryId;
    private String assName;
    private String founder;
    private String responsiblePerson;
    private String createdDate;
    private String status;
    private String description;
    private String phoneNumber;
    private String honor;
    private String logo;
    private String momentImg;
    private String address;
//    private List<User> userList;
//
//    @ManyToMany(mappedBy = "associationList")
//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }

    @Id
    @Column(name = "association_id")
    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "ass_name")
    public String getAssName() {
        return assName;
    }

    public void setAssName(String assName) {
        this.assName = assName;
    }

    @Basic
    @Column(name = "founder")
    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
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
    @Column(name = "created_date")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "honor")
    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    @Basic
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "moment_img")
    public String getMomentImg() {
        return momentImg;
    }

    public void setMomentImg(String momentImg) {
        this.momentImg = momentImg;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Association that = (Association) o;
        return associationId == that.associationId &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(assName, that.assName) &&
                Objects.equals(founder, that.founder) &&
                Objects.equals(responsiblePerson, that.responsiblePerson) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(description, that.description) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(honor, that.honor) &&
                Objects.equals(logo, that.logo) &&
                Objects.equals(momentImg, that.momentImg) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(associationId, categoryId, assName, founder, responsiblePerson, createdDate, status, description, phoneNumber, honor, logo, momentImg, address);
    }
}
