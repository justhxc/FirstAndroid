package com.example.a.broadcasttest2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new
                        Intent("com.example.broadcasttest.MY_BROADCAST");
//                intent.setComponent(new ComponentName("com.example.a.broadcasttest2","com.example.a.broadcasttest2.AnotherBroadcastReceiver"));//设置接收广播的类
                sendOrderedBroadcast(intent,null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
