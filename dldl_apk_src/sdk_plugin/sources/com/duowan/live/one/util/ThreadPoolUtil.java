package com.duowan.live.one.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ThreadPoolUtil {
    private static final int CORE_POOL_SIZE = 4;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static final String THREAD_NAME_PREFIX = "ThreadPoolUtil-";
    private static final ThreadFactory sFactory = new ThreadFactory() { // from class: com.duowan.live.one.util.ThreadPoolUtil.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, ThreadPoolUtil.THREAD_NAME_PREFIX + this.mCount.getAndIncrement());
        }
    };
    private static ThreadPoolExecutor sThreadPool;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), sFactory);
        sThreadPool = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private ThreadPoolUtil() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static void executorAsync(Runnable runnable) {
        if (runnable != null) {
            sThreadPool.execute(runnable);
        }
    }
}
