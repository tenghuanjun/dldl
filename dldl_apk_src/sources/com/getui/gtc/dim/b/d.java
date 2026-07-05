package com.getui.gtc.dim.b;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    public final Map<String, Integer> a;

    public static class a {
        private static final d a = new d(0);
    }

    private d() {
        this.a = new HashMap();
    }

    /* synthetic */ d(byte b) {
        this();
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(com.igexin.push.core.b.aj)) {
            String[] strArrSplit = str2.trim().split("#");
            if (strArrSplit.length >= 2) {
                com.getui.gtc.dim.c.a.c.put(strArrSplit[0].trim().toLowerCase(), strArrSplit[1].trim());
                com.getui.gtc.dim.d.a.a("dim sys permission map set: " + strArrSplit[0].trim() + "#" + strArrSplit[1].trim());
            }
        }
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.toLowerCase().split(com.igexin.push.core.b.aj)) {
            String[] strArrSplit = str2.trim().split(":");
            if (strArrSplit.length >= 2) {
                com.getui.gtc.dim.c.a.a.put(strArrSplit[0].trim(), strArrSplit[1].trim());
                com.getui.gtc.dim.d.a.a("dim sys rom map set: " + strArrSplit[0].trim() + ":" + strArrSplit[1].trim());
            }
        }
    }
}
