package org.qfln.android_wuyue;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import org.qfln.android_wuyue.adapter.VpItemAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.HomeHeadVpItem;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshListView;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/29 00:55
 * @备注：
 */
public class HeadVpActivity extends BaseActivity{
    private String vpitem_url= Constant.URL.VPITEM_URL;
    private TextView tvtitle;
    private PullToRefreshListView pullToRefresh;
    private ListView listView;

    @Override
    protected int getContentResId() {
        Fresco.initialize(this);//初始化fresco
        return R.layout.vpitem_layout;
    }

    @Override
    protected void initView() {
        tvtitle = (TextView) findViewById(R.id.tv_title1);
        pullToRefresh = (PullToRefreshListView) findViewById(R.id.pull_lv);
        listView = pullToRefresh.getRefreshableView();
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("vpitem_id",0);
//        L.w("id="+id);
        String vpitem_title = intent.getStringExtra("vpitem_title");
        tvtitle.setText(vpitem_title);
        String VPITEM_URL1=String.format(vpitem_url,id);
//        L.e("jjj="+VPITEM_URL1);
        VolleyUtil.requestString(VPITEM_URL1, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if(response!=null){
                    HomeHeadVpItem vpItem=new Gson().fromJson(response.toString(), HomeHeadVpItem.class);
                    HomeHeadVpItem.DataEntity data = vpItem.getData();
                    List<HomeHeadVpItem.DataEntity.PostsEntity> posts = data.getPosts();

                    VpItemAdapter itemAdapter=new VpItemAdapter(HeadVpActivity.this);
                    itemAdapter.setDatas(posts);
                    listView.setAdapter(itemAdapter);

                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {

            }
        });
    }

}
