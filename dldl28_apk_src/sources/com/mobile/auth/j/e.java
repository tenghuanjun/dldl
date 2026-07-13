package com.mobile.auth.j;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class e extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f732a;
    private byte[] b;
    private String c;
    private byte[] d;
    private String e;
    private boolean f = false;

    @Override // com.mobile.auth.j.g
    public String a() {
        return this.f732a.a();
    }

    @Override // com.mobile.auth.j.g
    protected String a(String str) {
        return null;
    }

    public void a(a aVar) {
        this.f732a = aVar;
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
                jSONObject.put("reqdata", com.mobile.auth.m.a.a(this.b, this.f732a.toString(), this.d));
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
        return this.f732a;
    }

    public void c(String str) {
        this.c = str;
    }
}
