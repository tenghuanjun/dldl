package com.mobile.auth.d;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes3.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f517a = "d";

    /* JADX WARN: Removed duplicated region for block: B:60:0x00f9 A[Catch: Exception -> 0x00f5, all -> 0x011b, TryCatch #7 {Exception -> 0x00f5, blocks: (B:56:0x00f1, B:60:0x00f9, B:62:0x00fe), top: B:98:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fe A[Catch: Exception -> 0x00f5, all -> 0x011b, TRY_LEAVE, TryCatch #7 {Exception -> 0x00f5, blocks: (B:56:0x00f1, B:60:0x00f9, B:62:0x00fe), top: B:98:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r16, java.lang.String r17, android.net.Network r18) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.d.d.a(android.content.Context, java.lang.String, android.net.Network):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0127, code lost:
    
        if (r1 != 0) goto L63;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x024b A[Catch: all -> 0x0298, TRY_ENTER, TRY_LEAVE, TryCatch #19 {all -> 0x0298, blocks: (B:61:0x0124, B:63:0x0129, B:80:0x017a, B:88:0x01bf, B:96:0x0205, B:104:0x024b, B:112:0x0291), top: B:135:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0291 A[Catch: all -> 0x0298, TRY_ENTER, TRY_LEAVE, TryCatch #19 {all -> 0x0298, blocks: (B:61:0x0124, B:63:0x0129, B:80:0x017a, B:88:0x01bf, B:96:0x0205, B:104:0x024b, B:112:0x0291), top: B:135:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0129 A[Catch: all -> 0x0298, PHI: r1 r2 r5 r8
  0x0129: PHI (r1v14 ??) = (r1v8 ??), (r1v9 ??), (r1v10 ??), (r1v11 ??), (r1v12 ??), (r1v17 ??) binds: [B:81:0x017d, B:89:0x01c2, B:97:0x0208, B:105:0x024e, B:113:0x0294, B:62:0x0127] A[DONT_GENERATE, DONT_INLINE]
  0x0129: PHI (r2v13 ??) = (r2v7 ??), (r2v8 ??), (r2v9 ??), (r2v10 ??), (r2v11 ??), (r2v14 ??) binds: [B:81:0x017d, B:89:0x01c2, B:97:0x0208, B:105:0x024e, B:113:0x0294, B:62:0x0127] A[DONT_GENERATE, DONT_INLINE]
  0x0129: PHI (r5v3 ??) = 
  (r5v7 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY])
  (r5v9 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY])
  (r5v11 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY])
  (r5v13 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY])
  (r5v15 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY])
  (r5v4 ??)
 binds: [B:81:0x017d, B:89:0x01c2, B:97:0x0208, B:105:0x024e, B:113:0x0294, B:62:0x0127] A[DONT_GENERATE, DONT_INLINE]
  0x0129: PHI (r8v10 java.lang.String) = 
  (r8v3 java.lang.String)
  (r8v4 java.lang.String)
  (r8v5 java.lang.String)
  (r8v6 java.lang.String)
  (r8v7 java.lang.String)
  (r8v11 java.lang.String)
 binds: [B:81:0x017d, B:89:0x01c2, B:97:0x0208, B:105:0x024e, B:113:0x0294, B:62:0x0127] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #19 {all -> 0x0298, blocks: (B:61:0x0124, B:63:0x0129, B:80:0x017a, B:88:0x01bf, B:96:0x0205, B:104:0x024b, B:112:0x0291), top: B:135:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x017a A[Catch: all -> 0x0298, TRY_ENTER, TRY_LEAVE, TryCatch #19 {all -> 0x0298, blocks: (B:61:0x0124, B:63:0x0129, B:80:0x017a, B:88:0x01bf, B:96:0x0205, B:104:0x024b, B:112:0x0291), top: B:135:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01bf A[Catch: all -> 0x0298, TRY_ENTER, TRY_LEAVE, TryCatch #19 {all -> 0x0298, blocks: (B:61:0x0124, B:63:0x0129, B:80:0x017a, B:88:0x01bf, B:96:0x0205, B:104:0x024b, B:112:0x0291), top: B:135:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0205 A[Catch: all -> 0x0298, TRY_ENTER, TRY_LEAVE, TryCatch #19 {all -> 0x0298, blocks: (B:61:0x0124, B:63:0x0129, B:80:0x017a, B:88:0x01bf, B:96:0x0205, B:104:0x024b, B:112:0x0291), top: B:135:0x001f }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r15, java.lang.String r16, java.lang.String r17, android.net.Network r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.d.d.a(android.content.Context, java.lang.String, java.lang.String, android.net.Network, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String a(Context context, HttpsURLConnection httpsURLConnection) {
        try {
            Map headerFields = httpsURLConnection.getHeaderFields();
            List list = (List) headerFields.get("Log-Level");
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    String str = (String) list.get(0);
                    if (!TextUtils.isEmpty(str)) {
                        com.mobile.auth.c.e.b(context, str);
                    }
                }
            }
            List list2 = (List) headerFields.get("p-ikgx");
            if (list2 != null && !list2.isEmpty()) {
                String str2 = (String) list2.get(0);
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return null;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return null;
                }
            }
        }
        return null;
    }

    public static final HostnameVerifier a() {
        try {
            return new HostnameVerifier() { // from class: com.mobile.auth.d.d.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    try {
                        return HttpsURLConnection.getDefaultHostnameVerifier().verify("id6.me", sSLSession);
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
