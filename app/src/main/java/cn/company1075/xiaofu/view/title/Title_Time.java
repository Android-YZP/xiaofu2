package cn.company1075.xiaofu.view.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.DayWeather;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.model.view.WeatherInfo;
import cn.company1075.xiaofu.model.view.WeatherType;
import cn.company1075.xiaofu.view.popup.SelectCity;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;


/**
 * Created by Administrator on 2017/1/10.
 */
public class Title_Time extends MyLayoutUnMovable implements View.OnClickListener{

    private static final String TAG = "Title_Time";

    private Context context;

    public Title_Time(Context context, Title_Info position) {
        super(context, R.layout.title_time, position);
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_time:

                break;
        }
    }

}
