package com.volcengine.d;

import android.os.Handler;
import android.os.Looper;
import com.volcengine.common.innerapi.ExecutorsService;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public class a implements ExecutorsService {
    private static final int d = Runtime.getRuntime().availableProcessors() * 4;
    private static final int e = Runtime.getRuntime().availableProcessors() * 8;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ExecutorService f1131a;
    private final ExecutorService b;
    private final b c;

    private static class b implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Handler f1132a;

        private b() {
            this.f1132a = new Handler(Looper.getMainLooper());
        }

        public void a(Runnable runnable, long j) {
            this.f1132a.postDelayed(runnable, j);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f1132a.post(runnable);
        }
    }

    private static class c implements ThreadFactory {
        private static final AtomicInteger d = new AtomicInteger(1);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ThreadGroup f1133a;
        private final AtomicInteger b = new AtomicInteger(1);
        private final String c;

        c(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.f1133a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.c = "vesdk-" + str + "-" + d.getAndIncrement() + "-t-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f1133a, runnable, this.c + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public a(ExecutorService executorService, ExecutorService executorService2, b bVar) {
        this.f1131a = executorService;
        this.b = executorService2;
        this.c = bVar;
    }

    public static int a() {
        return Runtime.getRuntime().availableProcessors() + 1;
    }

    private static ExecutorService b() {
        return Executors.newFixedThreadPool(a());
    }

    public static a c() {
        return new a(d(), b(), e());
    }

    private static ExecutorService d() {
        return new ThreadPoolExecutor(Math.max(16, d), Math.max(32, e), 10L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new c("io"), new ThreadPoolExecutor.AbortPolicy());
    }

    private static b e() {
        return new b();
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public void executeCpu(Runnable runnable) {
        this.b.execute(runnable);
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public void executeIO(Runnable runnable) {
        this.f1131a.execute(runnable);
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public void executeMain(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.c.execute(runnable);
        }
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public void executeMainDelay(Runnable runnable, long j) {
        this.c.a(runnable, j);
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public ExecutorService getCpuExecutor() {
        return this.b;
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public ExecutorService getIOExecutor() {
        return this.f1131a;
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public Future<?> submitCpu(Runnable runnable) {
        return this.b.submit(runnable);
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public <T> Future<T> submitCpu(Callable<T> callable) {
        return this.b.submit(callable);
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public Future<?> submitIO(Runnable runnable) {
        return this.f1131a.submit(runnable);
    }

    @Override // com.volcengine.common.innerapi.ExecutorsService
    public <T> Future<T> submitIO(Callable<T> callable) {
        return this.f1131a.submit(callable);
    }
}
