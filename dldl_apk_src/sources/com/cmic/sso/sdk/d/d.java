package com.cmic.sso.sdk.d;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.n.f;
import com.mobile.auth.n.k;
import com.mobile.auth.n.m;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class d {
    private com.cmic.sso.sdk.a a;

    private static void a(b bVar, com.cmic.sso.sdk.a aVar) {
        if (bVar == null || aVar == null) {
            return;
        }
        bVar.b(aVar.b("appid", ""));
        bVar.f(m.a());
        bVar.i(aVar.b("interfaceType", ""));
        bVar.h(aVar.b("interfaceCode", ""));
        bVar.g(aVar.b("interfaceElasped", ""));
        bVar.l(aVar.b("timeOut"));
        bVar.s(aVar.b("traceId"));
        bVar.v(aVar.b("networkClass"));
        bVar.n(aVar.b("simCardNum"));
        bVar.o(aVar.b("operatortype"));
        bVar.p(m.b());
        bVar.q(m.c());
        bVar.y(String.valueOf(aVar.b("networktype", 0)));
        bVar.t(aVar.b("starttime"));
        bVar.w(aVar.b("endtime"));
        bVar.m(String.valueOf(aVar.b("systemEndTime", 0L) - aVar.b("systemStartTime", 0L)));
        bVar.d(aVar.b("imsiState"));
        bVar.z(k.b("AID", ""));
        bVar.A(aVar.b("operatortype"));
        bVar.B(aVar.b("scripType"));
        com.mobile.auth.n.c.a("SendLog", "traceId" + aVar.b("traceId"));
    }

    private void a(JSONObject jSONObject) {
        com.mobile.auth.l.a.a().a(jSONObject, this.a, new com.mobile.auth.l.d() { // from class: com.cmic.sso.sdk.d.d.1
            @Override // com.mobile.auth.l.d
            public void a(String str, String str2, JSONObject jSONObject2) {
                long jCurrentTimeMillis;
                com.mobile.auth.f.a aVarB = d.this.a.b();
                HashMap map = new HashMap();
                if (!str.equals("103000")) {
                    if (aVarB.l() != 0 && aVarB.k() != 0) {
                        int iA = k.a("logFailTimes", 0) + 1;
                        if (iA >= aVarB.k()) {
                            map.put("logFailTimes", 0);
                            jCurrentTimeMillis = System.currentTimeMillis();
                        } else {
                            map.put("logFailTimes", Integer.valueOf(iA));
                        }
                    }
                    k.a(map);
                }
                map.put("logFailTimes", 0);
                jCurrentTimeMillis = 0;
                map.put("logCloseTime", Long.valueOf(jCurrentTimeMillis));
                k.a(map);
            }
        });
    }

    public void a(Context context, String str, com.cmic.sso.sdk.a aVar) {
        String str2 = "";
        try {
            b bVarA = aVar.a();
            String strB = f.b(context);
            bVarA.e(str);
            bVarA.x(aVar.b("loginMethod", ""));
            bVarA.r(aVar.b("isCacheScrip", false) ? "scrip" : "pgw");
            bVarA.j(f.a(context));
            if (!TextUtils.isEmpty(strB)) {
                str2 = strB;
            }
            bVarA.k(str2);
            bVarA.c(aVar.b("hsaReadPhoneStatePermission", false) ? "1" : "0");
            a(bVarA, aVar);
            JSONArray jSONArray = null;
            if (bVarA.a.size() > 0) {
                jSONArray = new JSONArray();
                for (Throwable th : bVarA.a) {
                    StringBuffer stringBuffer = new StringBuffer();
                    JSONObject jSONObject = new JSONObject();
                    for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                        stringBuffer.append("\n");
                        stringBuffer.append(stackTraceElement.toString());
                    }
                    jSONObject.put(com.igexin.push.core.b.Z, th.toString());
                    jSONObject.put("stack", stringBuffer.toString());
                    jSONArray.put(jSONObject);
                }
                bVarA.a.clear();
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                bVarA.a(jSONArray);
            }
            com.mobile.auth.n.c.a("SendLog", "登录日志");
            a(bVarA.b(), aVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(JSONObject jSONObject, com.cmic.sso.sdk.a aVar) {
        this.a = aVar;
        a(jSONObject);
    }
}
