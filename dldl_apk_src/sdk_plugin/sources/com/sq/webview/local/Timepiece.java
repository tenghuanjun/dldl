package com.sq.webview.local;

import android.os.SystemClock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Timepiece {
    private final long startTime;

    private Timepiece(long startTime) {
        this.startTime = startTime;
    }

    public static Timepiece start() {
        return new Timepiece(SystemClock.uptimeMillis());
    }

    long stop() {
        return SystemClock.uptimeMillis() - this.startTime;
    }
}
