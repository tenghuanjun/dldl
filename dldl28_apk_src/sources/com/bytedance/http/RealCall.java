package com.bytedance.http;

import com.bytedance.http.HttpResponse;
import com.bytedance.http.a.b;
import com.bytedance.http.a.e;
import com.bytedance.http.a.f;
import com.bytedance.http.b.c;
import com.bytedance.http.b.d;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public class RealCall implements Call {
    private Callback mCallback;
    private boolean mExecuted = false;
    private final HttpDispatcher mHttpDispatcher;
    private final HttpRequest mOriginalRequest;
    private final f mRetryInterceptor;
    private final com.bytedance.http.b.f mTransmitter;

    final class a extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Callback f382a;

        a(Callback callback) {
            super("AsyncCall");
            this.f382a = callback;
        }

        @Override // com.bytedance.http.b.d
        protected final void a() {
            HttpResponse httpResponseBuild;
            Callback callback;
            RealCall realCall;
            c.a("start to execute a HTTP request");
            long jCurrentTimeMillis = System.currentTimeMillis();
            int i = -2;
            boolean z = false;
            try {
                try {
                    ArrayList arrayList = new ArrayList(RealCall.this.mHttpDispatcher.interceptors());
                    arrayList.add(new e(RealCall.this.mHttpDispatcher));
                    arrayList.add(RealCall.this.mRetryInterceptor);
                    HttpDispatcher unused = RealCall.this.mHttpDispatcher;
                    arrayList.add(new com.bytedance.http.a.a());
                    arrayList.add(new com.bytedance.http.a.d(RealCall.this.mHttpDispatcher));
                    HttpDispatcher unused2 = RealCall.this.mHttpDispatcher;
                    arrayList.add(new b());
                    HttpDispatcher unused3 = RealCall.this.mHttpDispatcher;
                    arrayList.add(new com.bytedance.http.a.c());
                    HttpRequest httpRequest = RealCall.this.mOriginalRequest;
                    RealCall realCall2 = RealCall.this;
                    HttpResponse httpResponseProceed = new com.bytedance.http.b.e(arrayList, 0, httpRequest, realCall2, realCall2.mTransmitter).proceed(RealCall.this.mOriginalRequest);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    z = true;
                    if (RealCall.this.isCancelled()) {
                        httpResponseProceed = new HttpResponse.Builder().code(-2).message("Canceled").request(RealCall.this.mOriginalRequest).extras(httpResponseProceed.extras()).addExtra(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(jCurrentTimeMillis2)).build();
                    } else {
                        httpResponseProceed.extras().put(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(jCurrentTimeMillis2));
                    }
                    c.a("It is all well.");
                    this.f382a.onResponse(RealCall.this, httpResponseProceed);
                } catch (CancellationException e) {
                    c.c(e.getMessage());
                    if (!z) {
                        httpResponseBuild = new HttpResponse.Builder().code(-2).message(e.getMessage()).addExtra(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(System.currentTimeMillis() - jCurrentTimeMillis)).request(RealCall.this.mOriginalRequest).build();
                        callback = this.f382a;
                        realCall = RealCall.this;
                        callback.onResponse(realCall, httpResponseBuild);
                    }
                } catch (Exception e2) {
                    c.c(e2.getMessage());
                    if (!z) {
                        long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        HttpResponse.Builder builder = new HttpResponse.Builder();
                        if (!RealCall.this.isCancelled()) {
                            i = -1;
                        }
                        httpResponseBuild = builder.code(i).message(e2.getMessage()).addExtra(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(jCurrentTimeMillis3)).request(RealCall.this.mOriginalRequest).build();
                        callback = this.f382a;
                        realCall = RealCall.this;
                        callback.onResponse(realCall, httpResponseBuild);
                    }
                }
            } finally {
                RealCall.this.mHttpDispatcher.dispatcher().finished(this);
            }
        }

        @Override // com.bytedance.http.b.d
        public final Call b() {
            return RealCall.this;
        }
    }

    public RealCall(HttpDispatcher httpDispatcher, HttpRequest httpRequest) {
        this.mHttpDispatcher = httpDispatcher;
        this.mOriginalRequest = httpRequest;
        this.mTransmitter = new com.bytedance.http.b.f(httpDispatcher);
        this.mRetryInterceptor = new f(httpDispatcher);
    }

    public static Call newRealCall(HttpDispatcher httpDispatcher, HttpRequest httpRequest) {
        return new RealCall(httpDispatcher, httpRequest);
    }

    @Override // com.bytedance.http.Call
    public Callback callback() {
        return this.mCallback;
    }

    @Override // com.bytedance.http.Call
    public void cancel() {
        this.mTransmitter.c();
        this.mRetryInterceptor.a();
    }

    @Override // com.bytedance.http.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.mExecuted) {
                throw new IllegalStateException("Already Executed");
            }
            this.mExecuted = true;
        }
        this.mCallback = callback;
        this.mHttpDispatcher.dispatcher().enqueue(new a(callback));
    }

    @Override // com.bytedance.http.Call
    public boolean isCancelled() {
        return this.mRetryInterceptor.b();
    }

    @Override // com.bytedance.http.Call
    public boolean isExecuted() {
        return this.mExecuted;
    }

    @Override // com.bytedance.http.Call
    public HttpRequest request() {
        return this.mOriginalRequest;
    }
}
