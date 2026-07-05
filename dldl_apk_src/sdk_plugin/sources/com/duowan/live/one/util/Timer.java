package com.duowan.live.one.util;

import android.os.Handler;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Timer {
    private Handler mHandler;
    private Runnable mRunnable;
    private int mMSecs = 0;
    private boolean mRunning = false;

    public static class EventTimeout {
        public String mKey;
        public Timer mTimer;

        EventTimeout(Timer timer, String str) {
            this.mTimer = timer;
            this.mKey = str;
        }
    }

    public void setInterval(int i) {
        this.mMSecs = i;
    }

    public int interval() {
        return this.mMSecs;
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public void start(int i, final String str) {
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        if (this.mRunnable == null) {
            this.mRunnable = new Runnable() { // from class: com.duowan.live.one.util.Timer.1
                @Override // java.lang.Runnable
                public void run() {
                    L.info("Timer_test", "send EventTimeout...");
                    BaseApi.getSignalCenterApi().send(new EventTimeout(Timer.this, str));
                    Timer.this.mRunning = false;
                }
            };
        }
        this.mMSecs = i;
        this.mRunning = true;
        L.info("Timer_test", "spostDelayed..." + this.mMSecs);
        this.mHandler.postDelayed(this.mRunnable, (long) this.mMSecs);
    }

    public void stop() {
        Runnable runnable;
        L.info("Timer_test", "stop...");
        Handler handler = this.mHandler;
        if (handler != null && (runnable = this.mRunnable) != null) {
            handler.removeCallbacks(runnable);
        }
        this.mRunning = false;
    }
}
