//package cn.company1075.xiaofu.view;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.v4.util.ArrayMap;
//import android.support.v7.widget.AppCompatButton;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.blankj.utilcode.util.FileUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
//
//import cn.company1075.xiaofu.R;
//import cn.company1075.xiaofu.model.view.ShootPhoto;
//import cn.company1075.xiaofu.utils.LogInfo;
//import cn.company1075.xiaofu.utils.OkHttp3Utils;
//import cn.company1075.xiaofu.utils.xiaofu.FileBytes;
//import cn.company1075.xiaofu.utils.xiaofu.HttpUtils;
//import cn.company1075.xiaofu.utils.xiaofu.KeyBoardUtils;
//import cn.company1075.xiaofu.utils.xiaofu.StringUtils;
//import cn.company1075.xiaofu.utils.xiaofu.bean.ESHandler;
//import cn.company1075.xiaofu.utils.xiaofu.bean.HttpResult;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.Response;
//import xiaofu.xflibrary.ble.XFBleHelper;
//import xiaofu.xflibrary.camera.LedMode;
//import xiaofu.xflibrary.camera.XFCameraHelper;
//import xiaofu.xflibrary.camera.listener.XFCameraCaptureListener;
//import xiaofu.xflibrary.camera.listener.XFCameraHelperListener;
//import xiaofu.xflibrary.connect.ConnectShootUtils;
//import xiaofu.xflibrary.connect.listener.ConnectShootListener;
//
///**
// * Created by Administrator on 2018/7/26.
// */
//
//public class CameraActivity extends Activity {
//
//    private String TAG = "CameraActivity";
//    LinearLayout llView;
//    XFCameraHelper xfCameraHelper;
//    ConnectShootUtils connectShootUtils;
//    String wifiIp = "";
//
//
//    //Wifi
//    LinearLayout llPassword, llPasswordError, llPasswordConenct;
//    TextView tvPasswordName, tvPasswordCommit, tvPasswordError,part;
//    EditText etPassword;
//
//    ProgressDialog progressDialog;
//    private Context context;
//
//   // ArrayList<ShootPhoto> shootPhotoSingle = new ArrayList<>();//2单点检测
//   // ArrayList<ShootPhoto> shootPhotosAll;//1全脸检测 《这个数据怎么添加自己修改》
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        context = this;
//        llView = (LinearLayout) findViewById(R.id.ll_test_view);
//        printLog("onCreate");
//        //
//        xfCameraHelper = new XFCameraHelper(this, llView, false, new XFCameraHelperListener() {
//            @Override
//            public void onCameraStatus(boolean isOpen) {
//                printLog("onCameraStatus");
//                if (isDestroyed()) {
//                    return;
//                }
//                if (!isOpen) {//开启摄像头失败 （连接成功了 但是测试过程中  又断了 需要自己处理）
//                    //可以继续去打开
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            printLog("打开摄像头");
//                            xfCameraHelper.Start(wifiIp);
//                        }
//                    }, 3500l);
//
//                    //或者其他方法
//                } else {//开启摄像头成功
//                    //打开摄像头之后    镜头模式会被重置成表皮层模式
//
//                }
//            }
//
//        });
//        xfCameraHelper.setBitrate(200);
//        connectShootUtils = new ConnectShootUtils(this);
//
//        connectShootUtils.setListerner(new ConnectShootListener() {
//
//            /**
//             * 因为连接失败
//             * @param status -1蓝牙连接已断开 0 查询当前设备连接模式失败 1设置AP或Sta模式失败
//             */
//            @Override
//            public void onBackFinish(int status) {
//                if (xfCameraHelper.getOpenSuccess()) {
//                    return;
//                }
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.setOnDismissListener(null);
//                    progressDialog.dismiss();
//                }
//                printLog("onBackFinish");
//                finish();
//            }
//
//            @Override
//            public void is5G() {
//                printLog("is5G");
//            }
//
//            /**
//             * 选择了AP模式
//             * @param SSID AP名称
//             */
//            @Override
//            public void onSelectAPModel(String SSID) {
//                printLog("onSelectAPModel");
//                if (isDestroyed()) {
//                    return;
//                }
////                connectShootUtils.selectAPModel(true);
//            }
//
//            @Override
//            public void onConnectAPModelFailNoAgain(String s) {
//                printLog("onConnectAPModelFailNoAgain"+s);
//                if (isDestroyed()) {
//                    return;
//                }
////                connectShootUtils.selectAPModel();
//            }
//
//            /**
//             * 连接ap过程中失败
//             */
//            @Override
//            public void onConnectAPModelFail(String s) {
//                printLog("onConnectAPModelFail"+s);
//                if (isDestroyed()) {
//                    return;
//                }
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.setOnDismissListener(null);
//                    progressDialog.dismiss();
//                }
//
//                finish();//或者其他处理
//            }
//
//            /**
//             * 连接ap成功
//             */
//            @Override
//            public void onConnectAPModelSuccess() {
//                printLog("onConnectAPModelSuccess");
//                if (isDestroyed()) {
//                    return;
//                }
//                if (xfCameraHelper.getOpenSuccess()) {
//                    return;
//                }
//
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.setOnDismissListener(null);
//                    progressDialog.dismiss();
//                }
//                xfCameraHelper.Start(null);
//                wifiIp = null;
//            }
//
//            /**
//             *
//             * @param ip
//             */
//            @Override
//            public void onConnectSTAModelSuccess(String ip) {
//                printLog("onConnectSTAModelSuccess");
//                if (isDestroyed()) {
//                    return;
//                }
//                if (xfCameraHelper.getOpenSuccess()) {
//                    return;
//                }
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.setOnDismissListener(null);
//                    progressDialog.dismiss();
//                }
//                llPassword.setVisibility(View.GONE);
//
//                KeyBoardUtils.closeKeybord(etPassword, CameraActivity.this);
//
//                xfCameraHelper.Start(ip);
//                wifiIp = ip;
//            }
//
//            @Override
//            public void onSelectSTAModel(String SSID, String wifiPassword, int status) {
//                printLog("onSelectSTAModel");
//                if (isDestroyed()) {
//                    return;
//                }
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.setOnDismissListener(null);
//                    progressDialog.dismiss();
//                }
//                tvPasswordName.setText(SSID);
//                if (StringUtils.StringNotNull(wifiPassword)) {
//                    etPassword.setText(wifiPassword);
//                } else {
//                    etPassword.setText("");
//                }
//
//                llPasswordConenct.setVisibility(View.GONE);
//
//                if (status == -1) {
//                    tvPasswordError.setText("未知网络错误");
//                    llPasswordError.setVisibility(View.VISIBLE);
//
//                } else if (status == -2) {
//                    tvPasswordError.setText("WiFi密码错误");
//                    llPasswordError.setVisibility(View.VISIBLE);
//                } else {
//                    llPasswordError.setVisibility(View.GONE);
//                }
//
//                llPassword.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onSTAPublicError() {
//
//            }
//        });
//
//        connectShootUtils.queryWifiState();
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("链接中");
//        progressDialog.show();
//
//        llPassword = (LinearLayout) findViewById(R.id.ll_shoot_sta_password);
//        llPasswordError = (LinearLayout) findViewById(R.id.ll_shoot_sta_password_error);
//        tvPasswordError = (TextView) findViewById(R.id.tv_shoot_sta_password_error);
//        llPasswordConenct = (LinearLayout) findViewById(R.id.ll_shoot_sta_password_connect);
//        etPassword = (EditText) findViewById(R.id.et_shoot_sta_password);
//        tvPasswordName = (TextView) findViewById(R.id.tv_shoot_sta_name);
//        tvPasswordCommit = (TextView) findViewById(R.id.tv_shoot_sta_commit);
//        part = (TextView)findViewById(R.id.part);
//
//        shootPhotosAll = new ArrayList<>();
//        part.setText(R.string.part_forehead);
//
//        llPassword.setVisibility(View.GONE);
//        llPasswordError.setVisibility(View.GONE);
//
//
//        tvPasswordCommit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KeyBoardUtils.closeKeybord(etPassword, context);
//                tvPasswordCommit.setEnabled(false);
//                llPasswordConenct.setVisibility(View.VISIBLE);
//
//                connectShootUtils.selectSTAModel(etPassword.getText().toString());
//                tvPasswordCommit.setEnabled(true);
//            }
//        });
//
//        //真皮层 表皮层切换
//        final AppCompatButton change = (AppCompatButton) findViewById(R.id.change);
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!xfCameraHelper.getOpenSuccess()) {//确保摄像头已经开启
//                    return;
//                }
//
//                if (xfCameraHelper.getLedMode() == LedMode.EPIDERMAL) { //当前是表皮层
//                    change.setEnabled(false);
//                    change.setText("真皮层");
//                    xfCameraHelper.setLedMode(LedMode.CORIUM); //调用切换需要一定时间
//                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            change.setEnabled(true);
//                        }
//                    }, 1200);
//                } else if (xfCameraHelper.getLedMode() == LedMode.CORIUM) { //当前是真皮层
//                    change.setEnabled(false);
//                    change.setText("表皮层");
//                    xfCameraHelper.setLedMode(LedMode.EPIDERMAL); //调用切换需要一定时间
//                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            change.setEnabled(true);
//                        }
//                    }, 1200);
//                }
//            }
//        });
//
//        final AppCompatButton appCompatButton = (AppCompatButton) findViewById(R.id.shoot);
//        appCompatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (shootPhotosAll.size()>=5){
//                    updateAll();
//                    return;
//                }
//                if (!xfCameraHelper.getOpenSuccess()) {
//                    return;
//                }
//                appCompatButton.setEnabled(false);
//                if (progressDialog == null || !progressDialog.isShowing()) {
//                    progressDialog = new ProgressDialog(context);
//                    progressDialog.setCancelable(false);
//                    progressDialog.setMessage("拍摄中请勿移动");
//                    progressDialog.show();
//                }
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        /**
//                         *  imageName 图片名称   全脸检测需要拍摄五次（可以传5个不同的名称得到五组数据 ）
//                         */
//                        xfCameraHelper.CaptureNew("imageName", new XFCameraCaptureListener() {
//                            @Override
//                            public void onCaptureSuccess(String epidermal, String corium) {
//
//                                if (progressDialog != null && progressDialog.isShowing()) {
//                                    progressDialog.dismiss();
//                                }
//                                ShootPhoto shootPhoto = new ShootPhoto();
//                                shootPhoto.picRgb=corium;
//                                shootPhoto.picPl=epidermal;
//                                shootPhotosAll.add(shootPhoto);
//
//                                switch (shootPhotosAll.size()){
//                                    case 1:
//                                        part.setText(R.string.part_leftface);
//                                        break;
//                                    case 2:
//                                        part.setText(R.string.part_rightface);
//                                        break;
//                                    case 3:
//                                        part.setText(R.string.part_nose);
//                                        break;
//                                    case 4:
//                                        part.setText(R.string.part_chin);
//                                        break;
//                                    case 5:
//                                        part.setText(R.string.xiaofu_commit);
//                                        appCompatButton.setText(R.string.xiaofu_commit);
//                                        break;
//
//                                }
//                                appCompatButton.setEnabled(true);
//
//                            }
//
//                            @Override
//                            public void onCaptureFail() {
//                                appCompatButton.setEnabled(true);
//                                if (progressDialog != null && progressDialog.isShowing()) {
//                                    progressDialog.dismiss();
//                                }
//                                ToastShow("拍照失败");
//                            }
//                        });
//
//                    }
//                }).start();
//            }
//        });
//    }
//
//
//    /**
//     * 上传数据  全脸检测
//     */
//    private void updateAll() {
//        Map<String, String> arrayMap = new ArrayMap<>();
//        arrayMap.put("type", "1");
//        arrayMap.put("key", "https://api-test.xiaofutech.com/external/putdistinguishresult");
//        for (int i = 0; i < shootPhotosAll.size(); i++) {
//            ShootPhoto sp = shootPhotosAll.get(i);
//            try {
//                byte[] rgbBytes = null;
//                byte[] plBytes = null;
//                //根据图片路径获取byte[]
//                rgbBytes = FileBytes.imageBytes(sp.picRgb);
//                plBytes = FileBytes.imageBytes(sp.picPl);
//                switch (i) {
//                    case 0:
//                        arrayMap.put("forehead_rgb", rgbBytes.toString());
//                        arrayMap.put("forehead_pl", plBytes.toString());
//                        break;
//                    case 1:
//                        arrayMap.put("leftface_rgb", rgbBytes.toString());
//                        arrayMap.put("leftface_pl", plBytes.toString());
//                        break;
//                    case 2:
//                        arrayMap.put("rightface_rgb", rgbBytes.toString());
//                        arrayMap.put("rightface_pl", plBytes.toString());
//                        break;
//                    case 3:
//                        arrayMap.put("nose_rgb", rgbBytes.toString());
//                        arrayMap.put("nose_pl", plBytes.toString());
//                        break;
//                    case 4:
//                        arrayMap.put("chin_rgb", rgbBytes.toString());
//                        arrayMap.put("chin_pl", plBytes.toString());
//                        break;
//                }
//            } catch (OutOfMemoryError error) {
//
//                return;
//            } catch (Exception e) {
//
//                return;
//            }
//        }
//        /**
//         * 自己修改
//         */
//        OkHttp3Utils.getIntance().postOkhttp("https://api-test.xiaofutech.com/external/putdistinguishresult", arrayMap, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                    LogInfo.i(TAG,e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                LogInfo.i(TAG,response.body().string());
//            }
//        });
////        new HttpUtils<>(context, "https://api-test.xiaofutech.com/external/putdistinguishresult", arrayMap, Object.class, new ESHandler<Object>() {
////            @Override
////            public void handleMessage(final HttpResult<Object> result) {
////                super.handleMessage(result);
////                if (result.Success) {
////
////                } else {
////
////                }
////            }
////        }).Start();
//    }
//
//
//    /**
//     * 上传数据  单点检测
//     */
//    private void updateSingle() {
//        ArrayMap<String, Object> arrayMap = new ArrayMap<>();
//        arrayMap.put("type", 2);
//        arrayMap.put("key", "向服务器开发获取");
//        try {
//            byte[] rgbBytes = null;
//            byte[] plBytes = null;
//            //根据图片路径获取byte[]
////                rgbBytes = FileUtils.getbtyes(shootPhotoSingle.picRgb);
////                plBytes = FileUtils.getbtyes(shootPhotoSingle.picPl);
//
//            arrayMap.put("rgb", rgbBytes);
//            arrayMap.put("pl", plBytes);
//        } catch (OutOfMemoryError error) {
//            return;
//        } catch (Exception e) {
//            return;
//        }
//        /**
//         * 自己修改
//         */
////        new HttpUtils<>(this, "http请求地址", arrayMap, Object.class, new ESHandler<Object>() {
////            @Override
////            public void handleMessage(final HttpResult<Object> result) {
////                super.handleMessage(result);
////                if (result.Success) {
////
////                } else {
////
////                }
////            }
////        }).Start();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (xfCameraHelper != null) {
//            xfCameraHelper.Stop();
//        }
//        if (connectShootUtils != null) {
//            connectShootUtils.onDestroy();
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (llPassword != null && llPassword.isShown()) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void ToastShow(String text) {
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }
//
//    private void printLog(String text){
//        LogInfo.i(TAG,text);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        XFBleHelper.onActivityResult(this, requestCode, resultCode);
//    }
//}
