package cn.company1075.xiaofu.utils;

import java.util.List;

public class ShopData {


    /**
     * code : 0
     * msg : null
     * count : 16
     * data : [{"shopId":8,"shopUserId":6,"shopName":"新院美容","shopRemark":"哒哒","storeType":"1","cooperationMethod":"1","cooperationLevel":"1","cooperationStatus":0,"cooperationDeadline":"2018-07-30T05:28:17.000+0000","gmtCreate":"2018-07-30T05:28:20.000+0000","gmtModified":"2018-07-30T05:28:20.000+0000","shopUserOpenid":"dll","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":9,"shopUserId":8,"shopName":"美容","shopRemark":"顶顶顶顶","storeType":"1","cooperationMethod":"SDK","cooperationLevel":"1","cooperationStatus":1,"cooperationDeadline":"2018-07-30T16:00:00.000+0000","gmtCreate":"2018-07-30T07:44:01.000+0000","gmtModified":"2018-07-30T07:44:01.000+0000","shopUserOpenid":"dlll","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":10,"shopUserId":9,"shopName":"曹","shopRemark":"11","storeType":"1","cooperationMethod":"SDK","cooperationLevel":"1","cooperationStatus":1,"cooperationDeadline":"2018-08-22T16:00:00.000+0000","gmtCreate":"2018-08-06T03:35:16.000+0000","gmtModified":"2018-08-06T03:35:16.000+0000","shopUserOpenid":"c","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":11,"shopUserId":10,"shopName":"新","shopRemark":"111","storeType":"1","cooperationMethod":"SDK","cooperationLevel":"2","cooperationStatus":2,"cooperationDeadline":"2018-08-23T16:00:00.000+0000","gmtCreate":"2018-08-06T03:35:35.000+0000","gmtModified":"2018-08-06T03:35:35.000+0000","shopUserOpenid":"x","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":12,"shopUserId":11,"shopName":"远","shopRemark":"111","storeType":"2","cooperationMethod":"SDK","cooperationLevel":"1","cooperationStatus":1,"cooperationDeadline":"2018-08-22T16:00:00.000+0000","gmtCreate":"2018-08-06T03:35:56.000+0000","gmtModified":"2018-08-06T03:35:56.000+0000","shopUserOpenid":"y","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":13,"shopUserId":12,"shopName":"第","shopRemark":"的","storeType":"1","cooperationMethod":"SDK","cooperationLevel":"1","cooperationStatus":1,"cooperationDeadline":"2018-08-07T16:00:00.000+0000","gmtCreate":"2018-08-06T03:37:30.000+0000","gmtModified":"2018-08-06T03:37:30.000+0000","shopUserOpenid":"d","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":14,"shopUserId":13,"shopName":"乐","shopRemark":"额","storeType":"1","cooperationMethod":"SDK","cooperationLevel":"1","cooperationStatus":2,"cooperationDeadline":"2018-08-09T16:00:00.000+0000","gmtCreate":"2018-08-06T03:37:47.000+0000","gmtModified":"2018-08-06T03:37:47.000+0000","shopUserOpenid":"l","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null},{"shopId":15,"shopUserId":14,"shopName":"勒","shopRemark":"2额","storeType":"1","cooperationMethod":"SDK","cooperationLevel":"1","cooperationStatus":1,"cooperationDeadline":"2018-08-07T16:00:00.000+0000","gmtCreate":"2018-08-06T03:38:16.000+0000","gmtModified":"2018-08-06T03:38:16.000+0000","shopUserOpenid":"n","shopUserToken":null,"platformUserOpenid":"admin","platformUserId":1,"page":null,"limit":null}]
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
         * shopId : 8
         * shopUserId : 6
         * shopName : 新院美容
         * shopRemark : 哒哒
         * storeType : 1
         * cooperationMethod : 1
         * cooperationLevel : 1
         * cooperationStatus : 0
         * cooperationDeadline : 2018-07-30T05:28:17.000+0000
         * gmtCreate : 2018-07-30T05:28:20.000+0000
         * gmtModified : 2018-07-30T05:28:20.000+0000
         * shopUserOpenid : dll
         * shopUserToken : null
         * platformUserOpenid : admin
         * platformUserId : 1
         * page : null
         * limit : null
         */

        private int shopId;
        private int shopUserId;
        private String shopName;
        private String shopRemark;
        private String storeType;
        private String cooperationMethod;
        private String cooperationLevel;
        private int cooperationStatus;
        private String cooperationDeadline;
        private String gmtCreate;
        private String gmtModified;
        private String shopUserOpenid;
        private Object shopUserToken;
        private String platformUserOpenid;
        private int platformUserId;
        private Object page;
        private Object limit;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getShopUserId() {
            return shopUserId;
        }

        public void setShopUserId(int shopUserId) {
            this.shopUserId = shopUserId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopRemark() {
            return shopRemark;
        }

        public void setShopRemark(String shopRemark) {
            this.shopRemark = shopRemark;
        }

        public String getStoreType() {
            return storeType;
        }

        public void setStoreType(String storeType) {
            this.storeType = storeType;
        }

        public String getCooperationMethod() {
            return cooperationMethod;
        }

        public void setCooperationMethod(String cooperationMethod) {
            this.cooperationMethod = cooperationMethod;
        }

        public String getCooperationLevel() {
            return cooperationLevel;
        }

        public void setCooperationLevel(String cooperationLevel) {
            this.cooperationLevel = cooperationLevel;
        }

        public int getCooperationStatus() {
            return cooperationStatus;
        }

        public void setCooperationStatus(int cooperationStatus) {
            this.cooperationStatus = cooperationStatus;
        }

        public String getCooperationDeadline() {
            return cooperationDeadline;
        }

        public void setCooperationDeadline(String cooperationDeadline) {
            this.cooperationDeadline = cooperationDeadline;
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

        public String getShopUserOpenid() {
            return shopUserOpenid;
        }

        public void setShopUserOpenid(String shopUserOpenid) {
            this.shopUserOpenid = shopUserOpenid;
        }

        public Object getShopUserToken() {
            return shopUserToken;
        }

        public void setShopUserToken(Object shopUserToken) {
            this.shopUserToken = shopUserToken;
        }

        public String getPlatformUserOpenid() {
            return platformUserOpenid;
        }

        public void setPlatformUserOpenid(String platformUserOpenid) {
            this.platformUserOpenid = platformUserOpenid;
        }

        public int getPlatformUserId() {
            return platformUserId;
        }

        public void setPlatformUserId(int platformUserId) {
            this.platformUserId = platformUserId;
        }

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
    }
}
