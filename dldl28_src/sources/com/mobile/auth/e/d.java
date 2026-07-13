package com.mobile.auth.e;

import android.text.TextUtils;
import com.mobile.auth.m.k;

/* JADX INFO: loaded from: classes3.dex */
class d {
    static int a(int i) {
        return k.a("sso_config_xf", "maxFailedLogTimes", i);
    }

    static String a(String str) {
        String strA = k.a("sso_config_xf", "config_host", (String) null);
        return TextUtils.isEmpty(strA) ? str : strA;
    }

    static boolean a() {
        return System.currentTimeMillis() >= k.a("sso_config_xf", "client_valid", 0L);
    }

    static boolean a(boolean z) {
        return "1".equals(k.a("sso_config_xf", "CLOSE_IPV4_LIST", !z ? "0" : "1"));
    }

    static int b(int i) {
        return k.a("sso_config_xf", "pauseTime", i);
    }

    static String b(String str) {
        String strA = k.a("sso_config_xf", "https_get_phone_scrip_host", (String) null);
        return TextUtils.isEmpty(strA) ? str : strA;
    }

    static boolean b(boolean z) {
        return "1".equals(k.a("sso_config_xf", "CLOSE_IPV6_LIST", !z ? "0" : "1"));
    }

    static String c(String str) {
        String strA = k.a("sso_config_xf", "logHost", "");
        return TextUtils.isEmpty(strA) ? str : strA;
    }

    static boolean c(boolean z) {
        String str = !z ? "0" : "1";
        return "1".equals(k.a("sso_config_xf", "CLOSE_M008_APPID_LIST", str)) || "1".equals(k.a("sso_config_xf", "CLOSE_M008_SDKVERSION_LIST", str));
    }

    static boolean d(boolean z) {
        return k.a("sso_config_xf", "CLOSE_FRIEND_WAPKS", z ? "CU" : "").contains("CU");
    }

    static boolean e(boolean z) {
        return k.a("sso_config_xf", "CLOSE_FRIEND_WAPKS", z ? "CT" : "").contains("CT");
    }

    static boolean f(boolean z) {
        return "1".equals(k.a("sso_config_xf", "CLOSE_LOGS_VERSION", z ? "1" : "0"));
    }
}
