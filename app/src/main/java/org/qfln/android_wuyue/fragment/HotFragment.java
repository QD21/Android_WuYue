package org.qfln.android_wuyue.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.SearchActivity;
import org.qfln.android_wuyue.adapter.HotListAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.HotListEntity;
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
 * @创建时间: 2016/3/26 17:20
 * @备注：
 */
public class HotFragment extends BaseFragment implements PullToRefreshBase.OnLastItemVisibleListener, View.OnClickListener {
    private String hotlist_url= String.format(Constant.URL.HOTLIST_URL,10);
    private PullToRefreshListView pullToRefreshList;
    private ListView mlv;
    private HotListEntity.DataEntity data;
    private List<HotListEntity.DataEntity.ItemsEntity> datas=new ArrayList<>();
    private HotListAdapter hotAdapter;
    private int offset=10;
    private ImageView iv_hot_search;
    private CustomProgressDialog progressDialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.hot_layout;
    }
    public static HotFragment newInstance(){
        HotFragment hotFragment=new HotFragment();
        return hotFragment;
    }

    @Override
    protected void init(View view) {
        //在网络请求之前显示；
        progressDialog = new CustomProgressDialog(getActivity(),"正在加载中...", R.drawable.donghua_frame);
        progressDialog.show();

        iv_hot_search = (ImageView)view.findViewById(R.id.iv_hot_search);
        iv_hot_search.setOnClickListener(this);
        pullToRefreshList = (PullToRefreshListView)view.findViewById(R.id.hot_pull_lv);
        mlv = pullToRefreshList.getRefreshableView();
        pullToRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        pullToRefreshList.setOnLastItemVisibleListener(this);
    }

    @Override
    protected void loadData() {
        VolleyUtil.requestString(hotlist_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                progressDialog.dismiss();
                pullToRefreshList.onRefreshComplete();
                Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                if(response!=null) {
                    HotListEntity listEntity = new Gson().fromJson(response.toString(), HotListEntity.class);
                    data = listEntity.getData();
                    List<HotListEntity.DataEntity.ItemsEntity> items = data.getItems();
//                    L.d("00"+items);
                    datas.addAll(items);
                    hotAdapter = new HotListAdapter(getActivity());
                    hotAdapter.setDatas(items);
                    mlv.setAdapter(hotAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLastItemVisible() {
        offset +=10;
        String next_url = String.format(Constant.URL.HOTJIA_url,offset);
        VolleyUtil.requestString(next_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if(response!=null) {
                    HotListEntity listEntity = new Gson().fromJson(response.toString(), HotListEntity.class);
                    data = listEntity.getData();
                    List<HotListEntity.DataEntity.ItemsEntity> items = data.getItems();
                    hotAdapter.addData(items);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(), SearchActivity.class);
        getActivity().startActivity(intent);
    }
}
