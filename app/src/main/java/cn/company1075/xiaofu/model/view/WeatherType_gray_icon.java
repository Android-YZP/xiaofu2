package cn.company1075.xiaofu.model.view;


import cn.company1075.xiaofu.R;

/**
 * Created by Administrator on 2017/1/13.
 */
public enum WeatherType_gray_icon {
    weather1("晴", R.mipmap.sunny_gray), weather2("晴转多云", R.mipmap.cloudy_sunny_gray),
    weather3("阴", R.mipmap.cloudy_gray), weather4("多云", R.mipmap.overcast_gray),
    weather5("小雨", R.mipmap.light_rain_gray), weather6("中雨", R.mipmap.moderate_rain_gray),
    weather7("大雨", R.mipmap.heavy_rain_gray), weather8("暴雨", R.mipmap.torrential_rain_gray),
    weather9("小雪", R.mipmap.light_snow_gray), weather10("中雪", R.mipmap.moderate_snow_gray),
    weather11("大雪", R.mipmap.heavy_snow_gray),weather12("暴雪", R.mipmap.torrential_snow_gray),
    weather13("雷阵雨", R.mipmap.thunder_shower_gray),weather14("冻雨", R.mipmap.freezomh_rain_gray),
    weather15("冰雹", R.mipmap.hail_rain_gray),weather16("雨夹雪", R.mipmap.snow_and_rain_gray),
    weather17("霜冻", R.mipmap.frost_gray),weather18("强风", R.mipmap .strong_wind_gray),
    weather19("疾风", R.mipmap.near_gale_gray),weather20("大到飓风", R.mipmap.severe_wind_gray),
    weather21("台风", R.mipmap.typhoon_gray),weather22("浮沉", R.mipmap.floating_dust_gray),
    weather23("扬沙", R.mipmap.dust_blowing_gray),weather24("沙尘暴", R.mipmap.dust_devil_gray),
    weather25("雾", R.mipmap.fog_gray),weather26("强沙尘暴", R.mipmap.severe_dust_devil_gray),
    weather27("阵雨", R.mipmap.light_rain_gray);
    // 成员变量
    private String name;
    private int index;

    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private WeatherType_gray_icon(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (WeatherType_gray_icon c : WeatherType_gray_icon.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // 普通方法
    public static int getIndex(String name) {
        for (WeatherType_gray_icon c : WeatherType_gray_icon.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return R.mipmap.sunny_gray;
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

