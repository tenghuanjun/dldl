package com.sq.webview.util;

import android.net.SSLCertificateSocketFactory;
import android.os.Build;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;
import okhttp3.internal.tls.OkHostnameVerifier;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SniSSLSocketFactory extends SSLSocketFactory {
    private final String mHost;

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return null;
    }

    public SniSSLSocketFactory(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        this.mHost = httpUrl != null ? httpUrl.host() : null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
        String str = this.mHost;
        if (str == null) {
            str = host;
        }
        InetAddress inetAddress = socket.getInetAddress();
        if (autoClose) {
            socket.close();
        }
        SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0);
        SSLSocket sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket(inetAddress, port);
        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        if (Build.VERSION.SDK_INT >= 17) {
            WebLogUtil.v("Setting SNI hostname[%s] for %s", str, host);
            sSLCertificateSocketFactory.setHostname(sSLSocket, str);
        } else {
            WebLogUtil.wtf("No documented SNI support on Android < 4.2, trying with reflection", new Object[0]);
            try {
                sSLSocket.getClass().getMethod("setHostname", String.class).invoke(sSLSocket, str);
            } catch (Exception e) {
                WebLogUtil.e(e, "SNI not usable", new Object[0]);
            }
        }
        return sSLSocket;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return equal(this.mHost, ((SniSSLSocketFactory) o).mHost);
    }

    public int hashCode() {
        String str = this.mHost;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public static class SniHostnameVerifier implements HostnameVerifier {
        private final HostnameVerifier mDefault;
        private final String mHost;

        public SniHostnameVerifier(String url, HostnameVerifier hostnameVerifier) {
            HttpUrl httpUrl = HttpUrl.parse(url);
            this.mHost = httpUrl != null ? httpUrl.host() : null;
            this.mDefault = hostnameVerifier == null ? OkHostnameVerifier.INSTANCE : hostnameVerifier;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String hostname, SSLSession session) {
            String str = this.mHost;
            if (str != null && !str.isEmpty()) {
                return this.mDefault.verify(this.mHost, session);
            }
            return this.mDefault.verify(hostname, session);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SniHostnameVerifier sniHostnameVerifier = (SniHostnameVerifier) o;
            if (SniSSLSocketFactory.equal(this.mHost, sniHostnameVerifier.mHost)) {
                return this.mDefault.equals(sniHostnameVerifier.mDefault);
            }
            return false;
        }

        public int hashCode() {
            String str = this.mHost;
            return ((str != null ? str.hashCode() : 0) * 31) + this.mDefault.hashCode();
        }
    }

    public static X509TrustManager platformTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("No System TLS", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
