package com.example.a.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
//    private NetworkChangeReceiver networkChangeReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private MyBroadcastReceiver localReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver(); //网络变化监听广播
//        registerReceiver(networkChangeReceiver,intentFilter);

//        /**
//         * 自定义静态广播在8.0中做了限制, 需要定义接受广播的类的包名和具体类
//         * 参考   https://blog.csdn.net/kongqwesd12/article/details/78998151
//         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new
//                        Intent("com.example.broadcasttest.MY_BROADCAST");
//                intent.setComponent(new ComponentName("com.example.a.broadcasttest","com.example.a.broadcasttest.MyBroadcastReceiver"));//设置接收广播的类
//                sendBroadcast(intent);
//            }
//        });
        /**
         *
         * 自定义本地广播
         * 只能在泵应用中收取
         */
        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播

            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.MY_BROADCAST");
        localReceiver = new MyBroadcastReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);//注册本地广播监听器

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

//    private class NetworkChangeReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectivityManager= (ConnectivityManager)
//                    getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if(networkInfo != null && networkInfo.isAvailable()){
//                Toast.makeText(MainActivity.this,"network available",Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
//            }
//
////            Toast.makeText(MainActivity.this,"network changed",Toast.LENGTH_SHORT).show();
//        }
//    }
}
