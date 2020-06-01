package com.wd.model_base.Bean;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.common.bean
 * @ClassName: DoctorBankCardBean
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/5/31 0:53
 */
public class DoctorBankCardBean {
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
         private String bankCardNumber;
         private String bankName;
         private int bankCardType;

        public String getBankCardNumber() {
            return bankCardNumber;
        }

        public void setBankCardNumber(String bankCardNumber) {
            this.bankCardNumber = bankCardNumber;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getBankCardType() {
            return bankCardType;
        }

        public void setBankCardType(int bankCardType) {
            this.bankCardType = bankCardType;
        }
    }
}
