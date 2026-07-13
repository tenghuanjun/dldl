package com.tencent.open.a;

import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.open.log.SLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Version;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
class e implements com.tencent.open.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OkHttpClient f1041a;

    private void a(OkHttpClient.Builder builder) {
    }

    public e(String str) throws NoClassDefFoundError {
        a(str);
    }

    private void a(String str) {
        String strUserAgent = Version.userAgent();
        if (strUserAgent == null || !strUserAgent.startsWith("okhttp/3")) {
            throw new NoClassDefFoundError();
        }
        OkHttpClient.Builder builderAddInterceptor = new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)).connectTimeout(15000L, TimeUnit.MILLISECONDS).readTimeout(30000L, TimeUnit.MILLISECONDS).writeTimeout(30000L, TimeUnit.MILLISECONDS).cache(null).addInterceptor(new a(str));
        a(builderAddInterceptor);
        this.f1041a = builderAddInterceptor.build();
    }

    @Override // com.tencent.open.a.a
    public void a(long j, long j2) {
        if (j <= 0 || j2 <= 0) {
            return;
        }
        if (this.f1041a.connectTimeoutMillis() == j && this.f1041a.readTimeoutMillis() == j2) {
            return;
        }
        SLog.i("OkHttpServiceImpl", "setTimeout changed.");
        this.f1041a = this.f1041a.newBuilder().connectTimeout(j, TimeUnit.MILLISECONDS).readTimeout(j2, TimeUnit.MILLISECONDS).writeTimeout(j2, TimeUnit.MILLISECONDS).build();
    }

    @Override // com.tencent.open.a.a
    public g a(String str, String str2) throws IOException {
        SLog.i("OkHttpServiceImpl", "get.");
        if (!TextUtils.isEmpty(str2)) {
            int iIndexOf = str2.indexOf("?");
            if (iIndexOf == -1) {
                str = str + "?";
            } else if (iIndexOf != str.length() - 1) {
                str = str + "&";
            }
            str = str + str2;
        }
        return new d(this.f1041a.newCall(new Request.Builder().url(str).get().build()).execute(), str2.length());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map) throws IOException {
        SLog.i("OkHttpServiceImpl", "post data");
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    builder.add(str2, str3);
                }
            }
        }
        FormBody formBodyBuild = builder.build();
        return new d(this.f1041a.newCall(new Request.Builder().url(str).post(formBodyBuild).build()).execute(), (int) formBodyBuild.contentLength());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map, Map<String, byte[]> map2) throws IOException {
        if (map2 == null || map2.size() == 0) {
            return a(str, map);
        }
        SLog.i("OkHttpServiceImpl", "post data, has byte data");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    builder.addFormDataPart(str2, str3);
                }
            }
        }
        for (String str4 : map2.keySet()) {
            byte[] bArr = map2.get(str4);
            if (bArr != null && bArr.length > 0) {
                builder.addFormDataPart(str4, str4, RequestBody.create(MediaType.get("content/unknown"), bArr));
                SLog.w("OkHttpServiceImpl", "post byte data.");
            }
        }
        MultipartBody multipartBodyBuild = builder.build();
        return new d(this.f1041a.newCall(new Request.Builder().url(str).post(multipartBodyBuild).build()).execute(), (int) multipartBodyBuild.contentLength());
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class a implements Interceptor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1042a;

        public a(String str) {
            this.f1042a = str;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder().header(HttpHeaders.HEAD_KEY_USER_AGENT, this.f1042a).build());
        }
    }
}
