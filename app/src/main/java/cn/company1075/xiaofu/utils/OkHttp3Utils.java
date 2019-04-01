package cn.company1075.xiaofu.utils;


import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Cxy on 2018/8/1.
 */

public class OkHttp3Utils {
    private static final String TAG = "OkHttp3Utlis";

    private OkHttpClient client;
    private static OkHttp3Utils okHttp3Utlis;
    public Gson gson;

    public OkHttp3Utils() {
        //创建真正的操作类OkHttpClient
        if (client == null) {
            client = new OkHttpClient
                    .Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
            gson = new Gson();
        }
    }

    /**
     * 单列模式 通过方法获得对象,在别的方法实咧化 可以调用。
     */
    public static OkHttp3Utils getIntance() {
        //双重判断+ 同步线程锁
        if (okHttp3Utlis == null) {
            synchronized (OkHttp3Utils.class) {
                if (okHttp3Utlis == null)
                    okHttp3Utlis = new OkHttp3Utils();
            }
        }
        return okHttp3Utlis;
    }


    /**
     * get拼接传值方法
     *
     * @param url ?username=xxxx&password=xxx
     */
    public void getOkHttp(String url, Map<String, Integer> map, Callback callback) {
        StringBuffer sb = new StringBuffer();
        String string = "";
        String result = "";
        //当用户传入null或者传了一个空的map
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (sb == null) {
//                    sb = new StringBuffer ();
                    sb.append("?");
                } else {
                    //拼接好的网站去掉最后一个“&”符号
                    sb.append("&");
                }
                sb.append(entry.getKey() + "=" + entry.getValue());
            }
        }
        if (sb.toString() != null) {
            string = sb.toString();
            Log.e("", string);
            result = url + string;
            Log.e("", result);
        }
        Request request = new Request.Builder()
                .get() //声明我是get请求,如果不写默认就是get
                .url(string == "" ? url : result)//声明网站访问的网址
                .build();//创建Request
        Call call = client.newCall(request);
        //同步execute,异步enqueue
        //这里的同步是耗时的
        //而且OK 也没有为我们开启子线程‘
        // 如果你用同步方法的话，需要开启子线程
        call.enqueue(callback);
    }


    /**
     *
     * Post请求
     *
     * FormBody:用来提交一个不包涵文件的参数
     * 第
     */
    public void postOkhttp(String url, Map<String, String> map, Callback callBack) {
        //上传文字格式 数据的传输，区别于多媒体输出
        FormBody.Builder formbody = new FormBody.Builder();
        if (map != null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                formbody.add(key, map.get(key));
            }
            //创建请求体
            FormBody body = formbody.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)//请求体
                    .build();
            Call call = client.newCall(request);
            //异步请求方式
            call.enqueue(callBack);
        } else {
            //创建请求体
            FormBody body = formbody.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            //异步请求方式
            call.enqueue(callBack);
        }
    }


    /**
     * MultipartBody：用来提交包涵文件的参数
     *
     * @param path    :路径
     * @param map     ：普通参数
     * @param img     ：提交文件的关键字
     * @param imgPath ：提交文件的路径
     */
    public void postFileOkhttp(String path, HashMap<String, String> map, String img, String imgPath, Callback callBack) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        if (map!=null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                requestBody.addFormDataPart(key, map.get(key));
            }
            File file = new File(imgPath);
            requestBody.addFormDataPart(img, file.getPath()
                    , RequestBody.create(MediaType.parse("image/png"), file));
            Request request = new Request.Builder()
                    .post(requestBody.build())
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callBack);
        }else{
            File file = new File(imgPath);
            requestBody.addFormDataPart(img, file.getPath()
                    , RequestBody.create(MediaType.parse("image/png"), file));
            Request request = new Request.Builder()
                    .post(requestBody.build())
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callBack);
        }
    }

//    /**
//     * 获取JavaBean工具类
//     */
//    public void postBean(String url, Map<String, String> map, ImplCustom impl) {
//        postOkhttp(url, map, impl);
//    }
//
//    public void getBean(String url, Map<String, String> map, ImplCustom impl) {
//        getOkHttp(url, map, impl);
//    }
}
