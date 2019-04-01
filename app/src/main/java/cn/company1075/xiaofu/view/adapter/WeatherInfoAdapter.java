package cn.company1075.xiaofu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.DayWeather;
import cn.company1075.xiaofu.model.view.DayWeather2;
import cn.company1075.xiaofu.model.view.WeatherType;
import cn.company1075.xiaofu.utils.StringUtils;
import cn.company1075.xiaofu.utils.WeatherNeW;


public class WeatherInfoAdapter extends BaseAdapter {

    private List<WeatherNeW.DataBean.HeWeather6Bean.DailyForecastBean> mlistWeatherInfo = null;

    LayoutInflater infater = null;

    public WeatherInfoAdapter(Context context, List<WeatherNeW.DataBean.HeWeather6Bean.DailyForecastBean> dayWeathers) {
        infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mlistWeatherInfo = dayWeathers;
    }

    public void setData( List<WeatherNeW.DataBean.HeWeather6Bean.DailyForecastBean> dayWeathers){
        mlistWeatherInfo = dayWeathers;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mlistWeatherInfo.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mlistWeatherInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup arg2) {
        View view = null;
        ViewWeather holder = null;
        if (convertview == null || convertview.getTag() == null) {
            view = infater.inflate(R.layout.item_weather, null);
            holder = new ViewWeather(view);
            view.setTag(holder);
        } else {
            view = convertview;
            holder = (ViewWeather) convertview.getTag();
        }
        if (position < 5) {
            WeatherNeW.DataBean.HeWeather6Bean.DailyForecastBean    day = mlistWeatherInfo.get(position);
           // String DAY = day.getDate();
          //  String Day = DAY.substring(DAY.length() - 3, DAY.length());
            holder.day.setText(StringUtils.getWeek(day.getDate()));
            holder.typeIcon.setImageResource(WeatherType.getIndex(day.getCond_txt_d()));
            holder.wendu.setText(day.getTmp_min() + "        " + day.getTmp_max());
        }
        return view;
    }

    class ViewWeather {
        TextView day;
        ImageView typeIcon;
        TextView wendu;

        public ViewWeather(View view) {
            this.day = (TextView) view.findViewById(R.id.weather_day);
            this.typeIcon = (ImageView) view.findViewById(R.id.weather_type);
            this.wendu = (TextView) view.findViewById(R.id.weather_wendu);
        }
    }
}