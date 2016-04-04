package org.qfln.android_wuyue.fragment;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 17:34
 * @备注：
 */
public class CateCommitFragment extends BaseFragment{
    @Override
    protected int getLayoutResId() {
        return R.layout.catecommit_layout;
    }

    public static CateCommitFragment newInstance(String s) {
        CateCommitFragment commitFragment=new CateCommitFragment();
        return commitFragment;
    }
}
