package com.huya.mtp.hyns.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EasyTimer {
    private static HandlerThread handlerThread = new HandlerThread("NS-EasyTimer");
    private static volatile boolean isStarted = false;
    private int mDuration;
    private Handler mHandler;
    private Runnable mRunnable;

    public EasyTimer() {
        if (!isStarted) {
            isStarted = true;
            handlerThread.start();
        }
        this.mHandler = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.huya.mtp.hyns.util.EasyTimer.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (EasyTimer.this.mRunnable != null) {
                    EasyTimer.this.mRunnable.run();
                }
                EasyTimer.this.mHandler.sendEmptyMessageDelayed(0, EasyTimer.this.mDuration);
                return true;
            }
        });
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void setRunnable(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void stop() {
        this.mHandler.removeMessages(0);
    }

    public void start() {
        this.mHandler.sendEmptyMessageDelayed(0, 0L);
    }

    public void resetAndStart(int i, Runnable runnable) {
        setRunnable(runnable);
        stop();
        setDuration(i);
        start();
    }
}
