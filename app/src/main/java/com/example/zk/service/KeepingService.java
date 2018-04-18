package com.example.zk.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class KeepingService extends Service {
    private MServiceConnection mServiceConnection;
    public KeepingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new KeepingServiceBinder();
    }

    public class KeepingServiceBinder extends Binder{
        public KeepingService getService() {
            return  KeepingService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mServiceConnection = new MServiceConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.bindService(new Intent(this,MainService.class), mServiceConnection, Context.BIND_IMPORTANT);
//        Notification notification = new Notification(R.drawable.ic_launcher,
//                "",
//                System.currentTimeMillis());
//        mPintent= PendingIntent.getService(this, 0, intent, 0);
//        notification.setLatestEventInfo(this, "",
//                "防止被杀掉！", pintent);
//        startForeground(startId, notification);
        return START_STICKY;
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
            Toast.makeText(KeepingService.this, "MainService被干掉", Toast.LENGTH_SHORT).show();
            startService(new Intent(KeepingService.this,MainService.class));
            bindService(new Intent(KeepingService.this,MainService.class), mServiceConnection, Context.BIND_IMPORTANT);
        }

    }

}
