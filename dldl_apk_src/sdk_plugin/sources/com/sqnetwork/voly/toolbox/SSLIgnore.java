package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.VolleyLog;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SSLIgnore {
    private static final TrustManager[] trustAllCerts = {new X509TrustManager() { // from class: com.sqnetwork.voly.toolbox.SSLIgnore.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }};

    static /* synthetic */ boolean lambda$ignoreSSL$0(String str, SSLSession sSLSession) {
        return true;
    }

    private static SSLSocketFactory fakeTSLSocketFactory() {
        SSLContext sSLContext;
        try {
            sSLContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            VolleyLog.e("获取SSLContext(TLS)失败, " + e.getMessage(), new Object[0]);
            try {
                sSLContext = SSLContext.getInstance("SSL");
            } catch (NoSuchAlgorithmException e2) {
                VolleyLog.e("获取SSLContext(SSL)失败, " + e2.getMessage(), new Object[0]);
                sSLContext = null;
            }
        }
        if (sSLContext == null) {
            return null;
        }
        try {
            sSLContext.init(null, trustAllCerts, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (KeyManagementException e3) {
            VolleyLog.e("初始化SSLContext失败, " + e3.getMessage(), new Object[0]);
            return null;
        }
    }

    private static X509TrustManager fakeX509TrustManager() {
        return (X509TrustManager) trustAllCerts[0];
    }

    public static OkHttpClient.Builder ignoreSSL(OkHttpClient.Builder builder) {
        SSLSocketFactory sSLSocketFactoryFakeTSLSocketFactory = fakeTSLSocketFactory();
        if (sSLSocketFactoryFakeTSLSocketFactory != null) {
            builder.sslSocketFactory(sSLSocketFactoryFakeTSLSocketFactory, fakeX509TrustManager());
            builder.hostnameVerifier(new HostnameVerifier() { // from class: com.sqnetwork.voly.toolbox.-$$Lambda$SSLIgnore$O3d7n7xPUewU1Ym-s8BLun_sR54
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return SSLIgnore.lambda$ignoreSSL$0(str, sSLSession);
                }
            });
        } else {
            VolleyLog.e("获取SSLSocketFactory失败, 无法忽略SSL", new Object[0]);
        }
        return builder;
    }
}
