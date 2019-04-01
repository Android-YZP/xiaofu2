package cn.company1075.xiaofu.model.view;


import cn.company1075.xiaofu.utils.PinYinUtils;

/**
 * Created by liutianxu on 17/2/21.
 */

public class Selectcity {

    private String province;

    private String pinyin;

    public Selectcity(String province){
        this.province = province;
        this.pinyin = PinYinUtils.getPinYin(province);
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Selectcity{" +
                "province='" + province + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
