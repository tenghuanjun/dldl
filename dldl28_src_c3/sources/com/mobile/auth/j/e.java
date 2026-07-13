package com.mobile.auth.j;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class e extends g {
    private a a;
    private byte[] b;
    private String c;
    private byte[] d;
    private String e;
    private boolean f = false;

    @Override // com.mobile.auth.j.g
    public String a() {
        return this.a.a();
    }

    @Override // com.mobile.auth.j.g
    protected String a(String str) {
        return null;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(byte[] bArr) {
        this.b = bArr;
    }

    @Override // com.mobile.auth.j.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        if (this.f) {
            try {
                jSONObject.put("encrypted", this.c);
                jSONObject.put("encryptedIV", Base64.encodeToString(this.d, 0));
                jSONObject.put("reqdata", com.mobile.auth.m.a.a(this.b, this.a.toString(), this.d));
                jSONObject.put("securityreinforce", this.e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public void b(String str) {
        this.e = str;
    }

    public void b(byte[] bArr) {
        this.d = bArr;
    }

    public a c() {
        return this.a;
    }

    public void c(String str) {
        this.c = str;
    }
}
