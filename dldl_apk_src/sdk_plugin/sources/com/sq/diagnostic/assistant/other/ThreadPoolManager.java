package com.sq.diagnostic.assistant.other;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class ThreadPoolManager extends ThreadPoolExecutor {
    private static volatile ThreadPoolManager sInstance;

    public ThreadPoolManager() {
        super(1, 200, 1000L, TimeUnit.MILLISECONDS, new SynchronousQueue());
    }

    public static ThreadPoolManager getInstance() {
        if (sInstance == null) {
            synchronized (ThreadPoolManager.class) {
                if (sInstance == null) {
                    sInstance = new ThreadPoolManager();
                }
            }
        }
        return sInstance;
    }
}
