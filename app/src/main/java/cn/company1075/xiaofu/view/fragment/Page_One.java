package cn.company1075.xiaofu.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.baseinfo.View_title;
import cn.company1075.xiaofu.guide.GuideView;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.view.title.Title_Product;
import cn.company1075.xiaofu.view.title.Title_Setting;
import cn.company1075.xiaofu.view.title.Title_Time;
import cn.company1075.xiaofu.view.title.Title_Weather;
import cn.company1075.xiaofu.view.title.Title_Xiaofu;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;

/**
 * Created by Administrator on 2018/7/24.
 */

public class Page_One{
    private final String TAG = "Page_One";
    private Context context;
    private int width;
    private int height;

    private List<MyLayoutUnMovable> views;
    private static Page_One Page_OneInstance;


    public static synchronized Page_One getInstance(Context context){
        if (null==Page_OneInstance){
            Page_OneInstance = new Page_One(context);
        }
        return Page_OneInstance;
    }



    private Page_One(Context context) {
        this.context = context;
        views=new ArrayList<>();
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        View_title view_title = new View_title(width, height);
        //赋值统一属性
        AllUser.spaceY = view_title.getSpace_height();
        AllUser.spaceX = view_title.getSpace_width();
        for (Title_Info title_info : view_title.getTitle_infos()) {
            switch (title_info.getName()) {
                case TITLE_TIME:
                    Title_Time title_time = new Title_Time(context,title_info);
                    views.add(title_time);
                    break;
                case TITLE_WEATHER:
                    Title_Weather title_weather = new Title_Weather(context,title_info);
                    views.add(title_weather);
                    break;
                case TITLE_XIAOFU:
                    Title_Xiaofu title_xiaofu = new Title_Xiaofu(context,title_info);
                    views.add(title_xiaofu);
                    break;
                case TITLE_PRODUCT:
                    Title_Product title_product = new Title_Product(context,title_info);
                    views.add(title_product);
                    break;
                case TITLE_SETTING:
                    Title_Setting title_setting = new Title_Setting(context,title_info);
                    views.add(title_setting);
                    break;

            }

        }
       // showGuide(context);
    }

    private void showGuide(Context context ) {

        final GuideView.Builder builder = new GuideView.Builder(context, "1.0",true);

        Title_Xiaofu title_xiaofu = (Title_Xiaofu) views.get(2);
        builder.addHintView(title_xiaofu.title_xiaofu, R.mipmap.g1, GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR, 0, 0, new GuideView.OnClickCallback() {
            @Override
            public void onGuideViewClicked() {
                builder.showNext();
            }
        });
        Title_Setting title_setting = (Title_Setting) views.get(4);
        builder.addHintView(title_setting.title_setting, R.mipmap.g2, GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR, 0, 0, new GuideView.OnClickCallback() {
            @Override
            public void onGuideViewClicked() {
                builder.showNext();
            }
        });
        builder.show();

    }

    public List<MyLayoutUnMovable> getViews() {
        return views;
    }
}
