package com.duowan.live.one.util;

import android.os.SystemClock;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TimeLog {
    private static final int DEFAULT_INTERVAL = 30000;
    private long mInterval;
    private long mLastLogMillis;
    private String mLogTag;

    public TimeLog(String str, long j) {
        this.mLogTag = "";
        this.mLastLogMillis = 0L;
        this.mInterval = 30000L;
        this.mLogTag = str;
        this.mInterval = j;
    }

    public TimeLog(String str) {
        this(str, 30000L);
    }

    public void info(String str) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.mLastLogMillis > this.mInterval) {
            this.mLastLogMillis = jUptimeMillis;
            L.info(this.mLogTag, str);
        }
    }

    public void info(String str, Object... objArr) {
        info(String.format(str, objArr));
    }

    public void error(String str) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.mLastLogMillis > this.mInterval) {
            this.mLastLogMillis = jUptimeMillis;
            L.error(this.mLogTag, str);
        }
    }

    public void error(String str, Object... objArr) {
        error(String.format(str, objArr));
    }

    public long lastMillis() {
        return this.mLastLogMillis;
    }
}
