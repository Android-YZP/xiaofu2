package cn.company1075.xiaofu.utils;

import java.util.List;

public class Tc {


    /**
     * code : 0
     * msg : null
     * count : 9
     * data : [{"page":null,"limit":null,"packageId":4,"platformUserId":1,"packageName":"不能重复","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/7886456d18694969bd29da902beb905112da15ec.jpg?Expires=1851660499&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=UFzgScoZ%2FLm%2Fdk1cvoY5OuIqjAw%3D","packageDescribe":"不能重复","packageElement":"不能重复","packagePrice":666,"packagePriceReal":555,"gmtCreate":"2018-08-02T03:27:36.000+0000","gmtModified":"2018-09-07T06:08:20.000+0000","packageTag":3,"id":6,"shopId":null},{"page":null,"limit":null,"packageId":6,"platformUserId":1,"packageName":"几个地方","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/a7d50cefb7664efb89984dd4dcc52e28f79fbb8d.jpg?Expires=1851660512&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=21ChPb6fzxJ9vg2gBdL5nQRnhPk%3D","packageDescribe":"好人","packageElement":"谷歌","packagePrice":88,"packagePriceReal":225,"gmtCreate":"2018-08-07T08:24:57.000+0000","gmtModified":"2018-09-07T06:08:35.000+0000","packageTag":1,"id":8,"shopId":null},{"page":null,"limit":null,"packageId":7,"platformUserId":1,"packageName":"翻滚吧","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/6610fd99145d44cc8eaa4b0c55d6b2e191c4344c.jpg?Expires=1851660527&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=ILmr%2BfpwFbYaqncas65SteUYNJg%3D","packageDescribe":"发给他换","packageElement":"发过的","packagePrice":87,"packagePriceReal":6656,"gmtCreate":"2018-08-07T08:25:14.000+0000","gmtModified":"2018-09-07T06:08:47.000+0000","packageTag":1,"id":9,"shopId":null},{"page":null,"limit":null,"packageId":8,"platformUserId":1,"packageName":"客家话","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/43b857fc7e6041869d42d94a5f09c65492bfb1cf.jpg?Expires=1851660535&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=Gq094S5vot7BsqSpZbLEwa%2BQf24%3D","packageDescribe":"监听","packageElement":"给对方","packagePrice":785,"packagePriceReal":255,"gmtCreate":"2018-08-07T08:25:30.000+0000","gmtModified":"2018-09-07T06:08:57.000+0000","packageTag":4,"id":10,"shopId":null},{"page":null,"limit":null,"packageId":9,"platformUserId":1,"packageName":"发送到","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/ec888e326b824cbc9efb332331f4631dce1f9912.jpg?Expires=1851660660&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=SiLKkR8hm1z9B62eQ93Ma4EGNCI%3D","packageDescribe":"规划局","packageElement":"一天","packagePrice":74,"packagePriceReal":558,"gmtCreate":"2018-08-07T08:25:46.000+0000","gmtModified":"2018-09-07T06:11:04.000+0000","packageTag":4,"id":11,"shopId":null},{"page":null,"limit":null,"packageId":10,"platformUserId":1,"packageName":"锦觅","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/ab6f1989884d49f1bc95d3c86d39ff91f551da91.jpg?Expires=1851660924&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=9N%2F61wNXbAIJrI9dgkdrJHMFCoQ%3D","packageDescribe":"锦觅","packageElement":"锦觅","packagePrice":899,"packagePriceReal":55,"gmtCreate":"2018-08-07T08:26:00.000+0000","gmtModified":"2018-09-07T06:15:33.000+0000","packageTag":3,"id":12,"shopId":null},{"page":null,"limit":null,"packageId":11,"platformUserId":1,"packageName":"价格还能","packageImage":"b1bd93a26e034192bf9813499b3873cf2c51ece.jpg","packageDescribe":"好几个","packageElement":"衣服","packagePrice":7,"packagePriceReal":55,"gmtCreate":"2018-08-07T08:26:15.000+0000","gmtModified":"2018-08-07T08:26:15.000+0000","packageTag":0,"id":13,"shopId":null},{"page":null,"limit":null,"packageId":2,"platformUserId":1,"packageName":"新院套餐啊啊吖","packageImage":"http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/e8cd92e9f69444009398cfee1bef9b8448594506.jpg?Expires=1851660467&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=dvp4s2NWZ9P19xbzwuFucJWJhdA%3D","packageDescribe":"套餐ddd啊啊","packageElement":"套餐ddd363","packagePrice":999,"packagePriceReal":888,"gmtCreate":"2018-07-30T03:21:24.000+0000","gmtModified":"2018-09-07T06:07:50.000+0000","packageTag":2,"id":17,"shopId":null}]
     * url : null
     * id : null
     *
     * 套餐
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
         * packageId : 4
         * platformUserId : 1
         * packageName : 不能重复
         * packageImage : http://1075-cloud-makeup-platform.oss-cn-beijing.aliyuncs.com/image/packages/2018/9/7886456d18694969bd29da902beb905112da15ec.jpg?Expires=1851660499&OSSAccessKeyId=LTAI54GlGjdgzmt1&Signature=UFzgScoZ%2FLm%2Fdk1cvoY5OuIqjAw%3D
         * packageDescribe : 不能重复
         * packageElement : 不能重复
         * packagePrice : 666
         * packagePriceReal : 555
         * gmtCreate : 2018-08-02T03:27:36.000+0000
         * gmtModified : 2018-09-07T06:08:20.000+0000
         * packageTag : 3
         * id : 6
         * shopId : null
         */

        private Object page;
        private Object limit;
        private int packageId;
        private int platformUserId;
        private String packageName;
        private String packageImage;
        private String packageDescribe;
        private String packageElement;
        private int packagePrice;
        private int packagePriceReal;
        private String gmtCreate;
        private String gmtModified;
        private int packageTag;
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

        public int getPackageId() {
            return packageId;
        }

        public void setPackageId(int packageId) {
            this.packageId = packageId;
        }

        public int getPlatformUserId() {
            return platformUserId;
        }

        public void setPlatformUserId(int platformUserId) {
            this.platformUserId = platformUserId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPackageImage() {
            return packageImage;
        }

        public void setPackageImage(String packageImage) {
            this.packageImage = packageImage;
        }

        public String getPackageDescribe() {
            return packageDescribe;
        }

        public void setPackageDescribe(String packageDescribe) {
            this.packageDescribe = packageDescribe;
        }

        public String getPackageElement() {
            return packageElement;
        }

        public void setPackageElement(String packageElement) {
            this.packageElement = packageElement;
        }

        public int getPackagePrice() {
            return packagePrice;
        }

        public void setPackagePrice(int packagePrice) {
            this.packagePrice = packagePrice;
        }

        public int getPackagePriceReal() {
            return packagePriceReal;
        }

        public void setPackagePriceReal(int packagePriceReal) {
            this.packagePriceReal = packagePriceReal;
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

        public int getPackageTag() {
            return packageTag;
        }

        public void setPackageTag(int packageTag) {
            this.packageTag = packageTag;
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
