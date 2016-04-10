package org.qfln.android_wuyue.fragment;

import android.view.View;
import android.widget.GridView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.CollGiftAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.util.DBUtil;

import java.util.List;
import java.util.Map;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 11:19
 * @备注：
 */
public class GiftCollectFragment extends BaseFragment{

    private GridView gv;
    private CollGiftAdapter giftAdapter;
    private List<Map<String,Object>> sqlList;
    private DBUtil mUtil;

    @Override
    protected int getLayoutResId() {
        mUtil = new DBUtil(getActivity());
        return R.layout.collect_gift_fragment;
    }
    public static GiftCollectFragment newInstance(){
        GiftCollectFragment giftCollectFragment=new GiftCollectFragment();
        return giftCollectFragment;
    }

    @Override
    protected void init(View view) {
        gv = (GridView) view.findViewById(R.id.gv_collect);
        String sql = "select * from gift_like";
        sqlList = mUtil.queryList(sql, null);
        giftAdapter = new CollGiftAdapter(getContext(),gv);
        giftAdapter.addDatas(sqlList);
        gv.setAdapter(giftAdapter);
    }


}
