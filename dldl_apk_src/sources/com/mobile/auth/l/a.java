package com.mobile.auth.l;

import android.os.SystemClock;
import com.cmic.sso.sdk.b;
import com.mobile.auth.BuildConfig;
import com.mobile.auth.g.f;
import com.mobile.auth.k.e;
import com.mobile.auth.k.f;
import com.mobile.auth.k.h;
import com.mobile.auth.n.i;
import com.mobile.auth.n.k;
import com.mobile.auth.n.m;
import com.mobile.auth.n.o;
import com.mobile.auth.n.p;
import com.mobile.auth.n.q;
import com.tencent.open.SocialConstants;
import com.unionpay.tsmservice.mini.data.Constant;
import layaair.game.conch.LayaConch5;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    private static a a;

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private void a(final c cVar, final d dVar, final com.cmic.sso.sdk.a aVar) {
        com.mobile.auth.j.d dVar2 = new com.mobile.auth.j.d();
        com.mobile.auth.j.c cVar2 = new com.mobile.auth.j.c();
        com.mobile.auth.j.a aVar2 = new com.mobile.auth.j.a();
        dVar2.a(cVar2);
        cVar2.a(aVar2);
        cVar.a(SystemClock.elapsedRealtime());
        dVar2.a(cVar, new com.mobile.auth.m.c() { // from class: com.mobile.auth.l.a.1
            private void a() {
                if (cVar.a().contains("uniConfig")) {
                    return;
                }
                q.c(aVar, String.valueOf(SystemClock.elapsedRealtime() - cVar.i()));
            }

            @Override // com.mobile.auth.m.c
            public void a(com.mobile.auth.m.a aVar3) {
                if (cVar.g()) {
                    a();
                    q.b(aVar, String.valueOf(aVar3.a()));
                    dVar.a(String.valueOf(aVar3.a()), aVar3.b(), f.a(String.valueOf(aVar3.a()), aVar3.b()));
                }
            }

            @Override // com.mobile.auth.m.c
            public void a(com.mobile.auth.m.b bVar) {
                if (cVar.g()) {
                    try {
                        a();
                        JSONObject jSONObject = new JSONObject(bVar.c());
                        String string = jSONObject.has("resultcode") ? jSONObject.getString("resultcode") : jSONObject.getString(Constant.KEY_RESULT_CODE);
                        q.b(aVar, string);
                        dVar.a(string, jSONObject.optString(SocialConstants.PARAM_APP_DESC), jSONObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                        a(com.mobile.auth.m.a.a(102223));
                    }
                }
            }
        }, aVar);
    }

    public void a(com.cmic.sso.sdk.a aVar, d dVar) {
        String str;
        String strA;
        c bVar;
        int iC = aVar.c("networktype");
        h hVar = new h();
        hVar.b("1.0");
        hVar.c(BuildConfig.CMCC_SDK_VERSION);
        hVar.d(aVar.b("appid"));
        hVar.e(aVar.b("operatortype"));
        hVar.f(iC + "");
        hVar.g(m.a());
        hVar.h(m.b());
        hVar.i(m.c());
        hVar.j("0");
        hVar.k("2.0");
        hVar.l(q.b());
        hVar.m(o.a());
        hVar.o(aVar.b("apppackage"));
        hVar.p(aVar.b("appsign"));
        hVar.a_(k.b("AID", ""));
        if (aVar.c(LayaConch5.MARKET_LOGINTYPE) == 3) {
            str = "pre";
        } else {
            hVar.w(aVar.b("userCapaid"));
            hVar.w(aVar.c(LayaConch5.MARKET_LOGINTYPE) == 1 ? "200" : "50");
            str = "authz";
        }
        hVar.s(str);
        q.a(aVar, "scripAndTokenForHttps");
        com.mobile.auth.f.a aVarB = aVar.b();
        if (aVar.b("isCacheScrip", false)) {
            hVar.q(p.a(false));
            hVar.r(p.b(false));
            hVar.v(aVar.b("phonescrip"));
            hVar.n(hVar.u(aVar.b("appkey")));
            bVar = new c("https://" + aVarB.a() + "/unisdk/rs/scripAndTokenForHttps", hVar, "POST", aVar.b("traceId"));
            bVar.a("defendEOF", "0");
        } else {
            e eVar = new e();
            eVar.a(aVar.a(b.a.a));
            eVar.b(aVar.a(b.a.b));
            eVar.a(hVar);
            eVar.a(false);
            aVar.a("isCloseIpv4", aVarB.h());
            aVar.a("isCloseIpv6", aVarB.i());
            String str2 = "https://" + aVarB.b() + "/unisdk/rs/scripAndTokenForHttps";
            if (aVar.b("use2048PublicKey", false)) {
                com.mobile.auth.n.c.a("BaseRequest", "使用2对应的编码");
                eVar.b("2");
                strA = i.a().b(aVar.a(b.a.a));
            } else {
                strA = i.a().a(aVar.a(b.a.a));
            }
            eVar.c(strA);
            bVar = new b(str2, eVar, "POST", aVar.b("traceId"));
            bVar.a("defendEOF", "1");
            if (iC == 3) {
                bVar.a(true);
                aVar.a("doNetworkSwitch", true);
            } else {
                bVar.a(false);
                aVar.a("doNetworkSwitch", false);
            }
        }
        bVar.a("interfaceVersion", "2.0");
        a(bVar, dVar, aVar);
    }

    public void a(JSONObject jSONObject, com.cmic.sso.sdk.a aVar, d dVar) {
        com.mobile.auth.k.f fVar = new com.mobile.auth.k.f();
        f.a aVar2 = new f.a();
        f.b bVar = new f.b();
        bVar.e(q.b());
        bVar.f(o.a());
        bVar.b("2.0");
        bVar.c(aVar.b("appid", ""));
        bVar.d(bVar.u(""));
        aVar2.a(jSONObject);
        fVar.a(aVar2);
        fVar.a(bVar);
        a(new c("https://" + aVar.b().d() + "/log/logReport", fVar, "POST", aVar.b("traceId")), dVar, aVar);
    }

    public void a(boolean z, com.cmic.sso.sdk.a aVar, d dVar) {
        com.mobile.auth.k.b bVar = new com.mobile.auth.k.b();
        bVar.b("1.0");
        bVar.c("Android");
        bVar.d(k.b("AID", ""));
        bVar.e(z ? "1" : "0");
        bVar.f(BuildConfig.CMCC_SDK_VERSION);
        bVar.g(aVar.b("appid"));
        bVar.h(bVar.u("iYm0HAnkxQtpvN44"));
        a(new c("https://" + aVar.b().c() + "/client/uniConfig", bVar, "POST", aVar.b("traceId")), dVar, aVar);
    }
}
