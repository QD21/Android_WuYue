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
        String SELECT_LISTURL="http://api.liwushuo.com/v2/channels/104/items?limit=%d&ad=2&gender=2&offset=0&generation=1";
        String NEXT_UR="http://api.liwushuo.com/v2/channels/104/items?generation=1&gender=2&limit=10&ad=2&offset=%d";
        String HOMEVP_URL="http://api.liwushuo.com/v2/banners";
        String HOMEHEAD2_URL="http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
    }

    interface KEYS{

    }
}
