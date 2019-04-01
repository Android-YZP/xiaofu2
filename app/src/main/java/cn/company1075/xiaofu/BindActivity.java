package cn.company1075.xiaofu;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.utils.APKVersionCodeUtils;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.MacAdressUtils;
import cn.company1075.xiaofu.utils.MyListView;
import cn.company1075.xiaofu.utils.MyResult;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import cn.company1075.xiaofu.utils.PopUtils2;
import cn.company1075.xiaofu.utils.SPUtils;
import cn.company1075.xiaofu.utils.ShopData;
import cn.company1075.xiaofu.utils.ShopUserDeviceMan;
import cn.company1075.xiaofu.utils.UserData;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import cn.company1075.xiaofu.view.adapter.CompanyAdapter;
import cn.company1075.xiaofu.view.adapter.ShopAdapter;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//登录界面
public class BindActivity extends Activity implements View.OnClickListener{

    private int width;
    private int height;
    //private RelativeLayout bind_company,bind_shop;
    private ImageView bind;
    private Button close;
    private TextView name,pwd;//账号密码
//    private ListView companyListView,shopListView;
//    private CompanyAdapter companyAdapter;
//    private ShopAdapter shopAdapter;
    List<UserData.DataBean> companydata;
    List<ShopData.DataBean> shopdata;
    int  platformUserId;
    boolean isSelectCompany = false;//是否选择了平台用户（公司）
    boolean isSelectShop = false;
    int shopid;
    private Dialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bindshopinfo);
        initWindow();
    //   initCompanyData();
        initView();

    }



    private void initWindow() {
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
        lp.height = height*5/6+40;
        lp.gravity = Gravity.CENTER|Gravity.BOTTOM;
        getWindow().setAttributes(lp);

    }

    private void initView() {
//        bind_company = findViewById(R.id.select_company);
//        bind_company.setOnClickListener(this);
//        bind_shop = findViewById(R.id.select_shop);
//        bind_shop.setOnClickListener(this);

        bind = findViewById(R.id.btn_bind);
        bind.setOnClickListener(this);
        close = findViewById(R.id.close);
        close.setOnClickListener(this);
        name = findViewById(R.id.select_company_name);
        pwd = findViewById(R.id.select_shop_name);

        if (null != User.getInstance().name && !User.getInstance().name.equals("1") ){
            name.setText(User.getInstance().name);
            pwd.setText(User.getInstance().pwd);
        }
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (name.getText().toString().trim().equals("")){
                        pwd.setText("");
                }
            }
        });

    }


    //初始化门店数据
    private void initShopData(long  platformUserId) {
        if (null == shopdata){
            shopdata = new ArrayList<>();
        }else {
            shopdata.clear();
        }

        Log.e("Bind",shopdata.size()+"");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
        retrofit2.Call<ShopData> medol = service.getAllShop(platformUserId);
        medol.enqueue(new Callback<ShopData>() {
            @Override
            public void onResponse(Call<ShopData> call, Response<ShopData> response) {
                shopdata = response.body().getData();
                Log.e("Bind",shopdata.size()+"");
            }

            @Override
            public void onFailure(Call<ShopData> call, Throwable t) {

            }
        });

    }

    //初始化平台用户数据
    private void initCompanyData() {
        companydata = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
        retrofit2.Call<UserData> model =  service.getUserData();
        model.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                companydata = response.body().getData();
                Log.e("Bind", response.body().getData().get(0).getPlatformUserToken());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.select_company:
               // initSelectCompanyPop();
                break;
            case R.id.select_shop:

                break;
            case R.id.btn_bind:
                login();
//                if (isSelectCompany && isSelectShop){
//
//                   // bind();
//                }else {
//                    Toast.makeText(this, "请先选择公司和门店", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.close:
                finish();
                break;


        }

    }

    private void login() {
//        dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setTitle("正在登陆...");
//        dialog.show();

      if (TextUtils.isEmpty(name.getText().toString().trim()) || TextUtils.isEmpty(pwd.getText().toString().trim())){
          Toast.makeText(this, "账号或者密码不能为空 !", Toast.LENGTH_SHORT).show();
       //   closeDialog();
          return ; }

          final String username = name.getText().toString().trim();
         final String userpwd = pwd.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
        retrofit2.Call<ResponseBody> model =  service.Login(name.getText().toString().trim(), pwd.getText().toString().trim());
        model.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
              //  closeDialog();
                String jsonstr = null;
                try {
                    jsonstr = new String(response.body().bytes());
                    Log.i("json信息：",jsonstr);
                    JSONObject jsonObject = new JSONObject(jsonstr);
                    Integer code = (Integer) jsonObject.get("code");
                    String meg = (String) jsonObject.get("msg");
                    if (code == 0){
                        finish();
                        initCompleteToast();
                        Integer id  = (Integer) jsonObject.get("id");
                        save(id,username,userpwd);
                        LogInfo.i("LoginResult", id);
                        clearInput();
                    }else {
                        Toast.makeText(BindActivity.this, meg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(BindActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    LogInfo.i("LoginResult", e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                closeDialog();

            }
        });

         }

    private void closeDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    //清除输入框
    private  void clearInput(){
        if (null != name && null != pwd){
            name.setText("");
            pwd.setText("");
        }
    }


    //门店弹框
//    private void initSelectShopPop() {
//
//        PopUtils popUtils = new PopUtils(BindActivity.this, R.layout.bind_list, 520, 180, bind_shop, Gravity.CENTER | Gravity.TOP, 0, 540, new PopUtils.ClickListener() {
//            @Override
//            public void setUplistener(PopUtils.PopBuilder builder) {
//                shopListView = builder.getView(R.id.bind_list);
//                    shopAdapter = new ShopAdapter(BindActivity.this,shopdata);
//                    shopListView.setAdapter(shopAdapter);
//                    shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        shopName.setText(shopdata.get(i).getShopName());
//                        bind_shop.setBackgroundResource(R.drawable.btn_store_s);
//                        PopUtils.PopBuilder.getWindow().dismiss();
//                        isSelectShop = true;
//                        shopid  = shopdata.get(i).getShopId();
//                    }
//                });
//            }
//        });
//
//
//    }

    //平台用户弹框
//    private void initSelectCompanyPop() {
//        PopUtils popUtils = new PopUtils(BindActivity.this, R.layout.bind_list, 520, 180, bind_company, Gravity.CENTER | Gravity.TOP, 0, 320, new PopUtils.ClickListener() {
//            @Override
//            public void setUplistener(PopUtils.PopBuilder builder) {
//                companyListView = builder.getView(R.id.bind_list);
//                companyAdapter = new CompanyAdapter(BindActivity.this,companydata);
//                companyListView.setAdapter(companyAdapter);
//                companyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        isSelectCompany = true;
//                        companyName.setText(companydata.get(i).getPlatformUserOpenid());
//                        bind_company.setBackgroundResource(R.drawable.btn_store_s);
//                        PopUtils.PopBuilder.getWindow().dismiss();
//                        platformUserId = companydata.get(i).getPlatformUserId();
//                        initShopData(platformUserId);
//                        shopName.setText("");
//
//                    }
//                });
//            }
//        });
//    }


    //绑定接口
//    private void bind(){
//        String version  = APKVersionCodeUtils.getVersionCode(BindActivity.this)+"";
//        String name = APKVersionCodeUtils.getVerName(BindActivity.this);
//         String  Mac = MacAdressUtils.getMac(BindActivity.this);
//        LogInfo.i("============", version+name+Mac+shopid);
//
////        ShopUserDeviceMan shopUserDeviceMan = new ShopUserDeviceMan();
////        shopUserDeviceMan.setMirrorVersion(APKVersionCodeUtils.getVersionCode(BindActivity.this)+"");
////        shopUserDeviceMan.setMirrorName(APKVersionCodeUtils.getVerName(BindActivity.this));
////        shopUserDeviceMan.setMirrorMac(MacAdressUtils.getMac(BindActivity.this));
////        shopUserDeviceMan.setShopId(shopid);
//
//
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(User.URL) //设置网络请求的Url地址
//                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
//                .build();
//        GankService service = retrofit.create(GankService.class);
//        retrofit2.Call<MyResult> model = service.Bind2(shopid+"",name, Mac,version+"");
//
//        model.enqueue(new Callback<MyResult>() {
//            @Override
//            public void onResponse(Call<MyResult> call, Response<MyResult> response) {
//                MyResult result = response.body();
//                if (result.getCode() == 0){
//                        initCompleteToast();
//                        save();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MyResult> call, Throwable t) {
//
//            }
//        });
//
//
//
//    }

    //保存绑定的用户
    private void save(int id,String name,String pwd) {
        User user  = User.getInstance();
        user.shopid = id;
        user.name  = name;
        user.pwd = pwd;
        if (null != SPUtils.getUser(BindActivity.this, "User")){
            SPUtils.putBean(BindActivity.this, "User", user);
        }else {
            SPUtils.putBean(BindActivity.this, "User", user);
        }
    }

    private void initCompleteToast() {
        View view = LayoutInflater.from(BindActivity.this).inflate(R.layout.bindoverpop, null);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }


}
