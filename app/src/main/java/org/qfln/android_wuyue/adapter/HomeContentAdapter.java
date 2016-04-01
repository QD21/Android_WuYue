package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.HomeContentEntity;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 11:25
 * @备注：
 */
public class HomeContentAdapter extends AbsBaseAdapter<HomeContentEntity.DataEntity.ItemsEntity>{
    private List<HomeContentEntity.DataEntity.ItemsEntity> items;
    public HomeContentAdapter(Context context) {
        super(context, R.layout.homecontent_item);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, HomeContentEntity.DataEntity.ItemsEntity itemsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_homecontent_list,itemsEntity.getCover_image_url())
                .bindTextView(R.id.tv_homecontent_listtitle,itemsEntity.getTitle());
    }
}
