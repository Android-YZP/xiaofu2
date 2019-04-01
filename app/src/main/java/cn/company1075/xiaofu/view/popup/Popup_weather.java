package cn.company1075.xiaofu.view.popup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.model.view.DayWeather;
import cn.company1075.xiaofu.model.view.DayWeather2;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.model.view.WeatherInfo;
import cn.company1075.xiaofu.model.view.WeatherType;
import cn.company1075.xiaofu.utils.MyListView;
import cn.company1075.xiaofu.utils.WeatherNeW;
import cn.company1075.xiaofu.view.adapter.WeatherInfoAdapter;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutMovable;


/**
 * Created by Administrator on 2017/1/15.
 */
public class Popup_weather extends MyLayoutMovable implements View.OnClickListener{
    private List<DayWeather> mlistWeatherInfo;
    private ListView listView;
    private TextView city_click;
    private Context context;
    private Button close;
    private Title_Info position;
    private WeatherNeW dayWeather2;

    TextView wendu,fengxiang,t,today_min_max;
    ImageView today_weather_type;
    WeatherInfoAdapter weatherInfoAdapter;


    public Popup_weather(Context context, Title_Info position, String type, WeatherNeW dayWeather2) {
        super(context, R.layout.popup_weather, position, type);
        this.context = context;
        this.position = position;
        this.dayWeather2 = dayWeather2;
        city_click = (TextView) findViewById(R.id.city_weather);
        close = (Button) findViewById(R.id.close_layout);
        city_click.setOnClickListener(this);
        close.setOnClickListener(this);
        initView();
        initWeatherInfo(dayWeather2);
    }

    private void initView() {
        wendu = (TextView) findViewById(R.id.weather_wendu);
         fengxiang = (TextView) findViewById(R.id.wind);
         t = (TextView) findViewById(R.id.weather_t);
         today_min_max = (TextView) findViewById(R.id.today_min_max);
         today_weather_type = (ImageView) findViewById(R.id.today_weather_type);
    }

        public void initWeatherInfo( WeatherNeW dayWeather2) {

        if (null != dayWeather2){
            t.setText(dayWeather2.getData().getHeWeather6().get(0).getDaily_forecast().get(0).getCond_txt_d());
            wendu.setText(dayWeather2.getData().getHeWeather6().get(0).getNow().getTmp()+"° ");
            fengxiang.setText(dayWeather2.getData().getHeWeather6().get(0).getNow().getWind_dir()+" "+dayWeather2.getData().getHeWeather6().get(0).getNow().getWind_sc()+"级");
            today_min_max.setText(dayWeather2.getData().getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_min()+ "° "  + "  /  " + dayWeather2.getData().getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_max()+"°");
            today_weather_type.setImageResource(WeatherType.getIndex(dayWeather2.getData().getHeWeather6().get(0).getNow().getCond_txt()));
            city_click.setText(dayWeather2.getData().getHeWeather6().get(0).getBasic().getLocation());
        }

        listView = (ListView) findViewById(R.id.weather_list);
        listView.setDivider(null);
        listView.setEnabled(false);
        listView.setCacheColorHint(0);
        listView.setBackgroundColor(Color.rgb(0,0,0));
        if (null == weatherInfoAdapter){
            weatherInfoAdapter    = new WeatherInfoAdapter(context,dayWeather2.getData().getHeWeather6().get(0).getDaily_forecast());
            listView.setAdapter(weatherInfoAdapter);
        }else {
            weatherInfoAdapter.setData(dayWeather2.getData().getHeWeather6().get(0).getDaily_forecast());
        }

    }

//    public void initWeatherInfo(Context context, WeatherInfo weatherInfo) {
//        if (weatherInfo != null) {
//            mlistWeatherInfo = weatherInfo.getWeather();
//            listView = (ListView) findViewById(R.id.weather_list);
//            //去除listview分割线
//            listView.setDivider(null);
//            //使listview item不能点击
//            listView.setEnabled(false);
//            listView.setCacheColorHint(0);
//            listView.setBackgroundColor(Color.rgb(0,0,0));
//            WeatherInfoAdapter browseAppAdapter = new WeatherInfoAdapter(context, mlistWeatherInfo);
//            listView.setAdapter(browseAppAdapter);
//            //显示温度信息
//            TextView wendu = (TextView) findViewById(R.id.weather_wendu);
//            TextView fengxiang = (TextView) findViewById(R.id.wind);
//            TextView t = (TextView) findViewById(R.id.weather_t);
//            TextView today_min_max = (TextView) findViewById(R.id.today_min_max);
//            ImageView today_weather_type = (ImageView) findViewById(R.id.today_weather_type);
////        weather_relative.setBackgroundColor(Color.rgb(16,21,25));
//            if (weatherInfo != null && weatherInfo.weather.size()>0) {
//                city_click.setText(weatherInfo.getCity());
//                wendu.setText(weatherInfo.getWendu() + "°");
//                t.setText(weatherInfo.getDayWeather(0).getType());
//                today_min_max.setText(weatherInfo.getDayWeather(0).getLow().split("℃")[0] + "  /  " + weatherInfo.getDayWeather(0).getHigh().split("℃")[0]);
//                today_weather_type.setImageResource(WeatherType.getIndex(weatherInfo.getDayWeather(0).getType()));
//                fengxiang.setText(weatherInfo.getDayWeather(0).fengxiang);
//            }
//        }
//    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.city_weather:
                context.startActivity(new Intent(context, SelectCity.class));
                city_click.setEnabled(false);
                break;
            case R.id.close_layout:
                AllUser.close_layout(this,position);
                break;
        }
    }
}
