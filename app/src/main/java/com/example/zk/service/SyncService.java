package com.example.zk.service;

import android.accounts.Account;
import android.app.ActivityManager;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;

import java.util.List;

public class SyncService extends Service {
    public static final String SERVICE_KEEP_SYNC_SERVICE = "com.example.zk.service.SyncService";

    private static final Object syncLock = new Object();
    private static SyncAdapter syncAdapter = null;

    public SyncService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (syncLock) {
            if (syncAdapter == null) {
                syncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
       return syncAdapter.getSyncAdapterBinder();
    }

    class SyncAdapter extends AbstractThreadedSyncAdapter {

        public SyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        }

        @Override
        public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
            // 实现数据同步
            // getContext().getContentResolver().notifyChange(AccountProvider.CONTENT_URI,null, false);
            if (!isServiceRunning(SyncService.this, "com.example.zk.service.MainService")){
                Intent intent1 = new Intent(SyncService.this, MainService.class);
                startService(intent1);
            }
            Intent intent = new Intent(SERVICE_KEEP_SYNC_SERVICE);
            sendBroadcast(intent);
        }
    }

    /*
     * 判断服务是否启动,context上下文对象 ，className服务的name
     */
    private boolean isServiceRunning(Context mContext, String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(100);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
