package org.qfln.android_wuyue.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/28 18:50
 * @备注：
 */
public class HomeContentFragment extends BaseFragment{
    private TextView tvContent;
    @Override
    protected int getLayoutResId() {
        return R.layout.content_fragment;
    }
    // 获取ContentFragment实例的方法
    public static HomeContentFragment getInstance(String title) {
        HomeContentFragment contentfragment = new HomeContentFragment();
        Bundle args = new Bundle();
        args.putString("which", title);
        contentfragment.setArguments(args);
        return contentfragment;
    }

    @Override
    protected void init(View view) {
        tvContent = (TextView)view.findViewById(R.id.tv_content);
    }

    @Override
    protected void getDatas(Bundle bundle) {
        String titles = bundle.getString("which");
        tvContent.setText(titles);
    }
}
