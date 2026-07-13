package com.mobile.auth.j;

import com.mobile.auth.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class f extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f733a;
    private a b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private JSONObject f734a;

        public JSONObject a() {
            return this.f734a;
        }

        public void a(JSONObject jSONObject) {
            this.f734a = jSONObject;
        }
    }

    public static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f735a;
        private String b;
        private String c;
        private String d;
        private String e;

        @Override // com.mobile.auth.j.g
        public String a() {
            return this.d;
        }

        @Override // com.mobile.auth.j.g
        protected String a(String str) {
            return this.e + this.d + this.c + this.b + "@Fdiwmxy7CBDDQNUI";
        }

        @Override // com.mobile.auth.j.g
        public JSONObject b() {
            return null;
        }

        public void b(String str) {
            this.e = str;
        }

        public String c() {
            return this.e;
        }

        public void c(String str) {
            this.d = str;
        }

        public String d() {
            return this.f735a;
        }

        public void d(String str) {
            this.f735a = str;
        }

        public String e() {
            return this.b;
        }

        public void e(String str) {
            this.b = str;
        }

        public String f() {
            return this.c;
        }

        public void f(String str) {
            this.c = str;
        }
    }

    @Override // com.mobile.auth.j.g
    public String a() {
        return this.f733a.d;
    }

    @Override // com.mobile.auth.j.g
    protected String a(String str) {
        return null;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(b bVar) {
        this.f733a = bVar;
    }

    @Override // com.mobile.auth.j.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject2.put("sign", this.f733a.d());
            jSONObject2.put("msgid", this.f733a.e());
            jSONObject2.put("systemtime", this.f733a.f());
            jSONObject2.put("appid", this.f733a.a());
            jSONObject2.put("version", this.f733a.c());
            jSONObject.put("header", jSONObject2);
            jSONObject3.put(BuildConfig.FLAVOR_type, this.b.a());
            jSONObject.put("body", jSONObject3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
