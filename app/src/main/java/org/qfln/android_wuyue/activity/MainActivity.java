package org.qfln.android_wuyue.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.fragment.CategoryFragment;
import org.qfln.android_wuyue.fragment.HomeFragment;
import org.qfln.android_wuyue.fragment.HotFragment;
import org.qfln.android_wuyue.fragment.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rGroup;
    private List<Fragment> fragmentList;// 存放Fragment的集合
    private FragmentManager manager;// Fragment管理器
    private FragmentTransaction transaction;

    @Override
    protected int getContentResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rGroup = (RadioGroup) findViewById(R.id.rg_Group);
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(HotFragment.newInstance());
        fragmentList.add(CategoryFragment.newInstance());
        fragmentList.add(ProfileFragment.newInstance());

        /**
         *RadioGroup监听事件
         */
        rGroup.setOnCheckedChangeListener(this);
        /**
         * 模拟点击第一个RB
         */
        rGroup.getChildAt(0).performClick();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.rb_home:
                /**
                 * 判断fragment是否已经添加 若添加隐藏其他fragment
                 * 如果已经添加隐藏其他fragment显示当前选中的fragment
                 * 反之同理
                 */
                if (fragmentList.get(0).isAdded()) {
                    manager.beginTransaction()
                            .hide(fragmentList.get(1))
                            .hide(fragmentList.get(2))
                            .hide(fragmentList.get(3))
                            .show(fragmentList.get(0))
                            .commit();
                } else {
                    transaction.hide(fragmentList.get(1))
                            .hide(fragmentList.get(2))
                            .hide(fragmentList.get(3))
                            .add(R.id.fl_content, fragmentList.get(0))
                            .show(fragmentList.get(0))
                            .commit();
                }
                break;
            case R.id.rb_hot:
                if (fragmentList.get(1).isAdded()) {
                    manager.beginTransaction()
                            .hide(fragmentList.get(0))
                            .hide(fragmentList.get(2))
                            .hide(fragmentList.get(3))
                            .show(fragmentList.get(1))
                            .commit();
                } else {
                    transaction.hide(fragmentList.get(0))
                            .hide(fragmentList.get(2))
                            .hide(fragmentList.get(3))
                            .add(R.id.fl_content, fragmentList.get(1))
                            .show(fragmentList.get(1))
                            .commit();
                }
                break;
            case R.id.rb_category:
                if (fragmentList.get(2).isAdded()) {
                    manager.beginTransaction()
                            .hide(fragmentList.get(0))
                            .hide(fragmentList.get(1))
                            .hide(fragmentList.get(3))
                            .show(fragmentList.get(2))
                            .commit();
                } else {
                    transaction.hide(fragmentList.get(0))
                            .hide(fragmentList.get(1))
                            .hide(fragmentList.get(3))
                            .add(R.id.fl_content, fragmentList.get(2))
                            .show(fragmentList.get(2))
                            .commit();
                }
                break;
            case R.id.rb_profile:
                if (fragmentList.get(3).isAdded()) {
                    manager.beginTransaction()
                            .hide(fragmentList.get(0))
                            .hide(fragmentList.get(1))
                            .hide(fragmentList.get(2))
                            .show(fragmentList.get(3))
                            .commit();
                } else {
                    transaction.hide(fragmentList.get(0))
                            .hide(fragmentList.get(1))
                            .hide(fragmentList.get(2))
                            .add(R.id.fl_content, fragmentList.get(3))
                            .show(fragmentList.get(3))
                            .commit();
                }
                break;
        }
    }

    private long exitTime = 0;


    /**
     * 捕捉返回事件按钮
     *
     * 因为此 Activity 继承 TabActivity 用 onKeyDown 无响应，所以改用 dispatchKeyEvent
     * 一般的 Activity 用 onKeyDown 就可以了
     */

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 退出程序
     */
    private void exitApp() {
        // 判断2次点击事件时间
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }




}
