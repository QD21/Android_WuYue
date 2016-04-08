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
        /**
         * 首页
         */
        //首页中的tablayout
        String TAB_URL="http://api.liwushuo.com/v2/channels/preset?gender=2&generation=2";
        // 首页中tablayout中listView
        String TAB_ID="http://api.liwushuo.com/v2/channels/%d/items?";
        //加载更多
        String TAB_MORE="http://api.liwushuo.com/v2/channels/%d/items?limit=10&offset=%d";

        //精选listView
        String SELECT_LISTURL="http://api.liwushuo.com/v2/channels/104/items?limit=%d&ad=2&gender=2&offset=0&generation=1";
        // 精选listView加载更多
//        String newt_ur="http://api.liwushuo.com/v2/channels/104/items?generation=1&gender=2&limit=10&ad=2&offset=10";
        String NEXT_UR="http://api.liwushuo.com/v2/channels/104/items?generation=1&gender=2&limit=10&ad=2&offset=%d";
        //精选ViewPager
        String HOMEVP_URL="http://api.liwushuo.com/v2/banners";
        //精选ViewPager的详情   http://api.liwushuo.com/v2/posts_v2/1039727
        String VPXQ_URL="http://api.liwushuo.com/v2/posts_v2/%d";
        //头部视图2 天天
        String HOMEHEAD2_URL="http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
        //ViewPager的item详情
        String VPITEM_URL="http://api.liwushuo.com/v2/collections/%d/posts?limit=20&offset=0";
        String TIME_URL="http://api.liwushuo.com/v2/content_schedules?gender=2&generation=1";
        //天天刮奖
        String TIAN_URL="https://event.liwushuo.com/topics/daily-lucky";
        /**
         * 热门
         */

        String HOTLIST_URL="http://api.liwushuo.com/v2/items?limit=%d&offset=0&gender=2&generation=1";
        String HOTJIA_url="http://api.liwushuo.com/v2/items?generation=1&gender=2&limit=10&offset=%d";
        /**
         * 分类
         */
        String CATEGORY_URL="http://api.liwushuo.com/v2/item_categories/tree";
        //详情页  http://api.liwushuo.com/v2/item_subcategories/7/items?limit=20&offset=0    price%3Aasc
        String GRIDXQ_URL="http://api.liwushuo.com/v2/item_subcategories/%d/items?limit=20&offset=0";
        String GRIDXQ_PX="http://api.liwushuo.com/v2/item_subcategories/%d/items?limit=20&offset=20&sort=%s";
        String GRIDXQ_NEXT="http://api.liwushuo.com/v2/item_subcategories/%d/items?limit=20&offset=%d";
        String GRIDXQ_NEXT_PX="http://api.liwushuo.com/v2/item_subcategories/%d/items?sort=%s&limit=20&offset=%d";

        //详情的详情页
        String GRIDXQXQ_URL="http://api.liwushuo.com/v2/items/%d";
        //http://api.liwushuo.com/v2/items/1020470
        //评论
        String COMMIT_URL="http://api.liwushuo.com/v2/items/%d/comments?limit=20&offset=0";

        String COMMIT_GL="http://api.liwushuo.com/v2/posts/%d/comments?limit=20&offset=0";
        // 淘宝
        String TAOBAO_URL="http://h5.m.taobao.com/awp/core/detail.htm?id=%s&ali_trackid=2:mm_56503797_8596089_29498842:1459743296_253_646655584&sche=liwushuo&e=nywLq91vfzdw4vFB6t2Z2ueEDrYVVa64XoO8tOebS-dRAdhuF14FMSp8EgNNE5bblovu_CElQOutgmtnxDX9deVMA5qBABUs5mPg1WiM1P5OS0OzHKBZzQIomwaXGXUs78FqzS29vh5nPjZ5WWqolN_WWjML5JdM90WyHry0om2fzLsiumGGcyMZBvEAC8NMNcOghYQxuYLlNcSfHYgp4nF1-iobYkE-cUCbdqctVKinTITuRA60antbXDwud7WxGA-Ks2EAErVS95Z3pVuETDA-ufTRDA3b&type=2&tkFlag=0";
        //搜索 抹茶
        String SEARCH_URL1="http://api.liwushuo.com/v2/search/hot_words";
        //搜索              http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=&keyword=%E6%8A%B9%E8%8C%B6
        String SEARCH_URL2="http://api.liwushuo.com/v2/search/item?limit=%d&offset=0&sort=&keyword=%s";
        String SEARCH_PAIXU="http://api.liwushuo.com/v2/search/item?limit=%d&offset=0&sort=%s&keyword=%s";
        String ZUIJIA_URL="http://api.liwushuo.com/v2/search/item?sort=&limit=20&keyword=%s&offset=%d";
        String ZUIJIA_PAIXU="http://api.liwushuo.com/v2/search/item?sort=%s&limit=20&keyword=%s&offset=%d";



    }

    interface KEYS{

    }
}
