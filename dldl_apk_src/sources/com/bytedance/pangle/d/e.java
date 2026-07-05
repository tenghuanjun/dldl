package com.bytedance.pangle.d;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e {
    private static Executor a = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.bytedance.pangle.d.e.1
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pangle-Fast-" + this.a.getAndIncrement());
        }
    });
    private static final Object b = new Object();
    private static Handler c = null;

    public static void a(Runnable runnable) {
        a.execute(runnable);
    }

    public static ExecutorService a(int i) {
        return Executors.newFixedThreadPool(i, new ThreadFactory() { // from class: com.bytedance.pangle.d.e.2
            private final AtomicInteger a = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, "pangle-Install-" + this.a.getAndIncrement());
            }
        });
    }

    private static Handler a() {
        Handler handler;
        synchronized (b) {
            if (c == null) {
                c = new Handler(Looper.getMainLooper());
            }
            handler = c;
        }
        return handler;
    }

    public static void b(Runnable runnable) {
        if (a().getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            a().post(runnable);
        }
    }
}
