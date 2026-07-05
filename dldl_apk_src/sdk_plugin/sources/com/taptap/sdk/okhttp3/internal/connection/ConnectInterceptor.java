package com.taptap.sdk.okhttp3.internal.connection;

import com.taptap.sdk.okhttp3.Interceptor;
import com.taptap.sdk.okhttp3.OkHttpClient;
import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.Response;
import com.taptap.sdk.okhttp3.internal.http.RealInterceptorChain;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class ConnectInterceptor implements Interceptor {
    public final OkHttpClient client;

    public ConnectInterceptor(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    @Override // com.taptap.sdk.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request request = realInterceptorChain.request();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation();
        return realInterceptorChain.proceed(request, streamAllocation, streamAllocation.newStream(this.client, chain, !request.method().equals("GET")), streamAllocation.connection());
    }
}
