package com.wd.model_base.Bean;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.common.bean
 * @ClassName: DoctorIdCardBean
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/5/31 0:55
 */
public class DoctorIdCardBean {
      private String status;
      private String message;
      private ResultBean result;

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
    public class ResultBean{
        private String name;
        private String sex;
        private String nation;
        private String idNumber;

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

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
}
