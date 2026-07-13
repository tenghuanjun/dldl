package com.mobile.auth.w;

import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, HostnameVerifier> f804a = new ConcurrentHashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0194 A[Catch: all -> 0x0186, TryCatch #6 {all -> 0x0186, blocks: (B:93:0x0182, B:97:0x018a, B:99:0x018f, B:101:0x0194, B:103:0x0199), top: B:131:0x0182, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0199 A[Catch: all -> 0x0186, TRY_LEAVE, TryCatch #6 {all -> 0x0186, blocks: (B:93:0x0182, B:97:0x018a, B:99:0x018f, B:101:0x0194, B:103:0x0199), top: B:131:0x0182, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01ac A[Catch: all -> 0x01a8, TryCatch #8 {all -> 0x01a8, blocks: (B:109:0x01a4, B:113:0x01ac, B:115:0x01b1, B:117:0x01b6, B:119:0x01bb), top: B:135:0x01a4, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01b1 A[Catch: all -> 0x01a8, TryCatch #8 {all -> 0x01a8, blocks: (B:109:0x01a4, B:113:0x01ac, B:115:0x01b1, B:117:0x01b6, B:119:0x01bb), top: B:135:0x01a4, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01b6 A[Catch: all -> 0x01a8, TryCatch #8 {all -> 0x01a8, blocks: (B:109:0x01a4, B:113:0x01ac, B:115:0x01b1, B:117:0x01b6, B:119:0x01bb), top: B:135:0x01a4, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01bb A[Catch: all -> 0x01a8, TRY_LEAVE, TryCatch #8 {all -> 0x01a8, blocks: (B:109:0x01a4, B:113:0x01ac, B:115:0x01b1, B:117:0x01b6, B:119:0x01bb), top: B:135:0x01a4, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0182 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0154 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x015c A[Catch: all -> 0x0158, TryCatch #21 {all -> 0x0158, blocks: (B:75:0x0154, B:79:0x015c, B:81:0x0161, B:83:0x0166, B:85:0x016b), top: B:139:0x0154, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0161 A[Catch: all -> 0x0158, TryCatch #21 {all -> 0x0158, blocks: (B:75:0x0154, B:79:0x015c, B:81:0x0161, B:83:0x0166, B:85:0x016b), top: B:139:0x0154, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0166 A[Catch: all -> 0x0158, TryCatch #21 {all -> 0x0158, blocks: (B:75:0x0154, B:79:0x015c, B:81:0x0161, B:83:0x0166, B:85:0x016b), top: B:139:0x0154, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x016b A[Catch: all -> 0x0158, TRY_LEAVE, TryCatch #21 {all -> 0x0158, blocks: (B:75:0x0154, B:79:0x015c, B:81:0x0161, B:83:0x0166, B:85:0x016b), top: B:139:0x0154, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x018a A[Catch: all -> 0x0186, TryCatch #6 {all -> 0x0186, blocks: (B:93:0x0182, B:97:0x018a, B:99:0x018f, B:101:0x0194, B:103:0x0199), top: B:131:0x0182, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x018f A[Catch: all -> 0x0186, TryCatch #6 {all -> 0x0186, blocks: (B:93:0x0182, B:97:0x018a, B:99:0x018f, B:101:0x0194, B:103:0x0199), top: B:131:0x0182, outer: #4 }] */
    /* JADX WARN: Type inference failed for: r10v0, types: [int] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.mobile.auth.w.c] */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v25, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(com.mobile.auth.w.c r8, int r9, int r10) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.w.a.a(com.mobile.auth.w.c, int, int):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x013d A[Catch: all -> 0x0139, TryCatch #19 {all -> 0x0139, blocks: (B:104:0x0135, B:108:0x013d, B:110:0x0142, B:112:0x0147, B:114:0x014c), top: B:134:0x0135, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0142 A[Catch: all -> 0x0139, TryCatch #19 {all -> 0x0139, blocks: (B:104:0x0135, B:108:0x013d, B:110:0x0142, B:112:0x0147, B:114:0x014c), top: B:134:0x0135, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0147 A[Catch: all -> 0x0139, TryCatch #19 {all -> 0x0139, blocks: (B:104:0x0135, B:108:0x013d, B:110:0x0142, B:112:0x0147, B:114:0x014c), top: B:134:0x0135, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x014c A[Catch: all -> 0x0139, TRY_LEAVE, TryCatch #19 {all -> 0x0139, blocks: (B:104:0x0135, B:108:0x013d, B:110:0x0142, B:112:0x0147, B:114:0x014c), top: B:134:0x0135, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0113 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0135 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f0 A[Catch: all -> 0x00ec, TryCatch #7 {all -> 0x00ec, blocks: (B:70:0x00e8, B:74:0x00f0, B:76:0x00f5, B:78:0x00fa, B:80:0x00ff), top: B:126:0x00e8, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f5 A[Catch: all -> 0x00ec, TryCatch #7 {all -> 0x00ec, blocks: (B:70:0x00e8, B:74:0x00f0, B:76:0x00f5, B:78:0x00fa, B:80:0x00ff), top: B:126:0x00e8, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fa A[Catch: all -> 0x00ec, TryCatch #7 {all -> 0x00ec, blocks: (B:70:0x00e8, B:74:0x00f0, B:76:0x00f5, B:78:0x00fa, B:80:0x00ff), top: B:126:0x00e8, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ff A[Catch: all -> 0x00ec, TRY_LEAVE, TryCatch #7 {all -> 0x00ec, blocks: (B:70:0x00e8, B:74:0x00f0, B:76:0x00f5, B:78:0x00fa, B:80:0x00ff), top: B:126:0x00e8, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x011b A[Catch: all -> 0x0117, TryCatch #16 {all -> 0x0117, blocks: (B:88:0x0113, B:92:0x011b, B:94:0x0120, B:96:0x0125, B:98:0x012a), top: B:130:0x0113, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0120 A[Catch: all -> 0x0117, TryCatch #16 {all -> 0x0117, blocks: (B:88:0x0113, B:92:0x011b, B:94:0x0120, B:96:0x0125, B:98:0x012a), top: B:130:0x0113, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0125 A[Catch: all -> 0x0117, TryCatch #16 {all -> 0x0117, blocks: (B:88:0x0113, B:92:0x011b, B:94:0x0120, B:96:0x0125, B:98:0x012a), top: B:130:0x0113, outer: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012a A[Catch: all -> 0x0117, TRY_LEAVE, TryCatch #16 {all -> 0x0117, blocks: (B:88:0x0113, B:92:0x011b, B:94:0x0120, B:96:0x0125, B:98:0x012a), top: B:130:0x0113, outer: #15 }] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.mobile.auth.w.c] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v26, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v0, types: [int] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v23, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(com.mobile.auth.w.c r6, int r7, int r8, int r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.w.a.a(com.mobile.auth.w.c, int, int, int):java.lang.String");
    }

    public static String a(String str) {
        try {
            try {
                return new URL(str).getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private static HostnameVerifier b(String str) {
        try {
            String strA = a(str);
            if (f804a != null && !TextUtils.isEmpty(strA) && f804a.containsKey(strA)) {
                return f804a.get(strA);
            }
            HostnameVerifier hostnameVerifier = new HostnameVerifier() { // from class: com.mobile.auth.w.a.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str2, SSLSession sSLSession) {
                    try {
                        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str2, sSLSession);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                            return false;
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                            return false;
                        }
                    }
                }
            };
            if (f804a == null) {
                f804a = new ConcurrentHashMap<>();
            }
            f804a.put(strA, hostnameVerifier);
            return hostnameVerifier;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
