package org.qfln.android_wuyue.activity;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.util.Constant;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/6 09:08
 * @备注：
 */
public class HeadTianActivity extends BaseActivity{
    private WebView mWeb;
    private String tian_url= Constant.URL.TIAN_URL;
    @Override
    protected int getContentResId() {
        return R.layout.guajiang_layout;
    }

    @Override
    protected void initView() {
        mWeb = (WebView) findViewById(R.id.web_guajiang);
        WebSettings settings = mWeb.getSettings();
        settings.setDomStorageEnabled(true);
        mWeb.loadUrl(tian_url);
        mWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWeb.loadUrl(url);
                return true;
            }
        });
    }

    // 返回键监听，分享
    public void click(View v){
        if(mWeb.canGoBack()) {
            mWeb.goBack();
        }
        finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
            mWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
