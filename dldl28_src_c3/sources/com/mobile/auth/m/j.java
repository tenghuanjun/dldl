package com.mobile.auth.m;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class j {
    private static j b;
    private final Context a;

    private j(Context context) {
        this.a = context;
    }

    public static j a() {
        return b;
    }

    public static void a(Context context) {
        b = new j(context);
    }

    private String b(String str) {
        str.hashCode();
        switch (str) {
            case "46000":
            case "46002":
            case "46004":
            case "46007":
                c.a("SIMUtils", "中国移动");
                return "1";
            case "46001":
            case "46006":
            case "46009":
                c.a("SIMUtils", "中国联通");
                return "2";
            case "46003":
            case "46005":
            case "46011":
                c.a("SIMUtils", "中国电信");
                return "3";
            default:
                return "0";
        }
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = c();
        }
        return b(str);
    }

    public String b() {
        try {
            int iA = com.mobile.auth.g.a.a().b().a();
            return iA >= 0 ? Integer.toString(iA) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        String simOperator = telephonyManager.getSimOperator();
        c.b("SIMUtils", "SysOperator= " + simOperator);
        return simOperator;
    }
}
