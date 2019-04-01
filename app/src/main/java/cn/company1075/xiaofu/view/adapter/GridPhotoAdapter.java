package cn.company1075.xiaofu.view.adapter;

import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.ShootPhoto;

public class GridPhotoAdapter  extends RecyclerView.Adapter<GridPhotoAdapter.MyViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<ShootPhoto> data;
    private int mItemLayout;
    private Context context;

    public GridPhotoAdapter(Context context, int itemLayout, List<ShootPhoto> datalist) {
        mLayoutInflater = LayoutInflater.from(context);
        mItemLayout = itemLayout;
        data = datalist;
        this.context = context;
    }


    @NonNull
    @Override
    public GridPhotoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new MyViewHolder(mLayoutInflater.inflate(mItemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridPhotoAdapter.MyViewHolder holder, int position) {
        ShootPhoto photo = data.get(position);
        Glide.with(context).load(photo.picPl).into(holder.img);
        switch (position){
            case 0 :
                holder.textView.setText("①");
                break;
            case 1 :
                holder.textView.setText("②");
                break;
            case 2 :
                holder.textView.setText("③");
                break;
            case 3 :
                holder.textView.setText("④");
                break;
            case 4 :
                holder.textView.setText("⑤");
                break;




        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.gride_item_pgoto_img);
            textView =  itemView.findViewById(R.id.gride_item_pgoto_tv);

        }
    }
}
