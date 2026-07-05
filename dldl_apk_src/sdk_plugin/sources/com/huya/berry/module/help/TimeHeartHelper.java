package com.huya.berry.module.help;

import com.duowan.auk.util.L;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TimeHeartHelper {
    private static final ThreadFactory mFactory = new ThreadFactory() { // from class: com.huya.berry.module.help.TimeHeartHelper.1
        private String THREAD_NAME = "HeartThread-";
        private final AtomicLong mCount = new AtomicLong(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            String str = this.THREAD_NAME + this.mCount.getAndIncrement();
            L.info(this.THREAD_NAME, "new Thread %s", str);
            return new Thread(runnable, str);
        }
    };
    private ScheduledExecutorService mHeartService;
    private String mName;
    private AtomicBoolean mStart = new AtomicBoolean(false);

    public TimeHeartHelper(String str) {
        this.mName = str;
    }

    public boolean isStart() {
        return this.mStart.get();
    }

    public void schedule(Runnable runnable, long j, long j2) {
        start(runnable, j, j2);
    }

    public void start(Runnable runnable, long j, long j2) {
        String str = this.mName;
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.mHeartService == null);
        L.info(str, "start...mHeartService null =%s", objArr);
        if (this.mHeartService != null) {
            stop();
        }
        this.mHeartService = new ScheduledThreadPoolExecutor(1, mFactory);
        this.mStart.set(true);
        this.mHeartService.scheduleWithFixedDelay(runnable, j, j2, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        String str = this.mName;
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.mHeartService == null);
        L.info(str, "stop:mHeartService null = %s", objArr);
        ScheduledExecutorService scheduledExecutorService = this.mHeartService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.mHeartService = null;
            this.mStart.set(false);
        }
    }
}
