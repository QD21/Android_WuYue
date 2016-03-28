package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.SelectListEntity;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 17:13
 * @备注：
 */
public class SelectAdapter extends AbsBaseAdapter<SelectListEntity.DataEntity.ItemsEntity>{
    private List<SelectListEntity.DataEntity.ItemsEntity> items;
    public SelectAdapter(Context context) {
        super(context, R.layout.homelist_item);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, SelectListEntity.DataEntity.ItemsEntity itemsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_home_list,itemsEntity.getCover_image_url())
        .bindTextView(R.id.tv_home_listtitle,itemsEntity.getTitle());
    }
}
