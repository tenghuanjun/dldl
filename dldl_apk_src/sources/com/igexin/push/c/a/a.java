package com.igexin.push.c.a;

import com.igexin.b.a.b.d;
import com.igexin.b.a.d.a.e;
import com.igexin.push.c.c.f;
import com.igexin.push.c.c.h;
import com.igexin.push.c.c.k;
import com.igexin.push.c.c.m;
import com.igexin.push.c.c.n;
import com.igexin.push.c.c.p;
import com.igexin.push.c.c.q;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a extends d {
    private static final String a = "CommandFilter";

    public a(String str, d dVar) {
        super(str, (byte) 0);
        a(dVar);
    }

    private static boolean a(com.igexin.push.c.c.a aVar, com.igexin.push.c.c.c cVar) {
        String string;
        if (aVar.b != 26) {
            return false;
        }
        n nVar = (n) cVar;
        if (nVar.d() && nVar.f != null) {
            try {
                JSONObject jSONObject = new JSONObject((String) nVar.f);
                if (jSONObject.has("action") && (string = jSONObject.getString("action")) != null) {
                    if (string.equals(com.igexin.push.core.b.C)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                com.igexin.b.a.c.a.a("CommandFilter|" + e.toString(), new Object[0]);
            }
        }
        return false;
    }

    private static e c(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        com.igexin.push.c.c.a aVar = (com.igexin.push.c.c.a) obj;
        byte b = aVar.b;
        com.igexin.push.c.c.c hVar = b != 0 ? b != 5 ? b != 9 ? b != 20 ? b != 26 ? b != 37 ? b != 97 ? null : new h() : new m() : new n() : new q() : new p() : new k() : new f();
        if ((aVar.f != 1 && aVar.f != 7) || hVar == null) {
            return null;
        }
        hVar.a(aVar.e);
        if (aVar.f != 7) {
            if (a(aVar, hVar)) {
                return hVar;
            }
            return null;
        }
        if (aVar.g != 32 || a(aVar, hVar)) {
            return hVar;
        }
        return null;
    }

    @Override // com.igexin.b.a.b.d
    public final Object a(Object obj) throws Exception {
        if (obj instanceof com.igexin.push.c.c.c) {
            com.igexin.push.c.c.c cVar = (com.igexin.push.c.c.c) obj;
            com.igexin.push.c.c.a aVar = new com.igexin.push.c.c.a();
            aVar.b = (byte) cVar.m;
            aVar.a(cVar.b());
            aVar.c = cVar.n;
            aVar.d = cVar.o;
            return aVar;
        }
        if (!(obj instanceof com.igexin.push.c.c.c[])) {
            return null;
        }
        com.igexin.push.c.c.c[] cVarArr = (com.igexin.push.c.c.c[]) obj;
        com.igexin.push.c.c.a[] aVarArr = new com.igexin.push.c.c.a[cVarArr.length];
        for (int i = 0; i < cVarArr.length; i++) {
            aVarArr[i] = new com.igexin.push.c.c.a();
            aVarArr[i].b = (byte) cVarArr[i].m;
            aVarArr[i].a(cVarArr[i].b());
        }
        return aVarArr;
    }

    @Override // com.igexin.b.a.b.d
    public final /* synthetic */ Object b(Object obj) throws Exception {
        if (obj != null) {
            com.igexin.push.c.c.a aVar = (com.igexin.push.c.c.a) obj;
            byte b = aVar.b;
            com.igexin.push.c.c.c hVar = b != 0 ? b != 5 ? b != 9 ? b != 20 ? b != 26 ? b != 37 ? b != 97 ? null : new h() : new m() : new n() : new q() : new p() : new k() : new f();
            if ((aVar.f != 1 && aVar.f != 7) || hVar == null) {
                return null;
            }
            hVar.a(aVar.e);
            if (aVar.f == 7) {
                if (aVar.g != 32 || a(aVar, hVar)) {
                    return hVar;
                }
            } else if (a(aVar, hVar)) {
                return hVar;
            }
        }
        return null;
    }
}
