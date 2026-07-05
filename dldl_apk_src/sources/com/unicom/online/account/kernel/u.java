package com.unicom.online.account.kernel;

import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class u {
    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = "0".concat(String.valueOf(hexString));
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
