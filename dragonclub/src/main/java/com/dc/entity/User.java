package com.dc.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@DynamicUpdate
@DynamicInsert
public class User {
    private Integer userId;
    private Integer authId;
    private Integer associationId;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private Timestamp birthday;
    private String headPortrait;
    private Integer enable;
    private Timestamp registeryDate;
    private String address;
    private String realName;
    private String qq;
    private Integer collegeId;
    private Integer myclassId;
    private String sex;
    private String introduction;
    private String position;
    //维护一对一的关系
//    private Authorities authorities;
//
//    @OneToOne()
//    public Authorities getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Authorities authorities) {
//        this.authorities = authorities;
//    }

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "auth_id")
    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "birthday")
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "head_portrait")
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Basic
    @Column(name = "enable")
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "registery_date")
    public Timestamp getRegisteryDate() {
        return registeryDate;
    }

    public void setRegisteryDate(Timestamp registeryDate) {
        this.registeryDate = registeryDate;
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
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    @Basic
    @Column(name = "college_id")
    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }
    @Basic
    @Column(name = "myclass_id")
    public Integer getMyclassId() {
        return myclassId;
    }

    public void setMyclassId(Integer myclassId) {
        this.myclassId = myclassId;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(authId, user.authId) &&
                Objects.equals(associationId, user.associationId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(headPortrait, user.headPortrait) &&
                Objects.equals(enable, user.enable) &&
                Objects.equals(registeryDate, user.registeryDate) &&
                Objects.equals(address, user.address) &&
                Objects.equals(realName, user.realName) &&
                Objects.equals(qq, user.qq) &&
                Objects.equals(collegeId, user.collegeId) &&
                Objects.equals(myclassId, user.myclassId) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(introduction, user.introduction) &&
                Objects.equals(position, user.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, authId, associationId, username, password, phoneNumber, email, birthday, headPortrait, enable, registeryDate, address, realName, qq, collegeId, myclassId, sex, introduction, position);
    }
}
