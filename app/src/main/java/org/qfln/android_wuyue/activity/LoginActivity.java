package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.util.DBUtil;
import org.qfln.android_wuyue.util.LoginUtil;
import org.qfln.android_wuyue.util.SharePreferencesUtil;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/9 18:06
 * @备注：
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivClose,QQ,Sina;
    private DBUtil mDbUtil;
    private SQLiteDatabase db;
    @Override
    protected int getContentResId() {
        return R.layout.login_layout;
    }

    @Override
    protected void initView() {
        ivClose = (ImageView) findViewById(R.id.iv_close);
        QQ = (ImageView) findViewById(R.id.qq);
        Sina = (ImageView) findViewById(R.id.sina);

        ivClose.setOnClickListener(this);
        QQ.setOnClickListener(this);
        Sina.setOnClickListener(this);
        //数据库
        mDbUtil = new DBUtil(this);
        db = mDbUtil.getDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                finish();
                break;
            case R.id.qq:
                LoginUtil.QzomLogin(this, new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        String qq_id = hashMap.get("id").toString();//ID
                        String qq_name = hashMap.get("name").toString();//用户名
                        String qq_description = hashMap.get("description").toString();//描述
                        String qq_profile_image_url = hashMap.get("profile_image_url").toString();//头像链接
                        String str = "ID: " + qq_id + ";\n" +
                                "用户名： " + qq_name + ";\n" +
                                "描述：" + qq_description + ";\n" +
                                "用户头像地址：" + qq_profile_image_url;

                        if(hashMap!=null){

                            SharePreferencesUtil.putString("userid",qq_id);
                            SharePreferencesUtil.putString("username", qq_name);
                            SharePreferencesUtil.putString("userimage", qq_profile_image_url);
                            SharePreferencesUtil.putString("userdescription",qq_description);
                        }


                        Platform qZone = ShareSDK.getPlatform(LoginActivity.this, QZone.NAME);
                        if (qZone.isValid()) {
                            qZone.removeAccount();
                        }
                        Intent intent = getIntent();
                        intent.putExtra("username",qq_name);
                        intent.putExtra("userimage",qq_profile_image_url);
                        LoginActivity.this.setResult(RESULT_OK, intent);
                        LoginActivity.this.finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(LoginActivity.this, "登录错误", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(LoginActivity.this, "取消了登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            case R.id.sina:
                LoginUtil.sinaLongin(this, new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
                        String id = res.get("id").toString();//ID
                        String name = res.get("name").toString();//用户名
                        String description = res.get("description").toString();//描述
                        String profile_image_url = res.get("profile_image_url").toString();//头像链接
                        String str = "ID: " + id + ";\n" +
                                "用户名： " + name + ";\n" +
                                "描述：" + description + ";\n" +
                                "用户头像地址：" + profile_image_url;

                        if(res!=null){

                            SharePreferencesUtil.putString("userid",id);
                            SharePreferencesUtil.putString("username", name);
                            SharePreferencesUtil.putString("userimage", profile_image_url);
                            SharePreferencesUtil.putString("userdescription",description);
                        }


                        Platform sina = ShareSDK.getPlatform(LoginActivity.this, SinaWeibo.NAME);
                        if (sina.isValid()) {
                            sina.removeAccount();
                        }
                        Intent intent = getIntent();
                        intent.putExtra("username",name);
                        intent.putExtra("userimage",profile_image_url);
                        LoginActivity.this.setResult(RESULT_OK, intent);
                        LoginActivity.this.finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(LoginActivity.this, "登录错误", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(LoginActivity.this, "取消了登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
        }
    }
}
