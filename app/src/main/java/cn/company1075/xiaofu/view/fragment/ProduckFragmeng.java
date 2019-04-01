package cn.company1075.xiaofu.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.company1075.xiaofu.GankService;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.utils.Goods;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import cn.company1075.xiaofu.utils.RefreshRecyclerView;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.utils.xiaofu.CommonItemDecoration;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import cn.company1075.xiaofu.view.adapter.GridRecycleViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProduckFragmeng extends Fragment{

    SwipeRefreshLayout swipeRefreshLayout;
    int goods_page = 1;//
    private RefreshRecyclerView gridrecyclerView;
    private List<Goods.DataBean> grideData = new ArrayList<>();//
    GridRecycleViewAdapter adapter;
    int goodTag = 1;
    private TextView nonegoods;//暂无推荐
    SkinDeatil skin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.productfragment, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGrideData();
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_fragment);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);
        gridrecyclerView  = view.findViewById(R.id.product_gride_layout_recycler);
        gridrecyclerView.setLayoutManager( new GridLayoutManager(getActivity(), 5));
        gridrecyclerView.addItemDecoration(new CommonItemDecoration(5,10,20,0,0,0));
        gridrecyclerView.setLoadMoreEnable(true);//允许加载更多
        gridrecyclerView.setFooterResource(R.layout.item_footer);//设置脚布局
        adapter = new GridRecycleViewAdapter(getActivity(),R.layout._item_grid_img,grideData);
        //设置item之间的间距
        gridrecyclerView.setAdapter(adapter);
        initListener();
        nonegoods = view.findViewById(R.id.nonegoods);
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

        if (isAdded()){
            skin = (SkinDeatil) getArguments().getSerializable("SkinData");
        }

        //根据皮质推荐产品
        if (skin.getData().getPfType().contains("油")){
            goodTag = 1;
        }else if (skin.getData().getPfType().contains("中")){
            goodTag = 2;
        }else if (skin.getData().getPfType().contains("干")){
            goodTag =  3;
        }else {
            goodTag = 4;
        }

        LogInfo.i("ProduckFragmeng", skin.getData().getPfType()+"  "+goodTag);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        GankService service = retrofit.create(GankService.class);
        retrofit2.Call<Goods> model = service.getRecommendGoods(User.getInstance().shopid,goodTag, goods_page, 10);
        model.enqueue(new Callback<Goods>() {
            @Override
            public void onResponse(Call<Goods> call, Response<Goods> response) {
                List<Goods.DataBean> dataBean = response.body().getData();
                LogInfo.i("ProduckFragmeng",User.getInstance().shopid+"    "+ grideData.size());
                if (grideData.size()>0){
                    grideData.addAll(dataBean);
                    gridrecyclerView.notifyData();//刷新数据
                }
                if (grideData.size() == 0){

                    //无推荐产品时
                    if (dataBean.size() == 0){
                        nonegoods.setVisibility(View.VISIBLE);
                        swipeRefreshLayout.setVisibility(View.GONE);
                        return;
                    }

                    grideData = dataBean;
                    if (null != adapter){
                        adapter.setData(grideData);
                    }
                }
                if (dataBean.size()<10){
                    gridrecyclerView.setLoadMoreEnable(false);
                }

            }

            @Override
            public void onFailure(Call<Goods> call, Throwable t) {

            }
        });

    }


}
