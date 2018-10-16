package com.example.a.contentresolvertest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://com.example.app.provider/table1");
        String projection[] ={ "select * from"};
        String selection = "";
        String selectionArg[] ={""};
        String sortOrder = "";
        Cursor cursor = getContentResolver().query(
                uri,
                projection,
                selection,
                selectionArg,
                sortOrder
        );
        /**query*/
        if(cursor != null){
            while (cursor.moveToNext()){
                String column1 = cursor.getString(cursor.getColumnIndex("column1"));
                int column2 = cursor.getInt(cursor.getColumnIndex("column2"));

            }
            cursor.close();
        }
        /**insert*/
        ContentValues values = new ContentValues();
        values.put("column1","text");
        values.put("column2",1);
        getContentResolver().insert(uri,values);
        /**update*/
        ContentValues values1 = new ContentValues();
        values.put("column1","");
        getContentResolver().update(uri,values,"column1 = ? and column2 = ?",new String[]
                {"text","1"});
        /**delete*/
        getContentResolver().delete(uri,"column1 = ? and column2 = ?",
                new String[]{"text","1"});
    }
}
