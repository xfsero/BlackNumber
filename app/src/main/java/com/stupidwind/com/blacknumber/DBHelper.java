package com.stupidwind.com.blacknumber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库操作的帮助类
 * Created by 蠢风 on 2017/3/29.
 */

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "black_number.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "DBHelper onCreate()");
        db.execSQL("create table black_number(_id integer primary key autoincrement, " +
                "number int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
