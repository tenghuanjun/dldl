package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AcLogTarget {
    protected volatile boolean mActive;
    protected volatile AcLog.ILogger mLogger;

    protected AcLogTarget(boolean z) {
        this.mActive = z;
    }

    public abstract void d(String str, String str2);

    public abstract void e(String str, String str2);

    public abstract void e(String str, String str2, Throwable th);

    public abstract void f(String str, String str2);

    public abstract void i(String str, String str2);

    public void setActive(boolean z) {
        this.mActive = z;
    }

    public void setLogger(AcLog.ILogger iLogger) {
        this.mLogger = iLogger;
    }

    public abstract void v(String str, String str2);

    public abstract void w(String str, String str2);
}
