package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/7 23:47
 * @备注：
 */
public class WelcomeActivity extends BaseActivity{
    private final long SPLASH_LENGTH = 2000;
    private Handler handler = new Handler();
    private SharedPreferences sp;

    @Override
    protected int getContentResId() {
        return R.layout.welcome_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        // 判断程序是否是第一次启动

        sp = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转

                public void run() {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_LENGTH);//3秒后跳转至应用主界面BootActivity
        } else {
            handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转

                public void run() {

                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class),
                            R.anim.activity_in_right,R.anim.activity_out_left);
                    finish();
                }
            }, SPLASH_LENGTH);//3秒后跳转至应用主界面MainActivity
        }
    }
}
