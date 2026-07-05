package com.huya.berry.gamesdk.utils;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EasyTimer {
    private int mDuration;
    private Handler mHandler = new Handler() { // from class: com.huya.berry.gamesdk.utils.EasyTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (EasyTimer.this.mRunnable != null) {
                EasyTimer.this.mRunnable.run();
                sendEmptyMessageDelayed(0, EasyTimer.this.mDuration);
            }
        }
    };
    private boolean mIsRunning;
    private Runnable mRunnable;

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void setRunnable(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void stop() {
        this.mHandler.removeMessages(0);
        this.mRunnable = null;
        this.mIsRunning = false;
    }

    public void start() {
        this.mHandler.sendEmptyMessage(0);
        this.mIsRunning = true;
    }

    public void resetAndStart(int i, Runnable runnable) {
        stop();
        setRunnable(runnable);
        setDuration(i);
        start();
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }
}
