package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/29 19:16
 * @备注：
 */
public class WebXQActivity extends BaseActivity {
    private Toolbar toolbar;
//    private SimpleDraweeView sdvTop;
//    private TextView tvTitle;
    private WebView web;
    @Override
    protected int getContentResId() {
        return R.layout.webxq_layout;
    }

    @Override
    protected void initView() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        sdvTop = (SimpleDraweeView) findViewById(R.id.sdv_top);
//        tvTitle = (TextView) findViewById(R.id.tv_vpitemtitle);
        web = (WebView) findViewById(R.id.web_vp);
//        setSupportActionBar(toolbar);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
//        String cover_webp_url = intent.getStringExtra("Cover_webp_url");
//        String title = intent.getStringExtra("Title");
        String content_url = intent.getStringExtra("Content_url");
//        sdvTop.setImageURI(Uri.parse(cover_webp_url));
//        tvTitle.setText(title);
        
        web.loadUrl(content_url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String content_url) {
                web.loadUrl(content_url);
                return true;
            }
        });
    }

    // 返回键监听，分享
    public void click(View v){
        if(web.canGoBack()) {
            web.goBack();
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
