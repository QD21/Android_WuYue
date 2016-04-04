package org.qfln.android_wuyue.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.MainActivity;
import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:26
 * @备注：
 */
public class ProfileFragment extends BaseFragment{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager mvp;
    private VPAdapter vpAdapter;
    private CollapsingToolbarLayout collapsingToolbar;

    public static ProfileFragment newInstance(){
        ProfileFragment profileFragment=new ProfileFragment();
        return profileFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.profile_layout;
    }

    @Override
    protected void init(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mvp = (ViewPager)view.findViewById(R.id.my_vp);
        MainActivity main= (MainActivity) getActivity();
        main.setSupportActionBar(toolbar);
        main.getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) tabLayout.getLayoutParams();
        layoutParams.width = screenW;
        tabLayout.setLayoutParams(layoutParams);

        vpAdapter = new VPAdapter(getActivity().getSupportFragmentManager());
        mvp.setAdapter(vpAdapter);
        tabLayout.setTabsFromPagerAdapter(vpAdapter);
        tabLayout.setupWithViewPager(mvp);//与ViewPager联动
    }

    class VPAdapter extends FragmentPagerAdapter {
        private String[] tabName=new String[]{"礼物","攻略"};

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return GiftCollectFragment.newInstance(tabName[0]);
            }else {
                return StrategyFragment.newInstance(tabName[1]);
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }
    }


}
