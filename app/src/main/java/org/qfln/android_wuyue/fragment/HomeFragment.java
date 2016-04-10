package org.qfln.android_wuyue.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.SearchActivity;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.TabEntity;
import org.qfln.android_wuyue.custom.CustomProgressDialog;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/26 16:59
 * @备注：
 */
public class HomeFragment extends BaseFragment {
    private ImageView ivSearch;
    private String tabUrl = Constant.URL.TAB_URL;
    private TabLayout tabLayout;
    private ViewPager mvp;
    private List<TabEntity.DataEntity.CandidatesEntity> candidates;
    private List<String> tabName = new ArrayList<>();
    private List<Integer> tabId = new ArrayList<>();
    private ViewPagerAdapter vpAdapter;
    private CustomProgressDialog progressDialog;

    @Override
    protected int getLayoutResId() {
        Fresco.initialize(getActivity());// 初始化fresco
        return R.layout.home_layout;
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    protected void init(View view) {
        ivSearch = (ImageView) view.findViewById(R.id.iv_search);

        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        mvp = (ViewPager) view.findViewById(R.id.vp);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //在网络请求之前显示；
        progressDialog = new CustomProgressDialog(getActivity(),"正在加载中...", R.drawable.donghua_frame);
        progressDialog.show();

    }

    /**
     * 数据加载
     */
    @Override
    protected void loadData() {
//        L.d("wojdo");
        VolleyUtil.requestString(tabUrl, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                progressDialog.hide();//隐藏
                if (response != null) {
                    TabEntity tabEntity = new Gson().fromJson(response.toString(), TabEntity.class);
                    TabEntity.DataEntity data = tabEntity.getData();
                    candidates = data.getCandidates();
//                    L.e("kk"+candidates);
                    for (int i = 0; i < candidates.size(); i++) {
                        String name = candidates.get(i).getName();
                        int id = candidates.get(i).getId();
                        tabName.add(name);
                        tabId.add(id);
//                        L.w("--id"+id);
                    }
                    vpAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabName,tabId);
                    mvp.setAdapter(vpAdapter);
                    tabLayout.setTabsFromPagerAdapter(vpAdapter);

                    tabLayout.setupWithViewPager(mvp);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });


        //ivSearch的点击事件
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<String> tabName;
        private List<Integer> tabId;

        public ViewPagerAdapter(FragmentManager fm, List<String> tabName,List<Integer> tabId) {
            super(fm);
            this.tabName=tabName;
            this.tabId=tabId;
            tabName.add(0,"精选");
            tabId.add(0,0);
        }

        @Override
        public Fragment getItem(int position) {
            if (position != 0) {
                List<Fragment> lists = new ArrayList<>();
                for (int i = 0; i < tabId.size(); i++) {
                    HomeContentFragment fragment = HomeContentFragment.getInstance(tabId.get(i));
                    lists.add(fragment);
                }
                return lists.get(position);
            }
            return HomeSelectFragment.newInstance(tabName.get(0));
        }

        @Override
        public int getCount() {
            return tabName.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabName.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }



}
