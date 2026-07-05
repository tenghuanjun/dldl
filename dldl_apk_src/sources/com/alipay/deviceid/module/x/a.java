package com.alipay.deviceid.module.x;

import android.content.Context;
import java.util.Map;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_deviceid_1607;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class a {
    private Context a;
    private c b = c.a();
    private int c = 4;

    static {
        java2jni_do_not_delete_this_library_deviceid_1607.loadLibrary();
    }

    public a(Context context) {
        this.a = context;
    }

    public static native String a(Context context, String str);

    private native o b(Map<String, String> map);

    public final native int a(Map<String, String> map);
}
