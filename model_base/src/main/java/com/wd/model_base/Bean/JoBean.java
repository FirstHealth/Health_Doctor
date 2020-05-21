package com.wd.model_base.Bean;

import java.io.Serializable;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.login.bean
 * @ClassName: JoinBean
 * @Description: (java类作用描述)
 * @Author: 谭亚森
 * @CreateDate: 2020/5/19 22:26
 */
public class JoBean implements Serializable {
    private String email;
    private String code;
    private String pwd1;
    private String pwd2;
    private String name;
    private String inauguralHospital;
    private int departmentId;
    private int jobTitleId;
    private String personalProfile;
    private String goodField;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInauguralHospital() {
        return inauguralHospital;
    }

    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }

    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(int jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getGoodField() {
        return goodField;
    }

    public void setGoodField(String goodField) {
        this.goodField = goodField;
    }

    public JoBean(String email, String code, String pwd1, String pwd2, String name, String inauguralHospital, int departmentId, int jobTitleId, String personalProfile, String goodField) {
        this.email = email;
        this.code = code;
        this.pwd1 = pwd1;
        this.pwd2 = pwd2;
        this.name = name;
        this.inauguralHospital = inauguralHospital;
        this.departmentId = departmentId;
        this.jobTitleId = jobTitleId;
        this.personalProfile = personalProfile;
        this.goodField = goodField;
    }
}
