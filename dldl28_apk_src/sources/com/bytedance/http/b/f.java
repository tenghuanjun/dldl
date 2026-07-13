package com.bytedance.http.b;

import com.bytedance.dns.DnsResolver;
import com.bytedance.http.HttpDispatcher;
import com.bytedance.http.HttpUrl;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.CancellationException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes2.dex */
public class f {
    private static final HostnameVerifier h = new HostnameVerifier() { // from class: com.bytedance.http.b.f$$ExternalSyntheticLambda0
        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            return f.a(str, sSLSession);
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DnsResolver f393a;
    private final SSLSocketFactory b;
    private final HostnameVerifier c;
    private final boolean d;
    private final boolean e;
    private com.bytedance.http.b.a f;
    private volatile boolean g = false;

    public static class a implements X509TrustManager {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f394a = true;
        private final boolean b;

        public a(boolean z, boolean z2) {
            this.b = z2;
        }

        @Override // javax.net.ssl.X509TrustManager
        public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            throw new CertificateException("this trust manager cannot be used as server-side trust manager");
        }

        @Override // javax.net.ssl.X509TrustManager
        public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (x509CertificateArr == null) {
                throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
            }
            if (x509CertificateArr.length <= 0) {
                throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
            }
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
                trustManagerFactory.init((KeyStore) null);
                for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                    if (!this.b) {
                        ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
                    }
                }
            } catch (Exception e) {
                if (!this.f394a) {
                    throw new CertificateException(e);
                }
                c.b("Ignore certificate eror for ip direct");
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public final X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public f(HttpDispatcher httpDispatcher) {
        this.f393a = httpDispatcher.resolver();
        this.b = httpDispatcher.socketFactory();
        this.c = httpDispatcher.hostnameVerifier();
        this.d = httpDispatcher.logger();
        this.e = httpDispatcher.ignoreCertificateVerify();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(String str, SSLSession sSLSession) {
        if (g.c(str) && str.equals(sSLSession.getPeerHost())) {
            return true;
        }
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
    }

    public final com.bytedance.http.b.a a() {
        return this.f;
    }

    public final com.bytedance.http.b.a a(HttpUrl httpUrl) throws NoSuchAlgorithmException, KeyManagementException {
        com.bytedance.http.b.a hVar;
        com.bytedance.http.b.a hVar2;
        HostnameVerifier hostnameVerifier;
        if (this.g) {
            throw new CancellationException("Canceled");
        }
        String strScheme = httpUrl.scheme();
        strScheme.hashCode();
        switch (strScheme) {
            case "ws":
            case "wss":
                URI uri = httpUrl.uri();
                if (httpUrl.isWSS()) {
                    if (httpUrl.isIpDirect() || this.e) {
                        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                        sSLContext.init(null, new TrustManager[]{new a(true, this.e)}, new SecureRandom());
                        hVar2 = new h(uri, sSLContext.getSocketFactory());
                    } else {
                        SSLSocketFactory sSLSocketFactory = this.b;
                        if (sSLSocketFactory == null || this.c == null) {
                            hVar = new h(uri);
                        } else {
                            hVar2 = new h(uri, sSLSocketFactory);
                        }
                    }
                    this.f = hVar2;
                    return this.f;
                }
                hVar = new h(uri);
                this.f = hVar;
                return this.f;
            case "http":
            case "https":
                URL url = new URL(httpUrl.toString());
                if (!httpUrl.isHttps()) {
                    hVar = new b(url);
                } else {
                    if (httpUrl.isIpDirect() || this.e) {
                        SSLContext sSLContext2 = SSLContext.getInstance("TLSv1.2");
                        sSLContext2.init(null, new TrustManager[]{new a(true, this.e)}, new SecureRandom());
                        hVar2 = new b(url, sSLContext2.getSocketFactory(), h);
                        this.f = hVar2;
                        return this.f;
                    }
                    SSLSocketFactory sSLSocketFactory2 = this.b;
                    if (sSLSocketFactory2 != null && (hostnameVerifier = this.c) != null) {
                        this.f = new b(url, sSLSocketFactory2, hostnameVerifier);
                        return this.f;
                    }
                    hVar = new b(url);
                }
                this.f = hVar;
                return this.f;
            default:
                throw new IllegalArgumentException("unsupported protocol: " + httpUrl.scheme());
        }
    }

    public final DnsResolver b() {
        return this.f393a;
    }

    public final void c() {
        this.g = true;
        com.bytedance.http.b.a aVar = this.f;
        if (aVar != null) {
            aVar.c();
        }
    }

    public final boolean d() {
        return this.g;
    }

    public final boolean e() {
        return this.d;
    }
}
