package com.tencent.open.a;

import android.os.Build;
import android.text.TextUtils;
import com.tencent.open.log.SLog;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class a {
    private static a a;
    private OkHttpClient b;
    private com.tencent.open.utils.g c;

    protected a() {
        b();
    }

    private void b() {
        OkHttpClient.Builder builderAddInterceptor = new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)).connectTimeout(15000L, TimeUnit.MILLISECONDS).readTimeout(com.igexin.push.config.c.k, TimeUnit.MILLISECONDS).writeTimeout(com.igexin.push.config.c.k, TimeUnit.MILLISECONDS).cache(null).addInterceptor(new C0114a("AndroidSDK_" + Build.VERSION.SDK + "_" + Build.DEVICE + "_" + Build.VERSION.RELEASE));
        a(builderAddInterceptor);
        this.b = builderAddInterceptor.build();
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        a.c();
        return a;
    }

    public void a(com.tencent.open.utils.g gVar) {
        this.c = gVar;
        c();
    }

    private void c() {
        com.tencent.open.utils.g gVar = this.c;
        if (gVar == null) {
            return;
        }
        int iA = gVar.a("Common_HttpConnectionTimeout");
        if (iA == 0) {
            iA = 15000;
        }
        int iA2 = this.c.a("Common_SocketConnectionTimeout");
        if (iA2 == 0) {
            iA2 = 30000;
        }
        a(iA, iA2);
    }

    public void a(long j, long j2) {
        if (this.b.connectTimeoutMillis() == j && this.b.readTimeoutMillis() == j2) {
            return;
        }
        SLog.i("openSDK_LOG.OpenHttpService", "setTimeout changed.");
        this.b = this.b.newBuilder().connectTimeout(j, TimeUnit.MILLISECONDS).readTimeout(j2, TimeUnit.MILLISECONDS).writeTimeout(j2, TimeUnit.MILLISECONDS).build();
    }

    public b a(String str, Map<String, String> map) throws IOException {
        if (map == null || map.isEmpty()) {
            return a(str, "");
        }
        StringBuilder sb = new StringBuilder("");
        for (String str2 : map.keySet()) {
            String str3 = map.get(str2);
            if (str3 != null) {
                sb.append(URLEncoder.encode(str2, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(str3, "UTF-8"));
                sb.append(com.alipay.sdk.sys.a.b);
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return a(str, sb.toString());
    }

    public b a(String str, String str2) throws IOException {
        SLog.i("openSDK_LOG.OpenHttpService", "get.");
        if (!TextUtils.isEmpty(str2)) {
            int iIndexOf = str2.indexOf("?");
            if (iIndexOf == -1) {
                str = str + "?";
            } else if (iIndexOf != str.length() - 1) {
                str = str + com.alipay.sdk.sys.a.b;
            }
            str = str + str2;
        }
        return new b(this.b.newCall(new Request.Builder().url(str).get().build()).execute(), str2.length());
    }

    public b b(String str, Map<String, String> map) throws IOException {
        SLog.i("openSDK_LOG.OpenHttpService", "post data");
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
        return new b(this.b.newCall(new Request.Builder().url(str).post(formBodyBuild).build()).execute(), (int) formBodyBuild.contentLength());
    }

    public b a(String str, Map<String, String> map, Map<String, byte[]> map2) throws IOException {
        if (map2 == null || map2.size() == 0) {
            return b(str, map);
        }
        SLog.i("openSDK_LOG.OpenHttpService", "post data, has byte data");
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
                SLog.w("openSDK_LOG.OpenHttpService", "post byte data.");
            }
        }
        MultipartBody multipartBodyBuild = builder.build();
        return new b(this.b.newCall(new Request.Builder().url(str).post(multipartBodyBuild).build()).execute(), (int) multipartBodyBuild.contentLength());
    }

    private void a(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT < 16 || Build.VERSION.SDK_INT >= 21) {
            return;
        }
        try {
            c cVar = new c();
            TrustManager trustManagerA = cVar.a();
            if (trustManagerA == null) {
                return;
            }
            builder.sslSocketFactory(cVar, (X509TrustManager) trustManagerA);
            SLog.i("openSDK_LOG.OpenHttpService", "enableTls2: enabled.");
        } catch (KeyManagementException e) {
            SLog.e("openSDK_LOG.OpenHttpService", "enableTls2: failed.", e);
        } catch (KeyStoreException e2) {
            SLog.e("openSDK_LOG.OpenHttpService", "enableTls2: failed.", e2);
        } catch (NoSuchAlgorithmException e3) {
            SLog.e("openSDK_LOG.OpenHttpService", "enableTls2: failed.", e3);
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    private static class C0114a implements Interceptor {
        private final String a;

        public C0114a(String str) {
            this.a = str;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder().header("User-Agent", this.a).build());
        }
    }
}
