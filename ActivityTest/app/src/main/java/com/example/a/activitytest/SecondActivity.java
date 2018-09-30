package com.example.a.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "tag";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "2Task id is "+getTaskId());
        setContentView(R.layout.activity_second);
        Button button2 =findViewById(R.id.button_2);
//        获取数据
        Intent intent =getIntent();
        String data=intent.getStringExtra("info");
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
//       获取数据
        /**
         * 启动浏览器
         * */
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(intent);
            }
        });

        /**
         * 启动拨号
         */
        Button button3=findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        /**
         * 返回数据给上个活动
         */
        Button button4=findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("info2","info from secondActivity");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Button button5=findViewById(R.id.button_6);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 体验singleInstance
         */
        Button button6 =findViewById(R.id.button_7);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("info2","info from secondActivity");
        setResult(RESULT_OK,intent);
        finish();
    }
}
