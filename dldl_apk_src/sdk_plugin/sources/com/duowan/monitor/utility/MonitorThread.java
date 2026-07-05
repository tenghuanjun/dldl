package com.duowan.monitor.utility;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MonitorThread {
    private static final int CORE_POOL_SIZE = 0;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int MAXIMUM_POOL_SIZE = 10;
    private static final String THREAD_NAME_PREFIX = "MonitorThread-";
    private static final ThreadFactory FACTORY = new ThreadFactory() { // from class: com.duowan.monitor.utility.MonitorThread.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, MonitorThread.THREAD_NAME_PREFIX + this.mCount.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    };
    private static ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(0, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(10), FACTORY, new ThreadPoolExecutor.DiscardOldestPolicy());
    private static HandlerThreadWrapper sMonitorThread = new HandlerThreadWrapper("loop");

    private MonitorThread() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static void postDelayed(Runnable runnable, long j) {
        if (runnable != null) {
            sMonitorThread.getHandler().postDelayed(runnable, j);
        }
    }

    public static void runOnMonitorThread(Runnable runnable) {
        if (Thread.currentThread() == sMonitorThread.getThread()) {
            runnable.run();
        } else {
            sMonitorThread.getHandler().post(runnable);
        }
    }

    public static void removeCallbacks(Runnable runnable) {
        if (runnable != null) {
            sMonitorThread.getHandler().removeCallbacks(runnable);
        }
    }

    public static void execute(Runnable runnable) {
        if (runnable != null) {
            sThreadPool.execute(runnable);
        }
    }

    private static class HandlerThreadWrapper {
        private Handler mHandler;
        private HandlerThread mThread;

        public HandlerThreadWrapper(String str) {
            this(str, null);
        }

        public HandlerThreadWrapper(String str, Handler.Callback callback) {
            HandlerThread handlerThread = new HandlerThread(MonitorThread.THREAD_NAME_PREFIX + str);
            handlerThread.setPriority(10);
            this.mThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), callback);
        }

        public Handler getHandler() {
            return this.mHandler;
        }

        public Thread getThread() {
            return this.mThread;
        }
    }
}
