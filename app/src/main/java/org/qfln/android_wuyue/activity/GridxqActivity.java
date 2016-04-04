package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.GridxqAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.Cate_GridEntity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 00:24
 * @备注：
 */
public class GridxqActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private GridView mGv;
    private TextView tvTitle;
    private List<Cate_GridEntity.DataEntity.ItemsEntity> items;

    @Override
    protected int getContentResId() {
        return R.layout.category_gridxq;
    }

    @Override
    protected void initView() {
        mGv = (GridView) findViewById(R.id.gv_category);
        tvTitle = (TextView) findViewById(R.id.tv_cate_title);
        mGv.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id1", 0);
        String name1 = intent.getStringExtra("name1");
        tvTitle.setText(name1);
        String gridxq_url=String.format(Constant.URL.GRIDXQ_URL,id1);
        VolleyUtil.requestString(gridxq_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if(response!=null){
                    Cate_GridEntity gridEntity=new Gson().fromJson(response.toString(),Cate_GridEntity.class);
                    items = gridEntity.getData().getItems();
                    GridxqAdapter xqAdapter=new GridxqAdapter(GridxqActivity.this);
                    xqAdapter.setDatas(items);
                    mGv.setAdapter(xqAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(GridxqActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //GridView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(GridxqActivity.this,GridActivity.class);
        int id1 = items.get(position).getId();
        intent.putExtra("Grid_id",id1);
        startActivity(intent);
    }
}
