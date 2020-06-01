package com.wd.model_base.Bean;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.common.bean
 * @ClassName: DoctorWalletBean
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/5/31 0:18
 */
public class DoctorWalletBean {
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
         * balance : 5060
         * doctorId : 54
         * id : 54
         * version : 12
         * whetherBindBankCard : 2
         * whetherBindIdCard : 2
         */

        private int balance;
        private int doctorId;
        private int id;
        private int version;
        private int whetherBindBankCard;
        private int whetherBindIdCard;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getWhetherBindBankCard() {
            return whetherBindBankCard;
        }

        public void setWhetherBindBankCard(int whetherBindBankCard) {
            this.whetherBindBankCard = whetherBindBankCard;
        }

        public int getWhetherBindIdCard() {
            return whetherBindIdCard;
        }

        public void setWhetherBindIdCard(int whetherBindIdCard) {
            this.whetherBindIdCard = whetherBindIdCard;
        }
    }
}
