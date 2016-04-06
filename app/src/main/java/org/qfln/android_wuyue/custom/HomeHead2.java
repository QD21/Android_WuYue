package org.qfln.android_wuyue.custom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.HeadTianActivity;
import org.qfln.android_wuyue.activity.HeadVpActivity;
import org.qfln.android_wuyue.adapter.AbsRecyclerAdapter;
import org.qfln.android_wuyue.adapter.RecyclerAdapter;
import org.qfln.android_wuyue.bean.HomeHead2Entity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/29 10:27
 * @备注：
 */
public class HomeHead2 extends LinearLayout implements AbsRecyclerAdapter.OnClickListener {
    private String url = Constant.URL.HOMEHEAD2_URL;
    private RecyclerView recycler;
    private RecyclerAdapter recyclerAdapter;
    private List<HomeHead2Entity.DataEntity.SecondaryBannersEntity> secondary_banners;

    public HomeHead2(Context context) {
        this(context, null);
    }

    public HomeHead2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setData(url);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.homehead2_layout, this, true);
        recycler = (RecyclerView) findViewById(R.id.rv);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));//设置布局管理
        recyclerAdapter = new RecyclerAdapter(getContext());
        recyclerAdapter.setOnClickListener(this);
    }


    public void setData(String data) {
        this.url =data ;

        VolleyUtil.requestString(data, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response != null) {
                    HomeHead2Entity entity = new Gson().fromJson(response.toString(), HomeHead2Entity.class);
                    HomeHead2Entity.DataEntity data1 = entity.getData();
                    secondary_banners = data1.getSecondary_banners();
                    recyclerAdapter.setDatas(secondary_banners);

                    //设置适配器
                    recycler.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
            }
        });
    }

    @Override
    public void onClick(View v, int position) {
        if(position==0) {
            int id = secondary_banners.get(position).getId();
            Intent intent =new Intent(getContext(),HeadTianActivity.class);
            getContext().startActivity(intent);
        }else{
            int id = secondary_banners.get(position).getId();
            Intent intent = new Intent(getContext(), HeadVpActivity.class);
            intent.putExtra("vpitem_id", id);
            getContext().startActivity(intent);
        }
    }
}
