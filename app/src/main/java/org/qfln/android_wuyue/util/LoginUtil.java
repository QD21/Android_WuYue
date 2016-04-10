package org.qfln.android_wuyue.util;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/8 17:34
 * @备注：
 */
public class LoginUtil {

    public static void sinaLongin(Context context,PlatformActionListener listener){
        ShareSDK.initSDK(context);
        Platform weibo = ShareSDK.getPlatform(context, SinaWeibo.NAME);
        weibo.setPlatformActionListener(listener);
        weibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
        //weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
    }
    public static void QzomLogin(Context context,PlatformActionListener listener){
        ShareSDK.initSDK(context);
        Platform Qzone = ShareSDK.getPlatform(context, QZone.NAME);
        Qzone.setPlatformActionListener(listener);
        Qzone.showUser(null);//执行登录，登录后在回调里面获取用户资料
        //weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
    }



}
