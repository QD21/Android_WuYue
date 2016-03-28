package org.qfln.android_wuyue.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述： 下载工具类
 * @创建人： LN
 * @创建时间: 2016/3/7 18:37
 * @备注：
 */
public class DownUtil {
    // 线程池
    private static ExecutorService executorService= Executors.newFixedThreadPool(5);
    private static Handler handler=new Handler();
    /**
     * 根据path返回byte[]
     * @param path
     * @return
     */
    private static byte[] requestUrl(String path){
        try {
            InputStream in = null;
            ByteArrayOutputStream baos = null;
            URL url=new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            if(conn.getResponseCode()==200){
                in = conn.getInputStream();
                baos= new ByteArrayOutputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while((len = in.read(buffer)) != -1){
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toByteArray();
            }
            in.close();
            baos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求Json
     * @param path
     * @return
     */
    private static String requestJson(String path){
        byte[] bytes = requestUrl(path);
        if(bytes!=null){
            return new String(bytes);
        }
        return null;
    }
    /**
    请求图片
     */
    private static Bitmap requestBitmap(String path){
        byte[] bytes = requestUrl(path);
        if(bytes!=null){
            return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        }
        return null;
    }

    /**
     * 下载Json
     * @param path
     * @param mListener
     */
    public static void downJson(final String path, final OnDownListener mListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //得到下载的json
                final String json = requestJson(path);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mListener!=null){
                            mListener.downSuccess(path,json);
                        }
                    }
                });
            }
        });
    }

    /**
     * 下载图片
     */
    public static void downBitmap(final String path, final OnDownListener mListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //得到下载的json
                final Bitmap bitmap = requestBitmap(path);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mListener!=null){
                            mListener.downSuccess(path,bitmap);
                        }
                    }
                });
            }
        });
    }

    /**
     * 下载成功回调接口
     */
    public interface OnDownListener{
        void downSuccess(String path, Object obj);
    }

}
