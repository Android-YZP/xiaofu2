package cn.company1075.xiaofu.view.ui_controls;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.view.title.Title_Weather;

/**
 * Created by liutianxu on 17/2/15.
 */

public class MyLayout2 extends RelativeLayout {

    //小悬浮窗的参数
    private WindowManager windowManager;
    public RelativeLayout.LayoutParams mParams;
    public Title_Info iPosition;
    View mLayoutView;


    public MyLayout2(Context context) {
        super(context);

    }

    public MyLayout2(final Context context, int viewInt, Title_Info position) {
        super(context);
        iPosition = position;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        mLayoutView = LayoutInflater.from(context).inflate(viewInt, this);
        mParams = new RelativeLayout.LayoutParams(iPosition.getTakeX(), iPosition.getTakeY());
        mParams.topMargin = iPosition.getBeginX();
        mParams.leftMargin = iPosition.getBeginY();

    }

    /**
     * 更新小悬浮窗在屏幕中的位置。
     */
    public void updateViewPosition(int y) {
        mParams.topMargin = y;
        setLayoutParams(mParams);
    }

    /**
     * 更新小悬浮窗在屏幕中的位置。
     */
    public void updateViewPosition(int leftX, int leftY) {
//        mParams.leftMargin = leftX;
        mParams.topMargin = leftY;
        setLayoutParams(mParams);
    }

    /**
     * 打开大悬浮窗，同时关闭小悬浮窗。
     */
    public void openBigWindow() {

    }

}
