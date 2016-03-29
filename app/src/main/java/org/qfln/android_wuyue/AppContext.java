package org.qfln.android_wuyue;

import android.app.Application;

import org.qfln.android_wuyue.util.ShareUtil;
import org.qfln.android_wuyue.util.VolleyUtil;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 15:30
 * @备注：
 */
public class AppContext extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.initVolley(this);
        ShareUtil.initShared(this);//初始化SharedPreferences
    }
}
