package cn.company1075.xiaofu.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.Selectcity;


/**
 * Created by liutianxu on 17/2/21.
 */

public class IndexAdapter extends BaseAdapter{

    Context mContext;
    List<Selectcity> selectcityList;
    Activity activity;
    public IndexAdapter(Context context, List<Selectcity> selectcityList, Activity activity){
        this.mContext = context;
        this.selectcityList = selectcityList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return selectcityList.size();
    }

    @Override
    public Object getItem(int position) {
        return selectcityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null){
            convertView = View.inflate(mContext, R.layout.province_item,null);
            viewHolder = new ViewHolder(viewHolder);
            viewHolder.province_word = (TextView) convertView.findViewById(R.id.province_word);
            viewHolder.province_name = (TextView) convertView.findViewById(R.id.province_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String name = selectcityList.get(position).getProvince();
        String word = selectcityList.get(position).getPinyin().substring(0,1);//AFU->A
        viewHolder.province_word.setText(word);
        viewHolder.province_name.setText(name);
        if(position ==0){
            viewHolder.province_word.setVisibility(View.VISIBLE);
        }else{
            //得到前一个位置对应的字母，如果当前的字母和上一个相同，隐藏；否则就显示
            String preWord = selectcityList.get(position-1).getPinyin().substring(0,1);//A~Z
            if(word.equals(preWord)){
                viewHolder.province_word.setVisibility(View.GONE);
            }else{
                viewHolder.province_word.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.province_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, selectcityList.get(position).getProvince(), Toast.LENGTH_SHORT).show();
//                WeatherLayout weatherlayout = MainFragment1.weatherLayout;
//                weatherlayout.initWeatherData(selectcityList.get(position).getProvince());
//                SharedPreferences sharedPreferences_city = mContext.getSharedPreferences("city",mContext.MODE_PRIVATE);
//                SharedPreferences.Editor editor=sharedPreferences_city.edit();
//                editor.putString("cityname", selectcityList.get(position).getProvince());
//                editor.commit();
//                WeatherLayout.cityname.setText("  "+selectcityList.get(position).getProvince());
                activity.finish();
            }
        });



        return convertView;
    }

    static class ViewHolder {
        TextView province_word;
        TextView province_name;

        public ViewHolder(ViewHolder viewHolder) {
        }
    }

}

