package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.WebXQActivity;
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
    private Context context;
    public HomeContentAdapter(Context context) {
        super(context, R.layout.homecontent_item);
        this.context=context;
    }

    @Override
    protected void bindData(ViewHolder viewHolder, final HomeContentEntity.DataEntity.ItemsEntity itemsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_homecontent_list,itemsEntity.getCover_image_url())
                .bindTextView(R.id.tv_homecontent_listtitle,itemsEntity.getTitle());
        SimpleDraweeView sdv = (SimpleDraweeView) viewHolder.getView(R.id.sdv_homecontent_list);
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, WebXQActivity.class);
                intent.putExtra("item_id",itemsEntity.getId());
                intent.putExtra("gl_title",itemsEntity.getTitle());
                intent.putExtra("image",itemsEntity.getCover_image_url());
                intent.putExtra("share_msg",itemsEntity.getShare_msg());
                context.startActivity(intent);



            }
        });

    }
}
