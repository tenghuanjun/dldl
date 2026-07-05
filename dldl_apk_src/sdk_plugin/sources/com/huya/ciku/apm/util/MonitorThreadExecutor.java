package com.huya.ciku.apm.util;

import android.os.Build;
import android.os.Process;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MonitorThreadExecutor {
    static ScheduledThreadPoolExecutor sThreadPool;

    static {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5, new DefaultThreadFactory());
        sThreadPool = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setMaximumPoolSize(10);
        if (Build.VERSION.SDK_INT >= 21) {
            sThreadPool.setRemoveOnCancelPolicy(true);
        }
    }

    public static ScheduledFuture execute(Runnable runnable, long j) {
        if (runnable != null) {
            return sThreadPool.scheduleAtFixedRate(runnable, 0L, j, TimeUnit.MILLISECONDS);
        }
        return null;
    }

    public static void remove(Runnable runnable) {
        sThreadPool.remove(runnable);
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        int threadNum = 0;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "monitor-pool-thread-" + this.threadNum) { // from class: com.huya.ciku.apm.util.MonitorThreadExecutor.DefaultThreadFactory.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            this.threadNum = this.threadNum + 1;
            return thread;
        }
    }
}
