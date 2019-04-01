package cn.company1075.xiaofu.utils;

import java.util.List;

public class Goods {

    /**
     * code : 0
     * msg : null
     * count : 4
     * data : [{"page":null,"limit":null,"goodId":17,"platformUserId":null,"goodName":"阿里云","goodImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/goods/2018/8/f2ddd1d63b84479e8017cdc8d262427d7cd37c56.jpg?Expires=1849850354&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=todR1VlApW1wvI1b5drBu8Hy2e4=","goodDescribe":"阿里云","goodElement":"阿里云","goodPrice":999,"goodPriceReal":888,"gmtCreate":null,"gmtModified":null,"id":19,"shopId":null},{"page":null,"limit":null,"goodId":18,"platformUserId":null,"goodName":"发达","goodImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/goods/2018/8/72395d1b8e1c41cb8f8ace50278de519267e22b1.jpg?Expires=1850970192&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=jPWjOQAjHtfXayoLo1O6Eze8ICE=","goodDescribe":"哒哒","goodElement":"哒哒","goodPrice":99,"goodPriceReal":88,"gmtCreate":null,"gmtModified":null,"id":20,"shopId":null},{"page":null,"limit":null,"goodId":19,"platformUserId":null,"goodName":"对对对","goodImage":"","goodDescribe":"对对对","goodElement":"对对对","goodPrice":959,"goodPriceReal":5565,"gmtCreate":null,"gmtModified":null,"id":21,"shopId":null},{"page":null,"limit":null,"goodId":20,"platformUserId":null,"goodName":"烦烦烦","goodImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/goods/2018/8/81f45b4cf1da4e9ea9e12720ef71eb6ac3ce6a9c.jpg?Expires=1850263619&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=Ggtr8cUnfeSP7Pi1IARMAEIJYDk=","goodDescribe":"烦烦烦","goodElement":"烦烦烦","goodPrice":999,"goodPriceReal":888,"gmtCreate":null,"gmtModified":null,"id":22,"shopId":null}]
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
         * goodId : 17
         * platformUserId : null
         * goodName : 阿里云
         * goodImage : http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/goods/2018/8/f2ddd1d63b84479e8017cdc8d262427d7cd37c56.jpg?Expires=1849850354&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=todR1VlApW1wvI1b5drBu8Hy2e4=
         * goodDescribe : 阿里云
         * goodElement : 阿里云
         * goodPrice : 999
         * goodPriceReal : 888
         * gmtCreate : null
         * gmtModified : null
         * id : 19
         * shopId : null
         * 商品集合
         */

        private Object page;
        private Object limit;
        private Long goodId;
        private Object platformUserId;
        private String goodName;
        private String goodImage;
        private String goodDescribe;
        private String goodElement;
        private int goodPrice;
        private int goodPriceReal;
        private Object gmtCreate;
        private Object gmtModified;
        private int id;
        private Object shopId;

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

        public Long getGoodId() {
            return goodId;
        }

        public void setGoodId(Long goodId) {
            this.goodId = goodId;
        }

        public Object getPlatformUserId() {
            return platformUserId;
        }

        public void setPlatformUserId(Object platformUserId) {
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

        public String getGoodElement() {
            return goodElement;
        }

        public void setGoodElement(String goodElement) {
            this.goodElement = goodElement;
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

        public Object getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Object gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Object gmtModified) {
            this.gmtModified = gmtModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }
    }
}
