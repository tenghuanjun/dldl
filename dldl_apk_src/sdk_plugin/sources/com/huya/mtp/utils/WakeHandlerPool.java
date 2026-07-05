package com.huya.mtp.utils;

import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum WakeHandlerPool {
    WORKER("ThreadWorker"),
    MAIN(Looper.getMainLooper()),
    CURRENT;

    private WakeHandler mHandler;

    WakeHandlerPool() {
        this.mHandler = new WakeHandler();
    }

    WakeHandlerPool(String str) {
        this.mHandler = new WakeHandler(str);
    }

    WakeHandlerPool(String str, boolean z) {
        this.mHandler = new WakeHandler(str, z);
    }

    WakeHandlerPool(Looper looper) {
        this.mHandler = new WakeHandler(looper);
    }

    public WakeHandler getHandler() {
        return this.mHandler;
    }
}
