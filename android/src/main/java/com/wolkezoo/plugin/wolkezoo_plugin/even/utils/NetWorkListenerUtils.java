package com.wolkezoo.plugin.wolkezoo_plugin.even.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;

import com.wolkezoo.plugin.wolkezoo_plugin.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

import cn.hutool.core.lang.Singleton;

public class NetWorkListenerUtils {

    @SuppressLint("StaticFieldLeak")
    private volatile static NetWorkListenerUtils instance;

    private NetWorkListenerUtils() {
    }

    public static NetWorkListenerUtils getInstance() {
        if (null == instance) {
            synchronized (NetWorkListenerUtils.class) {
                if (null == instance) {
                    instance = new NetWorkListenerUtils();
                }
            }
        }
        return instance;
    }

    private Context context;
    private Handler handler;

    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;
    private Timer timer;

    private int cycles = -1;

    private int cyclesTime = -1;

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public void setCyclesTime(int cyclesTime) {
        this.cyclesTime = cyclesTime;
    }

    public int getCyclesTime() {
        return cyclesTime;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            showNetSpeed();
        }
    };

    private void showNetSpeed() {
        long nowTotalRxBytes = getTotalRxBytes();
        long nowTimeStamp = System.currentTimeMillis();
        long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换
        long speed2 = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 % (nowTimeStamp - lastTimeStamp));//毫秒转换

        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;
        if (handler != null) {
            Message msg = handler.obtainMessage();
            msg.what = 100;
            msg.obj = speed + "." + speed2 + " kb/s";
            handler.sendMessage(msg); // 通知消息
        }

        // 次数模式
        if (cycles != -1) {
            cycles = cycles - 1;
            if (cycles == 0) {
                // 关闭定时器
                unbindShowNetSpeed();
            }
        }

        // 时间模式
    }

    public void startShowNetSpeed() {
        lastTotalRxBytes = getTotalRxBytes();
        lastTimeStamp = System.currentTimeMillis();
        // 1s后启动任务，每2s执行一次、
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(task, 1000, 1000);
    }

    public void unbindShowNetSpeed() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        instance = null;
    }

    private long getTotalRxBytes() {
        return TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
    }
}
