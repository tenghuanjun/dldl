package com.mobile.auth.k;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jiguang.h5.PermissionUtils;
import com.mobile.auth.g.b;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class o {
    private static final String a = "o";
    private static o b;
    private Context c;

    private o(Context context) {
        this.c = context.getApplicationContext();
    }

    public static o a(Context context) {
        if (b == null) {
            b = new o(context);
        }
        return b;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.k.o.a(java.lang.String):java.lang.String");
    }

    @SuppressLint({"MissingPermission"})
    public String a() {
        try {
            b.C0068b c0068bB = com.mobile.auth.g.b.a().b();
            return c0068bB.g(c0068bB.e());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String a(boolean z) {
        TelephonyManager telephonyManager;
        b.C0068b c0068bB = com.mobile.auth.g.b.a().b();
        String strH = c0068bB.h(c0068bB.e());
        if (TextUtils.isEmpty(strH) && t.b(this.c) && (telephonyManager = (TelephonyManager) this.c.getSystemService("phone")) != null) {
            strH = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(strH) && k.a(this.c, PermissionUtils.PERMISSION_READ_PHONE_STATE) && t.e()) {
                String subscriberId = null;
                try {
                    subscriberId = telephonyManager.getSubscriberId();
                } catch (Exception unused) {
                    f.a(a, "getOperator失败");
                }
                if (!TextUtils.isEmpty(subscriberId) && subscriberId.length() >= 5) {
                    strH = subscriberId.substring(0, 5);
                }
            }
        }
        f.b(a, "operator: " + strH);
        return TextUtils.isEmpty(strH) ? z ? "0" : "" : a(strH);
    }

    public String b() {
        try {
            b.C0068b c0068bB = com.mobile.auth.g.b.a().b();
            String strG = c0068bB.g((c0068bB.e() + 1) % 2);
            return strG == null ? "" : strG;
        } catch (Exception unused) {
            return "";
        }
    }

    @SuppressLint({"MissingPermission"})
    public String c() {
        b.C0068b c0068bB = com.mobile.auth.g.b.a().b();
        return c0068bB.a(c0068bB.e());
    }

    public String d() {
        TelephonyManager telephonyManager = (TelephonyManager) this.c.getSystemService("phone");
        if (telephonyManager == null) {
            return "0";
        }
        String simOperator = telephonyManager.getSimOperator();
        f.b(a, "SysOperType = " + simOperator);
        return a(simOperator);
    }
}
