package org.qfln.android_wuyue.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.CommitAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.CommitEntity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 17:34
 * @备注：
 */
public class CateCommitFragment extends BaseFragment{
    private ListView mlv;
    private FloatingActionButton fab;
    private CommitAdapter commitAdapter;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected int getLayoutResId() {
        return R.layout.catecommit_layout;
    }

    public static CateCommitFragment newInstance(int id) {
        CateCommitFragment commitFragment=new CateCommitFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("commt_id",id);
        commitFragment.setArguments(bundle);
        return commitFragment;
    }

    @Override
    protected void init(View view) {
        mlv = (ListView) view.findViewById(R.id.lv_commit);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_commit);
        final View view1 = getActivity().getLayoutInflater().inflate(R.layout.edittext_layout, null);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindowWidthAndHeight();
                PopupWindow mWindow=new PopupWindow(view1,screenWidth,90);
                mWindow.setOutsideTouchable(true);
                mWindow.setFocusable(true);
                Drawable background = getResources().getDrawable(
                        R.drawable.edittext);
                mWindow.setBackgroundDrawable(background);
                //防止虚拟软键盘被弹出菜单遮住
                mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                //在底部显示
                mWindow.showAtLocation(getView(), Gravity.BOTTOM, 0, 0);
                mWindow.showAsDropDown(fab, 0, view1.getWidth());
            }
        });
    }

    /**
     * 获取屏幕宽高
     */
    public void getWindowWidthAndHeight() {
        // WindowManager:用来管理窗口信息的类
        WindowManager manager = getActivity().getWindowManager();
        // 进行屏幕的测量
        DisplayMetrics outMetrics = new DisplayMetrics();
        // getDefaultDisplay():获取屏幕默认的显示数据
        // getMetrics:进行屏幕的测量
        manager.getDefaultDisplay().getMetrics(outMetrics);
        // 屏幕的宽度
        screenWidth = outMetrics.widthPixels;
        // 屏幕的高度
        screenHeight = outMetrics.heightPixels;
    }
    @Override
    protected void getDatas(Bundle bundle) {
        int commt_id = (int) bundle.getSerializable("commt_id");
        String commit_url=String.format(Constant.URL.COMMIT_URL,commt_id);
        VolleyUtil.requestString(commit_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if(response!=null){
                    CommitEntity commitEntity=new Gson().fromJson(response.toString(),CommitEntity.class);
                    List<CommitEntity.DataEntity.CommentsEntity> comments = commitEntity.getData().getComments();
                    commitAdapter = new CommitAdapter(getActivity());
                    commitAdapter.setDatas(comments);
                    mlv.setAdapter(commitAdapter);

                }

            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {

            }
        });
    }


}
