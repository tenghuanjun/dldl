package com.mobile.auth.k;

import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
class r {
    private static char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(a[(b >>> 4) & 15]);
            sb.append(a[b & 15]);
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }
}
