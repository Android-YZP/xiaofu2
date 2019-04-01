package cn.company1075.xiaofu.model.view;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/13.
 */
public class WeatherInfo {
    public ArrayList<DayWeather> weather;
    public String wendu;
    public String ganmao;
    public String city;
//    public String aqi;

    public WeatherInfo(JSONObject info) {
        weather = new ArrayList<>();
        try {
            wendu = info.getString("wendu");
            ganmao = info.getString("ganmao");
            city = info.getString("city");
//            aqi = info.getString("aqi");
            JSONArray ob = info.getJSONArray("forecast");
            for (int i = 0; i < ob.length(); i++) {
                DayWeather day = new DayWeather((JSONObject) ob.get(i));
                weather.add(day);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public DayWeather getDayWeather(int i) {
        return weather.get(i);
    }

    public ArrayList<DayWeather> getWeather() {
        return weather;
    }

    public String getWendu() {
        return wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public String getCity() {
        return city;
    }

//    public String getAqi() {
//        return aqi;
//    }

}
