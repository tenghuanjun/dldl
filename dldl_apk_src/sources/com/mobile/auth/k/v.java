package com.mobile.auth.k;

import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class v {
    public static String a() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
    }

    public static String a(long j) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(j));
    }
}
