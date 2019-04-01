package cn.company1075.xiaofu;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inuker.bluetooth.library.model.BleGattProfile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.Skin;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import cn.company1075.xiaofu.view.ui_controls.CameraActivity2;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xiaofu.xflibrary.ble.XFBleHelper;
import xiaofu.xflibrary.ble.bean.XFBleScanDevice;
import xiaofu.xflibrary.ble.listener.XFBleConnectListener;
import xiaofu.xflibrary.ble.listener.XFBleScanListener;

public class FullFaceSkin extends Activity implements View.OnClickListener{


    private int width;
    private int height;

    private SkinDeatil skin;

    TextView SkinType;//肌肤状态
    TextView TotalScore;//综合得分
    TextView U;//U型区
    TextView T;//T型区

    TextView ForeheadOil;
    TextView LeftOil;
    TextView RightOil;
    TextView ChinOil;
    TextView NoseOil;

    TextView Makeup;

    Button close_layout;

    TextView Dryness;//干燥度
    TextView Sensitive;//敏感度
    TextView Pore;//毛孔
    TextView Senility;//肌肤衰老度

    ImageView DrynessDetail;
    ImageView SensitiveDetail;
    ImageView PoreDetail;
    ImageView SenilityDetail;

    RelativeLayout ranklayout1;
    RelativeLayout ranklayout2;
    RelativeLayout ranklayout3;
    RelativeLayout ranklayout4;

    RelativeLayout ranklayout11;
    RelativeLayout ranklayout12;
    RelativeLayout ranklayout13;
    RelativeLayout ranklayout14;

    RelativeLayout ranklayout21;
    RelativeLayout ranklayout22;
    RelativeLayout ranklayout23;
    RelativeLayout ranklayout24;

    RelativeLayout ranklayout31;
    RelativeLayout ranklayout32;
    RelativeLayout ranklayout33;
    RelativeLayout ranklayout34;

    ImageView reChcck;
    private Dialog dialog;
    Context context;

    private Button DrynessBtn,SensitiveBtn,PoreBtn,SenilityBtn,close;
    private TextView forehead,leftface,chin,nose,rightface,score,detail;

    private int flag = 1;//1.干燥度 2.敏感度 3.毛孔 4.衰老度

    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xiaofu_fullface);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        //设置Activity的宽高

        //设置屏幕宽高和显示位置
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width;
        lp.height = height*4/5;
        lp.gravity = Gravity.CENTER|Gravity.BOTTOM;
        getWindow().setAttributes(lp);

        initView();
        getSkinData();




    }

//    private void getDetailData() {
//
//        String distinguishId = skin.getData().getDistinguishId();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api-test.xiaofutech.com/") //设置网络请求的Url地址
//                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
//                .build();
//        UploadFileService service = retrofit.create(UploadFileService.class);
//        Map<String, String> params = new HashMap<>();
//        params.put("appId", "3b9a27a0-ff27-4a2c-bf2c-7c57c6f25850");
//        params.put("distinguishId", distinguishId);
//        retrofit2.Call<SkinDeatil> model = service.getSkinDetail(params);
//        model.enqueue(new Callback<SkinDeatil>() {
//            @Override
//            public void onResponse(Call<SkinDeatil> call, Response<SkinDeatil> response) {
//                skinDeatil = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<SkinDeatil> call, Throwable t) {
//
//            }
//        });
//
//
//
////        model.enqueue(new Callback<ResponseBody>() {
////            @Override
////            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                String jsonstr = null;
////                try {
////                    jsonstr = new String(response.body().bytes());
////                    Log.i("json信息：",jsonstr);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////
////            }
////
////            @Override
////            public void onFailure(Call<ResponseBody> call, Throwable t) {
////
////            }
////        });
//
//    }

    private void initView() {

        SkinType =  findViewById(R.id.state_tv);
        TotalScore = findViewById(R.id.score_tv);
        U = findViewById(R.id.u2);
        T = findViewById(R.id.t2);

        ForeheadOil = findViewById(R.id.skin_etouq_tv);
        LeftOil = findViewById(R.id.skin_zuolianjiaq_tv);
        RightOil = findViewById(R.id.skin_youlianjiaq_tv);
        ChinOil = findViewById(R.id.skin_xiabaq_tv);
        NoseOil = findViewById(R.id.skin_biziq_tv);

        Makeup = findViewById(R.id.last_warning);
        close_layout = findViewById(R.id.close_layout);
        close_layout.setOnClickListener(this);


        Dryness = findViewById(R.id.rank1_tv);
        ranklayout1 = findViewById(R.id.ranklayout1);
        ranklayout2 = findViewById(R.id.ranklayout2);
        ranklayout3 = findViewById(R.id.ranklayout3);
        ranklayout4 = findViewById(R.id.ranklayout4);
        DrynessDetail = findViewById(R.id.rank1_img);
        DrynessDetail.setOnClickListener(this);

        Sensitive = findViewById(R.id.rank2_tv);
        SensitiveDetail = findViewById(R.id.rank2_img);
        SensitiveDetail.setOnClickListener(this);
        ranklayout11 = findViewById(R.id.ranklayout11);
        ranklayout12 = findViewById(R.id.ranklayout12);
        ranklayout13 = findViewById(R.id.ranklayout13);
        ranklayout14 = findViewById(R.id.ranklayout14);

        Pore = findViewById(R.id.rank3_tv);
        PoreDetail = findViewById(R.id.rank3_img);
        PoreDetail.setOnClickListener(this);
        ranklayout21 = findViewById(R.id.ranklayout21);
        ranklayout22 = findViewById(R.id.ranklayout22);
        ranklayout23 = findViewById(R.id.ranklayout23);
        ranklayout24 = findViewById(R.id.ranklayout24);



        Senility = findViewById(R.id.rank4_tv);
        ranklayout31 = findViewById(R.id.ranklayout31);
        ranklayout32 = findViewById(R.id.ranklayout32);
        ranklayout33 = findViewById(R.id.ranklayout33);
        ranklayout34 = findViewById(R.id.ranklayout34);
        SenilityDetail = findViewById(R.id.rank4_img);
        SenilityDetail.setOnClickListener(this);

        reChcck = findViewById(R.id.reCheck);
        reChcck.setOnClickListener(this);

    }


    private void getSkinData() {

        skin = (SkinDeatil) getIntent().getSerializableExtra("skin");

//        Skin.DataBean dataBean = new Skin.DataBean();
//        dataBean.setChinOil("偏油");
//        dataBean.setDistinguishId("33534f26-67ae-4257-8465-1953ae77e1ed");
//        dataBean.setDryness("不干燥");
//        dataBean.setForeheadOil("偏油");
//        dataBean.setLeftColor(9);
//        dataBean.setLeftOil("偏油");
//        dataBean.setMakeup("发现您的肌肤上疑似覆有粉类妆品，可能会影响本次检测结果准确性");
//        dataBean.setNoseOil("偏油");
//        dataBean.setPore("细腻");
//        dataBean.setRightColor(9);
//        dataBean.setSenility("年轻肌");
//        dataBean.setSensitive("不敏感");
//        dataBean.setSkinColor(9);
//        dataBean.setSkinType("油性肤质");
//        dataBean.setT("偏油");
//        dataBean.setU("偏油");
//        dataBean.setTotalScore(73);
//        skin = new Skin(0,"s",dataBean,25L);


        SkinType.setText(skin.getData().getPfType());
        TotalScore.setText(skin.getData().getTotalScore()+"");
        U.setText(skin.getData().getU());
        T.setText(skin.getData().getT());

        ForeheadOil.setText(skin.getData().getDistinguishDataList2().get(0).getOil().getLevelAbout());
        LeftOil.setText(skin.getData().getDistinguishDataList2().get(1).getOil().getLevelAbout());
        RightOil.setText(skin.getData().getDistinguishDataList2().get(2).getOil().getLevelAbout());
        ChinOil.setText(skin.getData().getDistinguishDataList2().get(4).getOil().getLevelAbout());
        NoseOil.setText(skin.getData().getDistinguishDataList2().get(3).getOil().getLevelAbout());

        LogInfo.i("AAAAA", skin.getData().getDistinguishDataList2().get(0).getOil().getLevelAbout());
        LogInfo.i("AAAAA", skin.getData().getDistinguishDataList2().get(1).getOil().getLevelAbout());
        LogInfo.i("AAAAA", skin.getData().getDistinguishDataList2().get(2).getOil().getLevelAbout());
        LogInfo.i("AAAAA", skin.getData().getDistinguishDataList2().get(3).getOil().getLevelAbout());
        LogInfo.i("AAAAA", skin.getData().getDistinguishDataList2().get(4).getOil().getLevelAbout());


        if (skin.getData().getMakeup().equals("")){
            Makeup.setVisibility(View.GONE);
        }else {
            Makeup.setText(skin.getData().getMakeup());
        }

        Dryness.setText(skin.getData().getDistinguishResultList().get(2).getLevelAbout());
        doDryness();
        Sensitive.setText(skin.getData().getDistinguishResultList().get(1).getLevelAbout());
        doSensitive();
        Pore.setText(skin.getData().getDistinguishResultList().get(0).getLevelAbout());
        doPore();
        Senility.setText(skin.getData().getDistinguishResultList().get(3).getLevelAbout());
        doSenility();


    }

    private void doSenility() {

        String level = skin.getData().getDistinguishResultList().get(3).getLevelDesc();

        if (level.endsWith("A")){

            ranklayout31.setVisibility(View.VISIBLE);
            ranklayout32.setVisibility(View.GONE);
            ranklayout33.setVisibility(View.GONE);
            ranklayout34.setVisibility(View.GONE);
        }
        if (level.endsWith("B")){
            ranklayout31.setVisibility(View.GONE);
            ranklayout32.setVisibility(View.VISIBLE);
            ranklayout33.setVisibility(View.GONE);
            ranklayout34.setVisibility(View.GONE);
        }

        if (level.endsWith("C")){
            ranklayout31.setVisibility(View.GONE);
            ranklayout32.setVisibility(View.GONE);
            ranklayout33.setVisibility(View.VISIBLE);
            ranklayout34.setVisibility(View.GONE);
        }

        if (level.endsWith("D")){
            ranklayout31.setVisibility(View.GONE);
            ranklayout32.setVisibility(View.GONE);
            ranklayout33.setVisibility(View.GONE);
            ranklayout34.setVisibility(View.VISIBLE);
        }



    }


    private void doSensitive() {
        String level = skin.getData().getDistinguishResultList().get(1).getLevelDesc();
        if (level.endsWith("A")){
            ranklayout11.setVisibility(View.VISIBLE);
            ranklayout12.setVisibility(View.GONE);
            ranklayout13.setVisibility(View.GONE);
            ranklayout14.setVisibility(View.GONE);
        }
        if (level.endsWith("B")){
            ranklayout11.setVisibility(View.GONE);
            ranklayout12.setVisibility(View.VISIBLE);
            ranklayout13.setVisibility(View.GONE);
            ranklayout14.setVisibility(View.GONE);
        }

        if (level.endsWith("C")){
            ranklayout11.setVisibility(View.GONE);
            ranklayout12.setVisibility(View.GONE);
            ranklayout13.setVisibility(View.VISIBLE);
            ranklayout14.setVisibility(View.GONE);
        }

        if (level.endsWith("D")){
            ranklayout11.setVisibility(View.GONE);
            ranklayout12.setVisibility(View.GONE);
            ranklayout13.setVisibility(View.GONE);
            ranklayout14.setVisibility(View.VISIBLE);
        }


    }

    private void doPore() {
        String level = skin.getData().getDistinguishResultList().get(0).getLevelDesc();
        if (level.endsWith("A")){
            ranklayout21.setVisibility(View.VISIBLE);
            ranklayout22.setVisibility(View.GONE);
            ranklayout23.setVisibility(View.GONE);
            ranklayout24.setVisibility(View.GONE);
        }

        if (level.endsWith("B")){
            ranklayout21.setVisibility(View.GONE);
            ranklayout22.setVisibility(View.VISIBLE);
            ranklayout23.setVisibility(View.GONE);
            ranklayout24.setVisibility(View.GONE);
        }

        if (level.endsWith("C")){
            ranklayout21.setVisibility(View.GONE);
            ranklayout22.setVisibility(View.GONE);
            ranklayout23.setVisibility(View.VISIBLE);
            ranklayout24.setVisibility(View.GONE);
        }

        if (level.endsWith("D")) {
            ranklayout21.setVisibility(View.GONE);
            ranklayout22.setVisibility(View.GONE);
            ranklayout23.setVisibility(View.GONE);
            ranklayout24.setVisibility(View.VISIBLE);

        }

    }



    private void doDryness() {
        String level = skin.getData().getDistinguishResultList().get(2).getLevelDesc();
        if (level.endsWith("A")){
            ranklayout1.setVisibility(View.VISIBLE);
            ranklayout2.setVisibility(View.GONE);
            ranklayout3.setVisibility(View.GONE);
            ranklayout4.setVisibility(View.GONE);
        }

        if (level.endsWith("B")){
            ranklayout1.setVisibility(View.GONE);
            ranklayout2.setVisibility(View.VISIBLE);
            ranklayout3.setVisibility(View.GONE);
            ranklayout4.setVisibility(View.GONE);
        }

        if (level.endsWith("C")){
            ranklayout1.setVisibility(View.GONE);
            ranklayout2.setVisibility(View.GONE);
            ranklayout3.setVisibility(View.VISIBLE);
            ranklayout4.setVisibility(View.GONE);
        }

        if (level.endsWith("D")){
            ranklayout1.setVisibility(View.GONE);
            ranklayout2.setVisibility(View.GONE);
            ranklayout3.setVisibility(View.GONE);
            ranklayout4.setVisibility(View.VISIBLE);
        }

    }

    //请求蓝牙
    private void initXFBleHelper(){
        if (XFBleHelper.getBleConnectStatus()){
            //   context.startActivity(new Intent(context, CameraActivity.class));
            this.startActivity(new Intent(this, CameraActivity2.class));
        }else {
            dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            dialog.setTitle("搜索中");
            dialog.show();
            if (XFBleHelper.IsBleEnable()) {
                XFBleHelper.StartScan(5 * 1000, new XFBleScanListener() {
                    @Override
                    public void onBleScanDevice(XFBleScanDevice xfBleScanDevice) {
                        XFBleHelper.StopScan();
                        dialog.setTitle("连接中");
                        XFBleHelper.Connect((Activity) context, xfBleScanDevice, new XFBleConnectListener() {
                            @Override
                            public void onConnectError() {
                                closeDialog();
                                Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                                reChcck.setImageResource(R.mipmap.skin_again_btn_s);
                                reChcck.setClickable(true);
                            }

                            @Override
                            public void onConnectSuccess(BleGattProfile bleGattProfile) {
                                closeDialog();
                                context.startActivity(new Intent(context, CameraActivity2.class));
                                // context.startActivity(new Intent(context,CameraActivity.class));
                                // initPop();
                            }
                        });
                    }


                    @Override
                    public void onBleScanComplete() {
                        dialog.dismiss();
                        reChcck.setImageResource(R.mipmap.skin_again_btn_s);
                        reChcck.setClickable(true);
                    }

                    @Override
                    public void onBleScanError() {
                        dialog.dismiss();
                        reChcck.setImageResource(R.mipmap.skin_again_btn_s);
                        reChcck.setClickable(true);
                    }
                });
            }
        }


    }
    private void closeDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void initDetailPop(View view){

        PopUtils popUtils  = new PopUtils(this, R.layout.xiaofu_fullface_detail, width / 3, 4 * height / 5, view, Gravity.CENTER | Gravity.BOTTOM, 0, 0, new PopUtils.ClickListener() {
            @Override
            public void setUplistener(PopUtils.PopBuilder builder) {
                DrynessBtn = builder.getView(R.id.DrynessBtn);
                DrynessBtn.setOnClickListener(FullFaceSkin.this);
                SensitiveBtn =  builder.getView(R.id.SensitiveBtn);
                SensitiveBtn.setOnClickListener(FullFaceSkin.this);
                PoreBtn =  builder.getView(R.id.PoreBtn);
                PoreBtn.setOnClickListener(FullFaceSkin.this);
                SenilityBtn =  builder.getView(R.id.SenilityBtn);
                SenilityBtn.setOnClickListener(FullFaceSkin.this);



                //各部位状态
                forehead =  builder.getView(R.id.foreheadState);
                leftface =  builder.getView(R.id.leftfaceState);
                rightface =  builder.getView(R.id.rightfaceState);
                nose =  builder.getView(R.id.noseState);
                chin =  builder.getView(R.id.chinState);
                score =  builder.getView(R.id.detailscore);
                detail = builder.getView(R.id.detail);

                score.setText(skin.getData().getPfType());
                detail.setText(skin.getData().getPfDesc());

                close =  builder.getView(R.id.close);
                close.setOnClickListener(FullFaceSkin.this);
                initBtn(flag);
                initData();

            }
        });

    }

    private void initData(){

        switch (flag){
            case 1:

                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++) {

                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String Dryness = skin.getData().getDistinguishDataList2().get(i).getDryness().getLevelAbout();
                   if (position.equals("额头")){
                       forehead.setText(Dryness);
                   }

                    if (position.equals("左脸颊")){
                        leftface.setText(Dryness);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Dryness);
                    }

                    if (position.equals("下巴")){
                            chin.setText(Dryness);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Dryness);
                    }
                }

                break;

            case 2 :

                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++){
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String  Sensitive= skin.getData().getDistinguishDataList2().get(i).getSensitive().getLevelAbout();

                    if (position.equals("额头")){
                        forehead.setText(Sensitive);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Sensitive);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Sensitive);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Sensitive);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Sensitive);
                    }

                }

                break;

            case 3 :

                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++){
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String  Pore= skin.getData().getDistinguishDataList2().get(i).getPore().getLevelAbout();

                    if (position.equals("额头")){
                        forehead.setText(Pore);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Pore);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Pore);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Pore);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Pore);
                    }

                }

                break;
            case 4:
                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++){
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String  Sensitive= skin.getData().getDistinguishDataList2().get(i).getSenility().getLevelAbout();

                    if (position.equals("额头")){
                        forehead.setText(Sensitive);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Sensitive);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Sensitive);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Sensitive);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Sensitive);
                    }

                }

                break;
        }




        }




    @Override
    public void onClick(View view) {
        bundle.putSerializable("SkinDeatil", skin);
        switch ( view.getId()){
            case R.id.close_layout :
                finish();
                break;
                //干燥度详情
            case R.id.rank1_img :
                flag = 1;
                initDetailPop(view);
                Intent intent = new Intent(FullFaceSkin.this,FullSkinDetail.class);
                intent.putExtra("value", 1);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.rank2_img :
                flag = 2;
                initDetailPop(view);
                Intent intent2 = new Intent(FullFaceSkin.this,FullSkinDetail.class);
                intent2.putExtra("value", 2);
                intent2.putExtras(bundle);
                startActivity(intent2);
                finish();


                break;
                case R.id.rank3_img :
                    flag = 3;
                    initDetailPop(view);
                    Intent intent3 = new Intent(FullFaceSkin.this,FullSkinDetail.class);
                    intent3.putExtra("value", 3);
                    intent3.putExtras(bundle);
                    startActivity(intent3);
                    finish();



                break;
            case R.id.rank4_img :
                flag = 4;
                initDetailPop(view);
                Intent intent4 = new Intent(FullFaceSkin.this,FullSkinDetail.class);
                intent4.putExtra("value", 4);
                intent4.putExtras(bundle);
                startActivity(intent4);
                finish();



                break;
            case R.id.reCheck :
                reChcck.setClickable(false);
                reChcck.setImageResource(R.mipmap.skin_again_btn_n);
                initXFBleHelper();
                finish();
                break;

            case R.id.DrynessBtn :
                flag = 1;
                initBtn(1);
                initData();
                break ;
            case R.id.SensitiveBtn :
                flag = 2;
                initBtn(2);
                initData();
                break ;
            case R.id.PoreBtn :
                flag = 3;
                initBtn(3);
                initData();
                break ;
            case R.id.SenilityBtn :
                flag = 4;
                initBtn(4);
                initData();
                break ;
            case R.id.close :
                PopUtils.PopBuilder.getWindow().dismiss();
                break ;



        }
    }

    private void initBtn(int flag) {

        switch(flag){
            case 1 :
                DrynessBtn.setTextColor(this.getResources().getColor(R.color.string_gray7));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_s);

                SensitiveBtn.setTextColor(this.getResources().getColor(R.color.white));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_n);

                PoreBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SenilityBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_n);

                break;
            case 2 :

                DrynessBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SensitiveBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.string_gray7));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_s);

                PoreBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SenilityBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_n);

                break;
            case 3:
                DrynessBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SensitiveBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_n);

                PoreBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.string_gray7));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_s);

                SenilityBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_n);



                break;
            case 4 :


                DrynessBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SensitiveBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_n);

                PoreBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.white));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SenilityBtn.setTextColor(FullFaceSkin.this.getResources().getColor(R.color.string_gray7));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_s);

                break;


        }
    }

}
