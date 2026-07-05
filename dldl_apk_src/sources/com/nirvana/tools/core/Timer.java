package com.nirvana.tools.core;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class Timer {
    private volatile boolean isCancel;
    private AtomicBoolean isTimeout;
    private Handler mHandler;
    private Runnable mTimeoutCallback;
    private Runnable mTimeoutRunnable;
    private long mTimeoutStamp;

    public Timer(long j, Runnable runnable) {
        this(j, runnable, Looper.getMainLooper());
    }

    public Timer(long j, Runnable runnable, Looper looper) {
        this.isTimeout = new AtomicBoolean(false);
        this.isCancel = false;
        this.mTimeoutStamp = j;
        this.mTimeoutCallback = runnable;
        this.mHandler = new Handler(looper);
    }

    public boolean isTimeout() {
        return this.isTimeout.get();
    }

    public synchronized boolean notTimeoutAndStop() {
        boolean zIsTimeout;
        zIsTimeout = isTimeout();
        stop();
        return !zIsTimeout;
    }

    public void start() {
        if (this.mTimeoutCallback == null || this.mTimeoutStamp <= 0) {
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.nirvana.tools.core.Timer.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Timer.this) {
                    if (!Timer.this.isCancel) {
                        Timer.this.isTimeout.set(true);
                        Timer.this.mTimeoutCallback.run();
                    }
                }
            }
        };
        this.mTimeoutRunnable = runnable;
        this.mHandler.postDelayed(runnable, this.mTimeoutStamp);
    }

    public synchronized void stop() {
        this.isCancel = true;
        if (this.mTimeoutRunnable != null) {
            this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        }
        this.mTimeoutCallback = null;
        this.mTimeoutRunnable = null;
    }
}
