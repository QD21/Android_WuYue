package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.CommitGLAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.CommitGLEntity;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshBase;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshListView;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/7 16:45
 * @备注：
 */
public class CommitActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener, View.OnClickListener {
    private PullToRefreshListView pullCommit;
    private ImageView commit_back;
    private ListView mlv_commit;

    @Override
    protected int getContentResId() {
        return R.layout.commit_gonglue;
    }

    @Override
    protected void initView() {
        pullCommit = (PullToRefreshListView) findViewById(R.id.pullrefresh_com);
        commit_back = (ImageView) findViewById(R.id.commit_back);
        commit_back.setOnClickListener(this);
        mlv_commit = pullCommit.getRefreshableView();

        pullCommit.setOnRefreshListener(this);

    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        int item_id = intent.getIntExtra("commit_id", 0);
        final String Commit_GL=String.format(Constant.URL.COMMIT_GL,item_id);
        VolleyUtil.requestString(Commit_GL, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                pullCommit.onRefreshComplete();
                if(response!=null) {
                    CommitGLEntity glEntity = new Gson().fromJson(response.toString(), CommitGLEntity.class);
                    CommitGLEntity.DataEntity data = glEntity.getData();
                    List<CommitGLEntity.DataEntity.CommentsEntity> comments = data.getComments();
                    CommitGLAdapter glAdapter = new CommitGLAdapter(CommitActivity.this);
                    glAdapter.setDatas(comments);
                    mlv_commit.setAdapter(glAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(CommitActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
