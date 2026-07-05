package com.duowan.live.one.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ThreadPoolFactory {
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newSingleThreadScheduledExecutor();
    private static CopyOnWriteArraySet<ExecutorService> mThreadSet = new CopyOnWriteArraySet<>();
    private static AtomicReference<ExecutorService> mCachedPool = new AtomicReference<>();
    private static final ExecutorService mThread = getCachedThreadPool();
    private static ThreadCheckTask sCheckTask = new ThreadCheckTask();

    public static void run(Runnable runnable) {
        mThread.execute(runnable);
    }

    public static ExecutorService getCachedThreadPool() {
        ExecutorService executorServiceNewCachedThreadPool;
        ExecutorService executorService = mCachedPool.get();
        if (executorService != null && !executorService.isShutdown()) {
            return executorService;
        }
        synchronized (ThreadPoolFactory.class) {
            executorServiceNewCachedThreadPool = mCachedPool.get();
            if (executorServiceNewCachedThreadPool == null || executorServiceNewCachedThreadPool.isShutdown()) {
                executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
                mCachedPool.set(executorServiceNewCachedThreadPool);
            }
        }
        return executorServiceNewCachedThreadPool;
    }

    public static ExecutorService getFixedThreadPool(int i) {
        ExecutorService executorServiceNewFixedThreadPool;
        if (i == 1) {
            executorServiceNewFixedThreadPool = Executors.newSingleThreadExecutor();
        } else {
            executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(i);
        }
        recordThreadPool(executorServiceNewFixedThreadPool);
        return executorServiceNewFixedThreadPool;
    }

    public static ExecutorService getSingleThreadPool() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        recordThreadPool(executorServiceNewSingleThreadExecutor);
        return executorServiceNewSingleThreadExecutor;
    }

    public static ScheduledExecutorService getScheduledThreadPool(int i) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(i);
        recordThreadPool(scheduledExecutorServiceNewScheduledThreadPool);
        return scheduledExecutorServiceNewScheduledThreadPool;
    }

    public static void shutdown() {
        Iterator<ExecutorService> it = mThreadSet.iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
        mThreadSet.clear();
    }

    private static void recordThreadPool(ExecutorService executorService) {
        mThreadSet.add(executorService);
        if (sCheckTask.post()) {
            return;
        }
        removeShutdownThread();
    }

    private static class ThreadCheckTask implements Runnable {
        public static final int INTERVAL = 30;
        private AtomicBoolean mPosted;

        private ThreadCheckTask() {
            this.mPosted = new AtomicBoolean();
        }

        public boolean post() {
            if (!this.mPosted.compareAndSet(false, true)) {
                return false;
            }
            ThreadPoolFactory.asyncDelayRun(this, 30L, TimeUnit.SECONDS);
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadPoolFactory.removeShutdownThread();
            this.mPosted.set(false);
            post();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void removeShutdownThread() {
        ArrayList arrayList = new ArrayList();
        for (ExecutorService executorService : mThreadSet) {
            if (executorService.isShutdown()) {
                arrayList.add(executorService);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        mThreadSet.removeAll(arrayList);
    }

    public static void asyncDelayRun(Runnable runnable, long j, TimeUnit timeUnit) {
        SCHEDULED_EXECUTOR.schedule(runnable, j, timeUnit);
    }
}
