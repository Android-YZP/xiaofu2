package cn.company1075.xiaofu.utils.xiaofu;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2018/7/27.
 */

public class FileBytes {

    public static byte[] imageBytes(String imgSrc)
    {
        FileInputStream fin;
        byte[] bytes = null;
        try {
            fin = new FileInputStream(FileUtils.getFileByPath(imgSrc));
            bytes  = new byte[fin.available()];
            //将文件内容写入字节数组
            fin.read(bytes);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
