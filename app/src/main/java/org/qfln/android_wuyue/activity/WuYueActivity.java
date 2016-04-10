package org.qfln.android_wuyue.activity;

import android.view.View;
import android.widget.ImageView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/9 17:04
 * @备注：
 */
public class WuYueActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivWuYueBack;
    @Override
    protected int getContentResId() {
        return R.layout.wuyue_layout;
    }

    @Override
    protected void initView() {
        ivWuYueBack = (ImageView) findViewById(R.id.iv_wuyue_back);
        ivWuYueBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
