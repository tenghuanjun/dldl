package com.bytedance.sdk.openadsdk.e;

import com.bytedance.sdk.openadsdk.api.c;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private static volatile a a;
    private volatile ExecutorService b;
    private volatile ThreadPoolExecutor c;
    private volatile ThreadPoolExecutor d;

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                a = new a();
            }
        }
        return a;
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            try {
                a(true).execute(runnable);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private ExecutorService a(boolean z) {
        if (this.b == null) {
            return z ? b() : c();
        }
        return this.b;
    }

    private ExecutorService b() {
        if (this.c == null) {
            this.c = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0029a("init"));
        }
        return this.c;
    }

    private ExecutorService c() {
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(2, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0029a());
        }
        return this.d;
    }

    public void b(Runnable runnable) {
        if (runnable != null) {
            try {
                a(false).execute(runnable);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(ExecutorService executorService) {
        if (executorService != null) {
            this.b = executorService;
            if (this.d == null && this.c == null) {
                return;
            }
            b(executorService);
        }
    }

    private void b(ExecutorService executorService) {
        executorService.execute(new Runnable() { // from class: com.bytedance.sdk.openadsdk.e.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.c != null) {
                    try {
                        a.this.a(a.this.c);
                        c.b("ApiThread", "release init pool!");
                    } catch (Throwable th) {
                        c.a("ApiThread", "release mInitExecutor failed", th);
                    }
                    a.this.c = null;
                }
                if (a.this.d != null) {
                    try {
                        a.this.a(a.this.d);
                        c.b("ApiThread", "release api pool!");
                    } catch (Throwable th2) {
                        c.a("ApiThread", "release mApiExecutor failed", th2);
                    }
                    a.this.d = null;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.setKeepAliveTime(1L, TimeUnit.MILLISECONDS);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        while (true) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            }
            if (threadPoolExecutor.getQueue().size() <= 0 && threadPoolExecutor.getActiveCount() == 0) {
                threadPoolExecutor.shutdown();
                return;
            }
        }
    }

    /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.e.a$a, reason: collision with other inner class name */
    public static class ThreadFactoryC0029a implements ThreadFactory {
        private final ThreadGroup a;
        private final AtomicInteger b;
        private final String c;

        ThreadFactoryC0029a() {
            this.b = new AtomicInteger(1);
            this.a = new ThreadGroup("csj_api");
            this.c = "csj_api";
        }

        ThreadFactoryC0029a(String str) {
            this.b = new AtomicInteger(1);
            this.a = new ThreadGroup("csj_api");
            this.c = "csj_api_" + str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.a, runnable, this.c + "_" + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 10) {
                thread.setPriority(10);
            }
            return thread;
        }
    }
}
