package cn.company1075.xiaofu.view.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.guide.GuideView;
import cn.company1075.xiaofu.model.view.Pop_Info;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.view.popup.Popup_Setting;
import cn.company1075.xiaofu.view.popup.Popup_weather;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;


/**
 * Created by Administrator on 2017/1/10.
 */
public class Title_Setting extends MyLayoutUnMovable implements View.OnClickListener{

    private static final String TAG = "Title_Time";

    private Context context;

    public Popup_Setting popup_setting;

    public RelativeLayout title_setting;

    @SuppressLint("WrongViewCast")
    public Title_Setting(Context context, Title_Info position) {
        super(context, R.layout.title_setting, position);
        this.context = context;
        title_setting = findViewById(R.id.title_setting);
        title_setting.setOnClickListener(this);

        //添加至主界面中
        Pop_Info pop_info = new Pop_Info(22,0, 20, 28);
        popup_setting = new Popup_Setting(context, pop_info, "setting_pop");
        AllUser.parent.addView(popup_setting,popup_setting.mParams);
        popup_setting.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_setting:
                AllUser.initPopup_layout(popup_setting);
                break;
        }
    }

}
