package org.qfln.android_wuyue.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
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
import org.qfln.android_wuyue.fragment.CateCommitFragment;
import org.qfln.android_wuyue.fragment.TuWenFragment;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.L;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 23:51
 * @备注：
 */
public class GridActivity extends BaseActivity {
    private TextView tvCate_xqTitle,tvCate_xqprice,tvCate_xqContent;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager vpBottom;
    private ConvenientBanner convenientBanner;
    private VPBottomAdapter vpAdapter;

    @Override
    protected int getContentResId() {
        Fresco.initialize(this);
        return R.layout.category_xq;
    }

    @Override
    protected void initView() {
        tvCate_xqTitle = (TextView) findViewById(R.id.tv_cate_xqtitle);
        tvCate_xqprice = (TextView) findViewById(R.id.tv_cate_xqprice);
        tvCate_xqContent = (TextView) findViewById(R.id.tv_cate_xqcontent);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    }

    // 放置ViewPager图片的Viewhold
    public class LocalImageHolderView implements Holder<String> {
        private SimpleDraweeView sdv_vp;

        @Override
        public View createView(Context context) {
            sdv_vp = new SimpleDraweeView(context);
            sdv_vp.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
            GenericDraweeHierarchy hierarchy = sdv_vp.getHierarchy();
            hierarchy.setPlaceholderImage(R.drawable.bg_big);// 修改占位图
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
        int grid_id = intent.getIntExtra("Grid_id", 0);
        String grid_url = String.format(Constant.URL.GRIDXQXQ_URL, grid_id);
        VolleyUtil.requestString(grid_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response!=null) {
                    CateXQEntity cateXQEntity = new Gson().fromJson(response.toString(), CateXQEntity.class);
                    CateXQEntity.DataEntity data = cateXQEntity.getData();
                    setTextView(data);
                    List<String> image_urls = data.getImage_urls();
                    bindConvenientBanner(image_urls);

                    vpAdapter = new VPBottomAdapter(getSupportFragmentManager(),data);
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
    }

    private void setTextView(CateXQEntity.DataEntity data) {
        String name = data.getName();
        L.d(name);
        tvCate_xqTitle.setText(name);
        String price = data.getPrice();
        tvCate_xqprice.setText(price);
        String description = data.getDescription();
        tvCate_xqContent.setText(description);
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
                return CateCommitFragment.newInstance(tabName[1]);
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




}
