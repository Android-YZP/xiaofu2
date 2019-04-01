package cn.company1075.xiaofu.utils.xiaofu;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * SD卡相关的辅助类
 */
public class SDCardUtils {

    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     */
    @NonNull
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()  + File.separator;
    }

    @NonNull
    @SuppressLint("SdCardPath")
    public static String getSDPath() {
        if (isSDCardEnable()) {
            return getSDCardPath();
        } else {
            return "/data/data/blesdk/";
        }
    }



}
