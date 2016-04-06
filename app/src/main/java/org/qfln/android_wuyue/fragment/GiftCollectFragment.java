package org.qfln.android_wuyue.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.RecyclerAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.HomeHead2Entity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 11:19
 * @备注：
 */
public class GiftCollectFragment extends BaseFragment{
    private String url = Constant.URL.HOMEHEAD2_URL;
    private RecyclerView mrv;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.collect_layout;
    }
    public static GiftCollectFragment newInstance(String title){
        GiftCollectFragment giftCollectFragment=new GiftCollectFragment();
        return giftCollectFragment;
    }

    @Override
    protected void init(View view) {
        mrv = (RecyclerView)view.findViewById(R.id.rv_collect);
        //设置布局管理
        mrv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerAdapter = new RecyclerAdapter(getContext());
        VolleyUtil.requestString(url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response != null) {
                    HomeHead2Entity entity = new Gson().fromJson(response.toString(), HomeHead2Entity.class);
                    HomeHead2Entity.DataEntity data1 = entity.getData();
                    List<HomeHead2Entity.DataEntity.SecondaryBannersEntity> secondary_banners = data1.getSecondary_banners();
                    recyclerAdapter.setDatas(secondary_banners);
                    //设置适配器
                    mrv.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
