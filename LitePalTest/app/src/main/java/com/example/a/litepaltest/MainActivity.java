package com.example.a.litepaltest;

import android.database.Cursor;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * LitePal 持久化框架简单操作
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**litepal 创建数库*/
        Button createDb = findViewById(R.id.create_database);
        createDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        /**litepal 添加数据*/
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(222);
                book.setPrice(12.12);
                book.setPress("Unknow");
                book.save();
            }
        });

        /**LitePal 更新数据*/
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(222);
                book.setPrice(12.12);
                book.setPress("Unknow");
                book.save();
//                book.setPrice(22.22);
//                book.save();
                book.setPrice(33.33);
                book.setPress("Anchor");
                book.updateAll("name= ? and author =?","The Lost Symbol","Dan Brown");
            }
        });

        /**LitePal 删除数据
         * 2.0.0版本改动废弃DataSupport
         * https://mp.weixin.qq.com/s/zitlKlVVyAfnV09SqcSLAw
         */
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price <?","15");
            }
        });

        /**
         * LitePal 查询数据
         */
        Button queryDate = findViewById(R.id.select_data);
        queryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                Book firstBook = LitePal.findFirst(Book.class);//查询第一条
                List<Book> books1= LitePal.select("name","author").find(Book.class);//查单独列
                List<Book> book2 = LitePal.select("name","author","pages")
                        .where("pages >?","100")
                        .order("pages")
                        .limit(10)
                        .offset(10)
                        .find(Book.class);
                Cursor cursor = LitePal.findBySQL("select *from book where press =?","Unknown");
                for (Book book:books){
                    Toast.makeText(MainActivity.this,""+book.getName()+book.getAuthor(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
