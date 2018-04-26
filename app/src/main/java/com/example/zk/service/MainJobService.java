package com.example.zk.service;

import android.app.ActivityManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;

import java.util.List;

public class MainJobService extends JobService {
    public static final String SERVICE_KEEP_JOB_SERVICE = "com.example.zk.service.MainJobService.JobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        if (!isServiceRunning(this, "com.example.zk.service.MainService")){
            Intent intent1 = new Intent(this, MainService.class);
            startService(intent1);
        }
        Intent intent = new Intent(SERVICE_KEEP_JOB_SERVICE);
        sendBroadcast(intent);
        jobFinished(params, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (!isServiceRunning(this, "com.example.zk.service.MainService")){
            Intent intent1 = new Intent(this, MainService.class);
            startService(intent1);
        }
        Intent intent = new Intent(SERVICE_KEEP_JOB_SERVICE);
        sendBroadcast(intent);
        return false;
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
