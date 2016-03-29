package org.qfln.android_wuyue.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.qfln.android_wuyue.R;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ken on 2016/3/16.
 */
public class VolleyUtil {

    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;
    /**
     * 一级内存缓存 -- 强引用
     */
    private static LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)){

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }

        @Override
        protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
            if(evicted){
                softCache.put(key, new SoftReference<Bitmap>(oldValue));
            }
        }
    };

    /**
     * 一级内存缓存 -- 软引用
     */
    private static Map<String, SoftReference<Bitmap>> softCache = new HashMap<>();

    public static void initVolley(Context context){
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);


            imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
                @Override
                public Bitmap getBitmap(String url) {
                    //从缓存内获取数据
                    Bitmap bitmap = lruCache.get(url);
                    if(bitmap == null){
                        SoftReference<Bitmap> softbitmap = softCache.get(url);
                        if(softbitmap != null){
                            bitmap = softbitmap.get();
                        }
                    }
                    return bitmap;
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    //往缓存内添加数据
                    lruCache.put(url, bitmap);
                }
            });
        }
    }

    /**
     * 请求字符串
     * @param url
     */
    public static void requestString(final String url, final OnRequestListener onRequestListener){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(onRequestListener != null){
                    onRequestListener.onResponse(url, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(onRequestListener != null){
                    onRequestListener.onErrorResponse(url, error);
                }
            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * 请求图片
     * @param url
     * @param imageView
     */
    public static void requestImage(String url, ImageView imageView, int defualtImage, int errorImage){
        imageLoader.get(url, imageLoader.getImageListener(imageView, defualtImage, errorImage));
    }


    public static void requestImage(String url, ImageView imageView){
        imageLoader.get(url, imageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }

    public static void requestNetworkImageView(String url, NetworkImageView networkimageview){
        networkimageview.setDefaultImageResId(R.mipmap.ic_launcher);
        networkimageview.setImageUrl(url, imageLoader);
    }

    public interface OnRequestListener{
        void onResponse(String url, String response);
        void onErrorResponse(String url, VolleyError error);
    }
}
