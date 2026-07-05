package com.huya.mtp.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ThreadUtils {
    private static final String TAG = "KTU";
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());
    private static final Executor sTaskExecutor;

    static {
        int i = 8;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.huya.mtp.utils.ThreadUtils.1
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "KTU-task-" + this.threadNumber.getAndIncrement() + "-t");
            }
        }) { // from class: com.huya.mtp.utils.ThreadUtils.2
            @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                super.execute(runnable);
            }
        };
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        sTaskExecutor = threadPoolExecutor;
    }

    private static class NetworkFetcherThreadPoolHolder {
        static final ExecutorService sNetworkFetcherThreadPool = Executors.newFixedThreadPool(5, new PriorityThreadFactory(10, "HuyaNetworkFetcher"));

        private NetworkFetcherThreadPoolHolder() {
        }
    }

    private static class IoBoundThreadPoolHolder {
        static final ExecutorService sIoBoundThreadPool = Executors.newFixedThreadPool(3, new PriorityThreadFactory(10, "IOBound"));

        private IoBoundThreadPoolHolder() {
        }
    }

    private static class DecodeThreadPoolHolder {
        static final ExecutorService sDecodeThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory(10, "Decode"));

        private DecodeThreadPoolHolder() {
        }
    }

    private static class BackgroundThreadPoolHolder {
        static final ExecutorService sBackgroundThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory(10, "BackGroundProcess"));

        private BackgroundThreadPoolHolder() {
        }
    }

    public static void runAsync(Runnable runnable) {
        sTaskExecutor.execute(runnable);
    }

    public static ThreadFactory createThreadFactory(int i, String str) {
        return new DefaultThreadFactory(i, str);
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final int threadPriority;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group = Thread.currentThread().getThreadGroup();

        DefaultThreadFactory(int i, String str) {
            this.threadPriority = i;
            this.namePrefix = "KTU-" + str + poolNumber.getAndIncrement() + "-tf-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.threadPriority);
            return thread;
        }
    }

    public static boolean runAsync(final Runnable runnable, long j) {
        return runAsyncOnAvailableThread(new Runnable() { // from class: com.huya.mtp.utils.ThreadUtils.3
            @Override // java.lang.Runnable
            public void run() {
                ThreadUtils.runAsync(runnable);
            }
        }, j);
    }

    public static boolean runOnMainThread(Runnable runnable) {
        return sMainHandler.post(runnable);
    }

    public static boolean runOnMainThread(Runnable runnable, long j) {
        return sMainHandler.postDelayed(runnable, j);
    }

    public static boolean runAsyncOnCurrentThread(Runnable runnable) {
        return new Handler().post(runnable);
    }

    public static boolean runAsyncOnCurrentThread(Runnable runnable, long j) {
        return new Handler().postDelayed(runnable, j);
    }

    public static boolean runAsyncOnAvailableThread(Runnable runnable) {
        if (Looper.myLooper() != null) {
            return runAsyncOnCurrentThread(runnable);
        }
        return runOnMainThread(runnable);
    }

    public static boolean runAsyncOnAvailableThread(Runnable runnable, long j) {
        if (Looper.myLooper() != null) {
            return runAsyncOnCurrentThread(runnable, j);
        }
        return runOnMainThread(runnable, j);
    }

    public static void confirmLooperPrepared() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    public static HandlerThread newStartHandlerThread(String str) {
        return newStartHandlerThread(str, 0);
    }

    public static HandlerThread newStartHandlerThread(String str, int i) {
        HandlerThread handlerThread = new HandlerThread("KTU-" + str + "-h", i);
        handlerThread.start();
        return handlerThread;
    }

    public static Handler newThreadHandler(String str) {
        return newThreadHandler(str, (Handler.Callback) null);
    }

    public static Handler newThreadHandler(String str, int i) {
        return newThreadHandler(str, i, null);
    }

    public static Handler newThreadHandler(String str, Handler.Callback callback) {
        return newThreadHandler(str, 0, callback);
    }

    public static Handler newThreadHandler(String str, int i, Handler.Callback callback) {
        return new Handler(newStartHandlerThread(str, i).getLooper(), callback);
    }

    public static Thread newOneTimeThread(String str, Runnable runnable) {
        return new Thread(runnable, "KTU-" + str);
    }

    public static ExecutorService getNetworkFetcherThreadPool() {
        return NetworkFetcherThreadPoolHolder.sNetworkFetcherThreadPool;
    }

    public static ExecutorService getIoBoundThreadPool() {
        return IoBoundThreadPoolHolder.sIoBoundThreadPool;
    }

    public static ExecutorService getDecodeThreadPool() {
        return DecodeThreadPoolHolder.sDecodeThreadPool;
    }

    public static ExecutorService getBackgroundThreadPool() {
        return BackgroundThreadPoolHolder.sBackgroundThreadPool;
    }

    public static ThreadPoolExecutor getExecutorService(int i, int i2, long j, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(i, i2, j, TimeUnit.SECONDS, blockingQueue, threadFactory);
    }
}
