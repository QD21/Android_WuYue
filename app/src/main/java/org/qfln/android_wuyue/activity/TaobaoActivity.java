package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.ShareUtil;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/5 14:13
 * @备注：
 */
public class TaobaoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack,ivshare;
    private WebView web;
    private String taobao_url;

    @Override
    protected int getContentResId() {
        return R.layout.taobao_layout;
    }

    @Override
    protected void initView() {
        web = (WebView) findViewById(R.id.web_taobao);
        ivBack = (ImageView) findViewById(R.id.iv_taobao_back);
        ivshare = (ImageView) findViewById(R.id.iv_taobao_share);
        ivBack.setOnClickListener(this);
        ivshare.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        String purchase_id =  intent.getStringExtra("purchase_id");
        taobao_url = String.format(Constant.URL.TAOBAO_URL,purchase_id);
//        L.d(Taobao_url);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        web.loadUrl(taobao_url);
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return true;
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_taobao_back:
                if(web.canGoBack()){
                    web.goBack();
                }
                finish();
                break;
            case R.id.iv_taobao_share:
                ShareUtil.simpleShowShare(this,taobao_url,"宝贝详情");
                break;
        }
    }
}
