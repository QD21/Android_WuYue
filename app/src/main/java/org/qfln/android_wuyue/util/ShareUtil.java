package org.qfln.android_wuyue.util;

import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/8 20:01
 * @备注：
 */
public class ShareUtil {
    public static void simpleShowShare(Context context, String TitleUrl,String Text){
        showShare(context,null,null,TitleUrl,Text,null);
    }

    public static void showShare(Context context, String weburl, String WeburlName, String TitleUrl, String Text, String Url) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("物·悦");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(TitleUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(Text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(Url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(WeburlName);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(weburl);

        // 启动分享GUI
        oks.show(context);
    }



}
