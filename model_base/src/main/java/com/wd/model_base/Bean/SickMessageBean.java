package com.wd.model_base.Bean;

/**
 * @ClassName SickMessageBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2222:11
 */
public class SickMessageBean {

    /**
     * result : {"amount":20,"authorName":"eV_JHYYZ","departmentId":2,"departmentName":"骨科 ","detail":"实在太多了，，，","disease":"颈椎病","id":38,"picture":"http://mobile.bwstudent.com/images/health/user/sick_circle/2019-09-28/EwqNKQ20190928162703.png,http://mobile.bwstudent.com/images/health/user/sick_circle/2019-09-28/kuOONx20190928162703.jpeg,http://mobile.bwstudent.com/images/health/user/sick_circle/2019-09-28/W2qMWe20190928162703.JPEG","title":"我的颈椎病中午治好了","treatmentEndTime":1566921600000,"treatmentHospital":"八维门诊","treatmentProcess":"","treatmentStartTime":1566921600000,"userId":9,"whetherContent":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * amount : 20
         * authorName : eV_JHYYZ
         * departmentId : 2
         * departmentName : 骨科
         * detail : 实在太多了，，，
         * disease : 颈椎病
         * id : 38
         * picture : http://mobile.bwstudent.com/images/health/user/sick_circle/2019-09-28/EwqNKQ20190928162703.png,http://mobile.bwstudent.com/images/health/user/sick_circle/2019-09-28/kuOONx20190928162703.jpeg,http://mobile.bwstudent.com/images/health/user/sick_circle/2019-09-28/W2qMWe20190928162703.JPEG
         * title : 我的颈椎病中午治好了
         * treatmentEndTime : 1566921600000
         * treatmentHospital : 八维门诊
         * treatmentProcess :
         * treatmentStartTime : 1566921600000
         * userId : 9
         * whetherContent : 2
         */

        private int amount;
        private String authorName;
        private int departmentId;
        private String departmentName;
        private String detail;
        private String disease;
        private int id;
        private String picture;
        private String title;
        private long treatmentEndTime;
        private String treatmentHospital;
        private String treatmentProcess;
        private long treatmentStartTime;
        private int userId;
        private int whetherContent;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getTreatmentEndTime() {
            return treatmentEndTime;
        }

        public void setTreatmentEndTime(long treatmentEndTime) {
            this.treatmentEndTime = treatmentEndTime;
        }

        public String getTreatmentHospital() {
            return treatmentHospital;
        }

        public void setTreatmentHospital(String treatmentHospital) {
            this.treatmentHospital = treatmentHospital;
        }

        public String getTreatmentProcess() {
            return treatmentProcess;
        }

        public void setTreatmentProcess(String treatmentProcess) {
            this.treatmentProcess = treatmentProcess;
        }

        public long getTreatmentStartTime() {
            return treatmentStartTime;
        }

        public void setTreatmentStartTime(long treatmentStartTime) {
            this.treatmentStartTime = treatmentStartTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherContent() {
            return whetherContent;
        }

        public void setWhetherContent(int whetherContent) {
            this.whetherContent = whetherContent;
        }
    }
}
