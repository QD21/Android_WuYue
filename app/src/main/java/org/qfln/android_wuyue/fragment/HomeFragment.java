package org.qfln.android_wuyue.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/26 16:59
 * @备注：
 */
public class HomeFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager mvp;
    @Override
    protected int getLayoutResId() {
        Fresco.initialize(getActivity());
        return R.layout.home_layout;
    }
    public static HomeFragment newInstance(){
        HomeFragment homeFragment=new HomeFragment();
        return homeFragment;
    }

    @Override
    protected void init(View view) {
        tabLayout = (TabLayout)view.findViewById(R.id.tab);
        mvp = (ViewPager) view.findViewById(R.id.vp);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ViewPagerAdapter vpAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        mvp.setAdapter(vpAdapter);

        tabLayout.setupWithViewPager(mvp);
    }
    class ViewPagerAdapter extends FragmentStatePagerAdapter{
        private  String[] titles=new String[]{"精选","海淘","送男票","礼物",
                "饰品","创意生活","纪念日","设计感","送爸妈","送闺蜜","美物","手工",
                "美护","涨姿势"};
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position!=0){
                List<Fragment> lists=new ArrayList<>();
                for (int i = 0; i < titles.length; i++) {
                    HomeContentFragment fragment = HomeContentFragment.getInstance(titles[i]);
                    lists.add(fragment);
                }
                return lists.get(position);
            }
            return HomeSelectFragment.newInstance(titles[0]);
        }

        @Override
        public int getCount() {
            return titles.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}
