package cn.company1075.xiaofu.model.view;


import cn.company1075.xiaofu.R;

/**
 * Created by Administrator on 2017/1/13.
 */
public enum WeatherType {
    weather1("晴", R.mipmap.sunny), weather2("晴转多云", R.mipmap.cloudy_sunny),
    weather3("阴", R.mipmap.cloudy), weather4("多云", R.mipmap.overcast),
    weather5("小雨", R.mipmap.light_rain), weather6("中雨", R.mipmap.moderate_rain),
    weather7("大雨", R.mipmap.heavy_rain), weather8("暴雨", R.mipmap.torrential_rain),
    weather9("小雪", R.mipmap.light_snow), weather10("中雪", R.mipmap.moderate_snow),
    weather11("大雪", R.mipmap.heavy_snow),weather12("暴雪", R.mipmap.torrential_snow),
    weather13("雷阵雨", R.mipmap.thunder_shower),weather14("冻雨", R.mipmap.freezomh_rain),
    weather15("冰雹", R.mipmap.hail_rain),weather16("雨夹雪", R.mipmap.snow_and_rain),
    weather17("霜冻", R.mipmap.frost),weather18("强风", R.mipmap .strong_wind),
    weather19("疾风", R.mipmap.near_gale),weather20("大到飓风", R.mipmap.severe_wind),
    weather21("台风", R.mipmap.typhoon),weather22("浮沉", R.mipmap.floating_dust),
    weather23("扬沙", R.mipmap.dust_blowing),weather24("沙尘暴", R.mipmap.dust_devil),
    weather25("雾", R.mipmap.fog),weather26("强沙尘暴", R.mipmap.severe_dust_devil),
    weather27("阵雨", R.mipmap.light_rain);
    // 成员变量
    private String name;
    private int index;

    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private WeatherType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (WeatherType c : WeatherType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // 普通方法
    public static int getIndex(String name) {
        for (WeatherType c : WeatherType.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return R.mipmap.sunny;
    }


    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

