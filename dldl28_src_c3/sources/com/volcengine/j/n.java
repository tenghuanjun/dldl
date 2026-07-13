package com.volcengine.j;

import android.content.SharedPreferences;
import com.volcengine.common.SDKContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class n {
    private static final Map<String, SharedPreferences> a = new ConcurrentHashMap();

    public static SharedPreferences a() {
        return a("vesdk");
    }

    public static SharedPreferences a(String str) {
        Map<String, SharedPreferences> map = a;
        SharedPreferences sharedPreferences = map.get(str);
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        SharedPreferences sharedPreferences2 = SDKContext.getContext().getSharedPreferences(str, 0);
        map.put(str, sharedPreferences2);
        return sharedPreferences2;
    }
}
