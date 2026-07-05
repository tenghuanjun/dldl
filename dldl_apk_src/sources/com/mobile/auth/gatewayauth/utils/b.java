package com.mobile.auth.gatewayauth.utils;

import android.content.Context;
import android.graphics.Typeface;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.TokenResultListener;
import java.lang.Thread;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b {

    public static abstract class a implements Runnable {
        protected abstract void a();

        protected abstract void a(Throwable th);

        protected void b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    try {
                        a();
                    } catch (Throwable th) {
                        a(th);
                    }
                } finally {
                    b();
                }
            } catch (Throwable th2) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th2);
                } catch (Throwable th3) {
                    com.mobile.auth.gatewayauth.a.a(th3);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.gatewayauth.utils.b$b, reason: collision with other inner class name */
    public static abstract class AbstractRunnableC0075b implements Runnable {
        private Thread.UncaughtExceptionHandler a;

        protected AbstractRunnableC0075b() {
            this.a = new Thread.UncaughtExceptionHandler() { // from class: com.mobile.auth.gatewayauth.utils.b.b.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    try {
                        f.c("未知异常:" + b.b(th));
                        b.a(thread, th);
                    } catch (Throwable th2) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        } catch (Throwable th3) {
                            com.mobile.auth.gatewayauth.a.a(th3);
                        }
                    }
                }
            };
        }

        protected AbstractRunnableC0075b(final TokenResultListener tokenResultListener) {
            this.a = new Thread.UncaughtExceptionHandler() { // from class: com.mobile.auth.gatewayauth.utils.b.b.2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, final Throwable th) {
                    try {
                        b.a(thread, th);
                        b.a(new a() { // from class: com.mobile.auth.gatewayauth.utils.b.b.2.1
                            @Override // com.mobile.auth.gatewayauth.utils.b.a
                            protected void a() {
                                try {
                                    if (tokenResultListener != null) {
                                        tokenResultListener.onTokenFailed(b.a(th));
                                    } else {
                                        f.c(b.b(th));
                                    }
                                } catch (Throwable th2) {
                                    try {
                                        com.mobile.auth.gatewayauth.a.a(th2);
                                    } catch (Throwable th3) {
                                        com.mobile.auth.gatewayauth.a.a(th3);
                                    }
                                }
                            }

                            @Override // com.mobile.auth.gatewayauth.utils.b.a
                            protected void a(Throwable th2) {
                            }
                        });
                    } catch (Throwable th2) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        } catch (Throwable th3) {
                            com.mobile.auth.gatewayauth.a.a(th3);
                        }
                    }
                }
            };
        }

        protected abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.currentThread().setUncaughtExceptionHandler(this.a);
                a();
                Thread.currentThread().setUncaughtExceptionHandler(null);
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    public static Typeface a(Context context, String str) {
        try {
            return Typeface.createFromAsset(context.getAssets(), str);
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
