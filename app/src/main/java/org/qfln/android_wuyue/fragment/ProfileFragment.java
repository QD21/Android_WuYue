package org.qfln.android_wuyue.fragment;

import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:26
 * @备注：
 */
public class ProfileFragment extends BaseFragment{
    @Override
    protected int getLayoutResId() {
        return 0;
    }
    public static ProfileFragment newInstance(){
        ProfileFragment profileFragment=new ProfileFragment();
        return profileFragment;
    }
}
