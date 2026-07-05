package com.alipay.deviceid.module.x;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class cb {
    public static String a(Context context, String str, String str2) {
        if (context == null || e.a(str) || e.a(str2)) {
            return null;
        }
        try {
            String strA = r.a(context, str, str2, "");
            if (e.a(strA)) {
                return null;
            }
            return i.b(i.a(), strA);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (e.a(str) || e.a(str2) || context == null) {
            return;
        }
        try {
            String strA = i.a(i.a(), str3);
            HashMap map = new HashMap();
            map.put(str2, strA);
            r.a(context, str, map);
        } catch (Exception unused) {
        }
    }
}
