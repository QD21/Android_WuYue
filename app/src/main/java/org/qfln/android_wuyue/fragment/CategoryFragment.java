package org.qfln.android_wuyue.fragment;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:09
 * @备注：
 */
public class CategoryFragment extends BaseFragment{
    @Override
    protected int getLayoutResId() {
        return R.layout.category_layout;
    }
    public static CategoryFragment newInstance(){
        CategoryFragment categoryFragment=new CategoryFragment();
        return categoryFragment;
    }
}
