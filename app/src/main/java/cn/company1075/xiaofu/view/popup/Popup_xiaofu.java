package cn.company1075.xiaofu.view.popup;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.inuker.bluetooth.library.model.BleGattProfile;

import org.apache.commons.net.bsd.RExecClient;

import java.util.ArrayList;
import java.util.List;

import cn.company1075.xiaofu.CommitActivity;
import cn.company1075.xiaofu.FullFaceSkin;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.model.view.ShootPhoto;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.SPUtils;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import cn.company1075.xiaofu.view.ui_controls.CameraActivity2;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutMovable;
import xiaofu.xflibrary.ble.XFBleHelper;
import xiaofu.xflibrary.ble.bean.XFBleScanDevice;
import xiaofu.xflibrary.ble.listener.XFBleConnectListener;
import xiaofu.xflibrary.ble.listener.XFBleHelperListener;
import xiaofu.xflibrary.ble.listener.XFBleScanListener;
import xiaofu.xflibrary.camera.XFCameraHelper;
import xiaofu.xflibrary.camera.listener.XFCameraHelperListener;
import xiaofu.xflibrary.connect.ConnectShootUtils;

/**
 * Created by liutianxu on 17/2/9.
 */

public class Popup_xiaofu extends MyLayoutMovable implements View.OnClickListener {
    private String TAG = "Popup_xiaofu";
    private Context context;
    private Title_Info position;
    private Button close;
    private Button btn_start;

    private Dialog dialog;

    private List<XFBleScanDevice> xfBleScanDevices;

    private int width;
    private int height;
    Button optimization_result;//优化结果
   public static boolean isOpmn ;//是否优化 显示结果之后会变回false

    private  int times = 0;//连接保存的蓝牙设备的次数


    //拍照相关
     ImageView photo;
    XFCameraHelper xfCameraHelper;
    ConnectShootUtils connectShootUtils;
    String wifiIp = "";

    ArrayList<ShootPhoto> shootPhotosAll;//1全脸检测 《这个数据怎么添加自己修改》
    ArrayList<ShootPhoto> shootPhotoSingle = new ArrayList<>();//2单点检测



    public Popup_xiaofu(Context mcontext, Title_Info position, String type) {
        super(mcontext, R.layout.popup_xiaofu, position, type);
        this.context = mcontext;
        this.position = position;
        close = findViewById(R.id.close_layout);
        btn_start = findViewById(R.id.xiaofu_start);
        optimization_result = findViewById(R.id.optimization_result);
        optimization_result.setOnClickListener(this);
        optimization_result.setBackgroundResource(R.drawable.skin_shot_btn_s);
        isOpmn = false;

        close.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        btn_start.setClickable(true);

        //获取屏幕宽度
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;






        XFBleHelper.registerListener(new XFBleHelperListener((Activity) context) {
            @Override
            public void onBleStateChanged(boolean openOrClose) {
                Toast.makeText(context,"蓝牙状态变化"+ openOrClose, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDisConnected() {
                Toast.makeText(context,"蓝牙未连接", Toast.LENGTH_SHORT).show();
                btn_start.setClickable(true);
            }
        });
        Activity activity = (Activity)context;
        XFBleHelper.EnableBle(activity);//调用此方法会有蓝牙开启是否成功的回调 （onBleStateChanged）

        xfBleScanDevices = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.xiaofu_start:
                closeBasicPop();
                initXFBleHelper();
                break;
            case R.id.close_layout:
                AllUser.close_layout(this,position);
                break;

            case R.id.optimization_result:
                if (null == SPUtils.getSkinDetail(context, "SkinDeatil")){
                  //  Toast.makeText(context, "初次检测结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                optimization_result.setClickable(true);
                if (isOpmn){
                    isOpmn = false;
                    optimization_result.setBackgroundResource(R.drawable.skin_shot_btn_s);
                }else {
                    isOpmn = true;
                    optimization_result.setBackgroundResource(R.drawable.skin_shot_btn);
                }



                break;
        }
    }




    private void closeDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }



    //初始化弹窗
    private void initPop(){
        PopUtils popUtils = new PopUtils(context, R.layout.xiaofu_pop_commit, width/3, height*4/5, btn_start, Gravity.BOTTOM, 0, 0, new PopUtils.ClickListener() {
            @Override
            public void setUplistener(PopUtils.PopBuilder builder) {



//                ImageView photo = builder.getView(R.id.camera_img);
//                TextView title = builder.getView(R.id.camera_takePhoto_tv);
//                ImageView take = builder.getView(R.id.camera_takePhoto_btn);

            }
        });

    }




    //请求蓝牙
    private void initXFBleHelper(){

        if (!XFBleHelper.IsBleEnable()){
            Toast.makeText(context,"请先开启蓝牙..", Toast.LENGTH_SHORT).show();
            return;
        }

        if (XFBleHelper.getBleConnectStatus()){
            LogInfo.i(TAG, "onConnected");
         //   context.startActivity(new Intent(context, CameraActivity.class));
            closeBasicPop();
            context.startActivity(new Intent(context, CameraActivity2.class));
        }else {
            dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            XFBleScanDevice xfBleScanDevice =  SPUtils.getXFBleScanDevice(context, "XFBleScanDevice");

//            boolean a = (null != xfBleScanDevice);
//            LogInfo.i(TAG, "onConnectOldDev"+a+times);

            //连接连接过的设备 尝试次数最多两次
            if (null != xfBleScanDevice && times <2) {
                dialog.setTitle("连接中");
                dialog.show();
                XFBleHelper.Connect((Activity) context, xfBleScanDevice, new XFBleConnectListener() {
                    @Override
                    public void onConnectError() {
                        times++;
                        Toast.makeText(context, "连接失败,请重试", Toast.LENGTH_SHORT).show();
                        btn_start.setClickable(true);
                        closeDialog();
                    }

                    @Override
                    public void onConnectSuccess(BleGattProfile bleGattProfile) {
                        LogInfo.i(TAG, "onConnectSuccess2");
                        times = 0;
                        context.startActivity(new Intent(context, CameraActivity2.class));
                        closeDialog();
                    }
                });
            } else {
                SPUtils.removeBean(context, "XFBleScanDevice");//删除之前保存的设备，重新搜索
                dialog.setTitle("搜索中");
                dialog.show();
                //
                XFBleHelper.StartScan(5 * 1000, new XFBleScanListener() {
                    @Override
                    public void onBleScanDevice(final XFBleScanDevice xfBleScanDevice) {
                        xfBleScanDevices.add(xfBleScanDevice);
                        XFBleHelper.StopScan();
                        dialog.setTitle("连接中");
                        XFBleHelper.Connect((Activity) context, xfBleScanDevice, new XFBleConnectListener() {
                            @Override
                            public void onConnectError() {
                                LogInfo.i(TAG, "onConnectError");
                                closeDialog();
                                Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                                btn_start.setClickable(true);
                            }

                            @Override
                            public void onConnectSuccess(BleGattProfile bleGattProfile) {
                                LogInfo.i(TAG, "onConnectSuccess");
                                //删除之前保存的蓝牙设备，添加连接的蓝牙设备
                                SPUtils.putBean(context, "XFBleScanDevice", xfBleScanDevice);

                                context.startActivity(new Intent(context, CameraActivity2.class));
                                closeDialog();
                                // context.startActivity(new Intent(context,CameraActivity.class));
                                // initPop();
                            }
                        });
                    }


                    @Override
                    public void onBleScanComplete() {
                        LogInfo.i(TAG, "搜索完成,搜索到设备个数" + xfBleScanDevices.size());
                        if (xfBleScanDevices.size() == 0){
                            closeDialog();
                            Toast.makeText(context, "没有搜索到可连接设备", Toast.LENGTH_SHORT).show();
                        }
                        btn_start.setClickable(true);
                    }

                    @Override
                    public void onBleScanError() {
                        closeDialog();
                        btn_start.setClickable(true);
                    }
                });
            }
        }
    }

    private void closeBasicPop(){
        //        //关闭所有底层Pop
        if (null !=  AllUser.popup_info &&  AllUser.popup_info.size()>0){
            for (int i = 0; i < AllUser.popup_info.size(); i++) {
                AllUser.close_layout(AllUser.popup_info.get(i), AllUser.popup_info.get(i).getiPosition());
               // AllUser.moveout(AllUser.popup_info.get(i));
            }
        }
    }

}
