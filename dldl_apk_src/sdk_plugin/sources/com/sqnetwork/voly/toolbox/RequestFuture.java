package com.sqnetwork.voly.toolbox;

import android.os.SystemClock;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestFuture<T> implements Future<T>, Response.Listener<T>, Response.ErrorListener {
    private VolleyError mException;
    private Request<?> mRequest;
    private T mResult;
    private boolean mResultReceived = false;

    public static <E> RequestFuture<E> newFuture() {
        return new RequestFuture<>();
    }

    private RequestFuture() {
    }

    public void setRequest(Request<?> request) {
        this.mRequest = request;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        if (this.mRequest == null) {
            return false;
        }
        if (isDone()) {
            return false;
        }
        this.mRequest.cancel();
        return true;
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        try {
            return doGet(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public T get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        return doGet(Long.valueOf(TimeUnit.MILLISECONDS.convert(timeout, unit)));
    }

    private synchronized T doGet(Long timeoutMs) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        if (this.mResultReceived) {
            return this.mResult;
        }
        if (timeoutMs == null) {
            while (!isDone()) {
                wait(0L);
            }
        } else if (timeoutMs.longValue() > 0) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            long jLongValue = timeoutMs.longValue() + jUptimeMillis;
            while (!isDone() && jUptimeMillis < jLongValue) {
                wait(jLongValue - jUptimeMillis);
                jUptimeMillis = SystemClock.uptimeMillis();
            }
        }
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        if (!this.mResultReceived) {
            throw new TimeoutException();
        }
        return this.mResult;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        Request<?> request = this.mRequest;
        if (request == null) {
            return false;
        }
        return request.isCanceled();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0012  */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isDone() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.mResultReceived     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L12
            com.sqnetwork.voly.VolleyError r0 = r1.mException     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L12
            boolean r0 = r1.isCancelled()     // Catch: java.lang.Throwable -> L15
            if (r0 == 0) goto L10
            goto L12
        L10:
            r0 = 0
            goto L13
        L12:
            r0 = 1
        L13:
            monitor-exit(r1)
            return r0
        L15:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqnetwork.voly.toolbox.RequestFuture.isDone():boolean");
    }

    @Override // com.sqnetwork.voly.Response.Listener
    public synchronized void onResponse(Response<T> raw, T response) {
        this.mResultReceived = true;
        this.mResult = response;
        notifyAll();
    }

    @Override // com.sqnetwork.voly.Response.ErrorListener
    public synchronized void onErrorResponse(VolleyError error) {
        this.mException = error;
        notifyAll();
    }
}
