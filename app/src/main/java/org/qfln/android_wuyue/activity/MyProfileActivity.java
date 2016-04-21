package org.qfln.android_wuyue.activity;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.util.SharePreferencesUtil;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/9 16:48
 * @备注：
 */
public class MyProfileActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivMyBack,ivMyBianJi;
    private SimpleDraweeView sdvTX;
    private TextView tvUserName,tvMiaoShu;
    @Override
    protected int getContentResId() {
        return R.layout.my_layout;
    }

    @Override
    protected void initView() {
        ivMyBack = (ImageView) findViewById(R.id.iv_my_back);
        ivMyBianJi = (ImageView) findViewById(R.id.iv_bianji);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvMiaoShu = (TextView) findViewById(R.id.tv_miaoshu);
        sdvTX = (SimpleDraweeView) findViewById(R.id.sdv_touxiang);

        ivMyBack.setOnClickListener(this);
        ivMyBianJi.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        tvMiaoShu.setOnClickListener(this);
        sdvTX.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_user_name:
                tvUserName.setText(SharePreferencesUtil.getString("username"));
                break;
            case R.id.tv_miaoshu:
                tvMiaoShu.setText(SharePreferencesUtil.getString("userdescription"));
                break;
            case R.id.sdv_touxiang:
                sdvTX.setImageURI(Uri.parse(SharePreferencesUtil.getString("userimage")));
                break;
            case R.id.iv_my_back:
                finish();
                break;
//            case R.id.iv_bianji:
//                break;

        }
    }
}
