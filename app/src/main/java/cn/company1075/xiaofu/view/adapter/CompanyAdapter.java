package cn.company1075.xiaofu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.utils.UserData;

public class CompanyAdapter extends BaseAdapter {

    private List<UserData.DataBean> list;
    private Context context;

    public CompanyAdapter(Context context, List<UserData.DataBean> list) {
        this.list = list;
        this.context = context;
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
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.tv_item, null);
            holder.tvName=convertView.findViewById(R.id.item_tv);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position).getPlatformUserOpenid());
        return convertView;
    }




    private class ViewHolder{
        private TextView tvName;
    }


}
