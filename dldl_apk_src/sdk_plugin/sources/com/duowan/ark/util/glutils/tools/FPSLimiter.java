package com.duowan.ark.util.glutils.tools;

import android.os.SystemClock;
import com.duowan.ark.util.KLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FPSLimiter {
    private long mFPSInterval;
    private long mPreviousFrameTimeStamp = 0;
    private int mFPS = 0;
    private long mLast = 0;
    private long mStart = System.currentTimeMillis();

    public FPSLimiter(int i) {
        this.mFPSInterval = 0L;
        this.mFPSInterval = 1000 / i;
    }

    public void limitFPS() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = jElapsedRealtime - this.mPreviousFrameTimeStamp;
        long j2 = this.mFPSInterval;
        if (j < j2) {
            try {
                Thread.sleep(j2 - j);
            } catch (InterruptedException e) {
                KLog.error(this, e);
            }
            this.mPreviousFrameTimeStamp += this.mFPSInterval;
            return;
        }
        this.mPreviousFrameTimeStamp = jElapsedRealtime;
    }

    public void countFPS() {
        this.mFPS++;
        if (SystemClock.elapsedRealtime() - this.mLast >= 1000) {
            if (System.currentTimeMillis() - this.mStart >= 3000) {
                this.mStart = System.currentTimeMillis();
                KLog.info(this, "fps:%d", Integer.valueOf(this.mFPS));
            }
            this.mLast = SystemClock.elapsedRealtime();
            this.mFPS = 0;
        }
    }
}
