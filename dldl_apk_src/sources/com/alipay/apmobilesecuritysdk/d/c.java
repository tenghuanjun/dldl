package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.f;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class c {
    public static Map<String, String> a(Context context) {
        com.alipay.security.mobile.module.deviceinfo.b bVarA = com.alipay.security.mobile.module.deviceinfo.b.a();
        HashMap map = new HashMap();
        f fVarA = com.alipay.apmobilesecuritysdk.e.e.a(context);
        String strA = bVarA.a(context);
        String strB = bVarA.b(context);
        String strP = bVarA.p(context);
        String strS = bVarA.s(context);
        String strR = bVarA.r(context);
        if (fVarA != null) {
            if (com.alipay.security.mobile.module.a.a.a(strA)) {
                strA = fVarA.a();
            }
            if (com.alipay.security.mobile.module.a.a.a(strB)) {
                strB = fVarA.b();
            }
            if (com.alipay.security.mobile.module.a.a.a(strP)) {
                strP = fVarA.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(strS)) {
                strS = fVarA.d();
            }
            if (com.alipay.security.mobile.module.a.a.a(strR)) {
                strR = fVarA.e();
            }
        }
        f fVar = new f(strA, strB, strP, strS, strR);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", fVar.a());
                jSONObject.put("imsi", fVar.b());
                jSONObject.put("mac", fVar.c());
                jSONObject.put("bluetoothmac", fVar.d());
                jSONObject.put("gsi", fVar.e());
                String string = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key", string);
                com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", string);
            } catch (Exception e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
        map.put("AD1", strA);
        map.put("AD2", strB);
        map.put("AD3", bVarA.j(context));
        map.put("AD5", bVarA.l(context));
        map.put("AD6", bVarA.n(context));
        map.put("AD7", bVarA.o(context));
        map.put("AD8", strP);
        map.put("AD9", bVarA.q(context));
        map.put("AD10", strR);
        map.put("AD11", bVarA.d());
        map.put("AD12", bVarA.f());
        map.put("AD13", bVarA.g());
        map.put("AD14", bVarA.i());
        map.put("AD15", bVarA.j());
        map.put("AD16", bVarA.k());
        map.put("AD17", "");
        map.put("AD18", strS);
        map.put("AD19", bVarA.t(context));
        map.put("AD20", bVarA.n());
        map.put("AD22", "");
        map.put("AD23", bVarA.p());
        map.put("AD24", com.alipay.security.mobile.module.a.a.g(bVarA.k(context)));
        map.put("AD26", bVarA.g(context));
        map.put("AD27", bVarA.w());
        map.put("AD28", bVarA.y());
        map.put("AD29", bVarA.A());
        map.put("AD30", bVarA.x());
        map.put("AD31", bVarA.z());
        map.put("AD32", bVarA.u());
        map.put("AD33", bVarA.v());
        map.put("AD34", bVarA.x(context));
        map.put("AD35", bVarA.y(context));
        map.put("AD36", bVarA.v(context));
        map.put("AD37", bVarA.s());
        map.put("AD38", bVarA.q());
        map.put("AD39", bVarA.c(context));
        map.put("AD40", bVarA.d(context));
        map.put("AD41", bVarA.b());
        map.put("AD42", bVarA.c());
        map.put("AL3", bVarA.u(context));
        return map;
    }
}
