package com.example.zk.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.zk.keepingservice.MainActivity;
import com.example.zk.keepingservice.R;

public class MainService extends Service {

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MainServiceBinder();
    }

    public class MainServiceBinder extends Binder {
        public MainService getService() {
            return MainService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * https://developer.android.com/about/versions/oreo/android-8.0-changes.html?hl=zh-cn
     * TODO 该方式在Android8.0上面异常，原因见上面链接（等Android8.0全面适配了再说）
     */
    public void startForeground() {
        // 方法一
//        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
//        Intent nfIntent = new Intent(this, MainActivity.class);
//        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
//                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
//                .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
//                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
//                .setContentText("要显示的内容") // 设置上下文内容
//                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
//        Notification notification = builder.build(); // 获取构建好的Notification
//        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
//        startForeground(110, notification);

        //方法二
//        Intent activityIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplication(), 0, activityIntent, 0);
//        Notification notification = new Notification.Builder(getApplication()).setAutoCancel(true).
//                setSmallIcon(R.mipmap.ic_launcher).setTicker("前台Service启动").setContentTitle("前台Service运行中").
//                setContentText("这是一个正在运行的前台Service").setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent).build();
//        startForeground(1, notification);

        //方法三
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(MainService.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("TEST TICKER")
                .setWhen(System.currentTimeMillis())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("TEST TEXT")
                .setContentIntent(pendingIntent);
        Notification notification = mNotifyBuilder.build();
        /* Method 02
        Notification notification = new Notification(R.drawable.ic_launcher, tickerText,
                System.currentTimeMillis());
        notification.setLatestEventInfo(PlayService.this, getText(R.string.app_name),
                currSong, pendingIntent);
        */
        startForeground(1, notification);
    }
}
