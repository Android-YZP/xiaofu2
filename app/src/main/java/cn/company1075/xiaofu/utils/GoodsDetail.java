package cn.company1075.xiaofu.utils;

import java.util.List;

public class GoodsDetail {


    /**
     * code : 200
     * msg : SUCCESS
     * count : 0
     * data : [{"page":null,"limit":null,"goodId":17,"platformUserId":1,"goodName":"润玉","goodImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/goods/2018/9/068a15a598cf4b8ea07fb7e54041ac2215d9dacd.jpg?Expires=1851661012&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=gUNz5gxPeER4oxPteQhXdEGFA4w%3D","goodDescribe":"润玉","goodUseMethod":"就这么用","goodMl":"120ml","goodPrice":999,"goodPriceReal":888,"goodTag":null,"gmtCreate":"2018-08-17T07:19:22.000+0000","gmtModified":"2018-09-12T05:29:02.000+0000"}]
     * url : null
     * id : null
     */

    private int code;
    private String msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
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
         * goodId : 17
         * platformUserId : 1
         * goodName : 润玉
         * goodImage : http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/goods/2018/9/068a15a598cf4b8ea07fb7e54041ac2215d9dacd.jpg?Expires=1851661012&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=gUNz5gxPeER4oxPteQhXdEGFA4w%3D
         * goodDescribe : 润玉
         * goodUseMethod : 就这么用
         * goodMl : 120ml
         * goodPrice : 999
         * goodPriceReal : 888
         * goodTag : null
         * gmtCreate : 2018-08-17T07:19:22.000+0000
         * gmtModified : 2018-09-12T05:29:02.000+0000
         */

        private Object page;
        private Object limit;
        private int goodId;
        private int platformUserId;
        private String goodName;
        private String goodImage;
        private String goodDescribe;
        private String goodUseMethod;
        private String goodMl;
        private int goodPrice;
        private int goodPriceReal;
        private Object goodTag;
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

        public int getGoodId() {
            return goodId;
        }

        public void setGoodId(int goodId) {
            this.goodId = goodId;
        }

        public int getPlatformUserId() {
            return platformUserId;
        }

        public void setPlatformUserId(int platformUserId) {
            this.platformUserId = platformUserId;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getGoodImage() {
            return goodImage;
        }

        public void setGoodImage(String goodImage) {
            this.goodImage = goodImage;
        }

        public String getGoodDescribe() {
            return goodDescribe;
        }

        public void setGoodDescribe(String goodDescribe) {
            this.goodDescribe = goodDescribe;
        }

        public String getGoodUseMethod() {
            return goodUseMethod;
        }

        public void setGoodUseMethod(String goodUseMethod) {
            this.goodUseMethod = goodUseMethod;
        }

        public String getGoodMl() {
            return goodMl;
        }

        public void setGoodMl(String goodMl) {
            this.goodMl = goodMl;
        }

        public int getGoodPrice() {
            return goodPrice;
        }

        public void setGoodPrice(int goodPrice) {
            this.goodPrice = goodPrice;
        }

        public int getGoodPriceReal() {
            return goodPriceReal;
        }

        public void setGoodPriceReal(int goodPriceReal) {
            this.goodPriceReal = goodPriceReal;
        }

        public Object getGoodTag() {
            return goodTag;
        }

        public void setGoodTag(Object goodTag) {
            this.goodTag = goodTag;
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
