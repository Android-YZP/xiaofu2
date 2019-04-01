package cn.company1075.xiaofu.utils;

import java.util.List;

public class ADBean {

    /**
     * code : 0
     * msg : null
     * count : 4
     * data : [{"page":null,"limit":null,"adId":16,"shopId":8,"adVideoTitle":"发的发生","adVideoTitleUrl":"","adRemark":"发发发","gmtCreate":"2018-08-21T09:09:39.000+0000","gmtModified":"2018-08-21T09:09:39.000+0000"},{"page":null,"limit":null,"adId":17,"shopId":8,"adVideoTitle":"ff","adVideoTitleUrl":"","adRemark":"ff","gmtCreate":"2018-08-21T09:22:08.000+0000","gmtModified":"2018-08-21T09:22:08.000+0000"},{"page":null,"limit":null,"adId":18,"shopId":8,"adVideoTitle":"fff","adVideoTitleUrl":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/shopad/2018/8/960fa815a25249338663d2d1308184b061b6e4ff.jpg?Expires=1850203433&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=zRbbRg2QWgCe2vlzIpdUZC8otBg%3D","adRemark":"bgg","gmtCreate":"2018-08-21T09:24:10.000+0000","gmtModified":"2018-08-21T09:24:10.000+0000"},{"page":null,"limit":null,"adId":19,"shopId":8,"adVideoTitle":"对对对","adVideoTitleUrl":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/shopad/2018/8/982f5492cac047ffaf078e53639c0d7b26764838.jpg?Expires=1850263804&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=xjzYj1bv4bHPBrqsqFWZyuSYTdI%3D","adRemark":"对对对","gmtCreate":"2018-08-22T02:10:09.000+0000","gmtModified":"2018-08-22T02:10:09.000+0000"}]
     * url : null
     * id : null
     */

    private int code;
    private Object msg;
    private int count;
    private Object url;
    private Object id;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * page : null
         * limit : null
         * adId : 16
         * shopId : 8
         * adVideoTitle : 发的发生
         * adVideoTitleUrl :
         * adRemark : 发发发
         * gmtCreate : 2018-08-21T09:09:39.000+0000
         * gmtModified : 2018-08-21T09:09:39.000+0000
         */

        private Object page;
        private Object limit;
        private int adId;
        private int shopId;
        private String adVideoTitle;
        private String adVideoTitleUrl;
        private String adRemark;
        private String gmtCreate;
        private String gmtModified;

        public Object getPage() {
            return page;
        }

        public void setPage(Object page) {
            this.page = page;
        }

        public Object getLimit() {
            return limit;
        }

        public void setLimit(Object limit) {
            this.limit = limit;
        }

        public int getAdId() {
            return adId;
        }

        public void setAdId(int adId) {
            this.adId = adId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getAdVideoTitle() {
            return adVideoTitle;
        }

        public void setAdVideoTitle(String adVideoTitle) {
            this.adVideoTitle = adVideoTitle;
        }

        public String getAdVideoTitleUrl() {
            return adVideoTitleUrl;
        }

        public void setAdVideoTitleUrl(String adVideoTitleUrl) {
            this.adVideoTitleUrl = adVideoTitleUrl;
        }

        public String getAdRemark() {
            return adRemark;
        }

        public void setAdRemark(String adRemark) {
            this.adRemark = adRemark;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }
    }
}
