package com.huya.mtp.hyns.volley;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.internal.http.RouteSelector;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpsSupportStack extends HurlStack {
    private static boolean isUseHttps;
    private static byte[] mCertificate;
    private static String mDomain;
    private static HashMap<String, byte[]> msCAMap = new HashMap<>();
    private OkHttpClient mOkHttpClient;

    @Override // com.android.volley.toolbox.HurlStack
    protected HttpURLConnection createConnection(URL url) throws IOException {
        OkUrlFactory okUrlFactory;
        if (IDataSource.SCHEME_HTTPS_TAG.equals(url.getProtocol())) {
            String host = url.getHost();
            if (msCAMap.get(host) != null && msCAMap.containsKey(host)) {
                okUrlFactory = new OkUrlFactory(getSafeOkHttpClient(url.toString(), msCAMap.get(host)));
            } else {
                okUrlFactory = new OkUrlFactory(getSafeOkHttpClient(url.toString()));
            }
            return okUrlFactory.open(url);
        }
        return super.createConnection(url);
    }

    private static OkHttpClient getSafeOkHttpClient(String str) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setHostnameVerifier(getSafeHostnameVerifier(str));
        return okHttpClient;
    }

    private static OkHttpClient getSafeOkHttpClient(String str, byte[] bArr) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setHostnameVerifier(getSafeHostnameVerifier(str));
        okHttpClient.setSslSocketFactory(getSafeSSLSocketFactory(bArr));
        return okHttpClient;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setHostnameVerifier(getHostnameVerifier());
        okHttpClient.setSslSocketFactory(getSSLSocketFactory());
        return okHttpClient;
    }

    private static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() { // from class: com.huya.mtp.hyns.volley.HttpsSupportStack.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        };
    }

    private static HostnameVerifier getSafeHostnameVerifier(final String str) {
        return new HostnameVerifier() { // from class: com.huya.mtp.hyns.volley.HttpsSupportStack.2
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str2, SSLSession sSLSession) {
                if (str.equals(str2)) {
                    return true;
                }
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(str2, sSLSession);
            }
        };
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        SSLContext sSLContext;
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.huya.mtp.hyns.volley.HttpsSupportStack.3
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        try {
            sSLContext = SSLContext.getInstance("TLS");
            try {
                sSLContext.init(null, trustManagerArr, new SecureRandom());
            } catch (KeyManagementException e) {
                e = e;
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (KeyManagementException e3) {
            e = e3;
            sSLContext = null;
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            sSLContext = null;
        }
        if (sSLContext == null) {
            return null;
        }
        return sSLContext.getSocketFactory();
    }

    private static SSLSocketFactory getSafeSSLSocketFactory(byte[] bArr) {
        SSLContext sSLContext;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bArr));
            try {
                Certificate certificateGenerateCertificate = certificateFactory.generateCertificate(bufferedInputStream);
                bufferedInputStream.close();
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", certificateGenerateCertificate);
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                sSLContext = SSLContext.getInstance(RouteSelector.TLS_V1, "AndroidOpenSSL");
                try {
                    sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                } catch (KeyManagementException e2) {
                    e = e2;
                    e.printStackTrace();
                } catch (KeyStoreException e3) {
                    e = e3;
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e4) {
                    e = e4;
                    e.printStackTrace();
                } catch (NoSuchProviderException e5) {
                    e = e5;
                    e.printStackTrace();
                } catch (CertificateException e6) {
                    e = e6;
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                bufferedInputStream.close();
                throw th;
            }
        } catch (IOException e7) {
            e = e7;
            sSLContext = null;
        } catch (KeyManagementException e8) {
            e = e8;
            sSLContext = null;
        } catch (KeyStoreException e9) {
            e = e9;
            sSLContext = null;
        } catch (NoSuchAlgorithmException e10) {
            e = e10;
            sSLContext = null;
        } catch (NoSuchProviderException e11) {
            e = e11;
            sSLContext = null;
        } catch (CertificateException e12) {
            e = e12;
            sSLContext = null;
        }
        if (sSLContext == null) {
            return null;
        }
        return sSLContext.getSocketFactory();
    }

    public static void setUseHttps(boolean z) {
        isUseHttps = z;
    }

    public static byte[] setCertificate(String str, byte[] bArr) {
        mDomain = str;
        mCertificate = bArr;
        return bArr;
    }

    public static void setMsCAMap(HashMap<String, byte[]> map) {
        msCAMap = map;
    }
}
