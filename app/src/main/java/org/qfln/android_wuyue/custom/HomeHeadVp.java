package org.qfln.android_wuyue.custom;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.HomeVPEntity;
import org.qfln.android_wuyue.fragment.HeadVpFragment;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.DownUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/9 13:07
 * @备注：
 */
public class HomeHeadVp extends FrameLayout {
    private String url=Constant.URL.HOMEVP_URL;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private ViewPagerAdapter vPadapter;
    private NavView navView;//导航控件
    private List<HomeVPEntity.DataEntity> mlist;


    public HomeHeadVp(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;
        init();
        setUrl(url);
    }

    /**
     * 初始化方法
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.headvp_layout, this, true);
        viewPager = (ViewPager) this.findViewById(R.id.vp_head);

        navView = (NavView) findViewById(R.id.nv);

    }


    /**
     * 提供该方法让外界调用，设置数据的URL
     */
    public void setUrl(String url) {
        DownUtil.downJson(url, new DownUtil.OnDownListener() {
            @Override
            public void downSuccess(String path, Object obj) {
                if (obj != null) {
                    HomeVPEntity dataEntity = new Gson().fromJson(obj.toString(), HomeVPEntity.class);
                    HomeVPEntity.DataEntity data = dataEntity.getData();
                    List<HomeVPEntity.DataEntity.BannersEntity> banners = data.getBanners();

                    //L.d(banners+"-------------------------");
                    vPadapter = new ViewPagerAdapter(fragmentManager, banners);
                    viewPager.setAdapter(vPadapter);

                    /**
                     * 设置导航控件的个数
                     */
                    navView.setcount(banners.size());
                    navView.setViewPager(viewPager);
                }
            }
        });
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        List<HomeVPEntity.DataEntity.BannersEntity> mlist;

        public ViewPagerAdapter(FragmentManager fm, List<HomeVPEntity.DataEntity.BannersEntity> list) {
            super(fm);
            this.mlist = list;
        }

        @Override
        public Fragment getItem(int position) {
            return HeadVpFragment.newInstance(mlist.get(position));
        }

        @Override
        public int getCount() {
            return mlist.size();
        }


    }
}