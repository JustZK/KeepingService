# KeepingService
保活服务

## 方法一：将Service设置为前台进程（Android8.0不可行error）

## 方法二：在service的onStartCommand方法里返回 START_STICKY

## 方法三：添加Manifest文件属性值为android:persistent="true"

## 方法四：覆写Service的onDestroy

## 方法五：添加广播监听（本案例只是添加了部分静态注册的广播）

## 方法六：服务互相绑定（双进程互相守护）

## 方法七：设置闹钟，定时唤醒（TODO）

## 方法八：账户同步，定时唤醒（TODO）

## 方法九：native层保活（守护进程 TODO）

## About me
 - **QQ**：821188193
 - **Email**：zk199519@gmail.com
