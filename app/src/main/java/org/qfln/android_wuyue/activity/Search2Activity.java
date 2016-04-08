package org.qfln.android_wuyue.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.KeyWordEntity;
import org.qfln.android_wuyue.adapter.KeylistAdapter;
import org.qfln.android_wuyue.adapter.PopLvAdapter;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/7 10:22
 * @备注：
 */
public class Search2Activity extends BaseActivity implements View.OnClickListener {
    private ListView lv_word;
    private TextView tv_Empty;
    private EditText et_word;
    private ImageView iv_paixu, iv_wordBack, ivRefresh;
    private boolean isBottom = false;
    private boolean isLoading = false;
    private KeyWordEntity.DataEntity data;
    private String search_url2;
    private KeylistAdapter keyAdapter;
    private List<KeyWordEntity.DataEntity.ItemsEntity> datas = new ArrayList<>();
    private View footer;
    private String decode;
    private int offset = 20;
    private String hot_word;
    private View popview;
    private PopLvAdapter popAdapter;
    private String next_url1;

    @Override
    protected int getContentResId() {
        return R.layout.search2_layout;
    }

    @Override
    protected void initView() {
        footer = LayoutInflater.from(this).inflate(R.layout.list_foot_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);
        /**
         * 开启动画
         */
        Animation animtion = AnimationUtils.loadAnimation(this, R.anim.foot_rotate);
        ivRefresh.startAnimation(animtion);

        tv_Empty = (TextView) findViewById(R.id.tv_Empty);
        tv_Empty.setVisibility(View.INVISIBLE);
        lv_word = (ListView) findViewById(R.id.lv_word_search);
        lv_word.setEmptyView(tv_Empty);
        lv_word.addFooterView(footer);// 添加底部视图
        et_word = (EditText) findViewById(R.id.et_word_search);
        iv_paixu = (ImageView) findViewById(R.id.iv_word_paixu);
        iv_wordBack = (ImageView) findViewById(R.id.iv_word_back);
        iv_wordBack.setOnClickListener(this);
        iv_paixu.setOnClickListener(this);

        et_word.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String text = et_word.getText().toString();
                    try {
                        decode = URLEncoder.encode(text, "UTF-8");
                        search_url2 = String.format(Constant.URL.SEARCH_URL2, 20, decode);
                        // 加载新数据
                        next_url1 = String.format(Constant.URL.ZUIJIA_URL, decode, offset);
//                        L.e(next_url1);
                        loadData();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    return true;
                }
                return false;
            }
        });


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
                    search_url2 = String.format(Constant.URL.SEARCH_URL2, 20, decode);
                    // 加载新数据
                    next_url1 = String.format(Constant.URL.ZUIJIA_URL, decode, offset);
//                        L.e(next_url1);
                    loadData();
                } else if (position == 1) {
                    search_url2 = String.format(Constant.URL.SEARCH_PAIXU, 20, "hot", decode);
                    next_url1 = String.format(Constant.URL.ZUIJIA_PAIXU, "hot", decode, offset);
                    loadData();
                } else if (position == 2) {
                    search_url2 = String.format(Constant.URL.SEARCH_PAIXU, 20, "price%3Aasc", decode);
                    next_url1 = String.format(Constant.URL.ZUIJIA_PAIXU, "price%3Aasc", decode, offset);
                    loadData();

                } else if (position == 3) {
                    search_url2 = String.format(Constant.URL.SEARCH_PAIXU, 20, "price%3Adesc", decode);
                    next_url1 = String.format(Constant.URL.ZUIJIA_PAIXU, "price%3Adesc", decode, offset);
                    loadData();
                }
            }
        });


        Intent intent = getIntent();
        hot_word = intent.getStringExtra("hot_word");
        et_word.setText(hot_word);
        try {
            decode = URLEncoder.encode(hot_word, "UTF-8");
            search_url2 = String.format(Constant.URL.SEARCH_URL2, 20, decode);
//            L.e(search_url2);
            // 加载新数据
            next_url1 = String.format(Constant.URL.ZUIJIA_URL, decode, offset);
//      L.e(next_url1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadData() {
        VolleyUtil.requestString(search_url2, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response != null) {
                    KeyWordEntity wordEntity = new Gson().fromJson(response.toString(), KeyWordEntity.class);
                    data = wordEntity.getData();
                    List<KeyWordEntity.DataEntity.ItemsEntity> items = data.getItems();
//                        L.d("momo=" + items.size());
                    datas.addAll(items);
                    keyAdapter = new KeylistAdapter(Search2Activity.this);
                    keyAdapter.setDatas(items);
                    lv_word.setAdapter(keyAdapter);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(Search2Activity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });


        // 分页加载
        lv_word.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                // 屏幕空闲且数据到了底部
                if ((scrollState == SCROLL_STATE_IDLE) && isBottom) {
                    offset += 20;
//                    L.e(" "+offset);
                    if (!isLoading) {

                        isLoading = true;
                        footer.setVisibility(View.VISIBLE);

                        VolleyUtil.requestString(next_url1, new VolleyUtil.OnRequestListener() {
                            @Override
                            public void onResponse(String url, String response) {
                                if (response != null) {
                                    KeyWordEntity wordEntity = new Gson().fromJson(response.toString(), KeyWordEntity.class);
                                    KeyWordEntity.DataEntity data = wordEntity.getData();
                                    List<KeyWordEntity.DataEntity.ItemsEntity> items = data.getItems();
                                    keyAdapter.addData(items);
                                    onLoadComplete();
                                }

                            }

                            @Override
                            public void onErrorResponse(String url, VolleyError error) {
                                Toast.makeText(Search2Activity.this, "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
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
        this.footer.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_word_back:
                finish();
                break;
            case R.id.iv_word_paixu:
                PopupWindow windows = new PopupWindow(popview, 250, 280);
                windows.setFocusable(true);
                windows.setOutsideTouchable(true);
                Drawable background = getResources().getDrawable(
                        R.drawable.abc_ab_soli);
                windows.setBackgroundDrawable(background);
                windows.showAsDropDown(iv_paixu, 0, 18);

                break;
        }
    }


}
