package com.alipay.deviceid.module.x;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.deviceid.module.rpc.mrpc.core.HttpException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class au implements bi {
    private static au g;
    private static final ThreadFactory i = new ThreadFactory() { // from class: com.alipay.deviceid.module.x.au.2
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.a.getAndIncrement());
            thread.setPriority(4);
            return thread;
        }
    };
    Context a;
    ap b = ap.a("android");
    long c;
    long d;
    long e;
    int f;
    private ThreadPoolExecutor h;

    private au(Context context) {
        this.a = context;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 11, 3L, TimeUnit.SECONDS, new ArrayBlockingQueue(20), i, new ThreadPoolExecutor.CallerRunsPolicy());
        this.h = threadPoolExecutor;
        try {
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        CookieSyncManager.createInstance(this.a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public static final au a(Context context) {
        au auVar = g;
        return auVar != null ? auVar : b(context);
    }

    private static final synchronized au b(Context context) {
        if (g != null) {
            return g;
        }
        au auVar = new au(context);
        g = auVar;
        return auVar;
    }

    @Override // com.alipay.deviceid.module.x.bi
    public final Future<bb> a(ba baVar) {
        if (az.a(this.a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.h.getActiveCount());
            objArr[1] = Long.valueOf(this.h.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.h.getTaskCount());
            long j = this.e;
            objArr[3] = Long.valueOf(j == 0 ? 0L : ((this.c * 1000) / j) >> 10);
            int i2 = this.f;
            objArr[4] = Long.valueOf(i2 != 0 ? this.d / ((long) i2) : 0L);
            objArr[5] = Long.valueOf(this.c);
            objArr[6] = Long.valueOf(this.d);
            objArr[7] = Long.valueOf(this.e);
            objArr[8] = Integer.valueOf(this.f);
            String.format(str, objArr);
        }
        final ax axVar = new ax(this, (av) baVar);
        FutureTask<bb> futureTask = new FutureTask<bb>(axVar) { // from class: com.alipay.deviceid.module.x.au.1
            @Override // java.util.concurrent.FutureTask
            protected final void done() {
                av avVarA = axVar.a();
                if (avVarA.a() == null) {
                    super.done();
                    return;
                }
                try {
                    get();
                    if (isCancelled() || avVarA.f) {
                        avVarA.f = true;
                        if (isCancelled() && isDone()) {
                            return;
                        }
                        cancel(false);
                    }
                } catch (InterruptedException e) {
                    new StringBuilder().append(e);
                } catch (CancellationException unused) {
                    avVarA.f = true;
                } catch (ExecutionException e2) {
                    if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                        new StringBuilder().append(e2);
                        return;
                    }
                    HttpException httpException = (HttpException) e2.getCause();
                    httpException.getCode();
                    httpException.getMsg();
                } catch (Throwable th) {
                    throw new RuntimeException("An error occured while executing http request", th);
                }
            }
        };
        this.h.execute(futureTask);
        return futureTask;
    }
}
