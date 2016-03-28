package org.qfln.android_wuyue.fragment;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/26 17:20
 * @备注：
 */
public class HotFragment extends BaseFragment{
    @Override
    protected int getLayoutResId() {
        return R.layout.hot_layout;
    }
    public static HotFragment newInstance(){
        HotFragment hotFragment=new HotFragment();
        return hotFragment;
    }

}
