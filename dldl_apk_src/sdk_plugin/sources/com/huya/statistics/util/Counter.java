package com.huya.statistics.util;

import android.os.Handler;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Counter implements Runnable {
    private static final Callback NONE = new Callback() { // from class: com.huya.statistics.util.Counter.1
        @Override // com.huya.statistics.util.Counter.Callback
        public void onCount(int i) {
        }
    };
    private final long INTERVAL;
    private final int STEP;
    private int counter;
    private final Handler mHandler;
    private Callback mCallback = NONE;
    private boolean mRunning = false;

    public interface Callback {
        void onCount(int i);
    }

    public Counter(Handler handler, int i, long j, boolean z) {
        this.mHandler = handler;
        this.counter = i;
        this.INTERVAL = j;
        this.STEP = z ? 1 : -1;
    }

    public Counter setCounter(int i) {
        this.counter = i;
        return this;
    }

    public Counter reset() {
        return setCounter(0);
    }

    public Counter toggle(boolean z) {
        return z ? start(0L) : stop();
    }

    public Counter start(long j) {
        this.mHandler.removeCallbacks(this);
        this.mRunning = true;
        this.mHandler.postDelayed(this, j);
        return this;
    }

    public Counter stop() {
        this.mHandler.removeCallbacks(this);
        this.mRunning = false;
        return this;
    }

    public void setCallback(Callback callback) {
        if (callback == null) {
            callback = NONE;
        }
        this.mCallback = callback;
    }

    public int count() {
        return this.counter;
    }

    public boolean running() {
        return this.mRunning;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mRunning) {
            this.mCallback.onCount(this.counter);
            this.counter += this.STEP;
            this.mHandler.postDelayed(this, this.INTERVAL);
        }
    }

    public long getInterval() {
        return this.INTERVAL;
    }
}
