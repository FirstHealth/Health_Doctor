package com.wd.model_base.Bean;

import java.util.List;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.common.bean
 * @ClassName: LoginBean
 * @Description: (java类作用描述)
 * @Author: 谭亚森
 * @CreateDate: 2020/5/20 20:21
 */
public class LoginBean {
    private String status;
    private String message;

    private ResultBean result;

    public class ResultBean{
         private int id;
         private String sessionId;
         private String name;
         private String userName;
         private int reviewStatus;
         private String jiGuangPwd;
         private String imagePic;
         private String inauguralHospital;
         private  String jobTitle;
         private int departmentId;
         private String departmentName;
         private int whetherHaveImagePic;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getReviewStatus() {
            return reviewStatus;
        }

        public void setReviewStatus(int reviewStatus) {
            this.reviewStatus = reviewStatus;
        }

        public String getJiGuangPwd() {
            return jiGuangPwd;
        }

        public void setJiGuangPwd(String jiGuangPwd) {
            this.jiGuangPwd = jiGuangPwd;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getInauguralHospital() {
            return inauguralHospital;
        }

        public void setInauguralHospital(String inauguralHospital) {
            this.inauguralHospital = inauguralHospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getWhetherHaveImagePic() {
            return whetherHaveImagePic;
        }

        public void setWhetherHaveImagePic(int whetherHaveImagePic) {
            this.whetherHaveImagePic = whetherHaveImagePic;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}
