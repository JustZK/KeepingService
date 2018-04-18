package com.example.zk.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.zk.keepingservice.MainActivity;
import com.example.zk.keepingservice.R;

public class MainService extends Service {
    private MServiceConnection mServiceConnection;

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
        mServiceConnection = new MServiceConnection();
        startForeground();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        bindService(new Intent(this, KeepingService.class), mServiceConnection, Context.BIND_IMPORTANT);
        /**
         * 方法2、在service的onStartCommand方法里返回 START_STICKY
         * 主要的几个返回值：
         * 1. START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。
         * 2. START_STICKY：系统就会重新创建这个服务并且调用onStartCommand()方法，但是它不会重新传递最后的Intent对象，这适用于不执行命令的媒体播放器（或类似的服务），它只是无限期的运行着并等待工作的到来。
         * 3. START_NOT_STICKY：直到接受到新的Intent对象，才会被重新创建。这是最安全的，用来避免在不需要的时候运行你的服务。
         * 4. START_REDELIVER_INTENT：系统就会重新创建了这个服务，并且用最后的Intent对象调。等待中的Intent对象会依次被发送。这适用于如下载文件。
         测试的service是一个最基本的service，在相应的生命周期的触发函数上做了输出。在普通的服务使用还是可以的，做常驻进程的话就不行了，force close和一些清理软件很容易就清理掉!
         */
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        /**
         * 方法4、覆写Service的onDestroy方法
         * 在设置里面的正在运行，注意是正在运行里面，点击关闭，会走onDestroy回调方法，
         * 你在这里可以把自己启动起来。注意是正常关闭的时候是会自己启动起来，
         * 可是使用第三方的清理软件360，root过的360，force close这些来搞，压根不会走到onDestroy的方法，
         */
        Intent intent1 = new Intent(MainService.this, MainService.class);
        startService(intent1);
    }

    /**
     * 方法1、将Service设置为前台进程
     * 本质是修改了Service所在进程的进程优先级。
     * 有了前台进程的优先级，在android系统清理内存的时候，他被杀死的优先级仅高于前台的activity，
     * 也就是正在和用户交互的页面，而且使用ddms杀进程他也可以自己启动起来。
     * 首先ddms杀进程和在系统设置的正在运行中杀进程本身就不具威胁，
     * 在系统设置的所有应用中选择强行停止，
     * 仍然可以强停掉，360，cm等软杀更是能轻而易举杀死他。
     * 而且他还有一个缺点，在api17以上，设置了一个前台服务，
     * 他会以一个无法消除的notification的样式出现在用户的手机状态栏里，大大降低了用户体验。
     * 可是前台服务适合做一些音乐播放器，天气类的应用！
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

    /**
     * 方法6、服务互相绑定（双进程互相守护）
     */
    class MServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // 连接出现了异常断开了，被杀掉了
            Toast.makeText(MainService.this, "KeepingService被干掉", Toast.LENGTH_SHORT).show();
            startService(new Intent(MainService.this, KeepingService.class));
            bindService(new Intent(MainService.this, KeepingService.class),
                    mServiceConnection, Context.BIND_IMPORTANT);
        }

    }
}
