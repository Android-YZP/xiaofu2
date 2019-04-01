package cn.company1075.xiaofu.utils;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/1/11.
 */
public class LogInfo {
    public static boolean isDebug = true;

    public static void i(String TAG, String info) {
        if (isDebug)
            Log.i(TAG, info);
    }

    public static void i(String TAG, int info) {
        if (isDebug)
            Log.i(TAG, String.valueOf(info));
    }

    public static void i(String TAG, boolean info) {
        if (isDebug)
            Log.i(TAG, String.valueOf(info));
    }

    public static void i(String TAG, Object o, String className) {
        if (isDebug) {
            Class<?> c = null;
            try {
                c = Class.forName(className);
                Field[] fields = c.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                }
                for (Field f : fields) {
                    String field = f.toString().substring(f.toString().lastIndexOf(".") + 1);            //取出属性名称
                    try {
                        Log.i(TAG, field + " --> " + f.get(o));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
