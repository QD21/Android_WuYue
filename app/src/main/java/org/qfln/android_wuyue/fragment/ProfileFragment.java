package org.qfln.android_wuyue.fragment;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:26
 * @备注：
 */
public class ProfileFragment extends BaseFragment{

    public static ProfileFragment newInstance(){
        ProfileFragment profileFragment=new ProfileFragment();
        return profileFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.profile_layout;
    }

}
