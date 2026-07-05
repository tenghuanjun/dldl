package com.alipay.deviceid.module.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_deviceid_1607;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class bs {
    private int a;
    private Map<String, List<String>> b = new HashMap();

    static {
        java2jni_do_not_delete_this_library_deviceid_1607.loadLibrary();
    }

    public bs(int i) {
        this.a = 0;
        this.a = i;
    }

    public final native JSONArray a();

    public final native void a(String str, String str2);
}
