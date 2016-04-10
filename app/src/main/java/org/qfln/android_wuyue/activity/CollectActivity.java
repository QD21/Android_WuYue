package org.qfln.android_wuyue.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.fragment.GiftCollectFragment;
import org.qfln.android_wuyue.fragment.StrategyFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/9 00:23
 * @备注：
 */
public class CollectActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tab_coll;
    private ViewPager mvp_coll;
    private VPAdapter vpAdapter;
    private ImageView ivCollBack;
    @Override
    protected int getContentResId() {
        return R.layout.collect_layout;
    }

    @Override
    protected void initView() {
        mvp_coll = (ViewPager)findViewById(R.id.collect_vp);
        tab_coll = (TabLayout)findViewById(R.id.tab_collect);
        ivCollBack = (ImageView) findViewById(R.id.iv_collect_back);
        ivCollBack.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab_coll.getLayoutParams();
        layoutParams.width = screenW;
        tab_coll.setLayoutParams(layoutParams);

        vpAdapter = new VPAdapter(getSupportFragmentManager());
        mvp_coll.setAdapter(vpAdapter);
        tab_coll.setTabsFromPagerAdapter(vpAdapter);
        tab_coll.setupWithViewPager(mvp_coll);//与ViewPager联动
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    class VPAdapter extends FragmentPagerAdapter {
        private String[] tabName = new String[]{"礼物", "攻略"};

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return GiftCollectFragment.newInstance();
            } else {
                return StrategyFragment.newInstance();
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
