package com.alipay.sdk.tid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.util.c;
import java.util.Random;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class b {
    public static final String a = "alipay_tid_storage";
    public static final String b = "tidinfo";
    public static final String c = "upgraded_from_db";
    public static final String d = "tid";
    public static final String e = "client_key";
    public static final String f = "timestamp";
    public static final String g = "vimei";
    public static final String h = "vimsi";
    private static Context i;
    private static b o;
    private String j;
    private String k;
    private long l;
    private String m;
    private String n;
    private boolean p = false;

    private void o() {
    }

    public static synchronized b a(Context context) {
        if (o == null) {
            c.b("TidStorage", "getInstance");
            o = new b();
        }
        if (i == null) {
            o.b(context);
        }
        return o;
    }

    private void b(Context context) {
        if (context != null) {
            c.b("TidStorage", "TidStorage.initialize context != null");
            i = context.getApplicationContext();
        }
        if (this.p) {
            return;
        }
        this.p = true;
        k();
        l();
    }

    private void k() {
        com.alipay.sdk.tid.a aVar;
        Throwable th;
        com.alipay.sdk.tid.a aVar2;
        Context context = i;
        if (context == null) {
            return;
        }
        if (a.d(a, c)) {
            c.b("TidStorage", "transferTidFromOldDb: already migrated. returning");
            return;
        }
        try {
            c.b("TidStorage", "transferTidFromOldDb: tid from db: ");
            aVar = new com.alipay.sdk.tid.a(context);
            try {
                String strB = com.alipay.sdk.util.a.a(context).b();
                String strA = com.alipay.sdk.util.a.a(context).a();
                String strA2 = aVar.a(strB, strA);
                String strB2 = aVar.b(strB, strA);
                if (!TextUtils.isEmpty(strA2) && !TextUtils.isEmpty(strB2)) {
                    c.b("TidStorage", "transferTidFromOldDb: tid from db is " + strA2 + ", " + strB2);
                    a(strA2, strB2);
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    c.a(th);
                    if (aVar != null) {
                    }
                    c.b("TidStorage", "transferTidFromOldDb: removing database table");
                    aVar2 = new com.alipay.sdk.tid.a(context);
                    try {
                        aVar2.a();
                        aVar2.close();
                    } catch (Throwable th3) {
                        th = th3;
                        aVar = aVar2;
                        try {
                            c.a(th);
                        } finally {
                            if (aVar != null) {
                                aVar.close();
                            }
                        }
                    }
                    a.a(a, c, "updated", false);
                } finally {
                    if (aVar != null) {
                        aVar.close();
                    }
                }
            }
        } catch (Throwable th4) {
            aVar = null;
            th = th4;
        }
        aVar.close();
        try {
            c.b("TidStorage", "transferTidFromOldDb: removing database table");
            aVar2 = new com.alipay.sdk.tid.a(context);
            aVar2.a();
            aVar2.close();
        } catch (Throwable th5) {
            th = th5;
        }
        a.a(a, c, "updated", false);
    }

    public String a() {
        c.b("TidStorage", "TidStorage.getTid " + this.j);
        return this.j;
    }

    public String b() {
        c.b("TidStorage", "TidStorage.getClientKey " + this.k);
        return this.k;
    }

    public String c() {
        c.b("TidStorage", "TidStorage.getVirtualImei " + this.m);
        return this.m;
    }

    public String d() {
        c.b("TidStorage", "TidStorage.getVirtualImsi " + this.n);
        return this.n;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void l() {
        /*
            r9 = this;
            java.lang.String r0 = ""
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r2 = 0
            java.lang.String r3 = "alipay_tid_storage"
            java.lang.String r4 = "tidinfo"
            r5 = 1
            java.lang.String r3 = com.alipay.sdk.tid.b.a.a(r3, r4, r5)     // Catch: java.lang.Exception -> L52
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L52
            if (r4 != 0) goto L4e
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L52
            r4.<init>(r3)     // Catch: java.lang.Exception -> L52
            java.lang.String r3 = "tid"
            java.lang.String r3 = r4.optString(r3, r0)     // Catch: java.lang.Exception -> L52
            java.lang.String r5 = "client_key"
            java.lang.String r5 = r4.optString(r5, r0)     // Catch: java.lang.Exception -> L4b
            java.lang.String r6 = "timestamp"
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L48
            long r6 = r4.optLong(r6, r7)     // Catch: java.lang.Exception -> L48
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> L48
            java.lang.String r6 = "vimei"
            java.lang.String r6 = r4.optString(r6, r0)     // Catch: java.lang.Exception -> L48
            java.lang.String r7 = "vimsi"
            java.lang.String r0 = r4.optString(r7, r0)     // Catch: java.lang.Exception -> L46
            goto L5a
        L46:
            r0 = move-exception
            goto L56
        L48:
            r0 = move-exception
            r6 = r2
            goto L56
        L4b:
            r0 = move-exception
            r5 = r2
            goto L55
        L4e:
            r0 = r2
            r5 = r0
            r6 = r5
            goto L5b
        L52:
            r0 = move-exception
            r3 = r2
            r5 = r3
        L55:
            r6 = r5
        L56:
            com.alipay.sdk.util.c.a(r0)
            r0 = r2
        L5a:
            r2 = r3
        L5b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TidStorage.load "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = " "
            r3.append(r4)
            r3.append(r5)
            r3.append(r4)
            r3.append(r1)
            r3.append(r4)
            r3.append(r6)
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "TidStorage"
            com.alipay.sdk.util.c.b(r4, r3)
            boolean r3 = r9.a(r2, r5, r6, r0)
            if (r3 == 0) goto L95
            r9.m()
            goto La3
        L95:
            r9.j = r2
            r9.k = r5
            long r1 = r1.longValue()
            r9.l = r1
            r9.m = r6
            r9.n = r0
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.b.l():void");
    }

    private boolean a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    public boolean e() {
        return TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.k) || TextUtils.isEmpty(this.m) || TextUtils.isEmpty(this.n);
    }

    private void m() {
        this.j = "";
        this.k = f();
        this.l = System.currentTimeMillis();
        this.m = n();
        this.n = n();
        a.b(a, b);
    }

    private String n() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    public String f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    public void g() {
        c.b("TidStorage", "TidStorage.delete " + String.format("TidStorage::delete > %s，%s，%s，%s，%s", this.j, this.k, Long.valueOf(this.l), this.m, this.n));
        m();
    }

    public boolean h() {
        return e();
    }

    public Long i() {
        return Long.valueOf(this.l);
    }

    public void a(String str, String str2) {
        c.b("TidStorage", "TidStorage.save " + ("tid=" + str + ",clientKey=" + str2));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.j = str;
        this.k = str2;
        this.l = System.currentTimeMillis();
        p();
        o();
    }

    private void a(String str, String str2, String str3, String str4, Long l) {
        if (a(str, str2, str3, str4)) {
            return;
        }
        this.j = str;
        this.k = str2;
        this.m = str3;
        this.n = str4;
        if (l == null) {
            this.l = System.currentTimeMillis();
        } else {
            this.l = l.longValue();
        }
        p();
    }

    private void p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.j);
            jSONObject.put(e, this.k);
            jSONObject.put("timestamp", this.l);
            jSONObject.put(g, this.m);
            jSONObject.put(h, this.n);
            a.a(a, b, jSONObject.toString(), true);
        } catch (Exception e2) {
            c.a(e2);
        }
    }

    public static class a {
        private static String a() {
            return "!@#23457";
        }

        public static boolean a(String str, String str2) {
            if (b.i == null) {
                return false;
            }
            return b.i.getSharedPreferences(str, 0).contains(str2);
        }

        public static void b(String str, String str2) {
            if (b.i == null) {
                return;
            }
            b.i.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }

        public static String c(String str, String str2) {
            return a(str, str2, true);
        }

        public static boolean d(String str, String str2) {
            if (b.i == null) {
                return false;
            }
            return b.i.getSharedPreferences(str, 0).contains(str2);
        }

        public static String a(String str, String str2, boolean z) {
            String strB;
            if (b.i == null) {
                return null;
            }
            String string = b.i.getSharedPreferences(str, 0).getString(str2, null);
            if (TextUtils.isEmpty(string) || !z) {
                strB = string;
            } else {
                String strB2 = b();
                strB = com.alipay.sdk.encrypt.b.b(string, strB2);
                if (TextUtils.isEmpty(strB)) {
                    strB = com.alipay.sdk.encrypt.b.b(string, a());
                    if (!TextUtils.isEmpty(strB)) {
                        a(str, str2, strB, true);
                    }
                }
                if (TextUtils.isEmpty(strB)) {
                    String.format("LocalPreference::getLocalPreferences failed %s，%s", string, strB2);
                    c.b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences failed");
                }
            }
            c.b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences value " + string);
            return strB;
        }

        public static void a(String str, String str2, String str3) {
            a(str, str2, str3, true);
        }

        public static void a(String str, String str2, String str3, boolean z) {
            if (b.i == null) {
                return;
            }
            SharedPreferences sharedPreferences = b.i.getSharedPreferences(str, 0);
            if (z) {
                String strB = b();
                String strA = com.alipay.sdk.encrypt.b.a(str3, strB);
                if (TextUtils.isEmpty(strA)) {
                    String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, strB);
                }
                str3 = strA;
            }
            sharedPreferences.edit().putString(str2, str3).apply();
        }

        private static String b() {
            String packageName;
            try {
                packageName = b.i.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                c.a(th);
                packageName = "";
            }
            if (TextUtils.isEmpty(packageName)) {
                packageName = "unknow";
            }
            return (packageName + "00000000").substring(0, 8);
        }
    }
}
