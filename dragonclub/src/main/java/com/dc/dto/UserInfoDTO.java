package com.dc.dto;

import com.dc.entity.UserAssoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 个人信息类
 */
@Builder
public class UserInfoDTO {
    private int userId;
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
    private Integer collegeId;
    private Integer myclassId;
    private String status;
    private List<UserAssocDTO> userAssocs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserAssocDTO> getUserAssocs() {
        return userAssocs;
    }

    public void setUserAssocs(List<UserAssocDTO> userAssocs) {
        this.userAssocs = userAssocs;
    }

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


    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userId=" + userId +
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
                '}';
    }
}
