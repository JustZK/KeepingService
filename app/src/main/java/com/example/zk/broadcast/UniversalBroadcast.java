package com.example.zk.broadcast;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.zk.keepingservice.R;
import com.example.zk.service.MainService;

import java.util.List;

/**
 * 方法5、监听各种广播
 */
public class UniversalBroadcast extends BroadcastReceiver {
    private NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            //关闭或打开飞行模式时的广播
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                lowBatteryNotificationShow(context, 1, "关闭或打开飞行模式时的广播");
                break;
            //充电状态，或者电池的电量发生变化;
            //电池的充电状态、电荷级别改变，不能通过组建声;
            case Intent.ACTION_BATTERY_CHANGED:
                lowBatteryNotificationShow(context, 2, "充电状态,电池的电量发生变化");
                break;
            //表示电池电量低
            case Intent.ACTION_BATTERY_LOW:
                lowBatteryNotificationShow(context, 3, "电池电量低");
                break;
            //表示电池电量充足
            case Intent.ACTION_BATTERY_OKAY:
                lowBatteryNotificationShow(context, 4, "电池电量充足");
                break;
            //在系统启动完成后，这个动作被广播一次（只有一次）。
            case Intent.ACTION_BOOT_COMPLETED:
                lowBatteryNotificationShow(context, 5, "开机广播");
                break;
            //按下照相时的拍照按键(硬件按键)时发出的广播
            case Intent.ACTION_CAMERA_BUTTON:
                lowBatteryNotificationShow(context, 6, "按下照相时的拍照按键");
                break;
            //当屏幕超时进行锁屏时,当用户按下电源按钮,长按或短按(不管有没跳出话框)，进行锁屏时,android系统都会广播此Action消息
            case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                lowBatteryNotificationShow(context, 7, "屏幕超时进行锁屏");
                break;
            //设备当前设置被改变时发出的广播(包括的改变:界面语言，设备方向，等，请参考Configuration.java)
            case Intent.ACTION_CONFIGURATION_CHANGED:
                lowBatteryNotificationShow(context, 8, "设备当前设置被改变时发出的广播");
                break;
            //设备日期发生改变时会发出此广播
            case Intent.ACTION_DATE_CHANGED:
                lowBatteryNotificationShow(context, 9, "设备日期发生改变");
                break;
            //设备内存不足时发出的广播,此广播只能由系统使用，其它APP不可用
            case Intent.ACTION_DEVICE_STORAGE_LOW:
                lowBatteryNotificationShow(context, 10, "设备内存不足时发出的");
                break;
            //设备内存从不足到充足时发出的广播,此广播只能由系统使用，其它APP不可用
            case Intent.ACTION_DEVICE_STORAGE_OK:
                lowBatteryNotificationShow(context, 11, "设备内存从不足到充足时发出的广播");
                break;
            //发出此广播的地方frameworks\base\services\java\com\android\server\DockObserver.java
            case Intent.ACTION_DOCK_EVENT:
                lowBatteryNotificationShow(context, 12, "发出此广播的地方frameworks");
                break;
            //移动APP完成之后，发出的广播(移动是指:APP2SD)
            case Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE:
                lowBatteryNotificationShow(context, 13, "移动APP完成之后");
                break;
            //正在移动APP时，发出的广播(移动是指:APP2SD)
            case Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE:
                lowBatteryNotificationShow(context, 14, "正在移动APP时");
                break;
            //Gtalk已建立连接时发出的广播
            case Intent.ACTION_GTALK_SERVICE_CONNECTED:
                lowBatteryNotificationShow(context, 15, "Gtalk已建立连接时发出的广播");
                break;
            //Gtalk已断开连接时发出的广播
            case Intent.ACTION_GTALK_SERVICE_DISCONNECTED:
                lowBatteryNotificationShow(context, 16, "Gtalk已断开连接时发出的广播");
                break;
            //在耳机口上插入耳机时发出的广播
            case Intent.ACTION_HEADSET_PLUG:
                lowBatteryNotificationShow(context, 17, "在耳机口上插入耳机时发出的广播");
                break;
            //改变输入法时发出的广播
            case Intent.ACTION_INPUT_METHOD_CHANGED:
                lowBatteryNotificationShow(context, 18, "改变输入法时发出的广播");
                break;
            //设备当前区域设置已更改时发出的广播
            case Intent.ACTION_LOCALE_CHANGED:
                lowBatteryNotificationShow(context, 19, "设备当前区域设置已更改时发出的广播");
                break;
            //表示用户和包管理所承认的低内存状态通知应该开始。
            case Intent.ACTION_MANAGE_PACKAGE_STORAGE:
                lowBatteryNotificationShow(context, 20, "表示用户和包管理所承认的低内存状态通知应该开始");
                break;
            //未正确移除SD卡(正确移除SD卡的方法:设置--SD卡和设备内存--卸载SD卡)，但已把SD卡取出来时发出的广播 ,扩展介质（扩展卡）已经从 SD 卡插槽拔出，但是挂载点 (mount point) 还没解除 (unmount)
            case Intent.ACTION_MEDIA_BAD_REMOVAL:
                lowBatteryNotificationShow(context, 21, "未正确移除SD卡");
                break;
            //按下"Media Button" 按键时发出的广播,假如有"Media Button" 按键的话(硬件按键)
            case Intent.ACTION_MEDIA_BUTTON:
                lowBatteryNotificationShow(context, 22, "按下\"Media Button\" 按键时发出的广播");
                break;
            //插入外部储存装置，比如SD卡时，系统会检验SD卡，此时发出的广播?
            case Intent.ACTION_MEDIA_CHECKING:
                lowBatteryNotificationShow(context, 23, "插入外部储存装置");
                break;
            //已拔掉外部大容量储存设备发出的广播（比如SD卡，或移动硬盘）,不管有没有正确卸载都会发出此广播, 用户想要移除扩展介质（拔掉扩展卡）。
            case Intent.ACTION_MEDIA_EJECT:
                lowBatteryNotificationShow(context, 24, "已拔掉外部大容量储存设备发出的广播");
                break;
            //插入SD卡并且已正确安装（识别）时发出的广播, 扩展介质被插入，而且已经被挂载。
            case Intent.ACTION_MEDIA_MOUNTED:
                lowBatteryNotificationShow(context, 25, "插入SD卡并且已正确安装（识别）时发出的广播");
                break;
            //拓展介质存在，但使用不兼容FS（或为空）的路径安装点检查介质包含在Intent.mData领域。
            case Intent.ACTION_MEDIA_NOFS:
                lowBatteryNotificationShow(context, 26, "拓展介质存在");
                break;
            //外部储存设备已被移除，不管有没正确卸载,都会发出此广播， 扩展介质被移除。
            case Intent.ACTION_MEDIA_REMOVED:
                lowBatteryNotificationShow(context, 27, "外部储存设备已被移除");
                break;
            //广播：已经扫描完介质的一个目录
            case Intent.ACTION_MEDIA_SCANNER_FINISHED:
                lowBatteryNotificationShow(context, 28, "已经扫描完介质的一个目录");
                break;
            //请求媒体扫描仪扫描文件并将其添加到媒体数据库。
            case Intent.ACTION_MEDIA_SCANNER_SCAN_FILE:
                lowBatteryNotificationShow(context, 29, "请求媒体扫描仪扫描文件并将其添加到媒体数据库");
                break;
            //广播：开始扫描介质的一个目录
            case Intent.ACTION_MEDIA_SCANNER_STARTED:
                lowBatteryNotificationShow(context, 30, "开始扫描介质的一个目录");
                break;
            // 广播：扩展介质的挂载被解除 (unmount)，因为它已经作为 USB 大容量存储被共享。
            case Intent.ACTION_MEDIA_SHARED:
                lowBatteryNotificationShow(context, 31, "扩展介质的挂载被解除");
                break;
            case Intent.ACTION_MEDIA_UNMOUNTABLE:
                lowBatteryNotificationShow(context, 32, "ACTION_MEDIA_UNMOUNTABLE");
                break;
            // 广播：扩展介质存在，但是还没有被挂载 (mount)
            case Intent.ACTION_MEDIA_UNMOUNTED:
                lowBatteryNotificationShow(context, 33, "扩展介质存在，但是还没有被挂载");
                break;
            case Intent.ACTION_NEW_OUTGOING_CALL:
                lowBatteryNotificationShow(context, 34, "拨号");
                break;
            //成功的安装APK之后//广播：设备上新安装了一个应用程序包。//一个新应用包已经安装在设备上，数据包括包名（最新安装的包程序不能接收到这个广播）
            case Intent.ACTION_PACKAGE_ADDED:
                lowBatteryNotificationShow(context, 35, "成功的安装APK之后");
                break;
            //一个已存在的应用程序包已经改变，包括包名
            case Intent.ACTION_PACKAGE_CHANGED:
                lowBatteryNotificationShow(context, 36, "一个已存在的应用程序包已经改变，包括包名");
                break;
            //清除一个应用程序的数据时发出的广播(在设置－－应用管理－－选中某个应用，之后点清除数据时?)//用户已经清除一个包的数据，包括包名（清除包程序不能接收到这个广播）
            case Intent.ACTION_PACKAGE_DATA_CLEARED:
                lowBatteryNotificationShow(context, 37, "清除一个应用程序的数据时发出的广播");
                break;
            //触发一个下载并且完成安装时发出的广播，比如在电子市场里下载应用？
            case Intent.ACTION_PACKAGE_INSTALL:
                lowBatteryNotificationShow(context, 38, "触发一个下载并且完成安装时发出的广播");
                break;
            //成功的删除某个APK之后发出的广播, 一个已存在的应用程序包已经从设备上移除，包括包名（正在被安装的包程序不能接收到这个广播）
            case Intent.ACTION_PACKAGE_REMOVED:
                lowBatteryNotificationShow(context, 39, "成功的删除某个APK之后发出的广播");
                break;
            //替换一个现有的安装包时发出的广播（不管现在安装的APP比之前的新还是旧，都会发出此广播？）
            case Intent.ACTION_PACKAGE_REPLACED:
                lowBatteryNotificationShow(context, 40, "替换一个现有的安装包时发出的广播");
                break;
            //用户重新开始一个包，包的所有进程将被杀死，所有与其联系的运行时间状态应该被移除，包括包名（重新开始包程序不能接收到这个广播）
            case Intent.ACTION_PACKAGE_RESTARTED:
                lowBatteryNotificationShow(context, 41, "用户重新开始一个包");
                break;
            //插上外部电源时发出的广播
            case Intent.ACTION_POWER_CONNECTED:
                lowBatteryNotificationShow(context, 42, "插上外部电源时发出的广播");
                break;
            //已断开外部电源连接时发出的广播
            case Intent.ACTION_POWER_DISCONNECTED:
                lowBatteryNotificationShow(context, 43, "已断开外部电源连接时发出的广播");
                break;
            case Intent.ACTION_PROVIDER_CHANGED:
                lowBatteryNotificationShow(context, 44, "ACTION_PROVIDER_CHANGED");
                break;
            //重启设备时的广播
            case Intent.ACTION_REBOOT:
                lowBatteryNotificationShow(context, 45, "重启设备时的广播");
                break;
            //屏幕被关闭之后的广播 得动态注册的广播才行
            case Intent.ACTION_SCREEN_OFF:
                lowBatteryNotificationShow(context, 46, "屏幕被关闭之后的广播");
                break;
            //屏幕被打开之后的广播 得动态注册的广播才行
            case Intent.ACTION_SCREEN_ON:
                lowBatteryNotificationShow(context, 47, "屏幕被打开之后的广播");
                break;
            //关闭系统时发出的广播
            case Intent.ACTION_SHUTDOWN:
                lowBatteryNotificationShow(context, 48, "关闭系统时发出的广播");
                break;
            //时区发生改变时发出的广播
            case Intent.ACTION_TIMEZONE_CHANGED:
                lowBatteryNotificationShow(context, 49, "时区发生改变时发出的广播");
                break;
            //时间被设置时发出的广播
            case Intent.ACTION_TIME_CHANGED:
                lowBatteryNotificationShow(context, 50, "时间被设置时发出的广播");
                break;
            //广播：当前时间已经变化（正常的时间流逝）， 当前时间改变，每分钟都发送，不能通过组件声明来接收
            //，只有通过Context.registerReceiver() 方法来注册
            case Intent.ACTION_TIME_TICK:
                lowBatteryNotificationShow(context, 51, "当前时间已经变化");
                break;
            //一个用户ID已经从系统中移除发出的广播
            case Intent.ACTION_UID_REMOVED:
                lowBatteryNotificationShow(context, 52, "一个用户ID已经从系统中移除发出的广播");
                break;
            //设备已进入USB大容量储存状态时发出的广播？
            case Intent.ACTION_UMS_CONNECTED:
                lowBatteryNotificationShow(context, 53, "设备已进入USB大容量储存状态时发出的广播");
                break;
            //设备已从USB大容量储存状态转为正常状态时发出的广播？
            case Intent.ACTION_UMS_DISCONNECTED:
                lowBatteryNotificationShow(context, 54, "设备已从USB大容量储存状态转为正常状态时发出的广播");
                break;
            //解锁进入系统
            case Intent.ACTION_USER_PRESENT:
                lowBatteryNotificationShow(context, 55, "解锁进入系统");
                break;
            //设备墙纸已改变时发出的广播
            case Intent.ACTION_WALLPAPER_CHANGED:
                lowBatteryNotificationShow(context, 56, "设备墙纸已改变时发出的广播");
                break;
            case MainService.SERVICE_KEEP_ALARM:
                lowBatteryNotificationShow(context, 57, "收到AlarmManager定时发出的广播");
                break;
            default:
                break;
        }

        if (!isServiceRunning(context, "com.example.zk.service.MainService")){
            Intent intent1 = new Intent(context, MainService.class);
            context.startService(intent1);
        }
    }

    private void lowBatteryNotificationShow(Context context, int notifyID, String contentText) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("TEST_TITLE")
                .setContentText(contentText)
                .setTicker("TEST_TICKER")
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher);

        if (mNotificationManager == null)
            mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mBuilder.build());
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
