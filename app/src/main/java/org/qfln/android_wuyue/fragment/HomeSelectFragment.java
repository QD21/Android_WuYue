package org.qfln.android_wuyue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.SelectAdapter;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.SelectListEntity;
import org.qfln.android_wuyue.bean.TimeEntity;
import org.qfln.android_wuyue.custom.HomeHead2;
import org.qfln.android_wuyue.custom.HomeHeadVp;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshBase;
import org.qfln.android_wuyue.pulltorefresh.PullToRefreshListView;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/28 17:01
 * @备注：
 */
public class HomeSelectFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener, PullToRefreshBase.OnLastItemVisibleListener {
    private PullToRefreshListView pToRefreshListView;
    private ListView lv;
    private View homehead3;
    private SelectAdapter selectAdapter;
    private SelectListEntity.DataEntity data;
    private List<SelectListEntity.DataEntity.ItemsEntity> datas = new ArrayList<>();
    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;
    private TextView tvTime;
    private TextView tvNewTime;
    private int offset = 10;

    @Override
    protected int getLayoutResId() {
        return R.layout.homeselect_layout;
    }

    public static HomeSelectFragment newInstance(String title) {
        HomeSelectFragment homeselectFragment = new HomeSelectFragment();
        Bundle args = new Bundle();
        args.putString("which", title);
        homeselectFragment.setArguments(args);
        return homeselectFragment;
    }

    /**
     * 初始化view
     *
     * @param view
     */
    @Override
    protected void init(View view) {
        HomeHeadVp headVp = new HomeHeadVp(getActivity());
        HomeHead2 homehead2 = new HomeHead2(getActivity());
        homehead3 = LayoutInflater.from(getActivity()).inflate(R.layout.homehead3_layout, null);
        pToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.ptrflv);
        lv = pToRefreshListView.getRefreshableView();

        lv.addHeaderView(headVp);//添加头部视图1
        lv.addHeaderView(homehead2);//添加头部视图2
        lv.addHeaderView(homehead3);

        tvTime = (TextView) homehead3.findViewById(R.id.tv_time);
        tvNewTime = (TextView) homehead3.findViewById(R.id.tv_newtime);

        pToRefreshListView.setOnRefreshListener(this);
        pToRefreshListView.setOnLastItemVisibleListener(this);
        selectAdapter = new SelectAdapter(getContext());
        lv.setAdapter(selectAdapter);// 设置适配器
    }


    /**
     * 加载数据
     */
    @Override
    protected void loadData() {
        //得到首页listView的url
        String SELECT_LISTURL = String.format(Constant.URL.SELECT_LISTURL, 10);
        //进行Json下载
        VolleyUtil.requestString(SELECT_LISTURL, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                pToRefreshListView.onRefreshComplete();//刷新完成
                Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                if (response != null) {
                    SelectListEntity selectEntity = new Gson().fromJson(response.toString(), SelectListEntity.class);
                    data = selectEntity.getData();
                    List<SelectListEntity.DataEntity.ItemsEntity> items = data.getItems();
                    datas.addAll(items);
                    selectAdapter.setDatas(items);
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                pToRefreshListView.setPullToRefreshEnabled(false);

                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();

            }
        });
        String time_url = Constant.URL.TIME_URL;
        setTime();//设置当前年月日，星期
        VolleyUtil.requestString(time_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response != null) {
                    TimeEntity timeEntity = new Gson().fromJson(response.toString(), TimeEntity.class);
                    TimeEntity.DataEntity data = timeEntity.getData();
                    TimeEntity.DataEntity.SchedulesEntity schedules = data.getSchedules();
                    List<String> post = schedules.getItem();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    String newtime = sdf.format(new Date());
                    String[] time = newtime.split("\\:");
                    int hour = Integer.valueOf(time[0]);
                    int minute = Integer.valueOf(time[1]);
                    if (hour < 8) {
                        if (minute <= 59) {
                            tvNewTime.setText("下次更新" + post.get(0));
                        }

                    } else if (hour < 12) {
                        if (minute <= 59) {
                            tvNewTime.setText("下次更新" + post.get(1));
                        }
                    } else if (hour > 12&&hour<24) {
                        if (minute <= 59) {
                            tvNewTime.setText("下次更新" + post.get(0));
                        }
                    }

                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTime() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        tvTime.setText(mYear + "-" + mMonth + "-" + mDay + " " + "周" + mWay);
    }

    /**
     * 下拉刷新的方法
     */
    @Override
    public void onRefresh() {
        loadData();
    }

    /**
     * 分页加载的方法
     */
    @Override
    public void onLastItemVisible() {
        offset += 10;
        String next_url = String.format(Constant.URL.NEXT_UR, offset);

        VolleyUtil.requestString(next_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response != null) {
                    SelectListEntity selectEntity = new Gson().fromJson(response.toString(), SelectListEntity.class);
                    final SelectListEntity.DataEntity data = selectEntity.getData();
                    List<SelectListEntity.DataEntity.ItemsEntity> items = data.getItems();
                    selectAdapter.addDatas(items);

                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "网络不给力，数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
