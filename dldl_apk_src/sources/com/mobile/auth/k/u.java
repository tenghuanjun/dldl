package com.mobile.auth.k;

import android.content.Context;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class u {
    private static ExecutorService a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue());

    public static abstract class a implements Runnable {
        private Thread.UncaughtExceptionHandler a;

        /* JADX INFO: renamed from: com.mobile.auth.k.u$a$a, reason: collision with other inner class name */
        class C0080a implements Thread.UncaughtExceptionHandler {
            C0080a(a aVar) {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                th.printStackTrace();
            }
        }

        class b implements Thread.UncaughtExceptionHandler {
            final /* synthetic */ Context a;
            final /* synthetic */ com.cmic.sso.sdk.a b;

            b(a aVar, Context context, com.cmic.sso.sdk.a aVar2) {
                this.a = context;
                this.b = aVar2;
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                th.printStackTrace();
                com.mobile.auth.e.a.a(this.a).a("200025", "发生未知错误", this.b, null, th);
            }
        }

        protected a() {
            this.a = new C0080a(this);
        }

        protected a(Context context, com.cmic.sso.sdk.a aVar) {
            this.a = new b(this, context, aVar);
        }

        protected abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(this.a);
            a();
            Thread.currentThread().setUncaughtExceptionHandler(null);
        }
    }

    public static void a(a aVar) {
        a.execute(aVar);
    }
}
