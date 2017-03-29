package com.stupidwind.com.blacknumber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackNumber的实体操作类
 * Created by 蠢风 on 2017/3/29.
 */

public class BlackNumberDao {

    private static String TAG = BlackNumberDao.class.getSimpleName();

    private DBHelper dbHelper;

    public BlackNumberDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * 添加一条记录
     * @param blackNumber
     */
    public void add(BlackNumber blackNumber) {
        // 获取数据库
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        // 插入一条数据
        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        long id = database.insert("black_number", null, values);
        Log.i(TAG, "id = " + id);
        blackNumber.setId((int)id);
        // 关闭数据库连接
        database.close();
    }

    /**
     * 通过Id删除对应的数据
     * @param id
     */
    public void deleteById(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        int deleteCount = database.delete("black_number", "_id=" + id, null);
        Log.i(TAG, "deleteCount = " + deleteCount);
        database.close();
    }

    /**
     * 通过Id更新对应的数据
     * @param blackNumber
     */
    public void update(BlackNumber blackNumber) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        int updateCount = database.update("black_number", values, "_id=" + blackNumber.getId(), null);
        Log.i(TAG, "updateCount = " +updateCount);
        database.close();
    }

    /**
     * 查询数据库中所有的记录并且封装成List<BlackNumber>
     * @return
     */
    public List<BlackNumber> getAll() {
        List<BlackNumber> list = new ArrayList<>();
        // 得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("black_number", null, null, null, null, null, "_id desc");
        // 从cursor中取出所有数据并封装到List中
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String number = cursor.getString(1);
            list.add(new BlackNumber(id, number));
        }

        // 关闭
        cursor.close();
        database.close();
        return list;
    }

}
