package org.qfln.android_wuyue.util;

/**
 * @描述： 常量工具类
 * @创建人： LN
 * @创建时间: 2016/3/9 19:21
 * @备注：
 */
public interface Constant {
    interface CODE{

    }

    interface URL{
        //首页中的tablayout
        String TAB_URL="http://api.liwushuo.com/v2/channels/preset?gender=2&generation=2";
        // 首页中tablayout中listView
        String TAB_ID="http://api.liwushuo.com/v2/channels/%d/items?";
        //精选listView
        String SELECT_LISTURL="http://api.liwushuo.com/v2/channels/104/items?limit=%d&ad=2&gender=2&offset=0&generation=1";
        // 精选listView加载更多
        String NEXT_UR="http://api.liwushuo.com/v2/channels/104/items?generation=1&gender=2&limit=10&ad=2&offset=%d";
        //精选ViewPager
        String HOMEVP_URL="http://api.liwushuo.com/v2/banners";
        //头部视图2
        String HOMEHEAD2_URL="http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
        //ViewPager的item详情
        String VPITEM_URL="http://api.liwushuo.com/v2/collections/%d/posts?limit=20&offset=0";
        String TIME_URL="http://api.liwushuo.com/v2/content_schedules?gender=2&generation=1";
        /**
         * 热门
         */
        String HOTLIST_URL="http://api.liwushuo.com/v2/items?limit=%d&offset=0&gender=2&generation=1";
    }

    interface KEYS{

    }
}
