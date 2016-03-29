package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.HomeHeadVpItem;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/29 17:08
 * @备注：
 */
public class VpItemAdapter extends AbsBaseAdapter<HomeHeadVpItem.DataEntity.PostsEntity>{
    private List<HomeHeadVpItem.DataEntity.PostsEntity> postlist;
    public VpItemAdapter(Context context) {
        super(context, R.layout.vplist_layout);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, HomeHeadVpItem.DataEntity.PostsEntity postsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_vplistitem,postsEntity.getCover_webp_url())
                .bindTextView(R.id.tv_vpitemtitle,postsEntity.getTitle());
    }
}
