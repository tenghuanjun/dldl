package com.nirvana.tools.core;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes3.dex */
public class Worker {
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private String mWorkerId;

    Worker(String str) {
        this.mHandlerThread = null;
        HandlerThread handlerThread = new HandlerThread("nirvana_base_worker_".concat(String.valueOf(str)));
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mWorkerId = str;
    }

    private void doRelease() {
        if (this.mHandlerThread != null) {
            this.mHandlerThread.quitSafely();
            this.mHandlerThread = null;
            this.mHandler = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        doRelease();
    }

    String getWorkerId() {
        return this.mWorkerId;
    }

    public synchronized void post(Runnable runnable) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    public synchronized void postDelayed(Runnable runnable, long j) {
        Handler handler = this.mHandler;
        if (handler != null) {
            if (j > 0) {
                handler.postDelayed(runnable, j);
                return;
            }
            handler.post(runnable);
        }
    }

    synchronized void release() {
        doRelease();
    }
}
