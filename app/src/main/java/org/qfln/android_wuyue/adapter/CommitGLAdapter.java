package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.CommitGLEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/7 17:45
 * @备注：
 */
public class CommitGLAdapter extends AbsBaseAdapter<CommitGLEntity.DataEntity.CommentsEntity>{
    public CommitGLAdapter(Context context) {
        super(context, R.layout.catecommit_item);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, CommitGLEntity.DataEntity.CommentsEntity commentsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_commit,commentsEntity.getUser().getAvatar_url())
                .bindTextView(R.id.tv_com_title,commentsEntity.getUser().getNickname())
                .bindTextView(R.id.tv_com_content,commentsEntity.getContent());
        long created_at = commentsEntity.getCreated_at();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss  yyyy-MM-dd");
        Date dt= new Date();
        Long time= dt.getTime();
        String date = simpleDateFormat.format(new Date(time - created_at));
        viewHolder.bindTextView(R.id.tv_com_time,date);
    }
}
