package com.alipay.security.mobile.module.b;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class d {
    public static synchronized void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.security.mobile.module.a.a.a(str)) {
            if (!com.alipay.security.mobile.module.a.a.a(str2) && context != null) {
                try {
                    String strA = com.alipay.security.mobile.module.a.a.c.a(com.alipay.security.mobile.module.a.a.c.a(), str3);
                    HashMap map = new HashMap();
                    map.put(str2, strA);
                    e.a(context, str, map);
                } catch (Throwable unused) {
                }
            }
        }
    }
}
