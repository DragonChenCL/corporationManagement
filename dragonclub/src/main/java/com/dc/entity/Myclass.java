package com.dc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Myclass {
    private int classId;
    private int collegeId;
    private String className;

    @Id
    @Column(name = "class_id")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "college_id")
    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    @Basic
    @Column(name = "class_Name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
