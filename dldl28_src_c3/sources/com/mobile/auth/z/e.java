package com.mobile.auth.z;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class e {
    private static volatile e b;
    public Context a;
    private ExecutorService c = Executors.newSingleThreadExecutor();

    private e() {
    }

    public static e a() {
        try {
            if (b == null) {
                synchronized (e.class) {
                    if (b == null) {
                        b = new e();
                    }
                }
            }
            return b;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static String a(Context context, String str, String str2) {
        try {
            return f.a(context, str, str2);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void a(int i, d dVar, String str) {
        try {
            t.e("type:" + i + "\nmsg:" + str);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultCode", 1);
                jSONObject.put("resultMsg", str);
                jSONObject.put("resultData", "");
                jSONObject.put("seq", "");
                dVar.onResult(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void a(boolean z) {
        try {
            t.a(z);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static boolean a(Context context) {
        try {
            if (g.a(context)) {
                if (g.c(context)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        try {
            return f.a(context, str, str2, str3);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static String b() {
        try {
            return p.b();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void b(Context context) {
        try {
            g.b(context);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static boolean b(String str) {
        try {
            if (!str.equalsIgnoreCase("ali.wosms.cn") && !str.equalsIgnoreCase("m.zzx.cnklog.com") && !str.equalsIgnoreCase("msv6.wosms.cn") && !str.equalsIgnoreCase("test.wosms.cn")) {
                p.c = "msv6.wosms.cn";
                return false;
            }
            p.c = str;
            return true;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static String c() {
        return "phoneinfo";
    }

    public static void c(Context context) {
        try {
            g.d(context);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static String d() {
        try {
            String str = p.c;
            if (((str == null || str.length() == 0 || str.trim().length() == 0 || "null".equals(str)) ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
                p.c = "msv6.wosms.cn";
            }
            return p.c;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static boolean d(Context context) {
        try {
            int iB = v.b(context);
            return iB == 0 || iB == 1;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static String e() {
        return "auth.wosms.cn";
    }

    public static void e(Context context) {
        try {
            f.a(context);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void f() {
        try {
            n.a = false;
            n.b = false;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void g() {
        try {
            q.a().b();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.lang.String r7) {
        /*
            r6 = this;
            android.content.Context r0 = r6.a     // Catch: java.lang.Throwable -> L90
            if (r0 != 0) goto L7
            java.lang.String r7 = "sdk 未初始化, context 为空"
            return r7
        L7:
            java.lang.String r0 = r7.toLowerCase()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            int r1 = r0.hashCode()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r1) {
                case -903629273: goto L3f;
                case 107902: goto L35;
                case 3528965: goto L2b;
                case 93029116: goto L21;
                case 909712337: goto L17;
                default: goto L16;
            }     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
        L16:
            goto L49
        L17:
            java.lang.String r1 = "packagename"
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            if (r0 == 0) goto L49
            r0 = 1
            goto L4a
        L21:
            java.lang.String r1 = "appid"
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            if (r0 == 0) goto L49
            r0 = 0
            goto L4a
        L2b:
            java.lang.String r1 = "sha1"
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            if (r0 == 0) goto L49
            r0 = 3
            goto L4a
        L35:
            java.lang.String r1 = "md5"
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            if (r0 == 0) goto L49
            r0 = 2
            goto L4a
        L3f:
            java.lang.String r1 = "sha256"
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            if (r0 == 0) goto L49
            r0 = 4
            goto L4a
        L49:
            r0 = -1
        L4a:
            if (r0 == 0) goto L77
            if (r0 == r5) goto L6c
            if (r0 == r4) goto L5d
            if (r0 == r3) goto L5d
            if (r0 != r2) goto L55
            goto L5d
        L55:
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            java.lang.String r0 = "no info"
            r7.<init>(r0)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            throw r7     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
        L5d:
            android.content.Context r0 = r6.a     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            java.lang.String r1 = r0.getPackageName()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            java.lang.String r7 = r7.toLowerCase()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            java.lang.String r7 = com.mobile.auth.z.v.a(r0, r1, r7)     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            return r7
        L6c:
            android.content.Context r7 = r6.a     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            android.content.Context r7 = r7.getApplicationContext()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            java.lang.String r7 = r7.getPackageName()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            return r7
        L77:
            java.lang.String r7 = com.mobile.auth.z.u.c()     // Catch: java.lang.Exception -> L7c java.lang.Throwable -> L90
            return r7
        L7c:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L90
            java.lang.String r1 = "no info:"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L90
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L90
            r0.append(r7)     // Catch: java.lang.Throwable -> L90
            java.lang.String r7 = r0.toString()     // Catch: java.lang.Throwable -> L90
            return r7
        L90:
            r7 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r7)
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.z.e.a(java.lang.String):java.lang.String");
    }
}
