package com.wd.model_base.Bean;

import java.util.List;

/**
 * @ClassName SearchSickBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2421:49
 */
public class SearchSickBean {

    /**
     * result : [{"amount":10,"collectionNum":0,"commentNum":1,"detail":"不是表面的背痛，是内里的腰部疼痛，本人一直从事长时间坐姿的工作，导致腰部经常酸痛，而且工比较忙，一直没有时间去看，导致最近有点疼的厉害。求一个治疗方案。","releaseTime":1569168000000,"sickCircleId":33,"title":"腰有点疼，求个好的治疗方案"}]
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
         * amount : 10
         * collectionNum : 0
         * commentNum : 1
         * detail : 不是表面的背痛，是内里的腰部疼痛，本人一直从事长时间坐姿的工作，导致腰部经常酸痛，而且工比较忙，一直没有时间去看，导致最近有点疼的厉害。求一个治疗方案。
         * releaseTime : 1569168000000
         * sickCircleId : 33
         * title : 腰有点疼，求个好的治疗方案
         */

        private int amount;
        private int collectionNum;
        private int commentNum;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(int sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
