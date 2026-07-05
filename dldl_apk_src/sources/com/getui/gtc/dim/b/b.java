package com.getui.gtc.dim.b;

import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.db.DbManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    public final Map<String, Long> a;

    public static class a {
        private static final b a = new b(0);
    }

    private b() {
        this.a = new HashMap();
        try {
            DbManager.init(GtcProvider.context(), com.getui.gtc.dim.a.a.class, com.getui.gtc.dim.a.b.class);
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
        }
    }

    /* synthetic */ b(byte b) {
        this();
    }

    public static boolean a(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return ((com.getui.gtc.dim.a.b) DbManager.getTable(com.getui.gtc.dim.a.a.class, com.getui.gtc.dim.a.b.class)).a(str, obj);
    }
}
