package com.volcengine.j;

import android.content.SharedPreferences;
import com.volcengine.common.SDKContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, SharedPreferences> f1149a = new ConcurrentHashMap();

    public static SharedPreferences a() {
        return a("vesdk");
    }

    public static SharedPreferences a(String str) {
        Map<String, SharedPreferences> map = f1149a;
        SharedPreferences sharedPreferences = map.get(str);
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        SharedPreferences sharedPreferences2 = SDKContext.getContext().getSharedPreferences(str, 0);
        map.put(str, sharedPreferences2);
        return sharedPreferences2;
    }
}
