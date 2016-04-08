package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.qfln.android_wuyue.R;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/8 10:36
 * @备注：
 */
public class PopLvAdapter extends BaseAdapter{
    private Context context;
    String[] str=new String[]{"默认排序","按热度排序","价格由低到高","价格由高到低"};
    public PopLvAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return str[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.poplv_item, null);
            holder.tvWz = (TextView) convertView.findViewById(R.id.tv_popwz);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String s = str[position];
        holder.tvWz.setText(s);
        return convertView;
    }
    class ViewHolder{
        private TextView tvWz;
    }
}
