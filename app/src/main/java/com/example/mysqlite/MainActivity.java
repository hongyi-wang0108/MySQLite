package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_create;
    private Button btn_query;
    private Button btn_delete;
    private Button btn_add;
    private Button btn_update;
    private MyDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_create = findViewById(R.id.btn_create);
        btn_query = findViewById(R.id.btn_query);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        helper = new MyDatabaseHelper(this,"BookStore.db",null,2);

        btn_create.setOnClickListener(this);
        btn_query .setOnClickListener(this);
        btn_delete .setOnClickListener(this);
        btn_add .setOnClickListener(this);
        btn_update .setOnClickListener(this);
    }

    private  SQLiteDatabase db;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                helper.getWritableDatabase();
                break;
            case R.id.btn_query:
                db = helper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                while (cursor.moveToNext()){
                    String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int page = cursor.getInt(cursor.getColumnIndex("page"));
                    Log.d("query",bookname+ author+page);
                }
                cursor.close();
                break;
            case R.id.btn_delete:
                db = helper.getWritableDatabase();
                db.delete("Book","page > ?",new String[]{"50"});
                break;
            case  R.id.btn_add:
                db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("bookname","第一本书");
                values.put("author","第一本书的作者");
                values.put("page",150);
                db.insert("Book",null,values);
            case  R.id.btn_update:
                db = helper.getWritableDatabase();
                ContentValues updatevalues = new ContentValues();
                updatevalues.put("bookname","按了btn_update后的第一本书");
                db.update("Book",updatevalues,"bookname =  ? ",new String[]{"第一本书"});
        }
    }
}