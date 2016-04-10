package org.qfln.android_wuyue.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/8 21:42
 * @备注：
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final String NAME="wuyue.db";
    private static final int VERSION=1;
    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // isCollect:是否收藏
        String sql="CREATE TABLE gift_like(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,id VARCHAR(10),title VARCHAR(10),icon VARCHAR(100),price VARCHAR(10),isCollect CHAR(2),userid VARCHAR(10))";
        String sql1="CREATE TABLE GL_Like(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,id VARCHAR(10),title VARCHAR(10),icon VARCHAR(100),isCollect CHAR(2),userid VARCHAR(10))";
        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
