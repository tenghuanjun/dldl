package com.alipay.deviceid.module.x;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class bx {
    private static String a = "";

    public static void a(Context context, String str) {
        a(context, "webrtcurl", str);
    }

    public static void a(Context context, String str, long j) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("alipay_device_id_settings", 0).edit();
            if (editorEdit != null) {
                editorEdit.putString("vkey_valid" + str, i.a(i.a(), String.valueOf(j)));
                editorEdit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(Context context, String str, String str2) {
        cb.a(context, "alipay_device_id_settings", str, str2);
    }

    public static void a(Context context, boolean z) {
        a(context, "log_switch", z ? "1" : "0");
    }

    public static boolean a(Context context) {
        String strA = cb.a(context, "alipay_device_id_settings", "log_switch");
        return strA != null && "1".equals(strA);
    }

    public static synchronized String b(Context context) {
        SharedPreferences.Editor editorEdit;
        if (e.a(a)) {
            String strA = r.a(context, "alipay_device_id_tags", "random", "");
            a = strA;
            if (e.a(strA)) {
                String strA2 = h.a(UUID.randomUUID().toString());
                a = strA2;
                if (strA2 != null && (editorEdit = context.getSharedPreferences("alipay_device_id_tags", 0).edit()) != null) {
                    editorEdit.putString("random", strA2);
                    editorEdit.commit();
                }
            }
        }
        return a;
    }
}
