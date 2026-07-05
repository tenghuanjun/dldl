package com.bytedance.pangle.download;

import android.os.Handler;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    private static volatile b e;
    final Map<String, Long> a = new ConcurrentHashMap();
    public final Handler b = new Handler(Looper.getMainLooper());
    public final Map<String, Runnable> c = new ConcurrentHashMap();
    final Map<String, Runnable> d = new ConcurrentHashMap();

    private b() {
    }

    public static b a() {
        if (e == null) {
            synchronized (b.class) {
                if (e == null) {
                    e = new b();
                }
            }
        }
        return e;
    }
}
