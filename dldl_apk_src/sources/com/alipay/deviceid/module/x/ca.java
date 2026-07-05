package com.alipay.deviceid.module.x;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class ca {
    public static boolean a(String str) {
        if (str != null) {
            try {
                int length = str.length();
                if (length != 0) {
                    for (int i = 0; i < length; i++) {
                        if (!Character.isWhitespace(str.charAt(i))) {
                            return false;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return true;
    }
}
