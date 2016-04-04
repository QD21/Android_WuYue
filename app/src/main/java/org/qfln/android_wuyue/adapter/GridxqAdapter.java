package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.Cate_GridEntity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 00:36
 * @备注：
 */
public class GridxqAdapter extends AbsBaseAdapter<Cate_GridEntity.DataEntity.ItemsEntity> {
    public GridxqAdapter(Context context) {
        super(context, R.layout.cate_grid_item);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, Cate_GridEntity.DataEntity.ItemsEntity itemsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_categrid1,itemsEntity.getCover_image_url())
                .bindTextView(R.id.tv_cate_name1,itemsEntity.getName())
                .bindTextView(R.id.tv_cate_price1,itemsEntity.getPrice());
    }
}
