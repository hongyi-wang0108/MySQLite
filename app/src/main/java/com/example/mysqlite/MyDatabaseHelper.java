package com.example.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_BOOK = "create table Book (" +
            "id integer primary key autoincrement,"+
            "bookname text," +
            "author text," +
            "page integer)";
    private String CREATE_WORK = "create table Work (" +
            "id integer primary key autoincrement,"+
            "workname text," +
            "workauthor text," +
            "workpage integer)";
    private Context m;
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        m = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_WORK);
        Toast.makeText(m,"successed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Work");
        onCreate(db);
    }
}