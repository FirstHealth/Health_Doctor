package com.wd.model_base.Bean;

import java.util.List;

/**
 * @ClassName AcceptedBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2620:57
 */
public class AcceptedBean {

    /**
     * result : [{"adoptTime":1590595200000,"content":"哈哈，鸡你太美","disease":"小儿麻痹症","releaseUserHeadPic":"http://mobile.bwstudent.com/images/health/user/head_pic/2020-05-21/H2HJ5820200521104158.jpg","releaseUserId":246,"releaseUserNickName":"rS_ZZYVX","title":"梦洋亚森是沙雕"}]
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
         * adoptTime : 1590595200000
         * content : 哈哈，鸡你太美
         * disease : 小儿麻痹症
         * releaseUserHeadPic : http://mobile.bwstudent.com/images/health/user/head_pic/2020-05-21/H2HJ5820200521104158.jpg
         * releaseUserId : 246
         * releaseUserNickName : rS_ZZYVX
         * title : 梦洋亚森是沙雕
         */

        private long adoptTime;
        private String content;
        private String disease;
        private String releaseUserHeadPic;
        private int releaseUserId;
        private String releaseUserNickName;
        private String title;

        public long getAdoptTime() {
            return adoptTime;
        }

        public void setAdoptTime(long adoptTime) {
            this.adoptTime = adoptTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public String getReleaseUserHeadPic() {
            return releaseUserHeadPic;
        }

        public void setReleaseUserHeadPic(String releaseUserHeadPic) {
            this.releaseUserHeadPic = releaseUserHeadPic;
        }

        public int getReleaseUserId() {
            return releaseUserId;
        }

        public void setReleaseUserId(int releaseUserId) {
            this.releaseUserId = releaseUserId;
        }

        public String getReleaseUserNickName() {
            return releaseUserNickName;
        }

        public void setReleaseUserNickName(String releaseUserNickName) {
            this.releaseUserNickName = releaseUserNickName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
