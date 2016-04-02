package org.qfln.android_wuyue.activity;

import android.view.View;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 19:19
 * @备注：
 */
public class SearchActivity extends BaseActivity{
    @Override
    protected int getContentResId() {
        return R.layout.search_layout;
    }
    public void click(View v){
        finish();
    }
}
