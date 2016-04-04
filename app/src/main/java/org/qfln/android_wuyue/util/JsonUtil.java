package org.qfln.android_wuyue.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.qfln.android_wuyue.bean.CateXQEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/4 14:20
 * @备注：
 */
public class JsonUtil {
    public static List<CateXQEntity> getXQJSON(String json) {
        if (json != null) {
            List<CateXQEntity> catelist=new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                int code = jsonObject.getInt("code");
                if (code == 200) {
                    //表示获取json成功
                    JSONObject data = jsonObject.getJSONObject("data");
                    int comments_count = data.getInt("comments_count");
                    String description = data.getString("description");
                    String detail_html = data.getString("detail_html");
                    String favorited = data.getString("favorited");
                    String favorites_count = data.getString("favorites_count");
                    int id = data.getInt("id");
                    String name = data.getString("name");
                    String price = data.getString("price");
                    String purchase_shop_id = data.getString("purchase_shop_id");
                    String purchase_url = data.getString("purchase_url");
                    String shares_count = data.getString("shares_count");
                    String url = data.getString("url");
                    JSONArray image_urls = data.getJSONArray("image_urls");
                    String[] img_url=new String[image_urls.length()];
                    for (int i = 0; i <image_urls.length() ; i++) {
                        String Tu_url = image_urls.getString(i);
                        img_url[i]=Tu_url;
                    }
//                    CateXQEntity cateXQEntity=new CateXQEntity(comments_count,description,detail_html,
//                            favorited,favorites_count,id,img_url,name,price,purchase_shop_id,purchase_url,shares_count,url);
//                    catelist.add(cateXQEntity);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return catelist;
        }
        return null;
    }
}
