package cn.company1075.xiaofu.utils;

import java.util.List;

public class UserData {


    /**
     * code : 200
     * msg : SUCCESS
     * count : 0
     * data : [{"platformUserId":1,"platformUserOpenid":"admin","platformUserToken":"698D51A19D8A121CE581499D7B701668","platformUserType":0,"platformUserMark":0,"gmtCreate":"2018-07-26T10:55:26.000+0000","gmtModified":"2018-07-26T10:55:26.000+0000"},{"platformUserId":2,"platformUserOpenid":"admin1","platformUserToken":"698D51A19D8A121CE581499D7B701668","platformUserType":0,"platformUserMark":0,"gmtCreate":"2018-07-27T02:18:24.000+0000","gmtModified":"2018-07-27T02:18:24.000+0000"},{"platformUserId":3,"platformUserOpenid":"dlldsb","platformUserToken":"698D51A19D8A121CE581499D7B701668","platformUserType":0,"platformUserMark":0,"gmtCreate":"2018-08-21T08:47:21.000+0000","gmtModified":"2018-08-21T08:47:21.000+0000"}]
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
         * platformUserId : 1
         * platformUserOpenid : admin
         * platformUserToken : 698D51A19D8A121CE581499D7B701668
         * platformUserType : 0
         * platformUserMark : 0
         * gmtCreate : 2018-07-26T10:55:26.000+0000
         * gmtModified : 2018-07-26T10:55:26.000+0000
         */

        private int platformUserId;
        private String platformUserOpenid;
        private String platformUserToken;
        private int platformUserType;
        private int platformUserMark;
        private String gmtCreate;
        private String gmtModified;

        public int getPlatformUserId() {
            return platformUserId;
        }

        public void setPlatformUserId(int platformUserId) {
            this.platformUserId = platformUserId;
        }

        public String getPlatformUserOpenid() {
            return platformUserOpenid;
        }

        public void setPlatformUserOpenid(String platformUserOpenid) {
            this.platformUserOpenid = platformUserOpenid;
        }

        public String getPlatformUserToken() {
            return platformUserToken;
        }

        public void setPlatformUserToken(String platformUserToken) {
            this.platformUserToken = platformUserToken;
        }

        public int getPlatformUserType() {
            return platformUserType;
        }

        public void setPlatformUserType(int platformUserType) {
            this.platformUserType = platformUserType;
        }

        public int getPlatformUserMark() {
            return platformUserMark;
        }

        public void setPlatformUserMark(int platformUserMark) {
            this.platformUserMark = platformUserMark;
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
