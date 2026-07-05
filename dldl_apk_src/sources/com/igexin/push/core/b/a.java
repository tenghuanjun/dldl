package com.igexin.push.core.b;

import android.os.Build;
import android.text.TextUtils;
import com.igexin.push.core.p;
import com.igexin.sdk.PushBuildConfig;
import com.parameters.performfeatureconfig.PerformFeatureKey;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f = PushBuildConfig.sdk_conf_channelid;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public long n;
    public String o;

    public a() {
        if (com.igexin.push.core.e.d != null) {
            this.f += ":" + com.igexin.push.core.e.d;
        }
        this.e = "3.2.4.0";
        this.b = com.igexin.push.core.e.B;
        this.c = com.igexin.push.core.e.A;
        this.d = com.igexin.push.core.e.E;
        this.a = com.igexin.push.core.e.C;
        this.h = "ANDROID";
        this.j = "android" + Build.VERSION.RELEASE;
        this.k = "MDP";
        this.g = com.igexin.push.core.e.F;
        this.n = System.currentTimeMillis();
        this.l = com.igexin.push.core.e.G;
        this.m = com.igexin.push.core.e.D;
        this.o = com.igexin.push.core.e.z;
    }

    private static String a(a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str = aVar.a;
        if (str == null) {
            str = "";
        }
        jSONObject.put("model", str);
        String str2 = aVar.b;
        if (str2 == null) {
            str2 = "";
        }
        jSONObject.put("sim", str2);
        String str3 = aVar.c;
        if (str3 == null) {
            str3 = "";
        }
        jSONObject.put("imei", str3);
        String str4 = aVar.d;
        if (str4 == null) {
            str4 = "";
        }
        jSONObject.put("mac", str4);
        String str5 = aVar.e;
        if (str5 == null) {
            str5 = "";
        }
        jSONObject.put("version", str5);
        String str6 = aVar.f;
        if (str6 == null) {
            str6 = "";
        }
        jSONObject.put("channelid", str6);
        jSONObject.put("type", "ANDROID");
        String str7 = aVar.k;
        if (str7 == null) {
            str7 = "";
        }
        jSONObject.put(Constants.JumpUrlConstants.SRC_TYPE_APP, str7);
        StringBuilder sb = new StringBuilder("ANDROID-");
        String str8 = aVar.g;
        if (str8 == null) {
            str8 = "";
        }
        sb.append(str8);
        jSONObject.put("deviceid", sb.toString());
        String str9 = aVar.l;
        if (str9 == null) {
            str9 = "";
        }
        jSONObject.put("device_token", str9);
        String str10 = aVar.m;
        if (str10 == null) {
            str10 = "";
        }
        jSONObject.put("brand", str10);
        String str11 = aVar.j;
        if (str11 == null) {
            str11 = "";
        }
        jSONObject.put("system_version", str11);
        String str12 = aVar.i;
        if (str12 == null) {
            str12 = "";
        }
        jSONObject.put("cell", str12);
        jSONObject.put("aid", com.igexin.push.f.n.g());
        jSONObject.put("adid", com.igexin.push.f.n.h());
        jSONObject.put("gtcid", TextUtils.isEmpty(aVar.o) ? "" : aVar.o);
        jSONObject.put(PerformFeatureKey.KEY_OAID, com.igexin.push.core.e.e == null ? "" : com.igexin.push.core.e.e);
        String name = p.a.a.a(com.igexin.push.core.e.i).getName();
        if (!com.igexin.push.core.b.ak.equals(name)) {
            jSONObject.put(com.igexin.push.f.o.a, name);
        }
        com.igexin.push.core.p unused = p.a.a;
        jSONObject.put(com.igexin.push.f.o.c, com.igexin.push.core.p.c(com.igexin.push.core.e.i));
        jSONObject.put("notification_enabled", com.igexin.push.f.c.b(com.igexin.push.core.e.i) ? 1 : 0);
        jSONObject.put("installChannel", com.igexin.b.b.a.b(com.igexin.push.core.e.b, "").replaceAll("\\|", ""));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("action", "addphoneinfo");
        jSONObject2.put("id", String.valueOf(aVar.n));
        jSONObject2.put(DBDefinition.SEGMENT_INFO, jSONObject);
        return jSONObject2.toString();
    }
}
