package com.alipay.security.mobile.module.b;

import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class b {
    public static String a(String str) {
        String strA;
        try {
            strA = f.a(str);
        } catch (Throwable unused) {
            strA = "";
        }
        if (!com.alipay.security.mobile.module.a.a.a(strA)) {
            return strA;
        }
        return c.a(".SystemConfig" + File.separator + str);
    }
}
