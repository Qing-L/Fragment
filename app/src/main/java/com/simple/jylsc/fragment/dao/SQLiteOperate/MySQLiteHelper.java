package com.simple.jylsc.fragment.dao.SQLiteOperate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    String CREATE_MEMORY = "CREATE TABLE Memory("
            +"id integer primary key autoincrement,"
            +"memoryname text,"
            +"memorycolor text)";
    String CREATE_FRAGMENT = "CREATE TABLE Fragment("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"content text,"
            +"imagepath text,"
            +"createdate text,"
            +"memory_id integer,"
            +"foreign key (memory_id) reference Memory(id))";

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEMORY);
        db.execSQL(CREATE_FRAGMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
