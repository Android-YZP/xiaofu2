package cn.company1075.xiaofu.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.utils.Tc;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder>{

    private List<Tc.DataBean> list;
    private Context context;

    public BannerAdapter(Context context,List<Tc.DataBean> list){
        this.list=list;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position%list.size()).getPackageImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.recycler_item_image);
        }

    }
}
