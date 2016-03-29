package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RecyclerView 封装的适配器
 * Created by Ken on 2016/3/14.
 */
public abstract class AbsRecyclerAdapter<T> extends RecyclerView.Adapter<AbsRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<T> datas;
    private int resid;

    private OnClickListener onClickListener;//点击事件监听
    private OnLongClickListener onLongClickListener;// 长按监听

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public AbsRecyclerAdapter(Context context, int resid){
        this.context = context;
        this.resid = resid;
        this.datas = new ArrayList<>();
    }

    /**
     * 设置数据
     * @param datas
     */
    public void setDatas(List<T> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    /**
     *添加数据
     */
    public void addData(T data, int position){
        this.datas.add(position, data);
        this.notifyItemInserted(position);
    }

    /**
     * 删除数据
     * @param position
     */
    public void deleteData(int position){
        this.datas.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public AbsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resid, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AbsRecyclerAdapter.MyViewHolder holder, int position) {
        bindDatas(holder, datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    protected abstract void bindDatas(MyViewHolder myViewHolder, T data, int position);

    class MyViewHolder extends RecyclerView.ViewHolder{

        private Map<Integer, View> cacheMap = new HashMap<>();
        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        onClickListener.onClick(v, getAdapterPosition());
                    }
                }
            });

            this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onLongClickListener != null){
                        onLongClickListener.onLongClick(v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }

        public View getView(int id){
            if(cacheMap.containsKey(id)){
                return cacheMap.get(id);
            } else {
                View view = itemView.findViewById(id);
                cacheMap.put(id, view);
                return view;
            }
        }

        /**
         * 文本绑定
         * @param id
         * @param data
         * @return
         */
        public MyViewHolder bindTextView(int id, String data){
            TextView tv = (TextView) getView(id);
            tv.setText(data);
            return this;
        }

        /**
         * 图片绑定
         */

        public MyViewHolder bindSimpleImg(int id,String url){
            SimpleDraweeView simpleImageView = (SimpleDraweeView) this.getView(id);
            simpleImageView.setImageURI(Uri.parse(url));
            return this;
        }
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }

    public interface OnLongClickListener{
        void onLongClick(View v, int position);
    }
}
