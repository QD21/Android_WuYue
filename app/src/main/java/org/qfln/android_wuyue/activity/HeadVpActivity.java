package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.VpItemAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.HomeHeadVpItem;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshBase;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshListView;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.ShareUtil;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/29 00:55
 * @备注：
 */
public class HeadVpActivity extends BaseActivity implements View.OnClickListener {
    private String vpitem_url = Constant.URL.VPITEM_URL;
    private TextView tvtitle;
    private PullToRefreshListView pullToRefresh;
    private ListView listView;
    private List<HomeHeadVpItem.DataEntity.PostsEntity> posts;
    private ImageView ivVpBack, ivVpShare;
    private String vpitem_title;
    private int id;

    @Override
    protected int getContentResId() {
        Fresco.initialize(this);//初始化fresco
        return R.layout.vpitem_layout;
    }

    @Override
    protected void initView() {
        ivVpBack = (ImageView) findViewById(R.id.iv_vp_back);
        ivVpShare = (ImageView) findViewById(R.id.iv_vp_share);
        ivVpBack.setOnClickListener(this);
        ivVpShare.setOnClickListener(this);

        tvtitle = (TextView) findViewById(R.id.tv_vp_title2);
        pullToRefresh = (PullToRefreshListView) findViewById(R.id.pull_lv);
        listView = pullToRefresh.getRefreshableView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HeadVpActivity.this, WebXQActivity.class);
                // webview的url
                intent.putExtra("item_id", posts.get(position).getId());
                startActivity(intent);
            }
        });
        // 刷新界面
        pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        // 获取Vp每个条目的id
        id = intent.getIntExtra("vpitem_id", 0);
        vpitem_title = intent.getStringExtra("vpitem_title");
        tvtitle.setText(vpitem_title);
        String VPITEM_URL1 = String.format(vpitem_url, id);
//        L.e("jjj="+VPITEM_URL1);
        VolleyUtil.requestString(VPITEM_URL1, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                pullToRefresh.onRefreshComplete();
                Toast.makeText(HeadVpActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                if (response != null) {
                    HomeHeadVpItem vpItem = new Gson().fromJson(response.toString(), HomeHeadVpItem.class);
                    HomeHeadVpItem.DataEntity data = vpItem.getData();
                    posts = data.getPosts();

                    VpItemAdapter itemAdapter = new VpItemAdapter(HeadVpActivity.this);
                    itemAdapter.setDatas(posts);
                    listView.setAdapter(itemAdapter);

                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_vp_back:
                finish();
                break;
            case R.id.iv_vp_share:
                String titleUrl=String.format("http://www.liwushuo.com/collections/%d",id);
                ShareUtil.simpleShowShare(this,titleUrl,vpitem_title);
                break;
        }
    }
}
