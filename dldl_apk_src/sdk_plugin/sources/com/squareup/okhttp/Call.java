package com.squareup.okhttp;

import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.OkHeaders;
import java.io.IOException;
import okio.BufferedSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Call {
    volatile boolean canceled;
    private final OkHttpClient client;
    private final Dispatcher dispatcher;
    HttpEngine engine;
    private boolean executed;
    private int redirectionCount;
    private Request request;

    Call(OkHttpClient okHttpClient, Dispatcher dispatcher, Request request) {
        this.client = okHttpClient;
        this.dispatcher = dispatcher;
        this.request = request;
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        Response response = getResponse();
        this.engine.releaseConnection();
        if (response != null) {
            return response;
        }
        throw new IOException("Canceled");
    }

    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.dispatcher.enqueue(new AsyncCall(callback));
    }

    public void cancel() {
        this.canceled = true;
        HttpEngine httpEngine = this.engine;
        if (httpEngine != null) {
            httpEngine.disconnect();
        }
    }

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        private AsyncCall(Callback callback) {
            super("OkHttp %s", Call.this.request.urlString());
            this.responseCallback = callback;
        }

        String host() {
            return Call.this.request.url().getHost();
        }

        Request request() {
            return Call.this.request;
        }

        Object tag() {
            return Call.this.request.tag();
        }

        Call get() {
            return Call.this;
        }

        @Override // com.squareup.okhttp.internal.NamedRunnable
        protected void execute() {
            IOException e;
            Response response;
            boolean z = true;
            try {
                try {
                    response = Call.this.getResponse();
                } finally {
                    Call.this.dispatcher.finished(this);
                }
            } catch (IOException e2) {
                e = e2;
                z = false;
            }
            try {
                if (Call.this.canceled) {
                    this.responseCallback.onFailure(Call.this.request, new IOException("Canceled"));
                } else {
                    Call.this.engine.releaseConnection();
                    this.responseCallback.onResponse(response);
                }
            } catch (IOException e3) {
                e = e3;
                if (!z) {
                    this.responseCallback.onFailure(Call.this.request, e);
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.squareup.okhttp.Response getResponse() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Call.getResponse():com.squareup.okhttp.Response");
    }

    private static class RealResponseBody extends ResponseBody {
        private final Response response;
        private final BufferedSource source;

        RealResponseBody(Response response, BufferedSource bufferedSource) {
            this.response = response;
            this.source = bufferedSource;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public MediaType contentType() {
            String strHeader = this.response.header("Content-Type");
            if (strHeader != null) {
                return MediaType.parse(strHeader);
            }
            return null;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public long contentLength() {
            return OkHeaders.contentLength(this.response);
        }

        @Override // com.squareup.okhttp.ResponseBody
        public BufferedSource source() {
            return this.source;
        }
    }
}
