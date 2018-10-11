package com.example.a.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new MyDatabaseHelper(this,"BoolStore.db",null,4);
        /**
         * 查询数据
         */
        Button queryDate = findViewById(R.id.select_data);
        queryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                Cursor cursor = db.query("book",null,null,
                        null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        //遍历Cursor
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("page"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Toast.makeText(MainActivity.this,""+name+"\n"+author+"\n"+pages+"\n"+price,
                                Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
                cursor.close();
//                或者直接执行查询语句
//                db.rawQuery("select * from book ",null);
            }
        });
        /**
         * 删除数据
         */
        Button deleteDate = findViewById(R.id.delete_data);
        deleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.delete("book","name like ?",new String[]{"English%"});
            }
        });
        /**
         * 更新数据
         */
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",22.22);
                db.update("book",values,"name=?",new String[]{"English book"});
            }
        });

        /**
         * 插入数据
         */
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装数据
                values.put("name","English book");
                values.put("author","English teacher");
                values.put("page",111);
                values.put("price",11.11);
                db.insert("book",null,values);

                values.clear();

                values.put("name","Chinese book");
                values.put("author","Chinese teacher");
                values.put("page",111);
                values.put("price",11.11);
                db.insert("book",null,values);
                values.clear();
//                或者直接执行sql语句
                db.execSQL("insert into book (name,author,page,price) values (?,?,?,?)",new String[]{"English book","English teacher","111","11.11"});
            }
        });
        /**
         * 创建数据库
         */
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.getWritableDatabase();
            }
        });
    }
}
