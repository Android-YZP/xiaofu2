package cn.company1075.xiaofu.utils;

import java.util.List;

public class WeatherNeW {


    /**
     * code : 0
     * message : 处理成功！
     * url : null
     * data : {"HeWeather6":[{"now":{"hum":"46","vis":"20","pres":"1016","pcpn":"0.0","fl":"26","wind_sc":"2","wind_dir":"东北风","wind_spd":"7","cloud":"25","wind_deg":"58","tmp":"26","cond_txt":"阴","cond_code":"104"},"update":{"loc":"2018-09-28 13:46","utc":"2018-09-28 05:46"},"basic":{"admin_area":"江苏","tz":"+8.00","location":"无锡","lon":"120.30166626","parent_city":"无锡","cnty":"中国","lat":"31.57472992","cid":"CN101190201"},"daily_forecast":[{"date":"2018-09-28","tmp_min":"19","hum":"67","vis":"20","cond_txt_d":"阴","pres":"1016","pcpn":"0.0","cond_code_n":"101","wind_sc":"3-4","wind_dir":"东北风","wind_spd":"15","pop":"1","wind_deg":"24","uv_index":"4","tmp_max":"28","cond_txt_n":"多云","cond_code_d":"104"},{"date":"2018-09-29","tmp_min":"17","hum":"72","vis":"20","cond_txt_d":"多云","pres":"1015","pcpn":"0.0","cond_code_n":"101","wind_sc":"3-4","wind_dir":"东北风","wind_spd":"12","pop":"22","wind_deg":"66","uv_index":"3","tmp_max":"27","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-09-30","tmp_min":"16","hum":"73","vis":"20","cond_txt_d":"晴","pres":"1013","pcpn":"0.0","cond_code_n":"100","wind_sc":"3-4","wind_dir":"西北风","wind_spd":"12","pop":"0","wind_deg":"349","uv_index":"7","tmp_max":"27","cond_txt_n":"晴","cond_code_d":"100"},{"date":"2018-10-01","tmp_min":"15","hum":"41","vis":"20","cond_txt_d":"多云","pres":"1016","pcpn":"0.0","cond_code_n":"101","wind_sc":"1-2","wind_dir":"西北风","wind_spd":"9","pop":"0","wind_deg":"304","uv_index":"4","tmp_max":"25","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-10-02","tmp_min":"17","hum":"52","vis":"20","cond_txt_d":"多云","pres":"1020","pcpn":"0.0","cond_code_n":"101","wind_sc":"1-2","wind_dir":"东南风","wind_spd":"11","pop":"0","wind_deg":"165","uv_index":"6","tmp_max":"26","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-10-03","tmp_min":"18","hum":"54","vis":"20","cond_txt_d":"多云","pres":"1021","pcpn":"0.0","cond_code_n":"101","wind_sc":"1-2","wind_dir":"西北风","wind_spd":"8","pop":"3","wind_deg":"348","uv_index":"6","tmp_max":"27","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-10-04","tmp_min":"19","hum":"63","vis":"20","cond_txt_d":"多云","pres":"1020","pcpn":"0.0","cond_code_n":"100","wind_sc":"1-2","wind_dir":"东北风","wind_spd":"11","pop":"7","wind_deg":"58","uv_index":"6","tmp_max":"27","cond_txt_n":"晴","cond_code_d":"101"}],"status":"ok","lifestyle":[{"txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。","brf":"较舒适","type":"comf"},{"txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。","brf":"热","type":"drsg"},{"txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。","brf":"少发","type":"flu"},{"txt":"阴天，且天气较热，请减少运动时间并降低运动强度。","brf":"较不宜","type":"sport"},{"txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。","brf":"适宜","type":"trav"},{"txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。","brf":"弱","type":"uv"},{"txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","brf":"较适宜","type":"cw"},{"txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。","brf":"良","type":"air"}]}]}
     */

    private int code;
    private String message;
    private Object url;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<HeWeather6Bean> HeWeather6;

        public List<HeWeather6Bean> getHeWeather6() {
            return HeWeather6;
        }

        public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
            this.HeWeather6 = HeWeather6;
        }

        public static class HeWeather6Bean {
            /**
             * now : {"hum":"46","vis":"20","pres":"1016","pcpn":"0.0","fl":"26","wind_sc":"2","wind_dir":"东北风","wind_spd":"7","cloud":"25","wind_deg":"58","tmp":"26","cond_txt":"阴","cond_code":"104"}
             * update : {"loc":"2018-09-28 13:46","utc":"2018-09-28 05:46"}
             * basic : {"admin_area":"江苏","tz":"+8.00","location":"无锡","lon":"120.30166626","parent_city":"无锡","cnty":"中国","lat":"31.57472992","cid":"CN101190201"}
             * daily_forecast : [{"date":"2018-09-28","tmp_min":"19","hum":"67","vis":"20","cond_txt_d":"阴","pres":"1016","pcpn":"0.0","cond_code_n":"101","wind_sc":"3-4","wind_dir":"东北风","wind_spd":"15","pop":"1","wind_deg":"24","uv_index":"4","tmp_max":"28","cond_txt_n":"多云","cond_code_d":"104"},{"date":"2018-09-29","tmp_min":"17","hum":"72","vis":"20","cond_txt_d":"多云","pres":"1015","pcpn":"0.0","cond_code_n":"101","wind_sc":"3-4","wind_dir":"东北风","wind_spd":"12","pop":"22","wind_deg":"66","uv_index":"3","tmp_max":"27","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-09-30","tmp_min":"16","hum":"73","vis":"20","cond_txt_d":"晴","pres":"1013","pcpn":"0.0","cond_code_n":"100","wind_sc":"3-4","wind_dir":"西北风","wind_spd":"12","pop":"0","wind_deg":"349","uv_index":"7","tmp_max":"27","cond_txt_n":"晴","cond_code_d":"100"},{"date":"2018-10-01","tmp_min":"15","hum":"41","vis":"20","cond_txt_d":"多云","pres":"1016","pcpn":"0.0","cond_code_n":"101","wind_sc":"1-2","wind_dir":"西北风","wind_spd":"9","pop":"0","wind_deg":"304","uv_index":"4","tmp_max":"25","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-10-02","tmp_min":"17","hum":"52","vis":"20","cond_txt_d":"多云","pres":"1020","pcpn":"0.0","cond_code_n":"101","wind_sc":"1-2","wind_dir":"东南风","wind_spd":"11","pop":"0","wind_deg":"165","uv_index":"6","tmp_max":"26","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-10-03","tmp_min":"18","hum":"54","vis":"20","cond_txt_d":"多云","pres":"1021","pcpn":"0.0","cond_code_n":"101","wind_sc":"1-2","wind_dir":"西北风","wind_spd":"8","pop":"3","wind_deg":"348","uv_index":"6","tmp_max":"27","cond_txt_n":"多云","cond_code_d":"101"},{"date":"2018-10-04","tmp_min":"19","hum":"63","vis":"20","cond_txt_d":"多云","pres":"1020","pcpn":"0.0","cond_code_n":"100","wind_sc":"1-2","wind_dir":"东北风","wind_spd":"11","pop":"7","wind_deg":"58","uv_index":"6","tmp_max":"27","cond_txt_n":"晴","cond_code_d":"101"}]
             * status : ok
             * lifestyle : [{"txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。","brf":"较舒适","type":"comf"},{"txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。","brf":"热","type":"drsg"},{"txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。","brf":"少发","type":"flu"},{"txt":"阴天，且天气较热，请减少运动时间并降低运动强度。","brf":"较不宜","type":"sport"},{"txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。","brf":"适宜","type":"trav"},{"txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。","brf":"弱","type":"uv"},{"txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","brf":"较适宜","type":"cw"},{"txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。","brf":"良","type":"air"}]
             */

            private NowBean now;
            private UpdateBean update;
            private BasicBean basic;
            private String status;
            private List<DailyForecastBean> daily_forecast;
            private List<LifestyleBean> lifestyle;

            public NowBean getNow() {
                return now;
            }

            public void setNow(NowBean now) {
                this.now = now;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public BasicBean getBasic() {
                return basic;
            }

            public void setBasic(BasicBean basic) {
                this.basic = basic;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<DailyForecastBean> getDaily_forecast() {
                return daily_forecast;
            }

            public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
                this.daily_forecast = daily_forecast;
            }

            public List<LifestyleBean> getLifestyle() {
                return lifestyle;
            }

            public void setLifestyle(List<LifestyleBean> lifestyle) {
                this.lifestyle = lifestyle;
            }

            public static class NowBean {
                /**
                 * hum : 46
                 * vis : 20
                 * pres : 1016
                 * pcpn : 0.0
                 * fl : 26
                 * wind_sc : 2
                 * wind_dir : 东北风
                 * wind_spd : 7
                 * cloud : 25
                 * wind_deg : 58
                 * tmp : 26
                 * cond_txt : 阴
                 * cond_code : 104
                 */

                private String hum;
                private String vis;
                private String pres;
                private String pcpn;
                private String fl;
                private String wind_sc;
                private String wind_dir;
                private String wind_spd;
                private String cloud;
                private String wind_deg;
                private String tmp;
                private String cond_txt;
                private String cond_code;

                public String getHum() {
                    return hum;
                }

                public void setHum(String hum) {
                    this.hum = hum;
                }

                public String getVis() {
                    return vis;
                }

                public void setVis(String vis) {
                    this.vis = vis;
                }

                public String getPres() {
                    return pres;
                }

                public void setPres(String pres) {
                    this.pres = pres;
                }

                public String getPcpn() {
                    return pcpn;
                }

                public void setPcpn(String pcpn) {
                    this.pcpn = pcpn;
                }

                public String getFl() {
                    return fl;
                }

                public void setFl(String fl) {
                    this.fl = fl;
                }

                public String getWind_sc() {
                    return wind_sc;
                }

                public void setWind_sc(String wind_sc) {
                    this.wind_sc = wind_sc;
                }

                public String getWind_dir() {
                    return wind_dir;
                }

                public void setWind_dir(String wind_dir) {
                    this.wind_dir = wind_dir;
                }

                public String getWind_spd() {
                    return wind_spd;
                }

                public void setWind_spd(String wind_spd) {
                    this.wind_spd = wind_spd;
                }

                public String getCloud() {
                    return cloud;
                }

                public void setCloud(String cloud) {
                    this.cloud = cloud;
                }

                public String getWind_deg() {
                    return wind_deg;
                }

                public void setWind_deg(String wind_deg) {
                    this.wind_deg = wind_deg;
                }

                public String getTmp() {
                    return tmp;
                }

                public void setTmp(String tmp) {
                    this.tmp = tmp;
                }

                public String getCond_txt() {
                    return cond_txt;
                }

                public void setCond_txt(String cond_txt) {
                    this.cond_txt = cond_txt;
                }

                public String getCond_code() {
                    return cond_code;
                }

                public void setCond_code(String cond_code) {
                    this.cond_code = cond_code;
                }
            }

            public static class UpdateBean {
                /**
                 * loc : 2018-09-28 13:46
                 * utc : 2018-09-28 05:46
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }

            public static class BasicBean {
                /**
                 * admin_area : 江苏
                 * tz : +8.00
                 * location : 无锡
                 * lon : 120.30166626
                 * parent_city : 无锡
                 * cnty : 中国
                 * lat : 31.57472992
                 * cid : CN101190201
                 */

                private String admin_area;
                private String tz;
                private String location;
                private String lon;
                private String parent_city;
                private String cnty;
                private String lat;
                private String cid;

                public String getAdmin_area() {
                    return admin_area;
                }

                public void setAdmin_area(String admin_area) {
                    this.admin_area = admin_area;
                }

                public String getTz() {
                    return tz;
                }

                public void setTz(String tz) {
                    this.tz = tz;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getLon() {
                    return lon;
                }

                public void setLon(String lon) {
                    this.lon = lon;
                }

                public String getParent_city() {
                    return parent_city;
                }

                public void setParent_city(String parent_city) {
                    this.parent_city = parent_city;
                }

                public String getCnty() {
                    return cnty;
                }

                public void setCnty(String cnty) {
                    this.cnty = cnty;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }
            }

            public static class DailyForecastBean {
                /**
                 * date : 2018-09-28
                 * tmp_min : 19
                 * hum : 67
                 * vis : 20
                 * cond_txt_d : 阴
                 * pres : 1016
                 * pcpn : 0.0
                 * cond_code_n : 101
                 * wind_sc : 3-4
                 * wind_dir : 东北风
                 * wind_spd : 15
                 * pop : 1
                 * wind_deg : 24
                 * uv_index : 4
                 * tmp_max : 28
                 * cond_txt_n : 多云
                 * cond_code_d : 104
                 */

                private String date;
                private String tmp_min;
                private String hum;
                private String vis;
                private String cond_txt_d;
                private String pres;
                private String pcpn;
                private String cond_code_n;
                private String wind_sc;
                private String wind_dir;
                private String wind_spd;
                private String pop;
                private String wind_deg;
                private String uv_index;
                private String tmp_max;
                private String cond_txt_n;
                private String cond_code_d;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTmp_min() {
                    return tmp_min;
                }

                public void setTmp_min(String tmp_min) {
                    this.tmp_min = tmp_min;
                }

                public String getHum() {
                    return hum;
                }

                public void setHum(String hum) {
                    this.hum = hum;
                }

                public String getVis() {
                    return vis;
                }

                public void setVis(String vis) {
                    this.vis = vis;
                }

                public String getCond_txt_d() {
                    return cond_txt_d;
                }

                public void setCond_txt_d(String cond_txt_d) {
                    this.cond_txt_d = cond_txt_d;
                }

                public String getPres() {
                    return pres;
                }

                public void setPres(String pres) {
                    this.pres = pres;
                }

                public String getPcpn() {
                    return pcpn;
                }

                public void setPcpn(String pcpn) {
                    this.pcpn = pcpn;
                }

                public String getCond_code_n() {
                    return cond_code_n;
                }

                public void setCond_code_n(String cond_code_n) {
                    this.cond_code_n = cond_code_n;
                }

                public String getWind_sc() {
                    return wind_sc;
                }

                public void setWind_sc(String wind_sc) {
                    this.wind_sc = wind_sc;
                }

                public String getWind_dir() {
                    return wind_dir;
                }

                public void setWind_dir(String wind_dir) {
                    this.wind_dir = wind_dir;
                }

                public String getWind_spd() {
                    return wind_spd;
                }

                public void setWind_spd(String wind_spd) {
                    this.wind_spd = wind_spd;
                }

                public String getPop() {
                    return pop;
                }

                public void setPop(String pop) {
                    this.pop = pop;
                }

                public String getWind_deg() {
                    return wind_deg;
                }

                public void setWind_deg(String wind_deg) {
                    this.wind_deg = wind_deg;
                }

                public String getUv_index() {
                    return uv_index;
                }

                public void setUv_index(String uv_index) {
                    this.uv_index = uv_index;
                }

                public String getTmp_max() {
                    return tmp_max;
                }

                public void setTmp_max(String tmp_max) {
                    this.tmp_max = tmp_max;
                }

                public String getCond_txt_n() {
                    return cond_txt_n;
                }

                public void setCond_txt_n(String cond_txt_n) {
                    this.cond_txt_n = cond_txt_n;
                }

                public String getCond_code_d() {
                    return cond_code_d;
                }

                public void setCond_code_d(String cond_code_d) {
                    this.cond_code_d = cond_code_d;
                }
            }

            public static class LifestyleBean {
                /**
                 * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
                 * brf : 较舒适
                 * type : comf
                 */

                private String txt;
                private String brf;
                private String type;

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
