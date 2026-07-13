package com.mobile.auth.j;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f729a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    @Override // com.mobile.auth.j.g
    public String a() {
        return this.f;
    }

    @Override // com.mobile.auth.j.g
    protected String a(String str) {
        return this.f729a + this.e + this.f + "iYm0HAnkxQtpvN44";
    }

    @Override // com.mobile.auth.j.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.f729a);
            jSONObject.put("apptype", this.b);
            jSONObject.put("phone_ID", this.c);
            jSONObject.put("certflag", this.d);
            jSONObject.put("sdkversion", this.e);
            jSONObject.put("appid", this.f);
            jSONObject.put("expandparams", "");
            jSONObject.put("sign", this.g);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void b(String str) {
        this.f729a = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.c = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void f(String str) {
        this.e = str;
    }

    public void g(String str) {
        this.f = str;
    }

    public void h(String str) {
        this.g = str;
    }
}
