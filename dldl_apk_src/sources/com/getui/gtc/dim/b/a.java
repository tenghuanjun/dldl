package com.getui.gtc.dim.b;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public final Map<String, Long> a;
    public final Map<String, e> b;

    /* JADX INFO: renamed from: com.getui.gtc.dim.b.a$a, reason: collision with other inner class name */
    public static class C0038a {
        private static final a a = new a(0);
    }

    private a() {
        this.a = new HashMap();
        this.b = new ConcurrentHashMap();
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public final void a(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e eVar = new e(obj, System.currentTimeMillis());
        com.getui.gtc.dim.d.a.a(str + " update dim ram cache = " + obj);
        this.b.put(str, eVar);
    }
}
