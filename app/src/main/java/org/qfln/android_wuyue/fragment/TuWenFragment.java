package org.qfln.android_wuyue.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.CateXQEntity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 17:31
 * @备注：
 */
public class TuWenFragment extends BaseFragment {
    private WebView mWeb;

    @Override
    protected int getLayoutResId() {
        return R.layout.tuwen_layout;
    }

    public static TuWenFragment newInstance(CateXQEntity.DataEntity mdata) {
        TuWenFragment tuwenFragment = new TuWenFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("detail_html", mdata.getDetail_html());
        tuwenFragment.setArguments(bundle);
        return tuwenFragment;
    }

    @Override
    protected void init(View view) {
        mWeb = (WebView)view.findViewById(R.id.cate_web);
    }

    @Override
    protected void getDatas(Bundle bundle) {

        String detail_html = (String) bundle.getSerializable("detail_html");
        // String 转换成 byte
        byte[] buffer = detail_html.getBytes();
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("/sdcard/wuyue/web.html")));
            bos.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        mWeb.loadUrl("file://com.android.htmlfileprovider/sdcard/wuyue/web.html");
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                mWeb.loadUrl(url);
                return true;
            }
        });
    }
}
