package cn.company1075.xiaofu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.utils.ShopData;

public class ShopAdapter extends BaseAdapter{

    private List<ShopData.DataBean> list;
    private Context context;

    public ShopAdapter(Context context, List<ShopData.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    public void update(int index,ListView listview){
        //得到第一个可见item项的位置
        int visiblePosition = listview.getFirstVisiblePosition();
        //得到指定位置的视图，对listview的缓存机制不清楚的可以去了解下
        View view = listview.getChildAt(index - visiblePosition);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvName = (TextView) view.findViewById(R.id.item_tv);
        holder.tvName.setText(list.get(index).getShopName());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ShopAdapter.ViewHolder holder=null;
        if(convertView==null){
            holder=new ShopAdapter.ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.tv_item, null);
            holder.tvName=convertView.findViewById(R.id.item_tv);
            convertView.setTag(holder);
        }else{
            holder=(ShopAdapter.ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position).getShopName());
        return convertView;
    }




    private class ViewHolder{
        private TextView tvName;
    }
}
