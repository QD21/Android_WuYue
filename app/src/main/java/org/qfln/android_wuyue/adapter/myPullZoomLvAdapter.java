package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.qfln.android_wuyue.R;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/8 10:36
 * @备注：
 */
public class MyPullZoomLvAdapter extends BaseAdapter{
    private Context context;
    String[] str=new String[]{"我的资料","我的收藏","检查更新","清除缓存","关于物悦"};
    int[] bitmap=new int[]{R.mipmap.checkusertype_icon_identity,R.mipmap.ic_default_like_list
    ,R.mipmap.ic_more_action_check_update,R.mipmap.ic_more_action_clean_cache,R.mipmap.ic_more_about};
    public MyPullZoomLvAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.pullzoom_item, null);
            holder.tvWz = (TextView) convertView.findViewById(R.id.tv_pz_item);
            holder.ivTu=(ImageView)convertView.findViewById(R.id.iv_tu);
            holder.ivpz=(ImageView)convertView.findViewById(R.id.iv_pz_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String s = str[position];
        holder.tvWz.setText(s);
        int i = bitmap[position];
        holder.ivTu.setImageResource(i);
        return convertView;
    }
    class ViewHolder{
        private TextView tvWz;
        private ImageView ivTu,ivpz;
    }
}
