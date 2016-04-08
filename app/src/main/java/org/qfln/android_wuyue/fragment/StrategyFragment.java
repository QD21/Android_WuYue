package org.qfln.android_wuyue.fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 11:19
 * @备注：
 */
public class StrategyFragment extends BaseFragment{
    private ListView mlv;
    @Override
    protected int getLayoutResId() {
        return R.layout.strategy_layout;
    }
    public static StrategyFragment newInstance(){
        StrategyFragment strategyFragment=new StrategyFragment();
        return strategyFragment;
    }

    @Override
    protected void init(View view) {
        mlv = (ListView)view. findViewById(R.id.lv_strategy);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("kkkksj"+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,list);
        mlv.setAdapter(adapter);

    }
}
