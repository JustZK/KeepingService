package com.example.zk.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.zk.keepingservice.MainActivity;
import com.example.zk.keepingservice.R;

public class KeepingService extends Service {
    private NotificationManager mNotificationManager;
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
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        frontNotificationShow();
    }

}
