package org.qfln.android_wuyue.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.CollectActivity;
import org.qfln.android_wuyue.activity.LoginActivity;
import org.qfln.android_wuyue.activity.MyProfileActivity;
import org.qfln.android_wuyue.activity.WuYueActivity;
import org.qfln.android_wuyue.adapter.MyPullZoomLvAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.custom.PullToZoomListView;
import org.qfln.android_wuyue.util.SharePreferencesUtil;

import java.io.File;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:26
 * @备注：
 */
public class ProfileFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private PullToZoomListView pullZoom_lv;
    private ImageView headerViewImg;
    private TextView tvlogin, tvMyname;
    private ImageView ivScan;
    private SimpleDraweeView sdvLogin;
    private static final int LOGOIN = 3;
    private static final int CAMERA = 1;
    private static final int NICKER = 2;
    private String username;
    private String cameradata;
    private String userimage;


    @Override
    protected int getLayoutResId() {
        return R.layout.profile_layout;
    }

    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();

        return profileFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        //默认记住用户名
        tvMyname.setText(SharePreferencesUtil.getString("username"));
        sdvLogin.setImageURI(Uri.parse(SharePreferencesUtil.getString("userimage")));
    }

    @Override
    protected void init(View view) {
        pullZoom_lv = (PullToZoomListView) view.findViewById(R.id.pull_zoom_lv);
        //头部视图
        headerViewImg = pullZoom_lv.getHeaderViewImg();
        headerViewImg.setImageResource(R.mipmap.bg_profile);
        headerViewImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        View headerView = pullZoom_lv.getHeaderViewcopy();
        tvlogin = (TextView) headerView.findViewById(R.id.tv_my_login);
        tvMyname = (TextView) headerView.findViewById(R.id.tv_my_name1);
        sdvLogin = (SimpleDraweeView) headerView.findViewById(R.id.sdv_my_logo);
        sdvLogin.setOnClickListener(this);
        tvlogin.setOnClickListener(this);

        MyPullZoomLvAdapter pullAdapter = new MyPullZoomLvAdapter(getActivity());
        pullZoom_lv.setAdapter(pullAdapter);
        pullZoom_lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {

            case 1:
                if (TextUtils.isEmpty(tvMyname.getText())) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), MyProfileActivity.class);
                    startActivity(intent);
                }
                break;
            case 2:
                if (TextUtils.isEmpty(tvMyname.getText())) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent2 = new Intent(getContext(), CollectActivity.class);
                    startActivity(intent2);
                }
                break;
            case 3:
                Toast.makeText(getActivity(), "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                /** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
                deleteFilesByDirectory(getActivity().getCacheDir());
//                deleteFilesByDirectory(new File("/data/data/"
//                        + getActivity().getPackageName() + "/save"));
                Toast.makeText(getActivity(), "缓存已清理", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent intent5 = new Intent(getActivity(), WuYueActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {// 通过requestCode来辨别数据来自哪个activity
            case CAMERA:// 取得来自相册的数据，并显示于画面上
                if (data != null) {
                    cameradata = data.getDataString();
                    sdvLogin.setImageURI(Uri.parse(cameradata));
                    SharePreferencesUtil.putString("userimage",cameradata);
                    tvMyname.setText(username);
                }
                break;
            case LOGOIN:// 用户名
                if (data != null) {
                    userimage = data.getStringExtra("userimage");
                    sdvLogin.setImageURI(Uri.parse(userimage));
                    username = data.getStringExtra("username");
                    tvMyname.setText(username);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_my_logo:
                if (TextUtils.isEmpty(tvMyname.getText())) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, LOGOIN);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                    startActivityForResult(intent, CAMERA);

                }
                break;
            case R.id.tv_my_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, LOGOIN);

                break;
        }
    }

    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
