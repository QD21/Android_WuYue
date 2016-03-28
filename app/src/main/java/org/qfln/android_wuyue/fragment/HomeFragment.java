package org.qfln.android_wuyue.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.SelectAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.SelectListEntity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.DownUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/26 16:59
 * @备注：
 */
public class HomeFragment extends BaseFragment{
    private ListView mLv;
    private boolean isBottom=false;
    private int limt=10;

    @Override
    protected int getLayoutResId() {
        Fresco.initialize(getActivity());
        return R.layout.home_layout;
    }
    public static HomeFragment newInstance(){
        HomeFragment homeFragment=new HomeFragment();
        return homeFragment;
    }

    /**
     * 初始化view
     * @param view
     */
    @Override
    protected void init(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
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
        DownUtil.downJson(SELECT_LISTURL, new DownUtil.OnDownListener() {
            @Override
            public void downSuccess(String path, Object obj) {
                if(obj!=null) {
                    SelectListEntity selectEntity = new Gson().fromJson(obj.toString(), SelectListEntity.class);
                    final SelectListEntity.DataEntity data = selectEntity.getData();
                    List<SelectListEntity.DataEntity.ItemsEntity> items = data.getItems();
//                    L.i("ijij"+items);
                    SelectAdapter selectAdapter=new SelectAdapter(getContext());
                    selectAdapter.setDatas(items);
                    /**
                     * 滚动
                     */
                    mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                            if(scrollState== SCROLL_STATE_IDLE && isBottom){
                                limt += 10;
                                String next_ur=String.format(Constant.URL.SELECT_LISTURL,limt) ;
//                                L.e("22"+next_ur);
                                DownUtil.downJson(next_ur, new DownUtil.OnDownListener() {
                                    @Override
                                    public void downSuccess(String path, Object obj) {
                                        if(obj!=null) {
                                            SelectListEntity selectEntity = new Gson().fromJson(obj.toString(), SelectListEntity.class);
                                            final SelectListEntity.DataEntity data = selectEntity.getData();
                                            List<SelectListEntity.DataEntity.ItemsEntity> items = data.getItems();
                                            SelectAdapter selectAdapter = new SelectAdapter(getContext());
                                            selectAdapter.setDatas(items);
                                            Toast.makeText(getActivity(), "加载更多...", Toast.LENGTH_SHORT).show();
                                            mLv.setAdapter(selectAdapter);
                                        }
                                    }
                                });
                            }
                            isBottom=false;
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            if(firstVisibleItem + visibleItemCount==totalItemCount){
                                isBottom=true;
                            }
                        }
                    });
                    mLv.setAdapter(selectAdapter);
                }
            }
        });



    }





}
