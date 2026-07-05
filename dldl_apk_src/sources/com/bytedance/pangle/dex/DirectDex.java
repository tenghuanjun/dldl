package com.bytedance.pangle.dex;

import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class DirectDex {
    private static native boolean native_init(int i);

    public static native Object native_load_direct_dex(String str);

    static {
        System.loadLibrary("zeus_direct_dex");
        native_init(Build.VERSION.SDK_INT);
    }
}
