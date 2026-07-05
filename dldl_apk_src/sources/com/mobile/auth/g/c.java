package com.mobile.auth.g;

import android.content.Context;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.cmic.sso.sdk.b;
import com.mobile.auth.n.l;
import java.util.UUID;
import layaair.game.conch.LayaConch5;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c {
    private static c c;
    private final com.mobile.auth.l.a a = com.mobile.auth.l.a.a();
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
        String strA = com.mobile.auth.n.d.a(l.a(this.b, packageName));
        aVar.a("apppackage", packageName);
        aVar.a("appsign", strA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.cmic.sso.sdk.a r21, com.mobile.auth.g.d r22, java.lang.String r23, java.lang.String r24, org.json.JSONObject r25) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.g.c.a(com.cmic.sso.sdk.a, com.mobile.auth.g.d, java.lang.String, java.lang.String, org.json.JSONObject):void");
    }

    private void b(com.cmic.sso.sdk.a aVar) {
        byte[] bytes = new byte[0];
        if (aVar.b("use2048PublicKey", false)) {
            com.mobile.auth.n.c.a("AuthnBusiness", "使用2048公钥对应的对称秘钥生成方式");
            bytes = com.mobile.auth.n.a.a();
        } else {
            com.mobile.auth.n.c.a("AuthnBusiness", "使用1024公钥对应的对称秘钥生成方式");
            try {
                bytes = UUID.randomUUID().toString().substring(0, 16).getBytes("utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        byte[] bArrA = com.mobile.auth.n.a.a();
        aVar.a(b.a.a, bytes);
        aVar.a(b.a.b, bArrA);
        aVar.a("authType", "3");
    }

    void a(com.cmic.sso.sdk.a aVar, d dVar) {
        com.mobile.auth.n.c.b("AuthnBusiness", "LoginCheck method start");
        int iC = aVar.c(LayaConch5.MARKET_LOGINTYPE);
        if (!aVar.b("isCacheScrip", false)) {
            b(aVar, dVar);
            return;
        }
        String strB = aVar.b("securityphone", "");
        if (iC == 3) {
            dVar.a("103000", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, aVar, f.a(strB));
        } else {
            b(aVar, dVar);
        }
    }

    public void b(final com.cmic.sso.sdk.a aVar, final d dVar) {
        String str;
        com.mobile.auth.n.c.b("AuthnBusiness", "getScripAndToken start");
        a(aVar);
        if (!aVar.b("isCacheScrip", false)) {
            b(aVar);
        }
        if (aVar.c(LayaConch5.MARKET_LOGINTYPE) != 1) {
            str = aVar.c(LayaConch5.MARKET_LOGINTYPE) == 0 ? "50" : "200";
            this.a.a(aVar, new com.mobile.auth.l.d() { // from class: com.mobile.auth.g.c.1
                @Override // com.mobile.auth.l.d
                public void a(String str2, String str3, JSONObject jSONObject) {
                    c.this.a(aVar, dVar, str2, str3, jSONObject);
                }
            });
        }
        aVar.a("userCapaid", str);
        this.a.a(aVar, new com.mobile.auth.l.d() { // from class: com.mobile.auth.g.c.1
            @Override // com.mobile.auth.l.d
            public void a(String str2, String str3, JSONObject jSONObject) {
                c.this.a(aVar, dVar, str2, str3, jSONObject);
            }
        });
    }
}
