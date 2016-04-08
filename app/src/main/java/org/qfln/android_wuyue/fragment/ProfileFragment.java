package org.qfln.android_wuyue.fragment;

import android.view.View;
import android.widget.ImageView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.MyPullZoomLvAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.custom.PullToZoomListView;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:26
 * @备注：
 */
public class ProfileFragment extends BaseFragment{
    private PullToZoomListView pullZoom_lv;

    @Override
    protected int getLayoutResId() {
        return R.layout.profile_layout;
    }

    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment =new ProfileFragment();
        return profileFragment;
    }

    @Override
    protected void init(View view) {
        pullZoom_lv = (PullToZoomListView)view.findViewById(R.id.pull_zoom_lv);
        ImageView headerView = pullZoom_lv.getHeaderView();
        headerView.setImageResource(R.mipmap.bg_profile);
        headerView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        MyPullZoomLvAdapter pullAdapter=new MyPullZoomLvAdapter(getActivity());
        pullZoom_lv.setAdapter(pullAdapter);
    }
}
