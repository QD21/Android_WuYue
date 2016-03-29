package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.HomeHead2Entity;

/**
 * Created by Ken on 2016/3/14.
 */
public class RecyclerAdapter extends AbsRecyclerAdapter<HomeHead2Entity.DataEntity.SecondaryBannersEntity> {


    public RecyclerAdapter(Context context) {
        super(context, R.layout.homehead_recycler);
    }

    @Override
    protected void bindDatas(MyViewHolder myViewHolder, HomeHead2Entity.DataEntity.SecondaryBannersEntity data, int position) {
        myViewHolder.bindSimpleImg(R.id.sdv_recycler,data.getImage_url());
    }
}
