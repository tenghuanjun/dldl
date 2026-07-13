package com.mobile.auth.g;

import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.bun.miitmdid.x$;
import com.hjq.permissions.Permission;
import com.mobile.auth.m.c;
import com.mobile.auth.m.g;
import com.mobile.auth.m.m;
import com.tencent.connect.common.Constants;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a {
    private static a a;
    private static long b;
    private C0180a c = null;

    /* JADX INFO: renamed from: com.mobile.auth.g.a$a, reason: collision with other inner class name */
    public static class C0180a {
        private int a = -1;
        private int b = -1;

        public int a() {
            return this.b;
        }
    }

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private void a(Context context, boolean z) {
        if (Build.VERSION.SDK_INT < 22) {
            this.c.a = -1;
            return;
        }
        SubscriptionManager subscriptionManagerM = x$.ExternalSyntheticApiModelOutline0.m(context.getApplicationContext());
        if (subscriptionManagerM != null) {
            try {
                if (this.c.a == -1 && Build.VERSION.SDK_INT >= 24) {
                    this.c.b = x$.ExternalSyntheticApiModelOutline0.m();
                    c.b("UMCTelephonyManagement", "android 7.0及以上手机getDefaultDataSubscriptionId适配成功: dataSubId = " + this.c.b);
                    return;
                }
            } catch (Exception unused) {
                c.a("UMCTelephonyManagement", "android 7.0及以上手机getDefaultDataSubscriptionId适配失败");
            }
            try {
                Object objInvoke = subscriptionManagerM.getClass().getMethod("getDefaultDataSubId", null).invoke(subscriptionManagerM, null);
                if ((objInvoke instanceof Integer) || (objInvoke instanceof Long)) {
                    this.c.b = ((Integer) objInvoke).intValue();
                    c.b("UMCTelephonyManagement", "android 7.0以下手机getDefaultDataSubId适配成功: dataSubId = " + this.c.b);
                    return;
                }
            } catch (Exception unused2) {
                c.a("UMCTelephonyManagement", "readDefaultDataSubId-->getDefaultDataSubId 反射出错");
            }
            try {
                Object objInvoke2 = subscriptionManagerM.getClass().getMethod("getDefaultDataSubscriptionId", null).invoke(subscriptionManagerM, null);
                if ((objInvoke2 instanceof Integer) || (objInvoke2 instanceof Long)) {
                    this.c.b = ((Integer) objInvoke2).intValue();
                    c.b("UMCTelephonyManagement", "反射getDefaultDataSubscriptionId适配成功: dataSubId = " + this.c.b);
                }
            } catch (Exception unused3) {
                c.a("UMCTelephonyManagement", "getDefaultDataSubscriptionId-->getDefaultDataSubscriptionId 反射出错");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0089 A[PHI: r11
  0x0089: PHI (r11v4 android.database.Cursor) = (r11v2 android.database.Cursor), (r11v6 android.database.Cursor) binds: [B:21:0x0087, B:18:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "sim_id"
            java.lang.String r1 = "_id"
            java.lang.String r2 = "readSimInfoDbStart"
            java.lang.String r3 = "UMCTelephonyManagement"
            com.mobile.auth.m.c.b(r3, r2)
            java.lang.String r2 = "content://telephony/siminfo"
            android.net.Uri r5 = android.net.Uri.parse(r2)
            android.content.ContentResolver r4 = r11.getContentResolver()
            r11 = 0
            java.lang.String[] r6 = new java.lang.String[]{r1, r0}     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            java.lang.String r7 = "sim_id>=?"
            java.lang.String r2 = "0"
            java.lang.String[] r8 = new java.lang.String[]{r2}     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            r9 = 0
            android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            if (r11 == 0) goto L7f
        L29:
            boolean r2 = r11.moveToNext()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            if (r2 == 0) goto L7f
            int r2 = r11.getColumnIndex(r0)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r2 = r11.getInt(r2)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r4 = r11.getColumnIndex(r1)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r4 = r11.getInt(r4)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            com.mobile.auth.g.a$a r5 = r10.c     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r5 = com.mobile.auth.g.a.C0180a.b(r5)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            r6 = -1
            if (r5 != r6) goto L71
            com.mobile.auth.g.a$a r5 = r10.c     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r5 = com.mobile.auth.g.a.C0180a.a(r5)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            if (r5 == r6) goto L71
            com.mobile.auth.g.a$a r5 = r10.c     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r5 = com.mobile.auth.g.a.C0180a.a(r5)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            if (r5 != r4) goto L71
            com.mobile.auth.g.a$a r5 = r10.c     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            com.mobile.auth.g.a.C0180a.a(r5, r2)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            r5.<init>()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            java.lang.String r6 = "通过读取sim db获取数据流量卡的卡槽值："
            r5.append(r6)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            r5.append(r2)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            com.mobile.auth.m.c.b(r3, r5)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
        L71:
            com.mobile.auth.g.a$a r5 = r10.c     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            int r5 = com.mobile.auth.g.a.C0180a.b(r5)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            if (r5 != r2) goto L29
            com.mobile.auth.g.a$a r2 = r10.c     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            com.mobile.auth.g.a.C0180a.b(r2, r4)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L92
            goto L29
        L7f:
            if (r11 == 0) goto L8c
            goto L89
        L82:
            java.lang.String r0 = "readSimInfoDb error"
            com.mobile.auth.m.c.a(r3, r0)     // Catch: java.lang.Throwable -> L92
            if (r11 == 0) goto L8c
        L89:
            r11.close()
        L8c:
            java.lang.String r11 = "readSimInfoDbEnd"
            com.mobile.auth.m.c.b(r3, r11)
            return
        L92:
            r0 = move-exception
            if (r11 == 0) goto L98
            r11.close()
        L98:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.g.a.b(android.content.Context):void");
    }

    private int c(Context context) {
        TelephonyManager telephonyManager;
        if (!g.a(context, Permission.READ_PHONE_STATE) || (telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone")) == null) {
            return -1;
        }
        if (!m.d()) {
            return x$.ExternalSyntheticApiModelOutline0.m(telephonyManager);
        }
        try {
            Method method = telephonyManager.getClass().getMethod("getDataNetworkType", Integer.TYPE);
            c.b("UMCTelephonyManagement", "data dataNetworkType defaultDataSubId = " + this.c.b);
            int iIntValue = ((Integer) method.invoke(telephonyManager, Integer.valueOf(this.c.b))).intValue();
            c.b("UMCTelephonyManagement", "data dataNetworkType ---------" + iIntValue);
            if (iIntValue != 0 || Build.VERSION.SDK_INT < 24) {
                return iIntValue;
            }
            c.b("UMCTelephonyManagement", "data dataNetworkType ---->=N " + iIntValue);
            return x$.ExternalSyntheticApiModelOutline0.m(telephonyManager);
        } catch (Exception e) {
            c.a("UMCTelephonyManagement", "data dataNetworkType ----反射出错-----");
            e.printStackTrace();
            return -1;
        }
    }

    public String a(Context context) {
        switch (c(context)) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "1";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "2";
            case 13:
            case 18:
            case 19:
                return "3";
            case 20:
                return Constants.VIA_TO_TYPE_QZONE;
            default:
                return "0";
        }
    }

    public void a(Context context, boolean z, boolean z2) {
        long jCurrentTimeMillis = System.currentTimeMillis() - b;
        if (jCurrentTimeMillis >= 5000 || jCurrentTimeMillis <= 0) {
            this.c = new C0180a();
            if (z2) {
                a(context, z);
                if (m.e() && m.d()) {
                    c.b("UMCTelephonyManagement", "华为手机兼容性处理");
                    if (this.c.b == 0 || this.c.b == 1) {
                        if (this.c.a == -1) {
                            C0180a c0180a = this.c;
                            c0180a.a = c0180a.b;
                        }
                        this.c.b = -1;
                    }
                    if (this.c.a != -1 || this.c.b != -1) {
                        b(context);
                    }
                }
                b = System.currentTimeMillis();
            }
        }
    }

    public C0180a b() {
        C0180a c0180a = this.c;
        return c0180a == null ? new C0180a() : c0180a;
    }
}
