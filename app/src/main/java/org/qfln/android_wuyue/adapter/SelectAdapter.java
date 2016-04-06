package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.WebXQActivity;
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
    private Context context;
    public SelectAdapter(Context context) {
        super(context, R.layout.homelist_item);
        this.context=context;
    }

    @Override
    protected void bindData(ViewHolder viewHolder, final SelectListEntity.DataEntity.ItemsEntity itemsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_home_list,itemsEntity.getCover_image_url())
        .bindTextView(R.id.tv_home_listtitle,itemsEntity.getTitle());
        SimpleDraweeView sdv_list = (SimpleDraweeView) viewHolder.getView(R.id.sdv_home_list);
        sdv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = itemsEntity.getId();
                Intent intent=new Intent(context, WebXQActivity.class);
                intent.putExtra("item_id",id);
                context.startActivity(intent);
            }
        });
    }
}
