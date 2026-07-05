package com.igexin.push.core.h;

import android.util.Base64;
import com.igexin.b.a.d.f;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.config.a.AnonymousClass7;
import com.igexin.push.config.a.AnonymousClass8;
import com.igexin.push.core.a.b.g;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c extends com.igexin.push.e.a.b {
    public static final String a = "GetIDCConfigHttpPlugin";
    public static JSONArray b;

    public c(String str, JSONArray jSONArray) {
        super(str);
        b = jSONArray;
    }

    private static void a(JSONArray jSONArray) {
        b = jSONArray;
    }

    @Override // com.igexin.push.e.a.b
    public final void a(Exception exc) {
        com.igexin.push.core.e.e.a().b(System.currentTimeMillis());
        com.igexin.b.a.c.a.a("-> get idc config " + exc.toString(), new Object[0]);
    }

    @Override // com.igexin.push.e.a.b
    public final void a(byte[] bArr) throws Exception {
        if (bArr != null) {
            try {
                String str = new String(com.igexin.b.b.a.c(Base64.decode(bArr, 0)));
                com.igexin.b.a.c.a.a("->get idc config server resp data : ".concat(String.valueOf(str)), new Object[0]);
                com.igexin.b.a.b.e.a().a((f) com.igexin.push.config.a.a().new AnonymousClass8(str), true, false);
                com.igexin.push.config.f.a(str, true);
                com.igexin.push.core.e.e.a().b(0L);
                com.igexin.b.a.b.e.a().a((f) com.igexin.push.config.a.a().new AnonymousClass7(b.toString()), true, false);
                SDKUrlConfig.setIdcConfigUrl(g.a(b));
            } catch (Exception e) {
                com.igexin.push.core.e.e.a().b(System.currentTimeMillis());
                throw e;
            }
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return 0;
    }
}
