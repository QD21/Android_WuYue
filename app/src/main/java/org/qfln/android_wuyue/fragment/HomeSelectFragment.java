package org.qfln.android_wuyue.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.SelectAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.SelectListEntity;
import org.qfln.android_wuyue.custom.HomeHead2;
import org.qfln.android_wuyue.custom.HomeHeadVp;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshBase;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshListView;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.DownUtil;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/28 17:01
 * @备注：
 */
public class HomeSelectFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener, PullToRefreshBase.OnLastItemVisibleListener{
    private PullToRefreshListView pToRefreshListView;
    private int limt=10;
    private ListView lv;

    @Override
    protected int getLayoutResId() {
        Fresco.initialize(getActivity());
        return R.layout.homeselect_layout;
    }
    public static HomeSelectFragment newInstance(String title){
        HomeSelectFragment homeselectFragment=new HomeSelectFragment();
        Bundle args = new Bundle();
        args.putString("which", title);
        homeselectFragment.setArguments(args);
        return homeselectFragment;
    }

    /**
     * 初始化view
     * @param view
     */
    @Override
    protected void init(View view) {
        HomeHeadVp headVp=new HomeHeadVp(getActivity());
        HomeHead2 homehead2=new HomeHead2(getActivity());
        pToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.ptrflv);
        lv = pToRefreshListView.getRefreshableView();

        lv.addHeaderView(headVp);//添加头部视图1
        lv.addHeaderView(homehead2);//添加头部视图2
        pToRefreshListView.setOnRefreshListener(this);

        pToRefreshListView.setOnLastItemVisibleListener(this);
    }


    /**
     * 加载数据
     */
    @Override
    protected void loadData() {
        //得到首页listView的url
        String SELECT_LISTURL = String.format(Constant.URL.SELECT_LISTURL, 10);
//        L.d("url="+SELECT_LISTURL);
        //进行Json下载
        VolleyUtil.requestString(SELECT_LISTURL, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                pToRefreshListView.onRefreshComplete();
                Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
                if(response!=null) {
                    SelectListEntity selectEntity = new Gson().fromJson(response.toString(), SelectListEntity.class);
                    final SelectListEntity.DataEntity data = selectEntity.getData();
                    List<SelectListEntity.DataEntity.ItemsEntity> items = data.getItems();
//                    L.i("ijij"+items);
                    SelectAdapter selectAdapter=new SelectAdapter(getContext());
                    selectAdapter.setDatas(items);
                    lv.setAdapter(selectAdapter);// 设置适配器
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {

            }
        });


    }

    /**
     * 下拉刷新的方法
     */
    @Override
    public void onRefresh() {
        loadData();
    }

    /**
     * 分页加载的方法
     */
    @Override
    public void onLastItemVisible() {
        limt += 10;
        String next_ur=String.format(Constant.URL.SELECT_LISTURL,limt) ;
        // 	 L.e("22"+next_ur);

        DownUtil.downJson(next_ur, new DownUtil.OnDownListener() {
            @Override
            public void downSuccess(String path, Object obj) {
                if(obj!=null) {
                    SelectListEntity selectEntity = new Gson().fromJson(obj.toString(), SelectListEntity.class);
                    final SelectListEntity.DataEntity data = selectEntity.getData();
                    List<SelectListEntity.DataEntity.ItemsEntity> items = data.getItems();
                    SelectAdapter selectAdapter = new SelectAdapter(getContext());
                    selectAdapter.setDatas(items);
                    lv.setAdapter(selectAdapter);
                }
            }
        });
    }
}
