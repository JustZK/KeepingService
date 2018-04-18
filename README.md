# KeepingService
保活服务

## 方法一：将Service设置为前台进程（Android8.0不可行error）

## 方法二：在service的onStartCommand方法里返回 START_STICKY

## 方法三：添加Manifest文件属性值为android:persistent="true"

## 方法四：覆写Service的onDestroy

## 方法五：添加广播监听（本案例只是添加了部分静态注册的广播）

## 方法六：服务互相绑定（双进程互相守护）

* 同时开启了两个服务，分别是A和B，互相守护，如果A守护B，当B挂掉，A就立刻把B启动起来，所以A和B互相守护，无论是被杀掉，对方就把它拉起来。具体通过bindService方式来互相绑定对方，分别在不同的进程中避免同时被杀死。


## 方法七：设置闹钟，定时唤醒（TODO）

* 监听系统时钟广播或AlarmManager周期性来检查进程状态

* 5.0以后的Android系统，我们就可以使用的jobscheduler，的jobscheduler来可以调度特殊场景下启动JobService来执行onStartJob，如设定无线连接时候啊，JobService是Android5.0以后新增的一个服务，即可以通过的jobscheduler来执行一些满足特定条件但不紧急的后台任务

## 方法八：账户同步，定时唤醒（TODO）

## 方法九：桶全家互拉方式

* 这种方案常见于_百度，360等全家桶，这种互相唤醒的方式，可以参考。


## 方法十：native层保活（守护进程 TODO）

## About me
 - **QQ**：821188193
 - **Email**：zk199519@gmail.com
