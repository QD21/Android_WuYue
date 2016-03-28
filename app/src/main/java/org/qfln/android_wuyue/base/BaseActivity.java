package org.qfln.android_wuyue.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/7 20:14
 * @备注：
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getContentResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResId());
        initView();
        loadData();
    }

    /**
     * 初始化
     */
    protected void initView() {

    }

    /**
     * 数据加载
     */
    protected void loadData(){

    }

    /**
     * 带过场动画的启动activity方式
     * @param intent
     * @param enterAnim
     * @param exitAnim
     */
    public void startActivity(Intent intent, int enterAnim, int exitAnim) {
        super.startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }

}
