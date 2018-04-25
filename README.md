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

* AlarmManager周期性来检查进程状态（关机或重启之后会失效）

* 5.0以后的Android系统，我们就可以使用的JobScheduler，JobScheduler来可以调度特殊场景下启动JobService来执行onStartJob，如设定无线连接时候啊，JobService是Android5.0以后新增的一个服务，即可以通过的JobScheduler来执行一些满足特定条件但不紧急的后台任务
<br> 按照官方文档的定义，在原生的Android系统上，当设定了一个Job之后，哪怕该App的进程已经结束或者被杀掉，对应的JobService也是可以启动的。
然而Android已经被国内的各大厂商重新定制过，导致的一个问题就是当前App的进程被杀掉之后，JobService无法启动。
例如在MIUI系统中，第三方App如果没有被用户设置到允许自启动的名单中，在启动Service的时候会被拦截掉。
这恐怕会导致很多第三方应用无法使用这个东西，正如之前的AlarmManager一样。
还有一个比较坑的地方在于，当Job正在执行时，如果使用类似MIUI上的一键清除所有进程，JobService会被强制停止，也不会执行对应的onStopJob方法。

## 方法八：账户同步，定时唤醒（TODO）

## 方法九：桶全家互拉方式（TODO）

* 这种方案常见于_百度，360等全家桶，这种互相唤醒的方式，可以参考。


## 方法十：native层保活（守护进程 TODO）

## About me
 - **QQ**：821188193
 - **Email**：zk199519@gmail.com
