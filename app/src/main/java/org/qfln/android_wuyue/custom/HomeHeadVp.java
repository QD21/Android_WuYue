package org.qfln.android_wuyue.custom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.qfln.android_wuyue.activity.HeadVpActivity;
import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.HomeVPEntity;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.DownUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/9 13:07
 * @备注：
 */
public class HomeHeadVp extends FrameLayout {
    private String url=Constant.URL.HOMEVP_URL;
    private ConvenientBanner convenientBanner;
    private List<String> imgUrls;
    private List<HomeVPEntity.DataEntity.BannersEntity> banners;

    public HomeHeadVp(Context context) {
        super(context);
        init();
        setUrl(url);
    }

    /**
     * 初始化方法
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.headvp_layout, this, true);

        imgUrls = new ArrayList<>();
        convenientBanner= (ConvenientBanner) findViewById(R.id.convenientBanner);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String target_id = banners.get(position).getTarget_id();
                if(target_id!=null) {
                    Intent intent=new Intent(getContext(), HeadVpActivity.class);
                    HomeVPEntity.DataEntity.BannersEntity.TargetEntity target = banners.get(position).getTarget();
                    intent.putExtra("vpitem_id",target.getId());
//                    L.i("target_id="+target_id);
                    intent.putExtra("vpitem_title",target.getTitle());
                    getContext().startActivity(intent);
                }

            }
        });

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
//            sdv_vp.setPadding(10,0,10,10);//设置边距
//            RoundingParams roundingParams = hierarchy.getRoundingParams();
//            roundingParams.setCornersRadius(5);//设置圆角
//            hierarchy.setRoundingParams(roundingParams);不对
            return sdv_vp;
        }

        @Override
        public void UpdateUI(Context context, int position, String img_url) {
            sdv_vp.setImageURI(Uri.parse(img_url));
        }
    }

    /**
     * 提供该方法让外界调用，设置数据的URL
     */
    public void setUrl(String url) {
        DownUtil.downJson(url, new DownUtil.OnDownListener() {
            @Override
            public void downSuccess(String path, Object obj) {
                if (obj != null) {
                    HomeVPEntity dataEntity = new Gson().fromJson(obj.toString(), HomeVPEntity.class);
                    HomeVPEntity.DataEntity data = dataEntity.getData();
                    banners = data.getBanners();
                    // 将图片的url放入到集合中的方法
                    bindConvenientBanner(banners);

                }
            }
        });
    }

    private void bindConvenientBanner(final List<HomeVPEntity.DataEntity.BannersEntity> banners) {
        for (int i = 0; i < banners.size(); i++) {
            imgUrls.add(banners.get(i).getImage_url());
        }

        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, imgUrls)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ind_tuan_banner_inactive,R.drawable.ind_tuan_banner_active})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            //设置翻页的效果，不需要翻页效果可用不设
            //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
            //convenientBanner.setManualPageable(false);//设置不能手动影响
            convenientBanner.startTurning(3000);

    }



}