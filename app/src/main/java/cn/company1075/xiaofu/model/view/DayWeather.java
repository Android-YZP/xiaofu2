package cn.company1075.xiaofu.model.view;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/13.
 */
public class DayWeather {
    //风向
    public String fengxiang;
    //风力
    public String fengli;
    //最高温度
    public String high;
    //天气状态
    public String type;
    //最低温度
    public String low;
    //日期
    public String date;

    public DayWeather(JSONObject info) {
        try {
            fengxiang = info.getString("fengxiang");
            fengli = info.getString("fengli");
            high = info.getString("high").substring(3);
            type = info.getString("type");
            low = info.getString("low").substring(3);
            date = info.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getFengxiang() {
        return fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public String getHigh() {
        return high;
    }

    public String getType() {
        return type;
    }

    public String getLow() {
        return low;
    }

    public String getDate() {
        return date;
    }
}
