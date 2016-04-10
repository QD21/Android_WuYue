package org.qfln.android_wuyue.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/8 20:01
 * @备注：
 */
public class SharePreferencesUtil {
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    /**
     * 初始化共享参数
     * @param context
     */
    public static void initShared(Context context){
        sp = context.getSharedPreferences("save", Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public static void putString(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public static String getString(String key){
        return sp.getString(key, "");
    }
    public static void putInt(String key,int value){
        editor.putInt(key,value);
        editor.commit();
    }
    public static int getInt(String key,int defalutvalue){
        return sp.getInt(key,defalutvalue);

    }
}
