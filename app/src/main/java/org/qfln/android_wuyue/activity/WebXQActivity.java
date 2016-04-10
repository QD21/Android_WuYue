package org.qfln.android_wuyue.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.HomeVPGLEntity;
import org.qfln.android_wuyue.custom.CustomProgressDialog;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.DBUtil;
import org.qfln.android_wuyue.util.L;
import org.qfln.android_wuyue.util.SharePreferencesUtil;
import org.qfln.android_wuyue.util.ShareUtil;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/29 19:16
 * @备注：
 */
public class WebXQActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvXqlike, tvXqshare, tvXqcommit;
    private ImageView ivXqlike, ivXqshare, ivXqcommit;
    private WebView web;
    private File file;
    private int item_id;
    private String share_msg;
    private String gl_title;
    //数据库
    // 标记是否进行了收藏,不使用selector.
    private boolean isCollected = false;
    private DBUtil mDbUtil;
    private SQLiteDatabase db;
    private String image;
    private String isCollect;
    private CustomProgressDialog progressDialog;

    @Override
    protected int getContentResId() {
        return R.layout.webxq_layout;
    }

    @Override
    protected void initView() {
        web = (WebView) findViewById(R.id.web_vp);
        tvXqlike = (TextView) findViewById(R.id.tv_xq_like);
        tvXqshare = (TextView) findViewById(R.id.tv_xq_share);
        tvXqcommit = (TextView) findViewById(R.id.tv_xq_commit);
        ivXqshare = (ImageView) findViewById(R.id.iv_xq_share);
        ivXqcommit = (ImageView) findViewById(R.id.iv_xq_commit);
        ivXqlike = (ImageView) findViewById(R.id.iv_xq_like);
        //点击事件
        ivXqcommit.setOnClickListener(this);
        ivXqshare.setOnClickListener(this);
        ivXqlike.setOnClickListener(this);

        //在网络请求之前显示；
        progressDialog = new CustomProgressDialog(this,"正在加载中...", R.drawable.donghua_frame);
        progressDialog.show();
    }

    @Override
    protected void loadData() {
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");

        Intent intent = getIntent();
        item_id = intent.getIntExtra("item_id", 0);
        share_msg = intent.getStringExtra("share_msg");
        gl_title = intent.getStringExtra("gl_title");
        image = intent.getStringExtra("image");
        isCollect = intent.getStringExtra("isCollect");


        String itemxq_url = String.format(Constant.URL.VPXQ_URL, item_id);
        VolleyUtil.requestString(itemxq_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                progressDialog.hide();
                if (response != null) {
                    HomeVPGLEntity vpglEntity = new Gson().fromJson(response.toString(), HomeVPGLEntity.class);
                    HomeVPGLEntity.DataEntity data = vpglEntity.getData();
                    int comments_count = data.getComments_count();//评论数
                    int likes_count = data.getLikes_count();//likes数
//                    L.d("" + likes_count);
                    int shares_count = data.getShares_count();//分享数
                    tvXqlike.setText(String.valueOf(likes_count));
                    tvXqshare.setText(String.valueOf(shares_count));
                    tvXqcommit.setText(String.valueOf(comments_count));
                    /**
                     * 得到HTML字符串
                     */
                    String content = data.getContent();
                    /**
                     * 修改HTML中的内容 把request替换成" "
                     */
                    String request = content.replace("request", " ");
//                  content.replace("http://www.liwushuo.com/items","http://api.liwushuo.com/v2/items");
                    //http://api.liwushuo.com/v2/items/1020470
                    //https://detail.tmall.com/item.htm?id=40042797269&ali_trackid=2:mm_56503797_8596089_29498842:1459864343_253_354461477
                    /**
                     *先判断sd卡是否存在，再创建文件 将HTML写到sd中
                     */
                    boolean sdCardExist = Environment.getExternalStorageState()
                            .equals(android.os.Environment.MEDIA_MOUNTED);
                    if (sdCardExist) {
                        file = new File("/sdcard/web.html");
                        byte[] buffer = request.getBytes();
                        BufferedOutputStream bos = null;
                        try {
                            bos = new BufferedOutputStream(new FileOutputStream(file));
                            bos.write(buffer, 0, buffer.length);
                            bos.flush();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                bos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        /**
                         * webView与js交互
                         */
                        WebwithJs();

                    }
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(WebXQActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });

        //数据库
        mDbUtil = new DBUtil(this);
        db = mDbUtil.getDatabase();
        // 查询数据库.展示收藏结果.
        String sql = "select * from GL_Like where id=?";
        Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(item_id) });
        if (cursor != null) {
            while (cursor.moveToNext()) {
                isCollect = cursor.getString(cursor
                        .getColumnIndex("isCollect"));
                L.i("isCollect=" + isCollect);
                if ("true".equals(isCollect)) {
                    ivXqlike.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                } else {
                    ivXqlike
                            .setImageResource(R.mipmap.ic_action_compact_favourite_normal);
                }
            }
        }


    }

    /**
     * webView与js交互
     */
    private void WebwithJs() {
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        web.loadUrl("file://org.qfln.android_wuyue/sdcard/web.html");
//        web.loadUrl("http://www.liwushuo.com/posts/1018209/content");
//        web.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                web.loadUrl(url);
//                return true;
//            }
//        });
    }

    // 返回键监听，分享
    public void click(View v) {
        if (web.canGoBack()) {
            web.goBack();
        }
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //tv ,iv 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_xq_commit:
                Intent intent = new Intent(this, CommitActivity.class);
                intent.putExtra("commit_id", item_id);
                startActivity(intent);
                break;
            case R.id.iv_xq_share:
                String titleUrl=String.format("http://www.liwushuo.com/posts/%d",item_id);
                ShareUtil.simpleShowShare(this,titleUrl,share_msg);
                break;
            case R.id.iv_xq_like:
                isCollected = !isCollected;
                if (isCollected) {// 收藏
                    ivXqlike
                            .setImageResource(R.mipmap.ic_action_compact_favourite_selected);

                    // 数据库实现收藏
                    ContentValues values = new ContentValues();

                    values.put("id", String.valueOf(item_id));
                    values.put("title", gl_title);
                    values.put("icon",image);
                    values.put("isCollect", "true");
                    values.put("userid", SharePreferencesUtil.getString("userid"));
                    long insert = db.insert("GL_Like", null, values);
                    if (insert > 0) {
                        Toast.makeText(this, "收藏成功",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {// 移除收藏
                    ivXqlike
                            .setImageResource(R.mipmap.ic_action_compact_favourite_normal);

                    int delete = db.delete("GL_Like", "id=?",
                            new String[] { String.valueOf(item_id) });
                    if (delete > 0) {
                        Toast.makeText(this, "取消收藏",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
}
