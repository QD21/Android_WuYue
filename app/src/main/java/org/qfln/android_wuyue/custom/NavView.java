package org.qfln.android_wuyue.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.qfln.android_wuyue.R;


/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/10 00:19
 * @备注：
 */
public class NavView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private LinearLayout uncheckedll,checkedll;
    private int checkedImg;
    private int uncheckedImg;
    private int count;
    private ImageView iv;


    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paresAttr(attrs);
        init();
    }
    /**
     * 解析自定义属性
     * @param attrs
     */
    private void paresAttr(AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.navview);
        checkedImg = typedArray.getResourceId(R.styleable.navview_checkedimg, 0);
        uncheckedImg = typedArray.getResourceId(R.styleable.navview_uncheckedimg, 0);
        typedArray.recycle();//资源回收
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_circle,this,true);
        checkedll= (LinearLayout) findViewById(R.id.ll_checked);
        uncheckedll= (LinearLayout) findViewById(R.id.ll_unchecked);

    }
    /**
     * 设置控件需要显示几个图标
     */
    public void setcount(int count){
        checkedll.removeAllViews();
        uncheckedll.removeAllViews();
        this.count=count;
        initView();
    }

    private void initView() {
        if(count > 0 && checkedImg != 0 && uncheckedImg != 0){
            for (int i = 0; i <count ; i++) {
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                ImageView univ=new ImageView(getContext());
                univ.setImageResource(uncheckedImg);
                univ.setPadding(2, 2, 2, 2);
                univ.setLayoutParams(layoutParams);//设置布局参数
                uncheckedll.addView(univ);//加到不居中
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv = new ImageView(getContext());
            iv.setImageResource(checkedImg);
            iv.setPadding(2, 2, 2, 2);
            iv.setLayoutParams(layoutParams);
            checkedll.addView(iv);
        }
    }
    /**
     * 设置与该导航控件联动的ViewPager
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager){
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        L.d("----->" + position + "   " + positionOffset);
        setMoveSize(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置导航的效果
     */
    public void setMoveSize(int position,  float positionOffset){
        if(iv != null){
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv.getLayoutParams();
            layoutParams.leftMargin = (int) (iv.getWidth() * (position + positionOffset));
            iv.setLayoutParams(layoutParams);
        }
    }
}
