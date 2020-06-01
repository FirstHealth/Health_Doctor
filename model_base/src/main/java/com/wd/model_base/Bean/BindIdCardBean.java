package com.wd.model_base.Bean;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.mine.bean
 * @ClassName: BindIdCardBean
 * @Description: (java类作用描述)
 * @Author: tys
 * @CreateDate: 2020/6/2 0:11
 */
public class BindIdCardBean {
     private int doctorId;
     private String name;
     private String sex;
     private String nation;
     private String birthday;
     private String address;
     private String idNumber;
     private String issueOffice;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIssueOffice() {
        return issueOffice;
    }

    public void setIssueOffice(String issueOffice) {
        this.issueOffice = issueOffice;
    }
}
