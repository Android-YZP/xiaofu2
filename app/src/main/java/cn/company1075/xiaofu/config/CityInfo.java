package cn.company1075.xiaofu.config;

import cn.company1075.xiaofu.utils.PinYinUtils;

public class CityInfo {

    private String cityCode;
    private String cityName;
    private String pinyin;

    public CityInfo(String cityName){
        this.cityName = cityName;
        this.pinyin = PinYinUtils.getPinYin(cityName);
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "Selectcity{" +
                "province='" + cityName + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
