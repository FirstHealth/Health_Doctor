package com.wd.model_base.Bean;

import java.util.List;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.common.bean
 * @ClassName: RevenueBean
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/5/31 0:23
 */
public class RevenueBean {

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private int direction;
        private int incomeType;
        private int money;
        private long recordTime;

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getIncomeType() {
            return incomeType;
        }

        public void setIncomeType(int incomeType) {
            this.incomeType = incomeType;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public long getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(long recordTime) {
            this.recordTime = recordTime;
        }
    }
}
