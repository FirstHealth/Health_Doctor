package com.wd.model_base.Bean;

/**
 * @ClassName DoctorBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2423:37
 */
public class DoctorBean {
    /**
     * result : {"departmentId":2,"departmentName":"骨科 ","goodField":"专治毒舌妇，价格公道，从不恶意收费","id":56,"imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image1.jpg","inauguralHospital":"中国第一人民医院","jobTitle":"主任医师","name":"武则天","personalProfile":"本人心地善良，从不恶意收费"}
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
         * departmentId : 2
         * departmentName : 骨科
         * goodField : 专治毒舌妇，价格公道，从不恶意收费
         * id : 56
         * imagePic : http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image1.jpg
         * inauguralHospital : 中国第一人民医院
         * jobTitle : 主任医师
         * name : 武则天
         * personalProfile : 本人心地善良，从不恶意收费
         */

        private int departmentId;
        private String departmentName;
        private String goodField;
        private int id;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String name;
        private String personalProfile;

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

        public String getGoodField() {
            return goodField;
        }

        public void setGoodField(String goodField) {
            this.goodField = goodField;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPersonalProfile() {
            return personalProfile;
        }

        public void setPersonalProfile(String personalProfile) {
            this.personalProfile = personalProfile;
        }
    }
}
