package com.igexin.push.core.a.a;

import android.text.TextUtils;
import com.igexin.push.c.c.i;
import com.igexin.push.c.c.p;
import com.igexin.push.core.d;
import com.igexin.push.core.l;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f extends com.igexin.push.core.a.a {
    private static final String b = "RegisterResult";

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            com.igexin.push.core.e.L = 0L;
            com.igexin.b.a.c.a.a("register resp |" + pVar.b + "|" + com.igexin.push.core.e.w, new Object[0]);
            com.igexin.b.a.c.a.a("register resp cid = " + pVar.d + " device id = " + pVar.e, new Object[0]);
            if (pVar.b != com.igexin.push.core.e.w) {
                com.igexin.push.core.e.s = false;
                long j = com.igexin.push.core.e.w;
                com.igexin.b.a.c.a.a("RegisterResult change session : from [" + com.igexin.push.core.e.w + "] to [" + pVar.b + "]", new Object[0]);
                String str = com.igexin.push.core.e.x;
                com.igexin.b.a.c.a.a("RegisterResult change cid : from [" + com.igexin.push.core.e.x + "] to [" + pVar.d + "]", new Object[0]);
                if (TextUtils.isEmpty(pVar.d) || TextUtils.isEmpty(pVar.e)) {
                    com.igexin.push.core.e.e.a().a(pVar.b);
                } else {
                    com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
                    String str2 = pVar.d;
                    String str3 = pVar.e;
                    com.igexin.push.core.e.w = pVar.b;
                    if (TextUtils.isEmpty(com.igexin.push.core.e.F)) {
                        com.igexin.push.core.e.F = str3;
                    }
                    com.igexin.push.core.e.x = str2;
                    eVarA.d();
                }
                com.igexin.push.core.e.N = 0L;
            }
            long j2 = com.igexin.push.core.e.w;
            String str4 = com.igexin.push.core.e.x;
            String str5 = com.igexin.push.core.e.F;
            com.igexin.b.a.c.a.a("loginReqAfterRegister|new session:" + com.igexin.push.core.e.w + ", cid :" + com.igexin.push.core.e.x + ", devId :" + com.igexin.push.core.e.F, new Object[0]);
            l.a();
            i iVarC = l.c();
            com.igexin.push.d.a aVar = d.a.a.i;
            StringBuilder sb = new StringBuilder("S-");
            sb.append(iVarC.b);
            aVar.a(sb.toString(), iVarC, true);
        }
        return true;
    }

    @Override // com.igexin.push.core.a.a
    public final void b() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean c() {
        return false;
    }
}
