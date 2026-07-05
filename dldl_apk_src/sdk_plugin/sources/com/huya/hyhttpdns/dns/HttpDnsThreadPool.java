package com.huya.hyhttpdns.dns;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsThreadPool {
    private static final int CORE_POOL_SIZE = 4;
    private static final int KEEP_ALIVE_TIME = 5;
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static final String TAG = "HttpDnsThreadPool";
    private static final String THREAD_NAME_PREFIX = "HttpDnsThread-";
    private static ThreadPoolExecutor sThreadPool;
    private static final ThreadFactory sFactory = new ThreadFactory() { // from class: com.huya.hyhttpdns.dns.HttpDnsThreadPool.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, HttpDnsThreadPool.THREAD_NAME_PREFIX + this.mCount.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    };
    private static ExecutorFactory sExecutorFactory = new ExecutorFactory() { // from class: com.huya.hyhttpdns.dns.HttpDnsThreadPool.2
        @Override // com.huya.hyhttpdns.dns.HttpDnsThreadPool.ExecutorFactory
        public ThreadPoolExecutor getThreadPool() {
            return new ThreadPoolExecutor(4, 4, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), HttpDnsThreadPool.sFactory) { // from class: com.huya.hyhttpdns.dns.HttpDnsThreadPool.2.1
                {
                    allowCoreThreadTimeOut(true);
                }
            };
        }
    };

    public interface ExecutorFactory {
        ThreadPoolExecutor getThreadPool();
    }

    public static void execute(Runnable runnable) {
        if (runnable != null) {
            getThreadPoolExecutor().execute(runnable);
        }
    }

    public static Future submit(Runnable runnable) {
        if (runnable != null) {
            return getThreadPoolExecutor().submit(runnable);
        }
        return null;
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (sThreadPool == null) {
            synchronized (HttpDnsThreadPool.class) {
                if (sThreadPool == null) {
                    sThreadPool = sExecutorFactory.getThreadPool();
                }
            }
        }
        return sThreadPool;
    }

    public static synchronized void setExecutorFactory(ExecutorFactory executorFactory) {
        sExecutorFactory = executorFactory;
    }
}
