package com.bytedance.http.a;

import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;
import com.lzy.okgo.model.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Interceptor {
    @Override // com.bytedance.http.Interceptor
    public final HttpResponse intercept(Interceptor.Chain chain) throws IOException {
        HttpRequest httpRequestRequest = chain.request();
        HttpRequest.Builder builderNewBuilder = httpRequestRequest.newBuilder();
        if (httpRequestRequest.body() != null && httpRequestRequest.body().length > 0) {
            byte[] bArrBody = httpRequestRequest.body();
            if ("gzip".equalsIgnoreCase(httpRequestRequest.headers().get(HttpHeaders.HEAD_KEY_CONTENT_ENCODING))) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bArrBody);
                bArrBody = byteArrayOutputStream.toByteArray();
                builderNewBuilder.body(bArrBody);
                byteArrayOutputStream.close();
                gZIPOutputStream.close();
            }
            if (bArrBody.length > 0) {
                builderNewBuilder.addHeader(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Integer.toString(bArrBody.length));
            }
        }
        StringBuilder sb = new StringBuilder();
        Map mapCookies = httpRequestRequest.cookies();
        boolean z = true;
        for (String str : mapCookies.keySet()) {
            if (z) {
                sb.append(str);
                sb.append("=");
                sb.append((String) mapCookies.get(str));
                z = false;
            } else {
                sb.append(";");
                sb.append(str);
                sb.append("=");
                sb.append((String) mapCookies.get(str));
            }
        }
        if (sb.length() > 0) {
            builderNewBuilder.addCookie(HttpHeaders.HEAD_KEY_COOKIE, sb.toString());
        }
        return chain.proceed(builderNewBuilder.build());
    }

    @Override // com.bytedance.http.Interceptor
    public final String name() {
        return "bridge_int";
    }
}
