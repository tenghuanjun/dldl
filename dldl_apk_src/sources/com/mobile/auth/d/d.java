package com.mobile.auth.d;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d {
    private static final String a = d.class.getSimpleName();

    /* JADX WARN: Removed duplicated region for block: B:64:0x0104 A[Catch: Exception -> 0x0100, all -> 0x0126, TryCatch #0 {Exception -> 0x0100, blocks: (B:60:0x00fc, B:64:0x0104, B:66:0x0109), top: B:88:0x00fc }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0109 A[Catch: Exception -> 0x0100, all -> 0x0126, TRY_LEAVE, TryCatch #0 {Exception -> 0x0100, blocks: (B:60:0x00fc, B:64:0x0104, B:66:0x0109), top: B:88:0x00fc }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r16, java.lang.String r17, android.net.Network r18) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.d.d.a(android.content.Context, java.lang.String, android.net.Network):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0282 A[Catch: all -> 0x0357, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x0357, blocks: (B:86:0x01f2, B:88:0x01f7, B:95:0x023a, B:97:0x023f, B:104:0x0282, B:112:0x02c6, B:120:0x030b, B:128:0x0350), top: B:154:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02c6 A[Catch: all -> 0x0357, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x0357, blocks: (B:86:0x01f2, B:88:0x01f7, B:95:0x023a, B:97:0x023f, B:104:0x0282, B:112:0x02c6, B:120:0x030b, B:128:0x0350), top: B:154:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x030b A[Catch: all -> 0x0357, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x0357, blocks: (B:86:0x01f2, B:88:0x01f7, B:95:0x023a, B:97:0x023f, B:104:0x0282, B:112:0x02c6, B:120:0x030b, B:128:0x0350), top: B:154:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0350 A[Catch: all -> 0x0357, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x0357, blocks: (B:86:0x01f2, B:88:0x01f7, B:95:0x023a, B:97:0x023f, B:104:0x0282, B:112:0x02c6, B:120:0x030b, B:128:0x0350), top: B:154:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011f A[Catch: all -> 0x01fc, IOException -> 0x0244, SSLPeerUnverifiedException -> 0x0288, UnknownHostException -> 0x02cd, SocketTimeoutException -> 0x0312, TryCatch #10 {SocketTimeoutException -> 0x0312, UnknownHostException -> 0x02cd, SSLPeerUnverifiedException -> 0x0288, IOException -> 0x0244, all -> 0x01fc, blocks: (B:12:0x0022, B:14:0x002d, B:16:0x0031, B:17:0x0035, B:19:0x003d, B:21:0x005f, B:22:0x0062, B:24:0x0079, B:26:0x009a, B:29:0x00a2, B:31:0x00aa, B:33:0x00b4, B:35:0x00cf, B:38:0x010d, B:40:0x0114, B:42:0x011a, B:44:0x0123, B:46:0x0148, B:48:0x0150, B:50:0x015d, B:52:0x0163, B:54:0x016c, B:53:0x0168, B:55:0x0187, B:57:0x018f, B:43:0x011f, B:25:0x0097, B:18:0x0038), top: B:154:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023a A[Catch: all -> 0x0357, TRY_ENTER, TryCatch #11 {all -> 0x0357, blocks: (B:86:0x01f2, B:88:0x01f7, B:95:0x023a, B:97:0x023f, B:104:0x0282, B:112:0x02c6, B:120:0x030b, B:128:0x0350), top: B:154:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x023f A[Catch: all -> 0x0357, PHI: r1 r3 r11
  0x023f: PHI (r1v20 ??) = (r1v15 ??), (r1v16 ??), (r1v17 ??), (r1v18 ??), (r1v21 ??) binds: [B:96:0x023d, B:105:0x0285, B:113:0x02c9, B:121:0x030e, B:129:0x0353] A[DONT_GENERATE, DONT_INLINE]
  0x023f: PHI (r3v12 ??) = (r3v7 ??), (r3v8 ??), (r3v9 ??), (r3v10 ??), (r3v13 ??) binds: [B:96:0x023d, B:105:0x0285, B:113:0x02c9, B:121:0x030e, B:129:0x0353] A[DONT_GENERATE, DONT_INLINE]
  0x023f: PHI (r11v7 java.lang.String) = 
  (r11v2 java.lang.String)
  (r11v3 java.lang.String)
  (r11v4 java.lang.String)
  (r11v5 java.lang.String)
  (r11v8 java.lang.String)
 binds: [B:96:0x023d, B:105:0x0285, B:113:0x02c9, B:121:0x030e, B:129:0x0353] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #11 {all -> 0x0357, blocks: (B:86:0x01f2, B:88:0x01f7, B:95:0x023a, B:97:0x023f, B:104:0x0282, B:112:0x02c6, B:120:0x030b, B:128:0x0350), top: B:154:0x0022 }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v50 */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r22, java.lang.String r23, java.lang.String r24, android.net.Network r25, java.lang.String r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 883
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
