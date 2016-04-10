package org.qfln.android_wuyue.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.base.BaseActivity;
import org.qfln.android_wuyue.bean.CateXQEntity;
import org.qfln.android_wuyue.custom.CustomProgressDialog;
import org.qfln.android_wuyue.fragment.CateCommitFragment;
import org.qfln.android_wuyue.fragment.TuWenFragment;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.DBUtil;
import org.qfln.android_wuyue.util.L;
import org.qfln.android_wuyue.util.SharePreferencesUtil;
import org.qfln.android_wuyue.util.ShareUtil;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 23:51
 * @备注：
 */
public class GridActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvCate_xqTitle,tvCate_xqprice,tvCate_xqContent;
    private TextView tvCate_xqQtb;
    private TabLayout tabLayout;
    private ImageView ivxqBack,ivxqShare,ivCate_xplike;
    private ViewPager vpBottom;
    private ConvenientBanner convenientBanner;
    private VPBottomAdapter vpAdapter;
    private int grid_id;
    private CateXQEntity.DataEntity data;
    private DBUtil mDbUtil;
    private SQLiteDatabase db;
    private String image;
    private String isCollect;
    private boolean isCollected=false;
    private CustomProgressDialog progressDialog;


    @Override
    protected int getContentResId() {
        Fresco.initialize(this);
        return R.layout.category_xq;
    }

    @Override
    protected void initView() {
        //在网络请求之前显示；
        progressDialog = new CustomProgressDialog(this,"正在加载中...", R.drawable.donghua_frame);
        progressDialog.show();

        tvCate_xqTitle = (TextView) findViewById(R.id.tv_cate_xqtitle);
        tvCate_xqprice = (TextView) findViewById(R.id.tv_cate_xqprice);
        tvCate_xqContent = (TextView) findViewById(R.id.tv_cate_xqcontent);
        tvCate_xqQtb = (TextView) findViewById(R.id.tv_cate_xq_qtb);
        ivCate_xplike = (ImageView) findViewById(R.id.iv_cate_xplike);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        //viewpager
        convenientBanner = (ConvenientBanner) findViewById(R.id.cb_grid_xqtop);
        vpBottom = (ViewPager) findViewById(R.id.vp_grid_xq);
        //测量屏幕宽度 使tablayout 充满全屏
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabLayout.getLayoutParams();
        layoutParams.width = screenW;
        tabLayout.setLayoutParams(layoutParams);

        // 返回 、分享
        ivxqBack = (ImageView) findViewById(R.id.iv_cata_xqback);
        ivxqShare = (ImageView) findViewById(R.id.iv_cata_xqshare);
        ivxqBack.setOnClickListener(this);
        ivxqShare.setOnClickListener(this);
        ivCate_xplike.setOnClickListener(this);

    }

    // 放置ViewPager图片的Viewhold
    public class LocalImageHolderView implements Holder<String> {

        private SimpleDraweeView sdv_vp;
        @Override
        public View createView(Context context) {
            sdv_vp = new SimpleDraweeView(context);
            sdv_vp.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
            GenericDraweeHierarchy hierarchy = sdv_vp.getHierarchy();
            hierarchy.setPlaceholderImage(R.drawable.bg_big1);// 修改占位图
            return sdv_vp;
        }

        @Override
        public void UpdateUI(Context context, int position, String img_url) {
            sdv_vp.setImageURI(Uri.parse(img_url));
        }

    }
    @Override
    protected void loadData() {
        Intent intent = getIntent();
        grid_id = intent.getIntExtra("Grid_id", 0);
        String grid_url = String.format(Constant.URL.GRIDXQXQ_URL, grid_id);
        VolleyUtil.requestString(grid_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                progressDialog.hide();
                if (response!=null) {
                    CateXQEntity cateXQEntity = new Gson().fromJson(response.toString(), CateXQEntity.class);
                    data = cateXQEntity.getData();
                    setTextView(data);
                    List<String> image_urls = data.getImage_urls();
                    bindConvenientBanner(image_urls);//自动轮播Viewpager

                    vpAdapter = new VPBottomAdapter(getSupportFragmentManager(), data);
                    vpBottom.setAdapter(vpAdapter);
                    tabLayout.setTabsFromPagerAdapter(vpAdapter);
                    tabLayout.setupWithViewPager(vpBottom);//与vpBottom联动
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(GridActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });

        //数据库
        mDbUtil = new DBUtil(this);
        db = mDbUtil.getDatabase();
        // 查询数据库.展示收藏结果.
        String sql = "select * from gift_like where id=?";
        Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(grid_id) });
        if (cursor != null) {
            while (cursor.moveToNext()) {
                isCollect = cursor.getString(cursor
                        .getColumnIndex("isCollect"));
                L.i("isCollect=" + isCollect);
                if ("true".equals(isCollect)) {
                    ivCate_xplike.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                } else {
                    ivCate_xplike.setImageResource(R.mipmap.ic_action_compact_favourite_normal);
                }
            }
        }



    }

    private void setTextView(CateXQEntity.DataEntity data) {
        String name = data.getName();
//        L.d(name);
        tvCate_xqTitle.setText(name);
        String price = data.getPrice();
        tvCate_xqprice.setText(price);
        String description = data.getDescription();
        tvCate_xqContent.setText(description);
        final String purchase_id = data.getPurchase_id();
//        final String purchase_url = data.getPurchase_url();
        tvCate_xqQtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GridActivity.this,TaobaoActivity.class);
                intent.putExtra("purchase_id",purchase_id);
                startActivity(intent);
            }
        });
    }

    //自动轮播Viewpager
    private void bindConvenientBanner(final List<String> xqjson) {

        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, xqjson)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ind_tuan_banner_inactive, R.drawable.ind_tuan_banner_active})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        //convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    /**
     * Viewpager适配器
     */
    class VPBottomAdapter extends FragmentPagerAdapter {

        private String[] tabName=new String[]{"图文介绍","评论"};
        private CateXQEntity.DataEntity mdata;

        public VPBottomAdapter(FragmentManager fm,CateXQEntity.DataEntity data) {
            super(fm);
            this.mdata=data;
        }
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return TuWenFragment.newInstance(mdata);
            }else {
                int id = mdata.getId();
                return CateCommitFragment.newInstance(id);
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }

    }

    /**
     * 返回、分享
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_cata_xqback:
                finish();
                break;
            case R.id.iv_cata_xqshare:
                String name = data.getName();
                String titleUrl=String.format("http://www.liwushuo.com/items/%d",grid_id);
                ShareUtil.simpleShowShare(this,titleUrl,name);
                break;
            case R.id.iv_cate_xplike:
                isCollected = !isCollected;
                if (isCollected) {// 收藏
                    ivCate_xplike
                            .setImageResource(R.mipmap.ic_action_compact_favourite_selected);

                    // 数据库实现收藏
                    ContentValues values = new ContentValues();
                    values.put("id", String.valueOf(grid_id));
                    values.put("title", data.getName());
                    values.put("icon",data.getCover_image_url());
                    values.put("price",data.getPrice());
                    values.put("isCollect", "true");
                    values.put("userid", SharePreferencesUtil.getString("userid"));
                    long insert = db.insert("gift_like", null, values);
                    if (insert > 0) {
                        Toast.makeText(this, "收藏成功",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {// 移除收藏
                    ivCate_xplike
                            .setImageResource(R.mipmap.ic_action_compact_favourite_normal);

                    int delete = db.delete("gift_like", "id=?",
                            new String[] { String.valueOf(grid_id) });
                    if (delete > 0) {
                        Toast.makeText(this, "取消收藏",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }




}
