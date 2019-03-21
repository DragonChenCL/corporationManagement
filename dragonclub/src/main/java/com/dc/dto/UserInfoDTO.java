package com.dc.dto;

import java.sql.Timestamp;

/**
 * 个人信息类
 */
public class UserInfoDTO {
    private int userId;
    private Integer associationId;
    private String username;
    private String phoneNumber;
    private String email;
    private Timestamp birthday;
    private String headPortrait;
    private Integer enable;
    private Timestamp registeryDate;
    private String address;
    private String realName;
    private String qq;
    private String college;
    private String myClass;
    private String sex;
    private String introduction;
    private String position;
    private String roles;
    private String association;
    private Integer collegeId;
    private Integer myclassId;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getMyclassId() {
        return myclassId;
    }

    public void setMyclassId(Integer myclassId) {
        this.myclassId = myclassId;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Timestamp getRegisteryDate() {
        return registeryDate;
    }

    public void setRegisteryDate(Timestamp registeryDate) {
        this.registeryDate = registeryDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userId=" + userId +
                ", associationId=" + associationId +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", headPortrait='" + headPortrait + '\'' +
                ", enable=" + enable +
                ", registeryDate=" + registeryDate +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", qq=" + qq +
                ", college='" + college + '\'' +
                ", myClass='" + myClass + '\'' +
                ", sex='" + sex + '\'' +
                ", introduction='" + introduction + '\'' +
                ", position='" + position + '\'' +
                ", roles='" + roles + '\'' +
                ", association='" + association + '\'' +
                '}';
    }
}
