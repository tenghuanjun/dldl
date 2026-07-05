package com.alipay.deviceid.module.x;

import android.content.Context;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class bu {
    public static bv a(Context context) {
        if (context == null) {
            return null;
        }
        String strA = cb.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (e.a(strA)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(strA);
            bv bvVar = new bv();
            bvVar.a = jSONObject.getString("imei");
            bvVar.b = jSONObject.getString("imsi");
            bvVar.c = jSONObject.getString("mac");
            bvVar.d = jSONObject.getString("bluetoothmac");
            bvVar.e = jSONObject.getString("gsi");
            return bvVar;
        } catch (Exception e) {
            v.a(e);
            return null;
        }
    }

    public static void a(Context context, bv bvVar) {
        if (context == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("imei", e.c(bvVar.a));
            jSONObject.put("imsi", e.c(bvVar.b));
            jSONObject.put("mac", e.c(bvVar.c));
            jSONObject.put("bluetoothmac", e.c(bvVar.d));
            jSONObject.put("gsi", e.c(bvVar.e));
            cb.a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject.toString());
        } catch (Exception e) {
            v.a(e);
        }
    }
}
