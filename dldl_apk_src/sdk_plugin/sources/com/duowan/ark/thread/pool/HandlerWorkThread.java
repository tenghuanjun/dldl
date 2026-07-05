package com.duowan.ark.thread.pool;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class HandlerWorkThread extends HandlerThread implements WorkThread {
    private Handler mHandler;

    public HandlerWorkThread(String str) {
        super(str);
        this.mHandler = null;
    }

    public HandlerWorkThread(String str, int i) {
        super(str, i);
        this.mHandler = null;
    }

    @Override // com.duowan.ark.thread.pool.WorkThread
    public void post(Runnable runnable, long j) {
        if (0 == j) {
            this.mHandler.post(runnable);
        } else {
            this.mHandler.postDelayed(runnable, j);
        }
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        super.start();
        this.mHandler = new Handler(getLooper());
    }
}
