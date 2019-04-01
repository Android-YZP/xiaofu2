package cn.company1075.xiaofu.config;

import android.app.Application;

import com.tencent.bugly.Bugly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.utils.SPUtils;
import cn.company1075.xiaofu.utils.xiaofu.SDCardUtils;
import xiaofu.xflibrary.ble.XFBleHelper;


/**
 * Created by Administrator on 2018/7/24.
 */

public class Config_App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XFBleHelper.Init(this, SDCardUtils.getSDPath() + "ZhiHuFu");
        Bugly.init(getApplicationContext(), "a5e6c77ecb", false);
        initUser();
        // String  Mac = MacAdressUtils.getMac(getApplicationContext());
    }





    //1为游客账号
    private void initUser() {
        if ( null ==  SPUtils.getUser(getApplicationContext(),"User" )){
            User.getInstance().shopid = 1;
            User.getInstance().name = "1";
            User.getInstance().pwd = "1";
           // 1为游客账号

        }else {
            User user  = (User) SPUtils.getUser(getApplicationContext(),"User" );
           // User.getInstance().shopid = 8;
             User.getInstance().shopid = user.shopid;
            User.getInstance().name = user.name;
            User.getInstance().pwd = user.pwd;
        }

    }





}
