package cn.company1075.xiaofu.view.ui_controls;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import cn.company1075.xiaofu.CommitActivity;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.ShootPhoto;
import cn.company1075.xiaofu.utils.FileUtil;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.xiaofu.KeyBoardUtils;
import cn.company1075.xiaofu.utils.xiaofu.SDCardUtils;
import cn.company1075.xiaofu.utils.xiaofu.StringUtils;
import xiaofu.xflibrary.ble.XFBleHelper;
import xiaofu.xflibrary.camera.LedMode;
import xiaofu.xflibrary.camera.XFCameraHelper;
import xiaofu.xflibrary.camera.listener.XFCameraCaptureListener;
import xiaofu.xflibrary.camera.listener.XFCameraHelperListener;
import xiaofu.xflibrary.connect.ConnectShootUtils;
import xiaofu.xflibrary.connect.listener.ConnectShootListener;

public class CameraActivity2 extends Activity implements View.OnClickListener{

    private Dialog dialog;
    private String TAG = "CameraActivity";
    private int width;
    private int height;
    //切换
    ImageView change;
    TextView change_tv;
    ImageView shoot;
    TextView shootTv;
    ImageView shootImg;

    //关闭
    Button close;


    LinearLayout photo ;

    XFCameraHelper xfCameraHelper;
    ConnectShootUtils connectShootUtils;
    String wifiIp = "";
    ProgressDialog progressDialog;
    Context context;

    //Wifi
    LinearLayout llPassword, llPasswordError, llPasswordConenct;
    TextView tvPasswordName, tvPasswordCommit, tvPasswordError;
    EditText etPassword;


    ArrayList<ShootPhoto> shootPhotoSingle = new ArrayList<>();// 单点检测
    ArrayList<ShootPhoto> shootPhotosAll;//全脸检测

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xiaofu_pop_cameralayout);
        //获取屏幕宽度
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        //设置Activity的宽高

        //设置屏幕宽高和显示位置
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width/3;
        lp.height = height*4/5;
        lp.gravity = Gravity.CENTER|Gravity.BOTTOM;
        getWindow().setAttributes(lp);

        //初始化页面
        initView();

        //初始化相机
        initxfCamera();





    }


    private void initxfCamera() {

        //初始化相机
        xfCameraHelper = new XFCameraHelper(this, photo, false, new XFCameraHelperListener() {
            @Override
            public void onCameraStatus(boolean isOpen) {
                if (isDestroyed()) {
                    return;
                }
                if (!isOpen) {//开启摄像头失败 （连接成功了 但是测试过程中  又断了 需要自己处理）
                    //可以继续去打开
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            printLog("打开摄像头");
                            xfCameraHelper.Start(wifiIp);
                        }
                    }, 5000);

                    //或者其他方法
                } else {//开启摄像头成功
                    //打开摄像头之后    镜头模式会被重置成表皮层模式
                    printLog("==================");
                }
            }
        });

        //设置相机分辨率
        xfCameraHelper.setBitrate(800);

        connectShootUtils = new ConnectShootUtils(this);
        connectShootUtils.setListerner(new ConnectShootListener() {
            @Override
            public void onBackFinish(int i) {
                if (xfCameraHelper.getOpenSuccess()) {
                    return;
                }
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.setOnDismissListener(null);
                    progressDialog.dismiss();
                }
                printLog("onBackFinish");
                finish();
            }

            @Override
            public void is5G() {
                printLog("is5G");
            }

            @Override
            public void onSelectAPModel(String s) {
                printLog("onSelectAPModel");
                if (isDestroyed()) {
                    return;
                }
            }

            @Override
            public void onConnectAPModelFailNoAgain(String s) {
                printLog("onConnectAPModelFailNoAgain"+s);
                if (isDestroyed()) {
                    return;
                }
            }

            @Override
            public void onConnectAPModelFail(String s) {
                printLog("onConnectAPModelFail"+s);
                if (isDestroyed()) {
                    return;
                }
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.setOnDismissListener(null);
                    progressDialog.dismiss();
                }

                finish();//或者其他处理
            }

            @Override
            public void onConnectAPModelSuccess() {
                printLog("onConnectAPModelSuccess");
                if (isDestroyed()) {
                    return;
                }
                if (xfCameraHelper.getOpenSuccess()) {
                    return;
                }

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.setOnDismissListener(null);
                    progressDialog.dismiss();
                }
                xfCameraHelper.Start(null);
                wifiIp = null;
            }

            @Override
            public void onConnectSTAModelSuccess(String ip) {
                printLog("onConnectSTAModelSuccess");
                if (isDestroyed()) {
                    return;
                }
                if (xfCameraHelper.getOpenSuccess()) {
                    return;
                }
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.setOnDismissListener(null);
                    progressDialog.dismiss();
                }
                llPassword.setVisibility(View.GONE);

                KeyBoardUtils.closeKeybord(etPassword, context);

                xfCameraHelper.Start(ip);
                wifiIp = ip;
            }

            @Override
            public void onSelectSTAModel(String SSID, String wifiPassword, int status) {
                printLog("onSelectSTAModel");
                if (isDestroyed()) {
                    return;
                }
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.setOnDismissListener(null);
                    progressDialog.dismiss();
                }
                tvPasswordName.setText(SSID);
                if (StringUtils.StringNotNull(wifiPassword)) {
                    etPassword.setText(wifiPassword);
                } else {
                    etPassword.setText("");
                }

                llPasswordConenct.setVisibility(View.GONE);

                if (status == -1) {
                    tvPasswordError.setText("未知网络错误");
                    llPasswordError.setVisibility(View.VISIBLE);

                } else if (status == -2) {
                    tvPasswordError.setText("WiFi密码错误");
                    llPasswordError.setVisibility(View.VISIBLE);
                } else {
                    llPasswordError.setVisibility(View.GONE);
                }

                llPassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void onSTAPublicError() {

            }
        });


        connectShootUtils.queryWifiState();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("连接中");
        progressDialog.show();



        llPassword = (LinearLayout) findViewById(R.id.ll_shoot_sta_password);
        llPasswordError = (LinearLayout) findViewById(R.id.ll_shoot_sta_password_error);
        tvPasswordError = (TextView) findViewById(R.id.tv_shoot_sta_password_error);
        llPasswordConenct = (LinearLayout) findViewById(R.id.ll_shoot_sta_password_connect);
        etPassword = (EditText) findViewById(R.id.et_shoot_sta_password);
        tvPasswordName = (TextView) findViewById(R.id.tv_shoot_sta_name);
        tvPasswordCommit = (TextView) findViewById(R.id.tv_shoot_sta_commit);

        shootPhotosAll = new ArrayList<>();

        llPassword.setVisibility(View.GONE);
        llPasswordError.setVisibility(View.GONE);


        tvPasswordCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtils.closeKeybord(etPassword, context);
                tvPasswordCommit.setEnabled(false);
                llPasswordConenct.setVisibility(View.VISIBLE);

                connectShootUtils.selectSTAModel(etPassword.getText().toString());
                tvPasswordCommit.setEnabled(true);
            }
        });

    }






    private void initView() {

        context = this;
        photo = findViewById(R.id.camera_img);
        change = findViewById(R.id.camera_change_Img);
        change.setOnClickListener(this);
        change_tv = findViewById(R.id.camera_change_tv);
        close = findViewById(R.id.close_layout);
        close.setOnClickListener(this);
        shoot = findViewById(R.id.camera_takePhoto_btn);
        shoot.setOnClickListener(this);
        shootTv = findViewById(R.id.camera_takePhoto_tv);
        shootImg = findViewById(R.id.camera_takePhoto_img);

    }
    private void printLog(String text){
        LogInfo.i(TAG,text);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xfCameraHelper != null) {
            xfCameraHelper.Stop();
        }
        if (connectShootUtils != null) {
            connectShootUtils.onDestroy();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (llPassword != null && llPassword.isShown()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void ToastShow(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        XFBleHelper.onActivityResult(this, requestCode, resultCode);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //拍照片
            case R.id.camera_takePhoto_btn :
                if (!xfCameraHelper.getOpenSuccess()) {
                    return;
                }

                shoot.setEnabled(false);
                if (progressDialog == null || !progressDialog.isShowing()) {
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("拍摄中请勿移动");
                    progressDialog.show();
                }


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        xfCameraHelper.CaptureNew("imageName", new XFCameraCaptureListener() {
                            @Override
                            public void onCaptureSuccess(String epidermal, String corium) {

                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }

                                String name = System.currentTimeMillis()+"";

                                ShootPhoto shootPhoto = new ShootPhoto();
                                shootPhoto.picRgb = FileUtil.renameForFile(new File(corium), "picRgb"+name);
                                shootPhoto.picPl = FileUtil.renameForFile(new File(epidermal), "picPl"+name);
                                shootPhotosAll.add(shootPhoto);

                                switch (shootPhotosAll.size()){
                                    case 0 :
                                        shootTv.setText(R.string.part_forehead);
                                        shootImg.setImageResource(R.drawable.skin_face_icon_1);
                                        break;
                                    case 1:
                                        shootTv.setText(R.string.part_leftface);
                                        shootImg.setImageResource(R.drawable.skin_face_icon_2);
                                        break;
                                    case 2:
                                        shootTv.setText(R.string.part_rightface);
                                        shootImg.setImageResource(R.drawable.skin_face_icon_3);
                                        break;
                                    case 3:
                                        shootTv.setText(R.string.part_nose);
                                        shootImg.setImageResource(R.drawable.skin_face_icon_4);
                                        break;
                                    case 4:
                                        shootTv.setText(R.string.part_chin);
                                        shootImg.setImageResource(R.drawable.skin_face_icon_5);
                                        break;

                                }

                                shoot.setEnabled(true);

                                if (shootPhotosAll.size()>=5){

//                                    Log.e("==================", shootPhotosAll.get(0).picPl);
//                                    Log.e("==================", shootPhotosAll.get(1).picPl);
//                                    Log.e("==================", shootPhotosAll.get(2).picPl);
//                                    Log.e("==================", shootPhotosAll.get(3).picPl);
//                                    Log.e("==================", shootPhotosAll.get(4).picPl);
//
//
//                                    Log.e("==================", shootPhotosAll.get(0).picRgb);
//                                    Log.e("==================", shootPhotosAll.get(1).picRgb);
//                                    Log.e("==================", shootPhotosAll.get(2).picRgb);
//                                    Log.e("==================", shootPhotosAll.get(3).picRgb);
//                                    Log.e("==================", shootPhotosAll.get(4).picRgb);


                                    Intent intent = new Intent(CameraActivity2.this, CommitActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putSerializable("list",(Serializable)shootPhotosAll);//序列化,要注意转化(Serializable)
                                    intent.putExtras(bundle);//发送数据
                                    startActivity(intent);
                                    finish();
                                    return;
                                }


                            }

                            @Override
                            public void onCaptureFail() {
                                shoot.setEnabled(true);
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                ToastShow("拍照失败");
                            }
                        });





                    }
                }) .start();








                break;
            case R.id.close_layout :
                finish();
                break ;

            //切换点击事件
            case R.id.camera_change_Img :
                if (!xfCameraHelper.getOpenSuccess()) {//确保摄像头已经开启
                    return;
                }

                if (xfCameraHelper.getLedMode() == LedMode.EPIDERMAL) { //当前是表皮层
                    change.setEnabled(false);
                    change_tv.setText("切换基底层");
                    xfCameraHelper.setLedMode(LedMode.CORIUM); //调用切换需要一定时间
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            change.setEnabled(true);
                        }
                    }, 1200);
                } else if (xfCameraHelper.getLedMode() == LedMode.CORIUM) { //当前是真皮层
                    change.setEnabled(false);
                    change_tv.setText("切换表皮层");
                    xfCameraHelper.setLedMode(LedMode.EPIDERMAL); //调用切换需要一定时间
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            change.setEnabled(true);
                        }
                    }, 1200);
                }
                break;
        }


    }


}
