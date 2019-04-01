package cn.company1075.xiaofu.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.company1075.xiaofu.GankService;
import cn.company1075.xiaofu.ProductActivity;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.utils.Goods;
import cn.company1075.xiaofu.utils.GoodsDetail;
import cn.company1075.xiaofu.utils.OkHttpUtils;
import cn.company1075.xiaofu.utils.xiaofu.PopUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;

public class GridRecycleViewAdapter extends  RecyclerView.Adapter<GridRecycleViewAdapter.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Goods.DataBean> data;
    private int mItemLayout;
    private  Context context;
    private int width;
    private int height;

    public GridRecycleViewAdapter(Context context, int itemLayout, List<Goods.DataBean> datalist) {
        mLayoutInflater = LayoutInflater.from(context);
        mItemLayout = itemLayout;
        data = datalist;
        this.context = context;
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
    }


    public void setData(List<Goods.DataBean> data){
            this.data = data;
            notifyDataSetChanged();
        }


    @NonNull
    @Override
    public GridRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(mItemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final GridRecycleViewAdapter.MyViewHolder holder, final int position) {

        holder.tv.setText(data.get(position).getGoodName());
        Glide.with(context).load(data.get(position).getGoodImage()).into(holder.img);

        holder. img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("value", (Long) data.get(position).getGoodId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.recycler_item_image);
            tv = itemView.findViewById(R.id.recycler_item_tv);
            //设置宽与高长度相等
            /*LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) img.getLayoutParams();
            params.height = params.width;
            img.setLayoutParams(params);*/
        }
    }
}
