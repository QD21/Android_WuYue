package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.CommitEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/6 14:36
 * @备注：
 */
public class CommitAdapter extends AbsBaseAdapter<CommitEntity.DataEntity.CommentsEntity>{
    public CommitAdapter(Context context) {
        super(context, R.layout.catecommit_item);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, CommitEntity.DataEntity.CommentsEntity commentsEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_commit,commentsEntity.getUser().getAvatar_url())
                .bindTextView(R.id.tv_com_title,commentsEntity.getUser().getNickname())
                .bindTextView(R.id.tv_com_content,commentsEntity.getContent());
        long created_at = commentsEntity.getCreated_at();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss  yyyy-MM-dd");
        Date dt= new Date();
        Long time= dt.getTime();
        String date = simpleDateFormat.format(new Date(time-created_at));
//        String format = formatter.format(calendar.getTime());
        viewHolder.bindTextView(R.id.tv_com_time,date);
    }
}
