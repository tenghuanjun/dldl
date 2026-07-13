package com.mobile.auth.m;

import android.content.Context;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ExecutorService f750a = new ThreadPoolExecutor(0, 30, 60, TimeUnit.SECONDS, new SynchronousQueue());

    public static abstract class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Thread.UncaughtExceptionHandler f751a;

        protected a() {
            this.f751a = new Thread.UncaughtExceptionHandler() { // from class: com.mobile.auth.m.n.a.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    th.printStackTrace();
                }
            };
        }

        protected a(final Context context, final com.cmic.sso.sdk.a aVar) {
            this.f751a = new Thread.UncaughtExceptionHandler() { // from class: com.mobile.auth.m.n.a.2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    aVar.a().f408a.add(th);
                    com.mobile.auth.f.e.b(context).a("200025", "发生未知错误", aVar, null);
                }
            };
        }

        protected abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(this.f751a);
            a();
            Thread.currentThread().setUncaughtExceptionHandler(null);
        }
    }

    public static void a(a aVar) {
        try {
            f750a.execute(aVar);
        } catch (Exception e) {
            aVar.f751a.uncaughtException(Thread.currentThread(), e);
        }
    }
}
