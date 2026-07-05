package com.taptap.sdk.themis.lite;

import android.util.Base64;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Base64Util {
    public static String decode(String str) {
        return new String(Base64.decode(str, 0));
    }
}
