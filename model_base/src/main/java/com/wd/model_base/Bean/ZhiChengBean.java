package com.wd.model_base.Bean;

import java.util.List;

/**
 * @ClassName ZhiChengBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/1921:50
 */
public class ZhiChengBean {

    /**
     * result : [{"id":1,"jobTitle":"主治医师"},{"id":2,"jobTitle":"副主任医师"},{"id":3,"jobTitle":"主任医师"}]
     * message : 查询成功
     * status : 0000
     */

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
        /**
         * id : 1
         * jobTitle : 主治医师
         */

        private int id;
        private String jobTitle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }
    }
}
