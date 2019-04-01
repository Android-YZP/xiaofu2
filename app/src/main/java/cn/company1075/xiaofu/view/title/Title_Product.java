package cn.company1075.xiaofu.view.title;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.company1075.xiaofu.GankService;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.utils.Goods;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.OkHttp3Utils;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import cn.company1075.xiaofu.utils.RefreshRecyclerView;
import cn.company1075.xiaofu.utils.SmoothLinearLayoutManager;
import cn.company1075.xiaofu.utils.Tc;
import cn.company1075.xiaofu.utils.xiaofu.BannerIndicator;
import cn.company1075.xiaofu.utils.xiaofu.CommonItemDecoration;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import cn.company1075.xiaofu.view.adapter.BannerAdapter;
import cn.company1075.xiaofu.view.adapter.GridRecycleViewAdapter;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/1/10.
 */
public class Title_Product extends MyLayoutUnMovable implements View.OnClickListener{

    private static final String TAG = "Title_Time";

    private Context context;
    public RelativeLayout layout;
    private int width;
    private int height;
    private RefreshRecyclerView gridrecyclerView;
    private  RecyclerView bannerRecyclerView;
    ScheduledExecutorService scheduledExecutorService;
    SmoothLinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    int goods_page = 1;//
    GridRecycleViewAdapter adapter;
    BannerAdapter bannerAdapter;


    private List<Tc.DataBean> bannerData;//
    private List<Goods.DataBean> grideData = new ArrayList<>();//
    PopUtils popUtils;


    public Title_Product(Context context, Title_Info position) {
        super(context, R.layout.title_product, position);
        this.context = context;
        layout = findViewById(R.id.title_product);
        layout.setOnClickListener(this);
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;

//        initBannerData();
       initGrideData();
    }


    private void initGride(PopUtils.PopBuilder builder){
        swipeRefreshLayout = builder.getView(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);
        gridrecyclerView  = builder.getView(R.id.product_gride_layout_recycler);
        gridrecyclerView.setLayoutManager( new GridLayoutManager(context, 4));
        gridrecyclerView.addItemDecoration(new CommonItemDecoration(20,20,0,0,0,0));
        gridrecyclerView.setLoadMoreEnable(true);//允许加载更多
        gridrecyclerView.setFooterResource(R.layout.item_footer);//设置脚布局
        adapter = new GridRecycleViewAdapter(context,R.layout._item_grid_img,grideData);
        //设置item之间的间距
        gridrecyclerView.setAdapter(adapter);


        initListener();


        Button close = builder.getView(R.id.gride_close_layout);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.PopBuilder.getWindow().dismiss();
            }
        });
    }



    //初始化轮播图
    private void initBanner(PopUtils.PopBuilder builder){
        bannerAdapter = new BannerAdapter(context, bannerData);
        bannerRecyclerView= builder.getView(R.id.product_layout_recycler);
        layoutManager = new SmoothLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        bannerRecyclerView.setLayoutManager(layoutManager);
        bannerRecyclerView.setHasFixedSize(true);
        bannerRecyclerView.setAdapter(bannerAdapter);
        bannerRecyclerView.scrollToPosition(bannerData.size() * 10);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(bannerRecyclerView);


        final BannerIndicator bannerIndicator = builder.getView(R.id.indicator);
        bannerIndicator.setNumber(bannerData.size());

        //下面的点指示器
        bannerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    ////得到指示器红点的位置
                    int i = layoutManager.findFirstVisibleItemPosition() % bannerData.size();
                    bannerIndicator.setPosition(i);
                }
            }
        });

        StartScheduledExecutorService();
        //防止自动轮播切换图片时速度太快scheduledExecutorService实现轮播


      //  if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()){}

//            scheduledExecutorService = Executors.newScheduledThreadPool(1);
//            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    bannerRecyclerView.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
//                }
//            }, 2000, 2000, TimeUnit.MILLISECONDS);




    }



    //初始化Banner的数据
    private void initBannerData(final PopUtils.PopBuilder builder) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
       // retrofit2.Call<Tc> model = service.getTc(User.getInstance().shopid, 1, 5);
       retrofit2.Call<Tc> model = service.getTcAll(8);
        model.enqueue(new Callback<Tc>() {
            @Override
            public void onResponse(Call<Tc> call, Response<Tc> response) {
                Tc data = response.body();
                bannerData  = new ArrayList<>();
                bannerData = data.getData();
                Log.e("Tc",bannerData.size()+"");
                initBanner(builder);
            }

            @Override
            public void onFailure(Call<Tc> call, Throwable t) {

            }
        });


//        if (bannerData.size() == 0){
//            bannerData.add(R.drawable.skin_off_banner_1);
//            bannerData.add(R.drawable.skin_off_banner_2);
//            bannerData.add(R.drawable.skin_off_banner_3);
//            bannerData.add(R.drawable.skin_off_banner_4);
//            bannerData.add(R.drawable.skin_off_banner_5);
//        }

    }

    private void closeBasicPop(){
        //        //关闭所有底层Pop
        if (null !=  AllUser.popup_info &&  AllUser.popup_info.size()>0){
            for (int i = 0; i < AllUser.popup_info.size(); i++) {
                AllUser.close_layout(AllUser.popup_info.get(i),AllUser.popup_info.get(i).getiPosition() );
            }
        }
    }

    //开始轮播
    private void StartScheduledExecutorService (){
            scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    bannerRecyclerView.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                }
            }, 5000, 5000, TimeUnit.MILLISECONDS);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_product:
                closeBasicPop();

                popUtils  = new PopUtils(context, R.layout.popup_product,  width, height-160, layout,Gravity.BOTTOM, 0, 0, new PopUtils.ClickListener() {
                    @Override
                    public void setUplistener(final PopUtils.PopBuilder builder) {
                        PopUtils.PopBuilder.getWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                if (null != scheduledExecutorService){
                                    scheduledExecutorService.shutdown();

                                }
                            }
                        });
                       final Button discount = builder.getView(R.id.product_discount);
                        final Button introduce = builder.getView(R.id.product_introduce);
                        final RelativeLayout banner = builder.getView(R.id.product_bannner_layout);
                        final RelativeLayout gride = builder.getView(R.id.product_gride_layout);


                        discount.setClickable(false);
                        introduce.setClickable(true);
                        banner.setVisibility(View.VISIBLE);
                        gride.setVisibility(View.GONE);


                        initBannerData(builder);
                         initGride(builder);


                        Button close  =  builder.getView(R.id.close_layout);
                        close.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PopUtils.PopBuilder.getWindow().dismiss();
                                if (null != scheduledExecutorService){
                                    scheduledExecutorService.shutdown();
                                }
                            }
                        });


                        discount.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                discount.setClickable(false);
                                introduce.setClickable(true);
                                discount.setBackgroundResource(R.drawable.skin_off_btn_s);
                                introduce.setBackgroundResource(R.drawable.skin_product_btn_n);
                                banner.setVisibility(View.VISIBLE);
                                gride.setVisibility(View.GONE);

                                if (scheduledExecutorService.isShutdown()){
                                    StartScheduledExecutorService();
                                }


                            }
                        });

                        introduce.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                introduce.setClickable(false);
                                discount.setClickable(true);
                                discount.setBackgroundResource(R.drawable.skin_off_btn_n);
                                introduce.setBackgroundResource(R.drawable.skin_product_btn_s);
                                banner.setVisibility(View.GONE);
                                if (null != scheduledExecutorService){
                                    scheduledExecutorService.shutdown();
                                }
                                gride.setVisibility(View.VISIBLE);


                            }
                        });

                    }
                });


                break;
        }
    }


    //网格的上拉刷新 下拉加载监听
    private void initListener() {

        if (null == gridrecyclerView || null == swipeRefreshLayout){
            return;
        }

        gridrecyclerView.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goods_page++;
                        initGrideData();
                    }
                }, 5000);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gridrecyclerView.setLoadMoreEnable(true);
                        swipeRefreshLayout.setRefreshing(false);
                        goods_page = 1;
                        grideData.clear();
                        initGrideData();
                    }
                }, 2000);
            }
        });
    }


    private Handler handler = new Handler();



    private void initGrideData() {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
        retrofit2.Call<Goods> model = service.getShopGoods(User.getInstance().shopid, goods_page, 8);
        model.enqueue(new Callback<Goods>() {
            @Override
            public void onResponse(Call<Goods> call, Response<Goods> response) {
                List<Goods.DataBean> dataBean = response.body().getData();
                if (grideData.size()>0){
                    grideData.addAll(dataBean);
                    if (null != gridrecyclerView){
                        gridrecyclerView.notifyData();//刷新数据
                    }
                }
                if (grideData.size()==0){
                    grideData = dataBean;
                    if (null != adapter){
                        adapter.setData(grideData);
                    }
                }
                if (dataBean.size()<8){
                    if (null != gridrecyclerView){
                        gridrecyclerView.setLoadMoreEnable(false);
                       // Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<Goods> call, Throwable t) {
                LogInfo.i("initGrideData", t.toString());

            }
        });

    }


}
