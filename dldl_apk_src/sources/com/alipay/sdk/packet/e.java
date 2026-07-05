package com.alipay.sdk.packet;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alipay.sdk.app.i;
import com.alipay.sdk.net.a;
import com.alipay.sdk.util.m;
import com.alipay.sdk.util.n;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class e {
    public static final String a = "msp-gzip";
    public static final String b = "Msp-Param";
    public static final String c = "Operation-Type";
    public static final String d = "content-type";
    public static final String e = "Version";
    public static final String f = "AppId";
    public static final String g = "des-mode";
    public static final String h = "namespace";
    public static final String i = "api_name";
    public static final String j = "api_version";
    public static final String k = "data";
    public static final String l = "params";
    public static final String m = "public_key";
    public static final String n = "device";
    public static final String o = "action";
    public static final String p = "type";
    public static final String q = "method";
    protected boolean r = true;
    protected boolean s = true;

    protected abstract JSONObject a() throws JSONException;

    protected String b() {
        return "4.9.0";
    }

    protected Map<String, String> a(boolean z, String str) {
        HashMap map = new HashMap();
        map.put(a, String.valueOf(z));
        map.put(c, "alipay.msp.cashier.dispatch.bytes");
        map.put(d, OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
        map.put(e, "2.0");
        map.put(f, "TAOBAO");
        map.put(b, a.a(str));
        map.put(g, "CBC");
        return map;
    }

    protected String c() throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        map.put(n, Build.MODEL);
        map.put("namespace", "com.alipay.mobilecashier");
        map.put(i, "com.alipay.mcpay");
        map.put(j, b());
        return a(map, new HashMap<>());
    }

    protected static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put(q, str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    protected String a(String str, JSONObject jSONObject) {
        com.alipay.sdk.sys.b bVarA = com.alipay.sdk.sys.b.a();
        com.alipay.sdk.tid.b bVarA2 = com.alipay.sdk.tid.b.a(bVarA.b());
        JSONObject jSONObjectA = com.alipay.sdk.util.b.a(new JSONObject(), jSONObject);
        try {
            jSONObjectA.put("tid", bVarA2.a());
            jSONObjectA.put(com.alipay.sdk.cons.b.b, bVarA.c().a(bVarA2));
            jSONObjectA.put(com.alipay.sdk.cons.b.e, n.b(bVarA.b(), i.a));
            jSONObjectA.put(com.alipay.sdk.cons.b.f, n.a(bVarA.b()));
            jSONObjectA.put(com.alipay.sdk.cons.b.d, str);
            jSONObjectA.put(com.alipay.sdk.cons.b.h, com.alipay.sdk.cons.a.d);
            jSONObjectA.put(com.alipay.sdk.cons.b.g, bVarA.e());
            jSONObjectA.put(com.alipay.sdk.cons.b.j, bVarA2.b());
            jSONObjectA.put(com.alipay.sdk.cons.b.k, bVarA.c().a(bVarA.b()));
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
        return jSONObjectA.toString();
    }

    private static boolean a(a.b bVar) {
        return Boolean.valueOf(a(bVar, a)).booleanValue();
    }

    private static String a(a.b bVar, String str) {
        List<String> list;
        if (bVar == null || str == null || bVar.a == null || (list = bVar.a.get(str)) == null) {
            return null;
        }
        return TextUtils.join(com.igexin.push.core.b.aj, list);
    }

    protected String a(HashMap<String, String> map, HashMap<String, String> map2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put("params", jSONObject3);
        }
        jSONObject.put(k, jSONObject2);
        return jSONObject.toString();
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(k);
            if (!jSONObject.has("params")) {
                return false;
            }
            String strOptString = jSONObject.getJSONObject("params").optString(m, null);
            if (TextUtils.isEmpty(strOptString)) {
                return false;
            }
            com.alipay.sdk.sys.b.a().c().a(strOptString);
            return true;
        } catch (JSONException e2) {
            com.alipay.sdk.util.c.a(e2);
            return false;
        }
    }

    public b a(Context context) throws Throwable {
        return a(context, "");
    }

    public b a(Context context, String str) throws Throwable {
        return a(context, str, m.a(context));
    }

    public b a(Context context, String str, String str2) throws Throwable {
        return a(context, str, str2, true);
    }

    protected b a(Context context, String str, String str2, boolean z) throws Throwable {
        com.alipay.sdk.util.c.b("", "PacketTask::request url >" + str2);
        c cVar = new c(this.s);
        d dVarA = cVar.a(new b(c(), a(str, a())), this.r);
        a.b bVarA = com.alipay.sdk.net.a.a(context, new a.C0009a(str2, a(dVarA.a(), str), dVarA.b()));
        if (bVarA == null) {
            throw new RuntimeException("Response is null.");
        }
        b bVarA2 = cVar.a(new d(a(bVarA), bVarA.c));
        return (bVarA2 != null && a(bVarA2.a()) && z) ? a(context, str, str2, false) : bVarA2;
    }
}
