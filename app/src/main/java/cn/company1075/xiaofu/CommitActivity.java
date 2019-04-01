package cn.company1075.xiaofu;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.model.view.ShootPhoto;
import cn.company1075.xiaofu.utils.GoodsDetail;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.OkHttp3Utils;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import cn.company1075.xiaofu.utils.SPUtils;
import cn.company1075.xiaofu.utils.Skin;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.utils.xiaofu.CommonItemDecoration;
import cn.company1075.xiaofu.utils.xiaofu.FileBytes;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import cn.company1075.xiaofu.view.PickerScrollView;
import cn.company1075.xiaofu.view.Pickers;
import cn.company1075.xiaofu.view.SkinSwitchProductActivity;
import cn.company1075.xiaofu.view.adapter.GridPhotoAdapter;
import cn.company1075.xiaofu.view.popup.Popup_xiaofu;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import xiaofu.xflibrary.connect.bean.NetWorkBean;

public class CommitActivity extends Activity implements View.OnClickListener{

    public  static String Tag  = "CommitActivity";
    private int width;
    private int height;
    private RecyclerView gridrecyclerView;
    private List<ShootPhoto> data;
    private Button close ;
    ImageView img,cancle,man,woman;
    ImageView ageselect;
    TextView ageselecttv;
    OkHttpClient client;
    List<Pickers> agedata;
    private Context context;
    String age = 1+"";
    private Dialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  //获取屏幕宽度
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xiaofu_pop_commit);
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

        initView();
        initgride();
        initPicks();

        context = this;







    }

    private void initPicks() {
        agedata= new ArrayList<Pickers>();
        for (int i = 0; i < 100; i++) {
            agedata.add(new Pickers(i+1+"",i+1+""));
        }

    }

    private void initView() {

        close = findViewById(R.id.close_layout);
        close.setOnClickListener(this);
        img = findViewById(R.id.camera_commit);
        img.setOnClickListener(this);
        cancle = findViewById(R.id.camera_cancle);
        cancle.setOnClickListener(this);
        man = findViewById(R.id.sex_btn_man);
        man.setOnClickListener(this);
        woman = findViewById(R.id.sex_btn_women);
        woman.setOnClickListener(this);

        ageselect = findViewById(R.id.age_select);
        ageselect.setOnClickListener(this);
        ageselect.setClickable(true);
        ageselecttv = findViewById(R.id.age_select_tv);
        ageselecttv.setOnClickListener(this);
    }

    private void initgride() {



        data = (List<ShootPhoto>) getIntent().getSerializableExtra("list");
        gridrecyclerView = findViewById(R.id.photo_layout_recycler);
        GridPhotoAdapter adapter = new GridPhotoAdapter(this,R.layout.gride_item_photo,data);
        gridrecyclerView.setLayoutManager( new GridLayoutManager(this, 5));
        //设置item之间的间距
        gridrecyclerView.addItemDecoration(new CommonItemDecoration(16,0,10,0,10,0));
        gridrecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.close_layout:
                finish();
                break;
            case R.id.camera_commit:
                dialog = new ProgressDialog(context);
                dialog.setCancelable(false);
                dialog.setTitle("分析中....");
                dialog.show();
                if (Popup_xiaofu.isOpmn){
                    getResultData();
                }else {
                    commitData();
                }
                break;
            case R.id.camera_cancle:

                finish();
            case R.id.sex_btn_man:
                man.setImageResource(R.drawable.skin_man_btn_s);
                man.setClickable(false);
                woman.setImageResource(R.drawable.skin_women_btn_n);
                woman.setClickable(true);
                break;
            case R.id.sex_btn_women:
                man.setImageResource(R.drawable.skin_man_btn_n);
                man.setClickable(true);
                woman.setImageResource(R.drawable.skin_women_btn_s);
                woman.setClickable(false);
                break;
            case  R.id.age_select:
               initAgepop();
                 break;
            case R.id.age_select_tv :
                initAgepop();

                break;
        }
    }

    private void initAgepop() {

        PopUtils popUtils = new PopUtils(context,R.layout.picker_layout,width/5,height/3,ageselect,Gravity.BOTTOM|Gravity.CENTER,0,0, new PopUtils.ClickListener() {
            @Override
            public void setUplistener(PopUtils.PopBuilder builder) {
                PickerScrollView pick = builder.getView(R.id.pickerscrlllview);
                Button queding = builder.getView(R.id.picker_yes);
                queding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ageselecttv.setText(age);
                        PopUtils.PopBuilder.getWindow().dismiss();
                    }
                });
                pick.setData(agedata);
                pick.setSelected(0);
                pick.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(Pickers pickers) {
                        age = pickers.getShowId();
                        // age  = pickers.getShowConetnt();
                    }
                });

            }
        });
    }


    //发送后端获取结果
    private void getResultData(){
        SkinDeatil skinDeatil =  SPUtils.getSkinDetail(CommitActivity.this, "SkinDeatil");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
//        RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=GBK"),
//                URLDecoder.decode(jsonstr, "UTF-8"))
        retrofit2.Call<ResponseBody> model = service.getFullSkinData(skinDeatil);
        model.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String jsonstr = null;
                try {
                    jsonstr = new String(response.body().bytes());
                    Gson gson = new Gson();
                    Type type = new TypeToken<SkinDeatil>(){}.getType();//用于获取泛型信息
                    SkinDeatil skin = gson.fromJson(jsonstr, type);
                     Intent intent = new Intent(CommitActivity.this,SkinSwitchProductActivity.class);
                    intent.putExtra("skin", skin);
                     startActivity(intent);
                     finish();
                    closeDialog();
                    Log.i("json信息：",skin.getData().getDistinguishDataList2().size()+"");
                } catch (IOException e) {
                    e.printStackTrace();
                    closeDialog();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                closeDialog();
            }
        });

       // Intent intent = new Intent(CommitActivity.this,SkinSwitchProductActivity.class);
        //intent.putExtra("skin", skin);
       // startActivity(intent);
      //  finish();
     //   closeDialog();
    }
    //保存提交测试的结果
    private void saveDta(SkinDeatil skinDeatil){

        SPUtils.putBean(this, "SkinDeatil", skinDeatil);
//        if (null != SPUtils.getSkinDetail(this, "SkinDeatil")){
//          //  SPUtils.removeBean(this, "SkinDeatil");
//            SPUtils.putBean(this, "SkinDeatil", skinDeatil);
//        }else {
//            SPUtils.putBean(this, "SkinDeatil", skinDeatil);
//        }
    }

    //提交检测获取结果
    private void commitData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-test.xiaofutech.com/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        GankService service = retrofit.create(GankService.class);
        Map<String, RequestBody> params = new HashMap<>();

        params.put("lng", RequestBody.create(MediaType.parse("text/plain"),"47.55" ));
        params.put("lat", RequestBody.create(MediaType.parse("text/plain"),"47.55" ));
        params.put("file\";filename=\"forehead_rgb\"", RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(0).picRgb)));
        params.put("file\";filename=\"forehead_pl\"",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(0).picPl)));
        params.put("file\";filename=\"leftface_rgb\"", RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(1).picRgb)));
        params.put("file\";filename=\"leftface_pl\"",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(1).picPl)));
        params.put("file\";filename=\"rightface_rgb\"", RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(2).picRgb)));
        params.put("file\";filename=\"rightface_pl\"",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(2).picPl)));
        params.put("file\";filename=\"nose_rgb\"", RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(3).picRgb)));
        params.put("file\";filename=\"nose_pl\"",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(3).picPl)));
        params.put("file\";filename=\"chin_rgb\"", RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(4).picRgb)));
        params.put("file\";filename=\"chin_pl\"",  RequestBody.create(MediaType.parse("multipart/form-data"), new File(data.get(4).picPl)));



        retrofit2.Call<SkinDeatil> model = service.uploadFile3(params,"3b9a27a0-ff27-4a2c-bf2c-7c57c6f25850","","");
        model.enqueue(new retrofit2.Callback<SkinDeatil>() {
            @Override
            public void onResponse(retrofit2.Call<SkinDeatil> call, retrofit2.Response<SkinDeatil> response) {
                SkinDeatil skin = response.body();
                saveDta(skin);
                //Intent intent = new Intent(CommitActivity.this,FullFaceSkin.class);
                Intent intent = new Intent(CommitActivity.this,SkinSwitchProductActivity.class);
                intent.putExtra("skin", skin);
                startActivity(intent);
                finish();
                closeDialog();
            }

            @Override
            public void onFailure(retrofit2.Call<SkinDeatil> call, Throwable t) {
                LogInfo.i(Tag, t.toString());
                closeDialog();
            }
        });

        //显示返回的json字符串
//        retrofit2.Call<ResponseBody> model = service.uploadFile2(params,"3b9a27a0-ff27-4a2c-bf2c-7c57c6f25850","","");
//        model.enqueue(new retrofit2.Callback<ResponseBody>() {
//            @Override
//            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                String jsonstr = null;
//                try {
//                    jsonstr = new String(response.body().bytes());
//                    Log.i("json信息：",jsonstr);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
//
//            }
//        });



    }

    private void closeDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
