package com.example.a.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
/**
 * 使用start方法启动后如果服务不存在先执行onCreate然后执行onExecute
 * 存在则直接执行onExecute,使用stop方法停止调用onDestroy
 * onBind方法和unBind方法分别用于启动和关闭服务
 * 貌似先start然后bind后只能用unbind结束不能用stop结束,用stop不调用onDestroy
 */

public class MyService extends Service {
    public static final String TAG = "tag";
    private DownloadBinder downloadBinder = new DownloadBinder();

    public MyService() {
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: service destroy!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service execute!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: service created!");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        NotificationChannel channel = new NotificationChannel("default","Default",NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);//安卓8.0后需要创建通知管道,用于通知分类等

        Notification notification = new NotificationCompat.Builder(this,"default")
                .setContentTitle("Title")
                .setContentText("Service is running Foreground")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return downloadBinder;
    }
    public class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d(TAG, "startDownload: you start download !");

        }
        public int getProgress(){
            Log.d(TAG, "getProgress: return progress");
            return 0;
        }

    }


}
