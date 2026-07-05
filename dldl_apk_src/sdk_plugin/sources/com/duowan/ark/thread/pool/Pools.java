package com.duowan.ark.thread.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Pools {
    public static ScheduledExecutor newScheduledExecutor(String str, int i, int i2) {
        return new ScheduledPoolExecutor(str, i, i2, new HandlerWorkFactory());
    }

    public static ScheduledExecutor newScheduledThreadPoolExecutor(int i) {
        return newScheduledThreadPoolExecutor(i, Integer.MAX_VALUE, "arkDefault");
    }

    public static ScheduledExecutor newScheduledThreadPoolExecutor(int i, int i2, final String str) {
        return new ScheduledExecutorAdapter(new ThreadPoolExecutor(i, i2, 60L, TimeUnit.SECONDS, new SynchronousQueue(), new java.util.concurrent.ThreadFactory() { // from class: com.duowan.ark.thread.pool.Pools.1
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, str + "-" + this.threadNumber.getAndIncrement() + "-thread");
            }
        }));
    }
}
