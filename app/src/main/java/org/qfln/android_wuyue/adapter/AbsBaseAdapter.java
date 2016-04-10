package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/8 23:15
 * @备注：
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> mlist;
    private int resId;

    public AbsBaseAdapter(Context context, int resId) {
        this.context = context;
        this.resId = resId;
        mlist = new ArrayList<>();
    }

    public void setDatas(List<T> list) {
        this.mlist = list;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<T> list) {
        this.mlist.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView=LayoutInflater.from(context).inflate(resId,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //数据绑定
        bindData(holder,mlist.get(position),position);
        return convertView;
    }
    //子类要重写的方法
    protected abstract void bindData(ViewHolder viewHolder, T t, int position);

    public class ViewHolder {

        Map<Integer, View> map = new HashMap<>();
        View LayoutView;
        public ViewHolder(View LayoutView) {
            this.LayoutView = LayoutView;
        }

        public View getView(int id) {
            if (map.containsKey(id)) {
                return map.get(id);
            } else {
                View view = LayoutView.findViewById(id);
                map.put(id, view);
                return view;
            }
        }
        /**
         * textview绑定数据的封装
         * @param id
         * @param data
         * @return
         */
        public ViewHolder bindTextView(int id, String data){
            TextView tv = (TextView) this.getView(id);
            tv.setText(data);
            return this;
        }

        /**
         * SimpleDraweeView绑定数据的封装
         * @param id
         * @param url
         * @return
         */
        public ViewHolder bindSimpleImg(int id,String url){
            SimpleDraweeView simpleImageView = (SimpleDraweeView) this.getView(id);
            if(url!=null) {
                simpleImageView.setImageURI(Uri.parse(url));
            }
            return this;
        }


    }



}
