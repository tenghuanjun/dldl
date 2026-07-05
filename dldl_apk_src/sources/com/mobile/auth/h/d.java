package com.mobile.auth.h;

import com.mobile.auth.BuildConfig;
import com.mobile.auth.k.h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d extends f {
    private b a;
    private a b;

    public static class a {
        private JSONArray a;

        public JSONArray a() {
            return this.a;
        }

        public void a(JSONArray jSONArray) {
            this.a = jSONArray;
        }
    }

    public static class b {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        public String a() {
            return this.e;
        }

        public void a(String str) {
            this.e = str;
        }

        public String b() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.a;
        }

        public void c(String str) {
            this.a = str;
        }

        public String d() {
            return this.b;
        }

        public void d(String str) {
            this.b = str;
        }

        public String e() {
            return this.c;
        }

        public void e(String str) {
            this.c = str;
        }

        public String f() {
            return h.a(this.e + this.d + this.c + this.b + "@Fdiwmxy7CBDDQNUI");
        }
    }

    @Override // com.mobile.auth.h.f
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject2.put("sign", this.a.c());
            jSONObject2.put("msgid", this.a.d());
            jSONObject2.put("systemtime", this.a.e());
            jSONObject2.put("appid", this.a.b());
            jSONObject2.put("version", this.a.a());
            jSONObject.put("header", jSONObject2);
            jSONObject3.put(BuildConfig.FLAVOR_type, this.b.a());
            jSONObject.put("body", jSONObject3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(b bVar) {
        this.a = bVar;
    }
}
