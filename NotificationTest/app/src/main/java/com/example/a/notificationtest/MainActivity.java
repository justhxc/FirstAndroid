package com.example.a.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

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
                        ,NotificationManager.IMPORTANCE_HIGH);
                channel.setShowBadge(true);
                channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);//安卓8.0后需要创建通知管道,用于通知分类等
                Notification notification = new NotificationCompat.Builder(this,"default")
//                        .setChannelId("default")
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        //角标消息
                        .setNumber(2)
                        //时间
                        .setWhen(System.currentTimeMillis())
                        //点击后启动的活动
                        .setContentIntent(pendingIntent)
                        //点击后自动消失
                        .setAutoCancel(true)
                        //声音
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        //震动,需要在xml中声明权限
                        .setVibrate(new long[]{0,1000,1000,1000})
                        //LED灯
                        .setLights(Color.GREEN,1000,1000)
                      //  .setDefaults(NotificationCompat.DEFAULT_ALL)//默认效果
                        //设置样式
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("hashdhasdhajkdhkasjdhjask,hdasjkdhkjahduiahsiujkljfaioshdfidsahdsahjkhjskadhjkhasjkdhsajkhdwqhkajjdhashdisahdadskasjdhoiwhokdajskhdsajkdhk "))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.big_image)))
                        //弹出通知,实测 不能弹出,需要在通知中设置为紧急通知才能弹出
//                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1,notification);
                break;
            default:
                break;
        }
    }
}
