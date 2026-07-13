package com.mobile.auth.f;

import android.content.Context;
import com.cmic.sso.sdk.b;
import com.mobile.auth.m.l;
import java.util.UUID;
import org.apache.commons.lang3.BooleanUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {
    private static c c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.mobile.auth.k.a f533a = com.mobile.auth.k.a.a();
    private final Context b;

    private c(Context context) {
        this.b = context.getApplicationContext();
    }

    public static c a(Context context) {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new c(context);
                }
            }
        }
        return c;
    }

    private void a(com.cmic.sso.sdk.a aVar) {
        String packageName = this.b.getPackageName();
        String strA = com.mobile.auth.m.d.a(l.a(this.b, packageName));
        aVar.a("apppackage", packageName);
        aVar.a("appsign", strA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.cmic.sso.sdk.a r21, com.mobile.auth.f.d r22, java.lang.String r23, java.lang.String r24, org.json.JSONObject r25) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.f.c.a(com.cmic.sso.sdk.a, com.mobile.auth.f.d, java.lang.String, java.lang.String, org.json.JSONObject):void");
    }

    private void b(com.cmic.sso.sdk.a aVar) {
        byte[] bytes = new byte[0];
        if (aVar.b("use2048PublicKey", false)) {
            com.mobile.auth.m.c.a("AuthnBusiness", "使用2048公钥对应的对称秘钥生成方式");
            bytes = com.mobile.auth.m.a.a();
        } else {
            com.mobile.auth.m.c.a("AuthnBusiness", "使用1024公钥对应的对称秘钥生成方式");
            try {
                bytes = UUID.randomUUID().toString().substring(0, 16).getBytes("utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        byte[] bArrA = com.mobile.auth.m.a.a();
        aVar.a(b.a.f405a, bytes);
        aVar.a(b.a.b, bArrA);
        aVar.a("authType", "3");
    }

    void a(com.cmic.sso.sdk.a aVar, d dVar) {
        com.mobile.auth.m.c.b("AuthnBusiness", "LoginCheck method start");
        int iC = aVar.c("logintype");
        if (!aVar.b("isCacheScrip", false)) {
            b(aVar, dVar);
            return;
        }
        String strB = aVar.b("securityphone", "");
        if (iC == 3) {
            dVar.a("103000", BooleanUtils.TRUE, aVar, f.a(strB));
        } else {
            b(aVar, dVar);
        }
    }

    public void b(final com.cmic.sso.sdk.a aVar, final d dVar) {
        String str;
        com.mobile.auth.m.c.b("AuthnBusiness", "getScripAndToken start");
        a(aVar);
        if (!aVar.b("isCacheScrip", false)) {
            b(aVar);
        }
        if (aVar.c("logintype") != 1) {
            str = aVar.c("logintype") == 0 ? "50" : "200";
            this.f533a.a(aVar, new com.mobile.auth.k.d() { // from class: com.mobile.auth.f.c.1
                @Override // com.mobile.auth.k.d
                public void a(String str2, String str3, JSONObject jSONObject) {
                    c.this.a(aVar, dVar, str2, str3, jSONObject);
                }
            });
        }
        aVar.a("userCapaid", str);
        this.f533a.a(aVar, new com.mobile.auth.k.d() { // from class: com.mobile.auth.f.c.1
            @Override // com.mobile.auth.k.d
            public void a(String str2, String str3, JSONObject jSONObject) {
                c.this.a(aVar, dVar, str2, str3, jSONObject);
            }
        });
    }
}
