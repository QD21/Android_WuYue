package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.GridActivity;
import org.qfln.android_wuyue.bean.HotListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 23:58
 * @备注：
 */
public class HotListAdapter extends BaseAdapter {
    private List<HotListEntity.DataEntity.ItemsEntity> hotlist;
    Context context;

    public HotListAdapter(Context context) {
        this.context = context;
        hotlist = new ArrayList<>();
    }

    public void setDatas(List<HotListEntity.DataEntity.ItemsEntity> list) {
        this.hotlist = list;
        notifyDataSetChanged();
    }

    public void addData(List<HotListEntity.DataEntity.ItemsEntity> list) {
        this.hotlist.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return hotlist.size() / 2;
    }

    @Override
    public Object getItem(int position) {
        return hotlist.get(position / 2);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_list_item, null);
            holder.sdvList1 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_hotlist1);
            holder.sdvList2 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_hotlist2);
            holder.tvName1 = (TextView) convertView.findViewById(R.id.tv_hot_name1);
            holder.tvName2 = (TextView) convertView.findViewById(R.id.tv_hot_name2);
            holder.tvPrice1 = (TextView) convertView.findViewById(R.id.tv_hot_price1);
            holder.tvPrice2 = (TextView) convertView.findViewById(R.id.tv_hot_price2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final HotListEntity.DataEntity.ItemsEntity.Data1Entity hot_data = hotlist.get(position * 2).getData();
        final HotListEntity.DataEntity.ItemsEntity.Data1Entity hot_data2 = hotlist.get(position * 2 + 1).getData();
        holder.tvName1.setText(hot_data.getName());
        holder.tvName2.setText(hot_data2.getName());
        holder.tvPrice1.setText(hot_data.getPrice());
        holder.tvPrice2.setText(hot_data2.getPrice());

        Uri uri1 = Uri.parse(hotlist.get(position * 2).getData().getCover_image_url());
        holder.sdvList1.setImageURI(uri1);
        Uri uri2 = Uri.parse(hotlist.get(position * 2 + 1).getData().getCover_image_url());
        holder.sdvList2.setImageURI(uri2);
        //点击事件监听
        sdvOnclick(holder, hot_data, hot_data2);
        return convertView;
    }

    private void sdvOnclick(ViewHolder holder, final HotListEntity.DataEntity.ItemsEntity.Data1Entity hot_data, final HotListEntity.DataEntity.ItemsEntity.Data1Entity hot_data2) {
        holder.sdvList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hot_id = hot_data.getId();
                Intent intent=new Intent(context, GridActivity.class);
                intent.putExtra("Grid_id",hot_id);
                context.startActivity(intent);
            }
        });
        holder.sdvList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hot_id = hot_data2.getId();
                Intent intent=new Intent(context, GridActivity.class);
                intent.putExtra("Grid_id",hot_id);
                context.startActivity(intent);
            }
        });
    }

    class ViewHolder {
        SimpleDraweeView sdvList1, sdvList2;
        TextView tvName1, tvName2, tvPrice1, tvPrice2;
    }
}
