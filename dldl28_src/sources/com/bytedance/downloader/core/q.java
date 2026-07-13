package com.bytedance.downloader.core;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.CancellationException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HostnameVerifier f364a = new HostnameVerifier() { // from class: com.bytedance.downloader.core.q$$ExternalSyntheticLambda0
        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            return q.a(str, sSLSession);
        }
    };

    public static class a implements X509TrustManager {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f365a = true;
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
                if (!this.f365a) {
                    throw new CertificateException(e);
                }
                h.b("Ignore certificate eror for ip direct");
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public final X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static String a() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length < 5) {
                return "";
            }
            return stackTrace[4].getClassName() + "." + stackTrace[4].getMethodName() + "-" + stackTrace[4].getLineNumber();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str, boolean[] zArr) throws Throwable {
        BufferedInputStream bufferedInputStream;
        int i;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream2);
                try {
                    byte[] bArr = new byte[4096];
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    do {
                        int i2 = bufferedInputStream.read(bArr);
                        if (i2 > 0) {
                            messageDigest.update(bArr, 0, i2);
                        }
                        if (i2 == -1) {
                            break;
                        }
                    } while (!zArr[0]);
                    if (zArr[0]) {
                        throw new CancellationException("");
                    }
                    byte[] bArrDigest = messageDigest.digest();
                    StringBuilder sb = new StringBuilder();
                    for (byte b : bArrDigest) {
                        sb.append(Integer.toString((b & UByte.MAX_VALUE) + 256, 16).substring(1));
                    }
                    String string = sb.toString();
                    a(fileInputStream2);
                    a(bufferedInputStream);
                    return string;
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        a(fileInputStream);
                        a(bufferedInputStream);
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        a(fileInputStream);
                        a(bufferedInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    a(fileInputStream);
                    a(bufferedInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                bufferedInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
        }
    }

    public static String a(byte[] bArr) {
        try {
            StringBuilder sb = new StringBuilder(new BigInteger(1, MessageDigest.getInstance("md5").digest(bArr)).toString(16));
            for (int i = 0; i < 32 - sb.length(); i++) {
                sb.insert(0, "0");
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            throw new RuntimeException("");
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean a(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    if (!a(file2)) {
                        return false;
                    }
                } else if (!file2.delete()) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean a(String str) {
        return str == null || "".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(String str, SSLSession sSLSession) {
        if (a(str) || !str.equals(sSLSession.getPeerHost())) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
        return true;
    }

    public static boolean b(String str) {
        return !a(str);
    }

    public static String c(String str) {
        int iIndexOf;
        if (a(str)) {
            return "";
        }
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("failed to connect to") || (iIndexOf = lowerCase.indexOf("/")) <= 0) {
            return "";
        }
        int i = iIndexOf + 1;
        for (int i2 = i; i2 < lowerCase.length(); i2++) {
            char cCharAt = lowerCase.charAt(i2);
            if (!Character.isDigit(cCharAt) && cCharAt != '.') {
                return lowerCase.substring(i, i2);
            }
        }
        return "";
    }
}
