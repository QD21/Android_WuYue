package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.HeadTianActivity;
import org.qfln.android_wuyue.activity.HeadVpActivity;
import org.qfln.android_wuyue.bean.HomeHead2Entity;

/**
 * Created by Ken on 2016/3/14.
 */
public class RecyclerAdapter extends AbsRecyclerAdapter<HomeHead2Entity.DataEntity.SecondaryBannersEntity> {
    private SimpleDraweeView sdv;
    private Context context;
    public RecyclerAdapter(Context context) {
        super(context, R.layout.homehead_recycler);
        this.context=context;
    }

    @Override
    protected void bindDatas(MyViewHolder myViewHolder, final HomeHead2Entity.DataEntity.SecondaryBannersEntity data, final int position) {
        myViewHolder.bindSimpleImg(R.id.sdv_recycler,data.getImage_url());
        SimpleDraweeView sdv = (SimpleDraweeView) myViewHolder.getView(R.id.sdv_recycler);
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0) {
                    Intent intent =new Intent(context,HeadTianActivity.class);
                    context.startActivity(intent);
                }else{
                    int id = data.getId();
                    Intent intent = new Intent(context, HeadVpActivity.class);
                    intent.putExtra("vpitem_id", id);
                    context.startActivity(intent);
                }
            }
        });
    }

}
