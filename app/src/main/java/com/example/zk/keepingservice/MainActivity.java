package com.example.zk.keepingservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.zk.provider.AccountProvider;
import com.example.zk.service.KeepingService;
import com.example.zk.service.MainJobService;
import com.example.zk.service.MainService;

public class MainActivity extends AppCompatActivity {
    // TYPE必须与account_preferences.xml中的TYPE保持一致
    public static final String ACCOUNT_TYPE ="com.example.zk.service.AuthenticationService";
    private AccountManager mAccountManager;
    private Context mContext;
    private int jobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        Intent intent1 = new Intent(MainActivity.this, MainService.class);
        startService(intent1);
        Intent intent2 = new Intent(MainActivity.this, KeepingService.class);
        startService(intent2);

        //版本号大于Android5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            scheduleJob();
        }

        Account account = new Account(getString(R.string.app_name),ACCOUNT_TYPE);
        mAccountManager = (AccountManager)getSystemService(ACCOUNT_SERVICE);
        // 获取系统帐户列表中已添加的帐户是否存在我们的帐户，用TYPE做为标识
        Account[] accounts =mAccountManager.getAccountsByType(ACCOUNT_TYPE);
        if(accounts.length > 0) {
//            Toast.makeText(this, "已添加当前登录的帐户",Toast.LENGTH_SHORT).show();
        } else {
            mAccountManager.addAccountExplicitly(account, null, null);
        }

        // 自动同步
        Bundle bundle= new Bundle();
        ContentResolver.setIsSyncable(account, AccountProvider.AUTHORITY, 1);
        ContentResolver.setSyncAutomatically(account, AccountProvider.AUTHORITY,true);
        // 间隔时间为2分钟，最低好像是一分钟
        ContentResolver.addPeriodicSync(account, AccountProvider.AUTHORITY,bundle, 2 * 60);
        // 手动同步
        //ContentResolver.requestSync(account, AccountProvider.AUTHORITY, bundle);

    }


    public void scheduleJob() {
        //开始配置JobInfo
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(mContext, MainJobService.class));

        //设置任务的延迟执行时间(单位是毫秒)
//        builder.setMinimumLatency(1000);
        //设置任务最晚的延迟时间。如果到了规定的时间时其他条件还未满足，你的任务也会被启动。(与setPeriodic不能同时存在）
//        builder.setOverrideDeadline(1000);
        //循环时间
        builder.setPeriodic(60 * 1000);
        //是否是周期性任务
        builder.setPersisted(true);
        //让你这个任务只有在满足指定的网络条件时才会被执行
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        //你的任务只有当用户没有在使用该设备且有一段时间没有使用时才会启动该任务。
//        builder.setRequiresDeviceIdle(true);
        //告诉你的应用，只有当设备在充电时这个任务才会被执行。
//        builder.setRequiresCharging(true);
        //传递Bundle
        PersistableBundle extras = new PersistableBundle();
        extras.putLong("MAIN_ACTIVITY", 60 * 1000);
        builder.setExtras(extras);
        // Schedule job
        JobScheduler mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        // 这里就将开始在service里边处理我们配置好的job
        jobId = mJobScheduler.schedule(builder.build());
        if (jobId < 0){
            //失败
        }

        //mJobScheduler.schedule(builder.build())会返回一个int类型的数据
        //如果schedule方法失败了，它会返回一个小于0的错误码。否则它会返回我们在JobInfo.Builder中定义的标识id。
    }
}



