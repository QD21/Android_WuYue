package org.qfln.android_wuyue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.HomeContentAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.HomeContentEntity;
import org.qfln.android_wuyue.custom.CustomProgressDialog;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshBase;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshListView;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/28 18:50
 * @备注：
 */
public class HomeContentFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener, PullToRefreshBase.OnLastItemVisibleListener {
    private PullToRefreshListView pullToRefreshListView;
    private String tabid_url;
    private ListView listView;
    private View footer;
    private ImageView ivRefresh;
    private List<HomeContentEntity.DataEntity.ItemsEntity> datas=new ArrayList<>();
    private HomeContentEntity.DataEntity data;
    private HomeContentAdapter contentAdapter;
    private int id;
    private int offset=10;
    private CustomProgressDialog progressDialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.content_fragment;
    }
    // 获取ContentFragment实例的方法
    public static HomeContentFragment getInstance(int id) {
        HomeContentFragment contentfragment = new HomeContentFragment();
        Bundle args = new Bundle();
        args.putInt("id",id);
//        L.d("id"+id);
        contentfragment.setArguments(args);
        return contentfragment;
    }

    @Override
    protected void init(View view) {
        //在网络请求之前显示；
        progressDialog = new CustomProgressDialog(getActivity(),"正在加载中...", R.drawable.donghua_frame);
        progressDialog.show();
        //底部动画
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.list_foot_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);
        /**
         * 开启动画
         */
        Animation animtion = AnimationUtils.loadAnimation(getActivity(), R.anim.foot_rotate);
        ivRefresh.startAnimation(animtion);
        //带下拉刷新的listview
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_content);
        //通过pullToRefreshListView.getRefreshableView()方法获取其内部封装的ListView
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(this);

        pullToRefreshListView.setOnLastItemVisibleListener(this);
    }

    @Override
    protected void getDatas(Bundle bundle) {
        id = bundle.getInt("id",0);
        tabid_url = String.format(Constant.URL.TAB_ID, id);
        loaddata(tabid_url);// 加载数据方法
    }

    private void loaddata(String url) {
        VolleyUtil.requestString(tabid_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                progressDialog.hide();
                pullToRefreshListView.onRefreshComplete();
                Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
                if(response!=null){
                    HomeContentEntity contentEntity=new Gson().fromJson(response.toString(),HomeContentEntity.class);
                    data = contentEntity.getData();
                    List<HomeContentEntity.DataEntity.ItemsEntity> items = data.getItems();
//                    L.w("--"+items);
                    datas.addAll(items);
                    contentAdapter = new HomeContentAdapter(getActivity());
                    contentAdapter.setDatas(items);
                    listView.setAdapter(contentAdapter);// 设置适配器
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onLastItemVisible() {
        ivRefresh.setVisibility(View.VISIBLE);
        offset +=10;
        String next_url = String.format(Constant.URL.TAB_MORE,id,offset);
//        L.d("90="+next_url);
        VolleyUtil.requestString(next_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if(response!=null) {
                    HomeContentEntity contentEntity=new Gson().fromJson(response.toString(),HomeContentEntity.class);
                    data = contentEntity.getData();
                    List<HomeContentEntity.DataEntity.ItemsEntity> items = data.getItems();
//                    L.w("--"+items);
                    contentAdapter.addDatas(items);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
