package com.bytedance.sdk.openadsdk.api.plugin.b;

import com.alipay.sdk.util.i;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c {
    private c() {
    }

    private static final class a {
        private static final c a = new c();
    }

    public static c a() {
        return a.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x007c A[EXC_TOP_SPLITTER, PHI: r5
  0x007c: PHI (r5v4 javax.net.ssl.HttpsURLConnection) = (r5v3 javax.net.ssl.HttpsURLConnection), (r5v8 javax.net.ssl.HttpsURLConnection) binds: [B:30:0x0089, B:20:0x007a] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(boolean r4, java.lang.String r5, byte[] r6) throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            javax.net.ssl.X509TrustManager r5 = r3.b()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            javax.net.ssl.SSLSocketFactory r5 = r3.a(r5)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r5)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            java.net.URLConnection r5 = r1.openConnection()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            r1 = 5000(0x1388, float:7.006E-42)
            r5.setConnectTimeout(r1)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            com.bytedance.sdk.openadsdk.api.plugin.b.a r1 = com.bytedance.sdk.openadsdk.api.plugin.b.a.a     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r5.setHostnameVerifier(r1)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            if (r4 == 0) goto L48
            if (r6 == 0) goto L48
            int r4 = r6.length     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            if (r4 == 0) goto L48
            r4 = 1
            r5.setDoOutput(r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            int r4 = r6.length     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r5.setFixedLengthStreamingMode(r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            java.io.OutputStream r1 = r5.getOutputStream()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r4.<init>(r1)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r4.write(r6)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r4.flush()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r4.close()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            java.lang.String r4 = "POST"
            r5.setRequestMethod(r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            goto L4d
        L48:
            java.lang.String r4 = "GET"
            r5.setRequestMethod(r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
        L4d:
            int r4 = r5.getResponseCode()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r6 = 200(0xc8, float:2.8E-43)
            if (r4 < r6) goto L7a
            r6 = 300(0x12c, float:4.2E-43)
            if (r4 >= r6) goto L7a
            java.lang.String r4 = "Content-Type"
            java.lang.String r4 = r5.getHeaderField(r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            java.lang.String r6 = "utf-8"
            java.lang.String r4 = a(r4, r6)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            java.io.InputStream r1 = r5.getInputStream()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r1 = a(r1, r2)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r6.<init>(r1, r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            if (r5 == 0) goto L79
            r5.disconnect()     // Catch: java.lang.Exception -> L79
        L79:
            return r6
        L7a:
            if (r5 == 0) goto L8c
        L7c:
            r5.disconnect()     // Catch: java.lang.Exception -> L8c
            goto L8c
        L80:
            r4 = move-exception
            goto L86
        L82:
            r4 = move-exception
            goto L8f
        L84:
            r4 = move-exception
            r5 = r0
        L86:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L8d
            if (r5 == 0) goto L8c
            goto L7c
        L8c:
            return r0
        L8d:
            r4 = move-exception
            r0 = r5
        L8f:
            if (r0 == 0) goto L94
            r0.disconnect()     // Catch: java.lang.Exception -> L94
        L94:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.openadsdk.api.plugin.b.c.a(boolean, java.lang.String, byte[]):java.lang.String");
    }

    private static byte[] a(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            return null;
        }
        if (i < 1) {
            i = 1;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[i];
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 != -1) {
                byteArrayOutputStream.write(bArr, 0, i2);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private static String a(String str, String str2) {
        if (str != null) {
            String[] strArrSplit = str.split(i.b, 0);
            for (int i = 1; i < strArrSplit.length; i++) {
                String[] strArrSplit2 = strArrSplit[i].trim().split("=", 0);
                if (strArrSplit2.length == 2 && strArrSplit2[0].equals("charset")) {
                    return strArrSplit2[1];
                }
            }
        }
        return str2;
    }

    private SSLSocketFactory a(X509TrustManager x509TrustManager) throws IOException {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new IOException("No System TLS", e);
        }
    }

    private X509TrustManager b() throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw new IOException("No System TLS", e);
        }
    }
}
