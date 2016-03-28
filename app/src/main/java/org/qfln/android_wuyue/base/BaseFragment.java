package org.qfln.android_wuyue.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/7 20:32
 * @备注：
 */
public abstract class BaseFragment extends Fragment {
    protected abstract int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(),container,false);
    }

    /**
     * 该方法会紧跟着onCreateView被调用  在此类中进行初始化，加载数据
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadData();
    }
    /**
     *  加载数据的方法
     */
    protected void loadData() {
    }

    /**
     * 初始化
     * @param view
     */
    protected void init(View view) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDatas(getArguments());
    }

    protected void getDatas(Bundle bundle){

    }
}
