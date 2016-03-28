package org.qfln.android_wuyue.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @描述： 图片保存到本地的工具类
 * @创建人： LN
 * @创建时间: 2016/3/9 00:46
 * @备注：
 */

public class ImageUtil {
    private static final String IMAGE_PATH = Environment.getExternalStorageDirectory() + "/image_cache";

    //判断下sd卡是否存在
    private static boolean isMount(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 保存图片
     * @param url
     * @param bitmap
     */
    public static void saveImage(String url, Bitmap bitmap){
        if(!isMount()){
            return ;
        }

        File file = new File(IMAGE_PATH);
        if(!file.exists()){
            file.mkdirs();
        }

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(file, "" + url.hashCode())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 取出图片
     * @param url
     * @return
     */
    public static Bitmap getImage(String url){
        if(!isMount()){
            return null;
        }

        File file = new File(IMAGE_PATH + "/" + url.hashCode());
        if(file.exists()){
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }

        return null;
    }
}
