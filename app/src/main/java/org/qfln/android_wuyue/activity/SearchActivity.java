package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.ReSearchAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.SearchRe_Entity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 19:19
 * @备注：
 */
public class SearchActivity extends BaseActivity{
    private TextView tvSearch;
    private RecyclerView reSearch;
    private EditText etSearch;
    private String search_re_url= Constant.URL.SEARCH_URL1;
    @Override
    protected int getContentResId() {
        return R.layout.search_layout;
    }

    @Override
    protected void initView() {
        tvSearch = (TextView) findViewById(R.id.tv_search);
        etSearch = (EditText) findViewById(R.id.et_search);
        reSearch = (RecyclerView) findViewById(R.id.relv_search);

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etSearch.getText().toString();
                if(!TextUtils.isEmpty(str)){
                    Intent intent=new Intent(SearchActivity.this,Search2Activity.class);
                    intent.putExtra("hot_word",str);
                    SearchActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(SearchActivity.this, "请输入关键字进行搜索", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void loadData() {

        VolleyUtil.requestString(search_re_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                SearchRe_Entity re_entity=new Gson().fromJson(response.toString(),SearchRe_Entity.class);
                List<String> hot_words = re_entity.getData().getHot_words();
                reSearch.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.HORIZONTAL));
                ReSearchAdapter searchAdapter=new ReSearchAdapter(SearchActivity.this);
                searchAdapter.setDatas(hot_words);
                reSearch.setAdapter(searchAdapter);
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(SearchActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void click(View v){
        finish();
    }
}
