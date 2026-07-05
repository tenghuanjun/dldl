package com.igexin.push.e;

import android.text.TextUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.f.k;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class f implements com.igexin.push.e.b.c {
    private static final String a = "com.igexin.push.e.f";
    private static final long c = 1800000;
    private long b = 0;
    private long d = 0;

    /* JADX INFO: renamed from: com.igexin.push.e.f$1, reason: invalid class name */
    final class AnonymousClass1 extends d {
        AnonymousClass1() {
        }

        @Override // com.igexin.push.e.d
        protected final void b() {
            try {
                String strH = k.h();
                if (TextUtils.isEmpty(strH)) {
                    return;
                }
                com.igexin.b.a.c.a.a("UploadBITask|upload type10 data = ".concat(String.valueOf(strH)), new Object[0]);
                com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.e.a.c(new com.igexin.push.core.h.e(SDKUrlConfig.getBiUploadServiceUrl(), strH.getBytes())), false, true);
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("UploadBITask|" + th.toString(), new Object[0]);
            }
        }
    }

    private void c() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(), false, true);
    }

    @Override // com.igexin.push.e.b.c
    public final void a() {
        if (!com.igexin.push.config.d.u || System.currentTimeMillis() - this.d < 3600000) {
            return;
        }
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(), false, true);
        this.d = System.currentTimeMillis();
    }

    @Override // com.igexin.push.e.b.c
    public final void a(long j) {
        this.b = j;
    }

    @Override // com.igexin.push.e.b.c
    public final boolean b() {
        return System.currentTimeMillis() - this.b > 1800000;
    }
}
