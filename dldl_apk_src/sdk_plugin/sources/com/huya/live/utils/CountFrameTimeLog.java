package com.huya.live.utils;

import android.os.SystemClock;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CountFrameTimeLog {
    private static final int DEFAULT_INTERVAL = 30000;
    private long mCount;
    private long mInterval;
    private long mLastLogMillis;
    private String mLogTag;

    public CountFrameTimeLog(String str, long j) {
        this.mLogTag = "";
        this.mLastLogMillis = 0L;
        this.mInterval = 30000L;
        this.mCount = 0L;
        this.mLogTag = str;
        this.mInterval = j;
    }

    public CountFrameTimeLog(String str) {
        this(str, 30000L);
    }

    public void info(String str) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mCount++;
        if (jUptimeMillis - this.mLastLogMillis > this.mInterval) {
            this.mLastLogMillis = jUptimeMillis;
            L.info(this.mLogTag, str + ", FrameCount = " + this.mCount);
            this.mCount = 0L;
        }
    }

    public void info(String str, Object... objArr) {
        info(String.format(str, objArr));
    }
}
