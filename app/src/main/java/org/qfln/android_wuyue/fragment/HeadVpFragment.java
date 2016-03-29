package org.qfln.android_wuyue.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.HomeVPEntity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/28 21:30
 * @备注：
 */
public class HeadVpFragment extends BaseFragment{
    private SimpleDraweeView sdvVp;
    @Override
    protected int getLayoutResId() {
        return R.layout.homeheadvp_fragment;
    }
    public static HeadVpFragment newInstance(HomeVPEntity.DataEntity.BannersEntity banner){
        HeadVpFragment headVpFragment=new HeadVpFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("vpurl",banner);
        headVpFragment.setArguments(bundle);
        return headVpFragment;
    }

    @Override
    protected void init(View view) {
        sdvVp = (SimpleDraweeView)view.findViewById(R.id.sdv_headvp);

    }

    @Override
    protected void getDatas(Bundle bundle) {
        HomeVPEntity.DataEntity.BannersEntity vpurl = (HomeVPEntity.DataEntity.BannersEntity) bundle.getSerializable("vpurl");
        String image_url = vpurl.getImage_url();
        sdvVp.setImageURI(Uri.parse(image_url));

    }
}
