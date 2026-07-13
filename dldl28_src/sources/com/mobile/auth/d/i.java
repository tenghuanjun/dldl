package com.mobile.auth.d;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ThreadPoolExecutor f523a;
    private static i b;

    public static abstract class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f524a = false;

        public void a(boolean z) {
            try {
                this.f524a = z;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }

        public boolean a() {
            try {
                return this.f524a;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return false;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return false;
                }
            }
        }
    }

    private i() {
        b();
    }

    public static i a() {
        try {
            if (b == null) {
                b = new i();
            }
            return b;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private void b() {
        try {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 3L, TimeUnit.SECONDS, new LinkedBlockingQueue());
            f523a = threadPoolExecutor;
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(Runnable runnable) {
        try {
            f523a.execute(runnable);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public Future b(Runnable runnable) {
        try {
            return f523a.submit(runnable);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
