package org.qfln.android_wuyue.fragment;

import android.view.View;
import android.widget.GridView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.GridxqAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.util.Constant;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 11:19
 * @备注：
 */
public class GiftCollectFragment extends BaseFragment{
    private String url = Constant.URL.HOMEHEAD2_URL;
    private GridView gv;
    private GridxqAdapter xqAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.collect_layout;
    }
    public static GiftCollectFragment newInstance(){
        GiftCollectFragment giftCollectFragment=new GiftCollectFragment();
        return giftCollectFragment;
    }

    @Override
    protected void init(View view) {
        gv = (GridView) view.findViewById(R.id.gv_collect);
        xqAdapter = new GridxqAdapter(getContext());
        gv.setAdapter(xqAdapter);
    }


}
