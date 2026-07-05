package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class a {
    public static final int a = 3500;
    public static final String b = "https://h5.m.taobao.com/mlapp/olist.html";
    public static final int c = 10;
    public static final boolean d = true;
    public static final boolean e = true;
    public static final int f = 1000;
    public static final int g = 20000;
    public static final String h = "alipay_cashier_dynamic_config";
    public static final String i = "timeout";
    public static final String j = "st_sdk_config";
    public static final String k = "tbreturl";
    public static final String l = "launchAppSwitch";
    public static final String m = "configQueryInterval";
    public static final String n = "scheme_pay";
    public static final String o = "scheme_pay_2";
    public static final String p = "intercept_batch";
    private static a x;
    private int r = a;
    private String s = b;
    private int t = 10;
    private boolean u = true;
    private boolean v = true;
    public boolean q = false;
    private List<C0008a> w = null;

    public int a() {
        int i2 = this.r;
        if (i2 < 1000 || i2 > 20000) {
            com.alipay.sdk.util.c.b("", "DynamicConfig::getJumpTimeout(default) >3500");
            return a;
        }
        com.alipay.sdk.util.c.b("", "DynamicConfig::getJumpTimeout >" + this.r);
        return this.r;
    }

    public boolean b() {
        return this.u;
    }

    public boolean c() {
        return this.v;
    }

    public String d() {
        return this.s;
    }

    public int e() {
        return this.t;
    }

    public List<C0008a> f() {
        return this.w;
    }

    public void a(boolean z) {
        this.q = z;
    }

    public static a g() {
        if (x == null) {
            a aVar = new a();
            x = aVar;
            aVar.h();
        }
        return x;
    }

    private void h() {
        a(j.b(com.alipay.sdk.sys.b.a().b(), h, null));
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.r = jSONObject.optInt("timeout", a);
            this.s = jSONObject.optString(k, b).trim();
            this.t = jSONObject.optInt(m, 10);
            this.w = C0008a.a(jSONObject.optJSONArray(l));
            this.u = jSONObject.optBoolean(o, true);
            this.v = jSONObject.optBoolean(p, true);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timeout", a());
            jSONObject.put(k, d());
            jSONObject.put(m, e());
            jSONObject.put(l, C0008a.a(f()));
            jSONObject.put(o, b());
            jSONObject.put(p, c());
            j.a(com.alipay.sdk.sys.b.a().b(), h, jSONObject.toString());
        } catch (Exception e2) {
            com.alipay.sdk.util.c.a(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject(j);
            if (jSONObjectOptJSONObject != null) {
                this.r = jSONObjectOptJSONObject.optInt("timeout", a);
                this.s = jSONObjectOptJSONObject.optString(k, b).trim();
                this.t = jSONObjectOptJSONObject.optInt(m, 10);
                this.w = C0008a.a(jSONObjectOptJSONObject.optJSONArray(l));
                this.u = jSONObjectOptJSONObject.optBoolean(o, true);
                this.v = jSONObjectOptJSONObject.optBoolean(p, true);
            } else {
                com.alipay.sdk.util.c.d("msp", "config is null");
            }
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
    }

    public void a(Context context) {
        new Thread(new b(this, context)).start();
    }

    /* JADX INFO: renamed from: com.alipay.sdk.data.a$a, reason: collision with other inner class name */
    public static final class C0008a {
        public final String a;
        public final int b;
        public final String c;

        public C0008a(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }

        public static C0008a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C0008a(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        public static List<C0008a> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C0008a c0008aA = a(jSONArray.optJSONObject(i));
                if (c0008aA != null) {
                    arrayList.add(c0008aA);
                }
            }
            return arrayList;
        }

        public static JSONObject a(C0008a c0008a) {
            if (c0008a == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", c0008a.a).put("v", c0008a.b).put("pk", c0008a.c);
            } catch (JSONException e) {
                com.alipay.sdk.util.c.a(e);
                return null;
            }
        }

        public static JSONArray a(List<C0008a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<C0008a> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(a(this));
        }
    }
}
