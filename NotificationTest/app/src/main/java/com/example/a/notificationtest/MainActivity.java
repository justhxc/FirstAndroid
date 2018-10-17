package com.example.a.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send = findViewById(R.id.send_notice);
        send.setOnClickListener(this);
    }
    /**
     * 8.0真机发送通知后系统界面崩溃bug
     * 关于8.0的通知适配https://blog.csdn.net/guolin_blog/article/details/79854070
     * 解决办法不使用mipmap-anydpi-v26下的图标
     * 详情 https://blog.csdn.net/tempersitu/article/details/78784350
     * https://blog.pusher.com/upgrade-app-android-oreo-avoid-factory-reset/
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel("default","default"
                        ,NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);//安卓8.0后需要创建通知管道,用于通知分类等
                Notification notification = new NotificationCompat.Builder(this,"default")
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification);
                break;
            default:
                break;
        }
    }
}
