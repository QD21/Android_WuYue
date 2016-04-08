package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.GridxqAdapter;
import org.qfln.android_wuyue.adapter.PopLvAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.Cate_GridEntity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 00:24
 * @备注：
 */
public class GridxqActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private GridView mGv;
    private TextView tvTitle;
    private View popview;
    private PopLvAdapter popAdapter;
    private List<Cate_GridEntity.DataEntity.ItemsEntity> items;
    private ImageView ivGxPaixu,ivGxback;
    private String gridxq_url;
    private String gridxq_next;
    private List<Cate_GridEntity.DataEntity.ItemsEntity> datas = new ArrayList<>();
    private boolean isBottom=false;
    private boolean isLoading=false;
    private int offset=20;
    private int id1;
    private GridxqAdapter xqAdapter;

    @Override
    protected int getContentResId() {
        return R.layout.category_gridxq;
    }

    @Override
    protected void initView() {

        mGv = (GridView) findViewById(R.id.gv_category);
        tvTitle = (TextView) findViewById(R.id.tv_cate_title);
        ivGxPaixu = (ImageView) findViewById(R.id.iv_xqpaixu);
        ivGxback = (ImageView) findViewById(R.id.iv_xqback);
        ivGxback.setOnClickListener(this);
        ivGxPaixu.setOnClickListener(this);
        mGv.setOnItemClickListener(this);
        /**
         * PopupWindow 里的listview
         */

        popview = LayoutInflater.from(this).inflate(R.layout.popwindow_listview, null);
        final ListView pop_lv = (ListView) popview.findViewById(R.id.pop_lv);
        popAdapter = new PopLvAdapter(this);
        pop_lv.setAdapter(popAdapter);

        pop_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    gridxq_url = String.format(Constant.URL.GRIDXQ_URL,id1);
                    // 加载新数据
                    gridxq_next = String.format(Constant.URL.GRIDXQ_NEXT,id1, offset);
//                        L.e(next_url1);
                    loadData();
                } else if (position == 1) {
                    gridxq_url = String.format(Constant.URL.GRIDXQ_PX, id1, "hot");
                    gridxq_next = String.format(Constant.URL.GRIDXQ_NEXT_PX,id1 ,"hot", offset);
                    loadData();
                } else if (position == 2) {
                    gridxq_url = String.format(Constant.URL.GRIDXQ_PX, id1, "price%3Aasc");
                    gridxq_next = String.format(Constant.URL.GRIDXQ_NEXT_PX,id1 ,"price%3Aasc", offset);
                    loadData();

                } else if (position == 3) {
                    gridxq_url = String.format(Constant.URL.GRIDXQ_PX, id1, "price%3Adesc");
                    gridxq_next = String.format(Constant.URL.GRIDXQ_NEXT_PX,id1 ,"price%3Adesc", offset);
                    loadData();
                }
            }
        });

        Intent intent = getIntent();
        id1 = intent.getIntExtra("id1", 0);
        String name1 = intent.getStringExtra("name1");
        tvTitle.setText(name1);
        gridxq_url = String.format(Constant.URL.GRIDXQ_URL, id1);
        gridxq_next = String.format(Constant.URL.GRIDXQ_NEXT,id1,offset);
    }

    @Override
    protected void loadData() {

        VolleyUtil.requestString(gridxq_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if(response!=null){
                    Cate_GridEntity gridEntity=new Gson().fromJson(response.toString(),Cate_GridEntity.class);
                    items = gridEntity.getData().getItems();
                    datas.addAll(items);
                    xqAdapter = new GridxqAdapter(GridxqActivity.this);
                    xqAdapter.setDatas(items);
                    mGv.setAdapter(xqAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(GridxqActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
        // 分页加载
        mGv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                // 屏幕空闲且数据到了底部
                if ((scrollState == SCROLL_STATE_IDLE) && isBottom) {
                    offset += 20;
//                    L.e(" "+offset);
                    if (!isLoading) {

                        isLoading = true;

                        VolleyUtil.requestString(gridxq_next, new VolleyUtil.OnRequestListener() {
                            @Override
                            public void onResponse(String url, String response) {
                                if (response != null) {
                                    Cate_GridEntity gridEntity=new Gson().fromJson(response.toString(),Cate_GridEntity.class);
                                    List<Cate_GridEntity.DataEntity.ItemsEntity> items = gridEntity.getData().getItems();
                                    xqAdapter.addDatas(items);
                                    onLoadComplete();
                                }

                            }

                            @Override
                            public void onErrorResponse(String url, VolleyError error) {
                                Toast.makeText(GridxqActivity.this, "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                isBottom = false;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 数据到了最后一条
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                }

            }
        });



    }

    public void onLoadComplete() {
        isLoading = false;
    }
    //GridView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(GridxqActivity.this,GridActivity.class);
        int id1 = items.get(position).getId();
        intent.putExtra("Grid_id",id1);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_xqback:
                finish();
                break;
            case R.id.iv_xqpaixu:
                PopupWindow windows = new PopupWindow(popview, 250, 280);
                windows.setFocusable(true);
                windows.setOutsideTouchable(true);
                Drawable background = getResources().getDrawable(
                        R.drawable.abc_ab_soli);
                windows.setBackgroundDrawable(background);
                windows.showAsDropDown(ivGxPaixu, 0, 18);
                break;
        }
    }
}
