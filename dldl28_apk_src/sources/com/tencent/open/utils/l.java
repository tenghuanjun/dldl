package com.tencent.open.utils;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public final class l {
    private static Handler c;
    private static HandlerThread d;
    private static Object b = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Executor f1093a = c();

    private static Executor c() {
        return new ThreadPoolExecutor(0, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static void a(Runnable runnable) {
        try {
            f1093a.execute(runnable);
        } catch (RejectedExecutionException unused) {
        }
    }

    public static Handler a() {
        if (c == null) {
            synchronized (l.class) {
                HandlerThread handlerThread = new HandlerThread("SDK_SUB");
                d = handlerThread;
                handlerThread.start();
                c = new Handler(d.getLooper());
            }
        }
        return c;
    }

    public static void b(Runnable runnable) {
        a().post(runnable);
    }

    public static Executor b() {
        return new a();
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class a implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Queue<Runnable> f1094a;
        Runnable b;

        private a() {
            this.f1094a = new LinkedList();
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(final Runnable runnable) {
            this.f1094a.offer(new Runnable() { // from class: com.tencent.open.utils.l.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        a.this.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable runnablePoll = this.f1094a.poll();
            this.b = runnablePoll;
            if (runnablePoll != null) {
                l.f1093a.execute(this.b);
            }
        }
    }
}
