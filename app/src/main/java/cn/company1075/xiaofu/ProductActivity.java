package cn.company1075.xiaofu;

import android.app.Activity;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.utils.GoodsDetail;
import cn.company1075.xiaofu.utils.GsonUtils;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class ProductActivity extends Activity{


    private int width;
    private int height;
    Long goodId;

    GoodsDetail info;
    Button close;
    ImageView photo;
    TextView name,priceAndsize,goodDescribe,goodUseMethod;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.productdetail);
        initWindow();
        initData();

    }

    private void initData() {
        goodId = getIntent().getLongExtra("value", 1 );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
        retrofit2.Call<GoodsDetail> model = service.getShopGoodsDetail(goodId);
        model.enqueue(new Callback<GoodsDetail>() {
            @Override
            public void onResponse(Call<GoodsDetail> call, Response<GoodsDetail> response) {
                info = response.body();
                initView();
                LogInfo.i("ProductActivity-initData", info.getData().get(0).getGmtModified());
            }

            @Override
            public void onFailure(Call<GoodsDetail> call, Throwable t) {

            }
        });




    }

    private void initView() {
        photo = findViewById(R.id.photo);

        close = findViewById(R.id.close_layout);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name = findViewById(R.id.goodsname);
        priceAndsize = findViewById(R.id.goodsprice);
        goodDescribe = findViewById(R.id.goodDescribe);
        goodUseMethod = findViewById(R.id.goodUseMethod);

        Glide.with(ProductActivity.this).load(info.getData().get(0).getGoodImage()).into(photo);
        name.setText(info.getData().get(0).getGoodName());
        priceAndsize.setText("¥"+info.getData().get(0).getGoodPrice()+"/"+info.getData().get(0).getGoodMl());
        goodDescribe.setText(info.getData().get(0).getGoodDescribe());
        goodUseMethod.setText(info.getData().get(0).getGoodUseMethod());

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
        lp.width = width*2/3;
        lp.height = height*4/5;
        lp.gravity = Gravity.CENTER|Gravity.BOTTOM;
        getWindow().setAttributes(lp);

    }
}
