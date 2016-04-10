package org.qfln.android_wuyue.fragment;

import android.view.View;
import android.widget.ListView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.CollectGLAdapter;
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
public class StrategyFragment extends BaseFragment{
    private ListView GL_mlv;
    private List<Map<String,Object>> sqlList;
    private DBUtil mUtil;
    @Override
    protected int getLayoutResId() {
        mUtil = new DBUtil(getActivity());
        return R.layout.strategy_layout;
    }
    public static StrategyFragment newInstance(){
        StrategyFragment strategyFragment=new StrategyFragment();
        return strategyFragment;
    }

    @Override
    protected void init(View view) {
        GL_mlv = (ListView)view. findViewById(R.id.lv_strategy);
        String sql = "select * from GL_Like";
        sqlList = mUtil.queryList(sql, null);
        CollectGLAdapter collectGLAdapter=new CollectGLAdapter(getActivity(),GL_mlv);
        collectGLAdapter.addDatas(sqlList);
        GL_mlv.setAdapter(collectGLAdapter);


    }


}
