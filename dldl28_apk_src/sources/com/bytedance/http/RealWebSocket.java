package com.bytedance.http;

import com.bytedance.http.HttpResponse;
import com.bytedance.http.a.b;
import com.bytedance.http.a.e;
import com.bytedance.http.a.f;
import com.bytedance.http.a.g;
import com.bytedance.http.b.c;
import com.bytedance.http.b.d;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public class RealWebSocket implements WebSocket {
    private Callback mCallback;
    private boolean mExecuted = false;
    private final HttpDispatcher mHttpDispatcher;
    private final HttpRequest mOriginalRequest;
    private final f mRetryInterceptor;
    private final com.bytedance.http.b.f mTransmitter;
    private final g mWebSocketInterceptor;

    final class a extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WebSocketListener f383a;

        public a(WebSocketListener webSocketListener) {
            super("AsyncCall");
            this.f383a = webSocketListener;
        }

        @Override // com.bytedance.http.b.d
        protected final void a() {
            HttpResponse httpResponseBuild;
            WebSocketListener webSocketListener;
            RealWebSocket realWebSocket;
            c.a("start to execute a HTTP request");
            long jCurrentTimeMillis = System.currentTimeMillis();
            int i = -2;
            boolean z = false;
            try {
                try {
                    try {
                        ArrayList arrayList = new ArrayList(RealWebSocket.this.mHttpDispatcher.interceptors());
                        arrayList.add(new e(RealWebSocket.this.mHttpDispatcher));
                        arrayList.add(RealWebSocket.this.mRetryInterceptor);
                        HttpDispatcher unused = RealWebSocket.this.mHttpDispatcher;
                        arrayList.add(new com.bytedance.http.a.a());
                        arrayList.add(new com.bytedance.http.a.d(RealWebSocket.this.mHttpDispatcher));
                        HttpDispatcher unused2 = RealWebSocket.this.mHttpDispatcher;
                        arrayList.add(new b());
                        arrayList.add(RealWebSocket.this.mWebSocketInterceptor);
                        HttpRequest httpRequest = RealWebSocket.this.mOriginalRequest;
                        RealWebSocket realWebSocket2 = RealWebSocket.this;
                        HttpResponse httpResponseProceed = new com.bytedance.http.b.e(arrayList, 0, httpRequest, realWebSocket2, realWebSocket2.mTransmitter).proceed(RealWebSocket.this.mOriginalRequest);
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        z = true;
                        if (RealWebSocket.this.isCancelled()) {
                            httpResponseProceed = new HttpResponse.Builder().code(-2).message("Canceled").request(RealWebSocket.this.mOriginalRequest).extras(httpResponseProceed.extras()).addExtra(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(jCurrentTimeMillis2)).build();
                        } else {
                            httpResponseProceed.extras().put(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(jCurrentTimeMillis2));
                        }
                        c.a("It is all well.");
                        this.f383a.onResponse(RealWebSocket.this, httpResponseProceed);
                    } catch (CancellationException e) {
                        c.c(e.getMessage());
                        if (!z) {
                            httpResponseBuild = new HttpResponse.Builder().code(-2).message(e.getMessage()).addExtra(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(System.currentTimeMillis() - jCurrentTimeMillis)).request(RealWebSocket.this.mOriginalRequest).build();
                            webSocketListener = this.f383a;
                            realWebSocket = RealWebSocket.this;
                            webSocketListener.onResponse(realWebSocket, httpResponseBuild);
                        }
                    }
                } catch (Exception e2) {
                    c.c(e2.getMessage());
                    if (!z) {
                        long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        HttpResponse.Builder builder = new HttpResponse.Builder();
                        if (!RealWebSocket.this.isCancelled()) {
                            i = -1;
                        }
                        httpResponseBuild = builder.code(i).message(e2.getMessage()).addExtra(HttpExtra.HTTP_EXTRA_TASK_DURATION, Long.toString(jCurrentTimeMillis3)).request(RealWebSocket.this.mOriginalRequest).build();
                        webSocketListener = this.f383a;
                        realWebSocket = RealWebSocket.this;
                        webSocketListener.onResponse(realWebSocket, httpResponseBuild);
                    }
                }
            } finally {
                RealWebSocket.this.mHttpDispatcher.dispatcher().finished(this);
            }
        }

        @Override // com.bytedance.http.b.d
        public final Call b() {
            return RealWebSocket.this;
        }
    }

    public RealWebSocket(HttpDispatcher httpDispatcher, HttpRequest httpRequest) {
        this.mHttpDispatcher = httpDispatcher;
        this.mOriginalRequest = httpRequest;
        httpRequest.extras().put(HttpExtra.HTTP_EXTRA_IS_WEBSOCKET, Boolean.toString(true));
        this.mTransmitter = new com.bytedance.http.b.f(httpDispatcher);
        this.mRetryInterceptor = new f(httpDispatcher);
        this.mWebSocketInterceptor = new g(httpDispatcher);
    }

    public static WebSocket newWebSocket(HttpDispatcher httpDispatcher, HttpRequest httpRequest) {
        return new RealWebSocket(httpDispatcher, httpRequest);
    }

    @Override // com.bytedance.http.Call
    public Callback callback() {
        return this.mCallback;
    }

    @Override // com.bytedance.http.Call
    public void cancel() {
        this.mTransmitter.c();
        this.mRetryInterceptor.a();
        this.mWebSocketInterceptor.a();
    }

    @Override // com.bytedance.http.Call
    public void enqueue(Callback callback) {
        throw new UnsupportedOperationException("WebSocket");
    }

    @Override // com.bytedance.http.WebSocket
    public void enqueue(WebSocketListener webSocketListener) {
        synchronized (this) {
            if (this.mExecuted) {
                throw new IllegalStateException("Already Executed");
            }
            this.mExecuted = true;
        }
        this.mCallback = webSocketListener;
        this.mHttpDispatcher.dispatcher().enqueue(new a(webSocketListener));
    }

    @Override // com.bytedance.http.Call
    public boolean isCancelled() {
        return this.mRetryInterceptor.b() || this.mWebSocketInterceptor.b();
    }

    @Override // com.bytedance.http.Call
    public boolean isExecuted() {
        return this.mExecuted;
    }

    @Override // com.bytedance.http.WebSocket
    public boolean isOpen() {
        return this.mWebSocketInterceptor.c();
    }

    @Override // com.bytedance.http.WebSocket
    public int queueSize() {
        return this.mWebSocketInterceptor.d();
    }

    @Override // com.bytedance.http.Call
    public HttpRequest request() {
        return this.mOriginalRequest;
    }

    @Override // com.bytedance.http.WebSocket
    public boolean send(String str) {
        return this.mWebSocketInterceptor.a(str);
    }

    @Override // com.bytedance.http.WebSocket
    public boolean send(byte[] bArr) {
        return this.mWebSocketInterceptor.a(bArr);
    }
}
