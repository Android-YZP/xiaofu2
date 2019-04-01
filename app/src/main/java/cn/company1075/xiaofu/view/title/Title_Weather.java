package cn.company1075.xiaofu.view.title;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.config.CityInfo;
import cn.company1075.xiaofu.model.view.DayWeather2;
import cn.company1075.xiaofu.model.view.Pop_Info;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.model.view.WeatherInfo;
import cn.company1075.xiaofu.model.view.WeatherType;
import cn.company1075.xiaofu.utils.CharacterParser;
import cn.company1075.xiaofu.utils.GsonUtils;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import cn.company1075.xiaofu.utils.PinyinComparator;
import cn.company1075.xiaofu.utils.PopUtils2;
import cn.company1075.xiaofu.utils.SideBar;
import cn.company1075.xiaofu.utils.SortModel;
import cn.company1075.xiaofu.utils.StringUtils;
import cn.company1075.xiaofu.utils.WeatherNeW;
import cn.company1075.xiaofu.view.adapter.SortAdapter;
import cn.company1075.xiaofu.view.popup.Popup_weather;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;


/**
 * Created by Administrator on 2017/1/10.
 */
public class Title_Weather extends MyLayoutUnMovable implements View.OnClickListener {

    List<CityInfo> cityInfoList = new ArrayList<>();
    private static final String TAG = "Title_Weather";

    public TextView pm_value;
    public TextView pm_pin;

    private Context context;
    public LinearLayout weatherlayout;
    private TextView today_wendu;
    private TextView today_max_min;
    private TextView tomorrow_week;
    private TextView tomorrow_max_min;
    public Button cityname;
    private ImageView status;
    private ImageView tomorrow_status;
    public WeatherInfo weatherInfo;
    public Popup_weather popup_weather_layout = null;
    private int width;
    private int height;






    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private PinyinComparator pinyinComparator;

    public Title_Weather(Context context,Title_Info position) {
        super(context,R.layout.title_weather,position);
        this.context = context;
        weatherlayout = (LinearLayout) findViewById(R.id.weatherlayout);
        today_wendu = (TextView) findViewById(R.id.today_wendu);
        tomorrow_week = (TextView) findViewById(R.id.tomorrow_week);
        cityname = (Button) findViewById(R.id.cityname);
        today_max_min = (TextView) findViewById(R.id.today_max_min);
        tomorrow_max_min = (TextView) findViewById(R.id.tomorrow_min_max);
        status = (ImageView) findViewById(R.id.status);
        tomorrow_status = (ImageView) findViewById(R.id.tomorrow_status);
        cityname.setOnClickListener(this);
        weatherlayout.setOnClickListener(this);
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;


        Drawable drawable = getResources().getDrawable(R.drawable.top_wea_1);
        drawable.setBounds(0, 0, 24, 30);
        cityname.setCompoundDrawables(drawable,null,null,null);


        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initWeatherData();

    }


    /**
     * 初始化数据
     */
    private void initData() throws IOException {

        InputStream inputStream = getResources().getAssets().open("China");
        cityInfoList = getString(inputStream);

//        //排序
        Collections.sort(cityInfoList, new Comparator<CityInfo>() {
            @Override
            public int compare(CityInfo lhs, CityInfo rhs) {
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }


    public List<CityInfo> getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
                String cityCode = "";
                String cityName = "";
                cityCode = line.split("\t")[0]; //按tab分割
                cityName = line.split("\t")[2];
                CityInfo cityInfo = new CityInfo(cityName);
                cityInfo.setCityCode(cityCode);
                cityInfo.setCityName(cityName);
                cityInfoList.add(cityInfo);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityInfoList;
    }





    private void initWeatherData() {
        String citycode = null;
        for (CityInfo info : cityInfoList){
            if (info.getCityName().equals(cityname.getText().toString().trim())){
                citycode  = info.getCityCode();
            }
        }
        String weatherUrl = User.URLWeather+"/weather/get?citycode=" + citycode;

        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.getJsonStringByURL(weatherUrl, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {

                WeatherNeW weather2 =  GsonUtils.changeGsonToBean(result, WeatherNeW.class);
                today_wendu.setText(weather2.getData().getHeWeather6().get(0).getNow().getTmp()+"°");
                status.setImageResource(WeatherType.getIndex(weather2.getData().getHeWeather6().get(0).getNow().getCond_txt()));
                today_max_min.setText(weather2.getData().getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_min()+"° "  + "  /  "+weather2.getData().getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_max()+"° ");
                tomorrow_week.setText(StringUtils.getWeek(weather2.getData().getHeWeather6().get(0).getDaily_forecast().get(0).getDate()));
                tomorrow_max_min.setText(weather2.getData().getHeWeather6().get(0).getDaily_forecast().get(1).getTmp_min()+"° "  + "  /  "+weather2.getData().getHeWeather6().get(0).getDaily_forecast().get(1).getTmp_max()+"° ");
                tomorrow_status.setImageResource(WeatherType.getIndex(weather2.getData().getHeWeather6().get(0).getDaily_forecast().get(1).getCond_txt_d()));
                Pop_Info pop_info = new Pop_Info(22,0, 20, 28);
                if (null == popup_weather_layout){
                    popup_weather_layout = new Popup_weather(context, pop_info, "weather_pop",weather2);
                    AllUser.parent.addView(popup_weather_layout,popup_weather_layout.mParams);
                    popup_weather_layout.setVisibility(View.GONE);
                }else {
                  popup_weather_layout.initWeatherInfo(weather2);

                }

//                today_wendu.setText( weather2.getData().getWendu()+"°");
//                status.setImageResource(WeatherType.getIndex(weather2.getData().getForecast().get(0).getType()));
//                today_max_min.setText(weather2.getData().getForecast().get(0).getLow().split("℃")[0] .split("低温")[1]+ "° ~ " +weather2.getData().getForecast().get(0).getHigh().split("℃")[0].split("高温")[1]+"°");
//                tomorrow_week.setText(weather2.getData().getForecast().get(1).getDate().split("日")[1]);
//                tomorrow_max_min.setText(weather2.getData().getForecast().get(1).getLow().split("℃")[0].split("低温")[1] + "° ~ " + weather2.getData().getForecast()
//                        .get(1).getHigh().split("℃")[0].split("高温")[1]+"°");
//                tomorrow_status.setImageResource(WeatherType.getIndex(weather2.getData().getForecast().get(1).getType()));
//
//                //添加至主界面中
//                Pop_Info pop_info = new Pop_Info(22,0, 20, 28);
//                if (null == popup_weather_layout){
//                    popup_weather_layout = new Popup_weather(context, pop_info, "weather_pop",weather2);
//                    AllUser.parent.addView(popup_weather_layout,popup_weather_layout.mParams);
//                    popup_weather_layout.setVisibility(View.GONE);
//                }else {
//                    popup_weather_layout.initWeatherInfo(weather2);
//
//                }

            }
        });
    }

//    public void initWeatherData(final String cityName) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String cityCode = LocationCode.CHINESE_LOCAL_CODE.get(cityName);
//                    String weatherUrl = "http://116.62.218.54:8080/weather?cityCode="
//                            + cityCode;
//                    weatherJson = HttpGet.queryStringForGet(weatherUrl);
////                    LogInfo.i(TAG, weatherJson.toString());
//                    if (weatherJson != null) {
//                        JSONObject jsonObject = new JSONObject(weatherJson);
//                        //获取对应的值
//                        JSONObject weatherObject = jsonObject.getJSONObject("data");
//                        Message message = new Message();
//                        message.what = 0;
//                        message.obj = weatherObject;
//                        handler.sendMessage(message);
//
//                    } else {
//                        Message message = new Message();
//                        message.what = 1;
//                        handler.sendEmptyMessage(1);
//                    }
//
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }

//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    JSONObject object = (JSONObject) msg.obj;
//                    try {
//                        weatherInfo = new WeatherInfo(object);
//                        DayWeather day = weatherInfo.getWeather().get(0);
//                        DayWeather day2 = weatherInfo.getWeather().get(1);
//                        today_wendu.setText(weatherInfo.getWendu()+"°");
//                        today_max_min.setText(day.getLow().split("℃")[0] + "° ~ " + day.getHigh().split("℃")[0]+"°");
//                        status.setImageResource(WeatherType.getIndex(day.getType()));
//                        tomorrow_week.setText(day2.date.split("日")[1]);
//                        tomorrow_max_min.setText(day2.getLow().split("℃")[0] + "° ~ " + day2.getHigh().split("℃")[0]+"°");
//                        tomorrow_status.setImageResource(WeatherType.getIndex(day2.getType()));
//                    } catch (Exception e) {
//
//                        e.printStackTrace();
//                    }
//                    break;
//                case 1:
//                    Timer t = new Timer();
//                    task tt = new task();
//                    t.schedule(tt, 10000);//5000单位是毫秒=5秒
//                    break;
//            }
//
//        }
//    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weatherlayout:
                if (null != popup_weather_layout){
                    AllUser.initPopup_layout(popup_weather_layout);
                }

                break;
            case R.id.cityname:
                PopUtils2 popUtils = new PopUtils2(context, R.layout.activity_selectcity, width / 5, height / 2, cityname, Gravity.TOP|Gravity.LEFT, 300,80, new PopUtils2.ClickListener() {
                    @Override
                    public void setUplistener(PopUtils2.PopBuilder builder) {

                        characterParser = CharacterParser.getInstance();

                        pinyinComparator = new PinyinComparator();

                        sideBar = builder.getView(R.id.sidrbar);
                        dialog =  builder.getView(R.id.dialog);
                        sideBar.setTextView(dialog);

                        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

                            @Override
                            public void onTouchingLetterChanged(String s) {
                                //该字母首次出现的位置
                                int position = adapter.getPositionForSection(s.charAt(0));
                                if(position != -1){
                                    sortListView.setSelection(position);
                                }

                            }
                        });

                        sortListView = builder.getView(R.id.country_lvcountry);
                        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                cityname.setText(((SortModel)adapter.getItem(position)).getName());
                                PopUtils2.PopBuilder.getWindow().dismiss();
                                initWeatherData();
                            //    Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        String[] strs = new String[cityInfoList.size()];
                        for (int i = 0; i < cityInfoList.size(); i++) {
                            strs[i] =   cityInfoList.get(i).getCityName();
                        }

                        SourceDateList = filledData(strs);
                     //   SourceDateList = filledData(getResources().getStringArray(R.array.date));

                        // 根据a-z进行排序源数据
                        Collections.sort(SourceDateList, pinyinComparator);
                        adapter = new SortAdapter(context, SourceDateList);
                        sortListView.setAdapter(adapter);

                    }
                });
              //  context.startActivity(new Intent(context, CitySelectActivity.class));
                break;
        }
    }

    public void onDestroy() {
        if (weatherInfo != null) {
            weatherInfo = null;
        }
    }

    class task extends TimerTask {
        public void run() {
//            initWeatherData(MainActivity.cityname);
        }
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledData(String [] date){
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for(int i=0; i<date.length; i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }


    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
