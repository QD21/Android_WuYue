package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.Search2Activity;

import java.util.List;

/**
 * Created by Ken on 2016/3/14.
 */
public class ReSearchAdapter extends AbsRecyclerAdapter<String> {
    private List<String> hot_word;
    private Context context;
    public ReSearchAdapter(Context context) {
        super(context, R.layout.search_hotwords);
        this.context=context;
    }


    @Override
    protected void bindDatas(MyViewHolder myViewHolder, final String data, int position) {
        myViewHolder.bindTextView(R.id.tv_hot_words,data);
        TextView tv = (TextView) myViewHolder.getView(R.id.tv_hot_words);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Search2Activity.class);
                intent.putExtra("hot_word",data);
                context.startActivity(intent);
            }
        });
    }
}
