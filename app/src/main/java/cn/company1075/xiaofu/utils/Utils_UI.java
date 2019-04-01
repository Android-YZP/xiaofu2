package cn.company1075.xiaofu.utils;

import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/1/12.
 */
public class Utils_UI {
    private static String TAG = "Utils_UI";
    private static int statusBarHeight;


    //判断是否包含在layout内
    public static boolean isInclude(View view, float x, float y) {
        y = y + getStatusBarHeight(view);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int xView = location[0];
        int yView = location[1];
        double minX = xView;
        double maxX = xView + view.getWidth();
        double minY = yView;
        double maxY = yView + view.getHeight();
//        LogInfo.i(TAG, "x =" + x + " y =" + y + " minX =" + minX + " maxX =" + maxX + " miny =" + minY + " maxY =" + maxY);
        if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(View view) {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = view.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }


    //判断是否包含在可点击范围内
    public static boolean isIncludeTouch(int x, int y, int beganX, int beganY) {
        int canTouch = 5;
        double minX = beganX - canTouch;
        double maxX = beganX + canTouch;
        double minY = beganY - canTouch;
        double maxY = beganY + canTouch;
        LogInfo.i(TAG, "x =" + x + " y =" + y + " minX =" + minX + " maxX =" + maxX + " miny =" + minY + " maxY =" + maxY);
        if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
            return true;
        } else {
            return false;
        }
    }

}
